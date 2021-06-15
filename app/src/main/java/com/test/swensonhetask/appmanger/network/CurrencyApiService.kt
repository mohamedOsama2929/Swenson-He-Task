package com.test.swensonhetask.appmanger.network

import com.test.swensonhetask.main.models.CurrenciesResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {

    // get currencies
    @GET("latest")
    fun getCurrencies(
        @Query("access_key") accessKey: String,
    ): Flowable<CurrenciesResponse>
/*

    @GET("convert")
    fun convert(
        @Query("access_key") accessKey: Int,
        @Query("from") accessKey: String,
        @Query("to") accessKey: String,
    ): Flowable<CurrenciesResponse>
*/

}