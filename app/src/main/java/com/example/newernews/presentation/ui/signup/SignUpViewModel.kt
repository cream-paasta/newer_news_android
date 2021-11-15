package com.example.newernews.presentation.ui.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newernews.domain.model.Signup
import com.example.newernews.domain.model.User
import com.example.newernews.domain.usecase.SignupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signupUseCase: SignupUseCase
) : ViewModel() {
    private val _isSubmitSuccessLiveData = MutableLiveData<Boolean>()
    val isSubmitSuccessLiveData: LiveData<Boolean> get() = _isSubmitSuccessLiveData

    fun submitSignUp(email: String, pw: String, nickname: String) {
        signupUseCase.execute(Signup(User(email, pw, nickname)))
            .subscribe(object: SingleObserver<Any> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onSuccess(t: Any?) {
                    _isSubmitSuccessLiveData.value = true
                }

                override fun onError(e: Throwable?) {
                    _isSubmitSuccessLiveData.value = false
                    Log.e("TESTLOG", "[submitSignUp] onError: $e")
                }
            })
    }
}