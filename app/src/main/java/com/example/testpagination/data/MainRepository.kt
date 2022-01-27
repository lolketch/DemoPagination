package com.example.testpagination.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.example.testpagination.data.remote.model.MyCharacter
import com.example.testpagination.data.remote.MyPagingSource
import io.reactivex.Flowable
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val myPagingSource: MyPagingSource
) {
    fun getCharacters(): Flowable<PagingData<MyCharacter>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 5
            ),
            pagingSourceFactory = { myPagingSource }
        ).flowable
    }
}