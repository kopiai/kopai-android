package com.kopai.shinkansen.data.local

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.kopai.shinkansen.data.local.entity.RemoteKeysEntity
import com.kopai.shinkansen.data.local.room.ProductsDatabase
import com.kopai.shinkansen.data.remote.response.ProductItem
import com.kopai.shinkansen.data.remote.retrofit.ApiService

@OptIn(ExperimentalPagingApi::class)
class ProductsRemoteMediator(
    private val database: ProductsDatabase,
    private val apiService: ApiService,
) : RemoteMediator<Int, ProductItem>() {
    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProductItem>,
    ): MediatorResult {
        val page =
            when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevKey =
                        remoteKeys?.prevKey
                            ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevKey
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey =
                        remoteKeys?.nextKey
                            ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextKey
                }
            }

        try {
            val responseData = apiService.getProducts(page, state.config.pageSize)

            val endOfPaginationReached = responseData.data.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeysDao().deleteRemoteKeys()
                    database.productDao().deleteAll()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys =
                    responseData.data.map {
                        RemoteKeysEntity(id = it.productId.toString(), prevKey = prevKey, nextKey = nextKey)
                    }
                database.remoteKeysDao().insertAll(keys)
                database.productDao().insertProduct(responseData.data)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, ProductItem>): RemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { data ->
            database.remoteKeysDao().getRemoteKeysId(data.productId.toString())
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, ProductItem>): RemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { data ->
            database.remoteKeysDao().getRemoteKeysId(data.productId.toString())
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, ProductItem>): RemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.productId?.let { id ->
                database.remoteKeysDao().getRemoteKeysId(id.toString())
            }
        }
    }
}