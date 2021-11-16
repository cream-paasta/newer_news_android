package com.example.newernews.presentation.ui.mainfragment.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.newernews.domain.model.KoreanAddress
import com.example.newernews.domain.usecase.GetAddressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAddressUseCase: GetAddressUseCase
) : ViewModel() {

    fun getKoreanAddress(): KoreanAddress {
        var koreanAddress = KoreanAddress("", "", "")
        getAddressUseCase.execute(Any())
            .subscribe(object: SingleObserver<KoreanAddress> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onSuccess(t: KoreanAddress?) {
                    if (t != null) {
                        Log.d("TESTLOG", "city: ${t.city} / gu: ${t.gu} / dong: ${t.dong}")
                        koreanAddress = t
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.e("TESTLOG", "[getKoreanAddress] onError: $e")
                }
            })
        return koreanAddress
    }
}