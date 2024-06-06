package com.kopai.shinkansen.data.local

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kopai.shinkansen.data.remote.response.StoryItem
import com.kopai.shinkansen.data.remote.retrofit.ApiService

class StoriesPagingSource(private val apiService: ApiService) : PagingSource<Int, StoryItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StoryItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getStories(position, params.loadSize)
            responseData.listStory.let {
                LoadResult.Page(
                    data = it,
                    prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                    nextKey = if (it.isEmpty()) null else position + 1,
                )
            }
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, StoryItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}
