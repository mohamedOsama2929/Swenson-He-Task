package com.test.swensonhetask.main.datasource

import com.test.swensonhetask.main.models.CurrenciesResponse
import io.reactivex.Flowable


interface ICurrenciesDataSource {

    fun getCurrencies(key: String): Flowable<CurrenciesResponse>

}