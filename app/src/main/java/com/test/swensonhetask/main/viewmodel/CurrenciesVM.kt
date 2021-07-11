package com.test.swensonhetask.main.viewmodel

import androidx.lifecycle.LiveData
import com.test.swensonhetask.appmanger.network.NetworkState
import com.test.swensonhetask.main.models.CurrenciesResponse
import com.test.swensonhetask.main.repo.CurrenciesRepo
import com.test.swensonhetask.utils.BaseViewModel
import com.test.swensonhetask.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CurrenciesVM
@Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var mainRepo: CurrenciesRepo

    private val currenciesLiveData = SingleLiveEvent<CurrenciesResponse>()

    fun getCurrencies(key: String) {
        addToDisposable(
            mainRepo.getCurrencies(key)
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