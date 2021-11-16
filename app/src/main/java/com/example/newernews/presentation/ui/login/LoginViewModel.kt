package com.example.newernews.presentation.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newernews.domain.model.Login
import com.example.newernews.domain.model.LoginResponse
import com.example.newernews.domain.model.LoginUser
import com.example.newernews.domain.model.User
import com.example.newernews.domain.usecase.LoginUseCase
import com.example.newernews.domain.usecase.SetLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val setLoginUseCase: SetLoginUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _isLoginSuccessLiveData = MutableLiveData<Boolean>()
    val isLoginSuccessLiveData: LiveData<Boolean> get() = _isLoginSuccessLiveData

    fun requestLogin(email: String, pw: String) {
        loginUseCase.execute(Login(LoginUser( email, pw)))
            .subscribe(object: SingleObserver<Response<LoginResponse>> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onSuccess(t: Response<LoginResponse>?) {
                    if (t?.isSuccessful == true) {

                        setLoginSuccess()
                    } else {
                        setLoginFail()
                    }
                }

                override fun onError(e: Throwable?) {
                    setLoginFail()
                    Log.e("TESTLOG", "[requestLogin] onError: $e")
                }
            })
    }

    fun setLoginSuccess() {
        setLoginUseCase.setLogin(value = true)
        _isLoginSuccessLiveData.value = true
    }

    fun setLoginFail() {
        setLoginUseCase.setLogin(value = false)
        _isLoginSuccessLiveData.value = false
    }
}