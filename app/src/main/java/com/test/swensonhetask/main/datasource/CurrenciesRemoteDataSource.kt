package com.test.swensonhetask.main.datasource

import com.test.swensonhetask.appmanger.network.CurrencyDriverApiClient
import com.test.swensonhetask.main.models.CurrenciesResponse
import io.reactivex.Flowable


object CurrenciesRemoteDataSource : ICurrenciesDataSource {

    override fun getCurrencies(key: String): Flowable<CurrenciesResponse> {
        return CurrencyDriverApiClient.getCurrencyApisService().getCurrencies(key)
    }
}