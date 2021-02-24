package com.blogspot.soyamr.arview

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:error")
fun setErrorText(view: TextInputLayout, errorMessage: String) {
    if (errorMessage.isEmpty())
        view.error = null
    else
        view.error = errorMessage
}
