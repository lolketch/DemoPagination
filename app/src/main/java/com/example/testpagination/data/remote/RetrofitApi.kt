package com.example.testpagination.data.remote

import com.example.testpagination.data.remote.model.Results
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("character/")
    fun getCharacters(
        @Query("page") page: Int,
    ): Single<Results>
}