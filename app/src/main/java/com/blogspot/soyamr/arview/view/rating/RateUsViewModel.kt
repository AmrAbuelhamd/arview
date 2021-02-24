package com.blogspot.soyamr.arview.view.rating

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blogspot.soyamr.arview.model.ArviewRepository

class RateUsViewModel @ViewModelInject constructor(private val repository: ArviewRepository) :
    ViewModel() {

    val ratingErrorMessage: MutableLiveData<String> = MutableLiveData("")


    val ratingText: MutableLiveData<String> = MutableLiveData("")
    val rating: MutableLiveData<Float> = MutableLiveData(0F)

    fun sendRating() {

        if (isValidateInput()) {
            val ratingText = ratingText.value
            val ratingNumber = rating.value
            //send rating
        }
    }

    private fun isValidateInput(): Boolean {
        var result = true

        when {
            ratingText.value.isNullOrEmpty() -> {
                ratingErrorMessage.value = "can't be empty"
                result = false
            }
            else -> {
                ratingErrorMessage.value = ""
            }
        }
        return result
    }
}