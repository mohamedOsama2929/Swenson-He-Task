package com.test.swensonhetask.main.viewmodel

import androidx.lifecycle.LiveData
import com.test.swensonhetask.appmanger.network.NetworkState
import com.test.swensonhetask.main.datasource.CurrenciesRemoteDataSource
import com.test.swensonhetask.main.models.CurrenciesResponse
import com.test.swensonhetask.main.repo.CurrenciesRepo
import com.test.swensonhetask.utils.BaseViewModel
import com.test.swensonhetask.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CurrenciesVM : BaseViewModel() {

    private val currenciesLiveData = SingleLiveEvent<CurrenciesResponse>()
    private val mainRepo = CurrenciesRepo(CurrenciesRemoteDataSource)

    fun getCurrencies(key: String) {
        addToDisposable(mainRepo.getCurrencies(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                currenciesLiveData.value = it
            }
        )
    }

    fun currenciesLiveData(): SingleLiveEvent<CurrenciesResponse> =
        currenciesLiveData


    fun getNetworkState(): LiveData<NetworkState> =
        mainRepo.getCurrenciesNetworkState()

    fun getError(): LiveData<String> = mainRepo.getError()
}