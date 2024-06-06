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
import com.kopai.shinkansen.data.remote.response.StoryItem
import com.kopai.shinkansen.data.repository.StoriesRepository
import com.kopai.shinkansen.data.repository.UserRepository
import com.kopai.shinkansen.getOrAwaitValue
import com.kopai.shinkansen.view.adapter.StoryAdapter
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
    private lateinit var storiesRepository: StoriesRepository

    @Test
    fun `when Get Stories Should Not Null and Return Data`() =
        runTest {
            val dummyStories = DataDummy.generateDummyStories()
            val data: PagingData<StoryItem> = StoryPagingSource.snapshot(dummyStories)
            val expectedQuote = MutableLiveData<PagingData<StoryItem>>()
            expectedQuote.value = data
            Mockito.`when`(storiesRepository.getStoriesPaging()).thenReturn(expectedQuote)

            val mainViewModel = MainViewModel(userRepository, storiesRepository)
            val actualQuote: PagingData<StoryItem> = mainViewModel.stories.getOrAwaitValue()

            val differ =
                AsyncPagingDataDiffer(
                    diffCallback = StoryAdapter.DIFF_CALLBACK,
                    updateCallback = noopListUpdateCallback,
                    workerDispatcher = Dispatchers.Main,
                )
            differ.submitData(actualQuote)

            Assert.assertNotNull(differ.snapshot())
            Assert.assertEquals(dummyStories.size, differ.snapshot().size)
            Assert.assertEquals(dummyStories[0], differ.snapshot()[0])
        }

    @Test
    fun `when Get Stories Empty Should Return No Data`() =
        runTest {
            val data: PagingData<StoryItem> = PagingData.from(emptyList())
            val expectedQuote = MutableLiveData<PagingData<StoryItem>>()
            expectedQuote.value = data
            Mockito.`when`(storiesRepository.getStoriesPaging()).thenReturn(expectedQuote)

            val mainViewModel = MainViewModel(userRepository, storiesRepository)
            val actualQuote: PagingData<StoryItem> = mainViewModel.stories.getOrAwaitValue()

            val differ =
                AsyncPagingDataDiffer(
                    diffCallback = StoryAdapter.DIFF_CALLBACK,
                    updateCallback = noopListUpdateCallback,
                    workerDispatcher = Dispatchers.Main,
                )
            differ.submitData(actualQuote)

            Assert.assertEquals(0, differ.snapshot().size)
        }
}

class StoryPagingSource : PagingSource<Int, LiveData<List<StoryItem>>>() {
    companion object {
        fun snapshot(items: List<StoryItem>): PagingData<StoryItem> {
            return PagingData.from(items)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LiveData<List<StoryItem>>>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LiveData<List<StoryItem>>> {
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
