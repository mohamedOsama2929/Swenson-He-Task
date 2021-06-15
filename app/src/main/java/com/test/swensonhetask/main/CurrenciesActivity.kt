package com.test.swensonhetask.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.test.swensonhetask.R
import com.test.swensonhetask.appmanger.network.NetworkState
import com.test.swensonhetask.databinding.ActivityMainBinding
import com.test.swensonhetask.main.adapters.CurrenciesRecycleViewAdapter
import com.test.swensonhetask.main.models.CurrenciesResponse
import com.test.swensonhetask.main.models.CurrencyModel
import com.test.swensonhetask.main.viewmodel.CurrenciesVM
import com.test.swensonhetask.result.ResultActivity

class CurrenciesActivity : AppCompatActivity(),
    CurrenciesRecycleViewAdapter.OnItemClickListenerCurrency {
    lateinit var binding: ActivityMainBinding
    lateinit var currenciesVM: CurrenciesVM
    lateinit var currenciesRecycleViewAdapter: CurrenciesRecycleViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        currenciesVM = ViewModelProvider(this).get(CurrenciesVM::class.java)
        currenciesVM.getCurrencies("37b4e8e2a6e2df13925e2c40b2111cc0")
        observeDepartmentsData()
        detectApiCompletedOrHasError()
    }

    private fun observeDepartmentsData() {
        currenciesVM.currenciesLiveData().observe(this, Observer { response ->
            if (response.success == true) {
                renderView(response)

            } else {
                Toast.makeText(this, "error", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun renderView(response: CurrenciesResponse) {
        binding.txtName.text = response.base
        val ratesList = mutableListOf<CurrencyModel>()
        response.rates?.forEach { rate ->
            val currencyModel = CurrencyModel(rate.key, rate.value)
            ratesList.add(currencyModel)
        }
        currenciesRecycleViewAdapter = CurrenciesRecycleViewAdapter(this, ratesList, this)
        binding.rvCurrencies.adapter = currenciesRecycleViewAdapter
        Toast.makeText(this, "success", Toast.LENGTH_LONG).show()
    }

    private fun detectApiCompletedOrHasError() {
        currenciesVM.getNetworkState().observe(this, Observer {
            if (it == NetworkState.LOADING) {
                binding.mainProgressBar.visibility = View.VISIBLE
                if (it == NetworkState.ERROR) {
                    binding.mainProgressBar.visibility = View.GONE
                }
            } else {
                binding.mainProgressBar.visibility = View.GONE
            }
        })
        currenciesVM.getError().observe(this, Observer {
            binding.mainProgressBar.visibility = View.GONE
        })
    }

    override fun onItemCurrencyClicked(item: CurrencyModel) {
        openResultActivity(item)
    }

    private fun openResultActivity(item: CurrencyModel) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(getString(R.string.currency), Gson().toJson(item))
        startActivity(intent)
    }
}