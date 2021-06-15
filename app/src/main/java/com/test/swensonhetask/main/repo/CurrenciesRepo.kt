package com.test.swensonhetask.main.repo

import androidx.lifecycle.LiveData
import com.test.swensonhetask.appmanger.network.NetworkState
import com.test.swensonhetask.appmanger.network.netWorkError
import com.test.swensonhetask.main.datasource.ICurrenciesDataSource
import com.test.swensonhetask.main.models.CurrenciesResponse
import com.test.swensonhetask.utils.SingleLiveEvent
import io.reactivex.Flowable

class CurrenciesRepo(private val currenciesDataSource: ICurrenciesDataSource) :
    ICurrenciesDataSource {

    private val networkState: SingleLiveEvent<NetworkState> = SingleLiveEvent()
    private val errorState: SingleLiveEvent<String> = SingleLiveEvent()


    fun getCurrenciesNetworkState(): LiveData<NetworkState> {
        return networkState
    }

    fun getError(): LiveData<String> {
        return errorState
    }

    override fun getCurrencies(key: String): Flowable<CurrenciesResponse> {
        networkState.postValue(NetworkState.LOADING)
        return currenciesDataSource.getCurrencies(key)
            .doOnNext {
                networkState.postValue(NetworkState.LOADED)
            }.doOnError {
                errorState.postValue(netWorkError(it))
                networkState.postValue(NetworkState.ERROR)
            }
    }

}