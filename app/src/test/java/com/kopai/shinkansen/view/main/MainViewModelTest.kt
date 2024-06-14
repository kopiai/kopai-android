package com.kopai.shinkansen.view.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.recyclerview.widget.ListUpdateCallback
import com.kopai.shinkansen.DataDummy
import com.kopai.shinkansen.MainDispatcherRule
import com.kopai.shinkansen.data.remote.response.ProductItem
import com.kopai.shinkansen.data.repository.ProductsRepository
import com.kopai.shinkansen.data.repository.UserRepository
import com.kopai.shinkansen.getOrAwaitValue
import com.kopai.shinkansen.view.adapter.ProductPagingAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var productsRepository: ProductsRepository

    @Test
    fun `when Get Products Should Not Null and Return Data`() =
        runTest {
            val dummyProducts = DataDummy.generateDummyProducts()
            val data: PagingData<ProductItem> = ProductPagingSource.snapshot(dummyProducts)
            val expectedQuote = MutableLiveData<PagingData<ProductItem>>()
            expectedQuote.value = data
            Mockito.`when`(productsRepository.getProductsPaging()).thenReturn(expectedQuote)

            val mainViewModel = MainViewModel(userRepository, productsRepository)
            val actualQuote: PagingData<ProductItem> = mainViewModel.productsPaging.getOrAwaitValue()

            val differ =
                AsyncPagingDataDiffer(
                    diffCallback = ProductPagingAdapter.DIFF_CALLBACK,
                    updateCallback = noopListUpdateCallback,
                    workerDispatcher = Dispatchers.Main,
                )
            differ.submitData(actualQuote)

            Assert.assertNotNull(differ.snapshot())
            Assert.assertEquals(dummyProducts.size, differ.snapshot().size)
            Assert.assertEquals(dummyProducts[0], differ.snapshot()[0])
        }

    @Test
    fun `when Get Products Empty Should Return No Data`() =
        runTest {
            val data: PagingData<ProductItem> = PagingData.from(emptyList())
            val expectedQuote = MutableLiveData<PagingData<ProductItem>>()
            expectedQuote.value = data
            Mockito.`when`(productsRepository.getProductsPaging()).thenReturn(expectedQuote)

            val mainViewModel = MainViewModel(userRepository, productsRepository)
            val actualQuote: PagingData<ProductItem> = mainViewModel.productsPaging.getOrAwaitValue()

            val differ =
                AsyncPagingDataDiffer(
                    diffCallback = ProductPagingAdapter.DIFF_CALLBACK,
                    updateCallback = noopListUpdateCallback,
                    workerDispatcher = Dispatchers.Main,
                )
            differ.submitData(actualQuote)

            Assert.assertEquals(0, differ.snapshot().size)
        }
}

class ProductPagingSource : PagingSource<Int, LiveData<List<ProductItem>>>() {
    companion object {
        fun snapshot(items: List<ProductItem>): PagingData<ProductItem> {
            return PagingData.from(items)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LiveData<List<ProductItem>>>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LiveData<List<ProductItem>>> {
        return LoadResult.Page(emptyList(), 0, 1)
    }
}

val noopListUpdateCallback =
    object : ListUpdateCallback {
        override fun onInserted(
            position: Int,
            count: Int,
        ) {
        }

        override fun onRemoved(
            position: Int,
            count: Int,
        ) {
        }

        override fun onMoved(
            fromPosition: Int,
            toPosition: Int,
        ) {
        }

        override fun onChanged(
            position: Int,
            count: Int,
            payload: Any?,
        ) {
        }
    }
