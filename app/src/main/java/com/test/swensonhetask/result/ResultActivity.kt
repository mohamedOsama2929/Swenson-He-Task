package com.test.swensonhetask.result

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.test.swensonhetask.R
import com.test.swensonhetask.databinding.ActivityResultBinding
import com.test.swensonhetask.main.models.CurrencyModel

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    lateinit var currencyModel: CurrencyModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.extras != null) {
            if (intent.hasExtra(getString(R.string.currency))) {
                currencyModel = Gson().fromJson(
                    intent.getStringExtra(getString(R.string.currency)),
                    CurrencyModel::class.java
                )
            }
        }
        intiView()
    }

    private fun intiView() {
        binding.editTextAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null && s.toString() != "") {
                    binding.editTextResult.text =
                        ((currencyModel.amount?.toDouble())!! * (s.toString()
                            .toDouble())).toString()
                } else {
                    binding.editTextResult.text = currencyModel.amount
                }

            }

        })
        binding.txtCurrency.text = currencyModel.name
        binding.editTextResult.text = currencyModel.amount
    }
}