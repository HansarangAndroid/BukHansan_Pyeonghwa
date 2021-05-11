package com.example.lecturesopt28th.signup.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lecturesopt28th.signup.data.dto.RequestSignUp
import com.example.lecturesopt28th.signup.repository.SignUpRespository
import com.example.lecturesopt28th.utils.InputChecker.checkBlank
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRespository
) : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    val birthday = MutableLiveData<String>()
    val _sex = MutableLiveData<String>()
    private val sex: LiveData<String>
        get() = _sex

    private val _isSuccessed = MutableLiveData<Boolean>()
    val isSuccessed: LiveData<Boolean>
        get() = _isSuccessed

    private val _isValueEmpty = MutableLiveData<Boolean>()
    val isValueEmpty: LiveData<Boolean>
        get() = _isValueEmpty

    fun changeSex(sex: Int) {
        _sex.value = sex.toString()
    }

    private fun getBlankValue(): Boolean {
        val list = listOf(email, password, nickname, birthday, sex)
        return checkBlank(list) <= 0
    }

    fun signUp() {
        if (getBlankValue()) {
            _isValueEmpty.value = true
            getSignUpResult()
        } else {
            _isValueEmpty.value = false
        }
    }

    @SuppressLint("CheckResult")
    fun getSignUpResult() {
        signUpRepository.signUp(
            RequestSignUp(
                email = email.value,
                password = password.value,
                sex = sex.value,
                nickname = nickname.value,
                phone = phoneNumber.value,
                birth = birthday.value
            )
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _isSuccessed.value = true
            }, {
                _isSuccessed.value = false
            })
    }
}