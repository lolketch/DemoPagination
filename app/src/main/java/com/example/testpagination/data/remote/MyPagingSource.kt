package com.example.testpagination.data.remote

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.testpagination.data.remote.model.MyCharacter
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MyPagingSource @Inject constructor(private val retrofitApi: RetrofitApi) :
    RxPagingSource<Int, MyCharacter>() {
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, MyCharacter>> {
        val page: Int = params.key ?: 1

        return retrofitApi.getCharacters(page)
            .subscribeOn(Schedulers.io())
            .map {
                LoadResult.Page(
                    data = it.results,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (page == it.info.pages) null else page + 1
                ) as LoadResult<Int, MyCharacter>
            }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }

    override fun getRefreshKey(state: PagingState<Int, MyCharacter>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }
}