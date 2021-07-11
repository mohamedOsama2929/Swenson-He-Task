package com.test.swensonhetask.main.datasource

import com.test.swensonhetask.appmanger.network.CurrencyApiService
import com.test.swensonhetask.main.models.CurrenciesResponse
import io.reactivex.Flowable
import javax.inject.Inject


class CurrenciesRemoteDataSource @Inject constructor(val currencyApiService: CurrencyApiService) :
    ICurrenciesDataSource {


    override fun getCurrencies(key: String): Flowable<CurrenciesResponse> {
        return currencyApiService.getCurrencies(key)
    }
}