package com.paasta.newernews.presentation.ui.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paasta.newernews.R
import com.paasta.newernews.domain.model.Signup
import com.paasta.newernews.domain.model.User
import com.paasta.newernews.domain.usecase.PostSignupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val postSignupUseCase: PostSignupUseCase
) : ViewModel() {
    private val _isSubmitSuccessLiveData = MutableLiveData<Int>()
    val isSubmitSuccessLiveData: LiveData<Int> get() = _isSubmitSuccessLiveData

    private val _signUpFailTextLiveData = MutableLiveData<Int>()
    val signUpFailTextLiveData: LiveData<Int> get() = _signUpFailTextLiveData

    fun submitSignUp(email: String, pw: String, rePw: String, nickname: String) {
        if (pw != rePw) {
            _isSubmitSuccessLiveData.value = R.string.sign_up_password_unmatched
        }
        postSignupUseCase.execute(Signup(User(email, pw, nickname)))
            .subscribe(object: SingleObserver<Any> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onSuccess(t: Any?) {
                    _isSubmitSuccessLiveData.value = 0
                }

                override fun onError(e: Throwable?) {
                    _isSubmitSuccessLiveData.value = R.string.sign_up_fail
                    Log.e("TESTLOG", "[submitSignUp] onError: $e")
                }
            })
    }
}