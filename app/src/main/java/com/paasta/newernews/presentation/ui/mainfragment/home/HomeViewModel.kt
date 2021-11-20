package com.paasta.newernews.presentation.ui.mainfragment.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paasta.newernews.domain.model.KoreanAddress
import com.paasta.newernews.domain.model.NewsList
import com.paasta.newernews.domain.model.WeatherModel
import com.paasta.newernews.domain.model.request.RequestGetCurrentWeather
import com.paasta.newernews.domain.model.request.RequestNewsListModel
import com.paasta.newernews.domain.usecase.GetAddressUseCase
import com.paasta.newernews.domain.usecase.GetCurrentWeatherUseCase
import com.paasta.newernews.domain.usecase.GetNewsListUseCase
import com.paasta.newernews.domain.usecase.local.*
import com.paasta.newernews.presentation.delegate.NewsOpenDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAddressUseCase: GetAddressUseCase,
    private val getUserNameUseCase: GetUserNameUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val setSavedCityUseCase: SetSavedCityUseCase,
    private val setSavedGuUseCase: SetSavedGuUseCase,
    private val setSavedDongUseCase: SetSavedDongUseCase,
    private val getNewsListUseCase: GetNewsListUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    val delegate: NewsOpenDelegate
) : ViewModel() {
    private val _currentAddressLiveData = MutableLiveData<KoreanAddress>()
    val currentAddressLiveData: LiveData<KoreanAddress> get() = _currentAddressLiveData

    private val _userNameLiveData = MutableLiveData<String>()
    val userNameLiveData: LiveData<String> get() = _userNameLiveData

    private val _weatherLiveData = MutableLiveData<WeatherModel>()
    val weatherLiveData: LiveData<WeatherModel> get() = _weatherLiveData

    private val _smallNewsListLiveData = MutableLiveData<NewsList>()
    val smallNewsListLivedata: LiveData<NewsList> get() = _smallNewsListLiveData

    private val _bigNewsListLiveData = MutableLiveData<NewsList>()
    val bigNewsListLiveData: LiveData<NewsList> get() = _bigNewsListLiveData

    fun requestCurrentAddress() {
        getAddressUseCase.execute(Any())
            .subscribe(object: SingleObserver<KoreanAddress> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onSuccess(t: KoreanAddress) {
                    saveAddress(t)
                    _currentAddressLiveData.value = t
                }


                override fun onError(e: Throwable?) {2
                    Log.e("TESTLOG", "[requestCurrentAddress] onError: $e")
                }
            })
    }

    fun requestUserName() {
        val userName = getUserNameUseCase.getUserName()
        if (userName != null) {
            _userNameLiveData.value = userName!!
        } else {
            _userNameLiveData.value = ""
        }
    }

    fun requestCurrentWeather(lat: Double, lon: Double) {
        val token = getTokenUseCase.getToken()
        if (token != null) {
            getCurrentWeatherUseCase.execute(RequestGetCurrentWeather(lat, lon))
                .subscribe(object: SingleObserver<WeatherModel> {
                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onSuccess(t: WeatherModel?) {
                        _weatherLiveData.value = t!!
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("TESTLOG", "[requestCurrentWeather] onError: $e")
                    }
                })
        }
    }

    fun requestNewsList(query: String, location: Int) { // 0: Small Location, 1: Big Location
        val token = getTokenUseCase.getToken()
        if (token != null) {
            getNewsListUseCase.execute(RequestNewsListModel(token, query))
                .subscribe(object: SingleObserver<NewsList> {
                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onSuccess(t: NewsList?) {
                        if (location == 0)_smallNewsListLiveData.value = t!!
                        else if (location == 1) _bigNewsListLiveData.value = t!!
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("TESTLOG", "[requestNewsList] onError: $e")
                    }
                })
        }
    }

    fun saveAddress(address: KoreanAddress) {
        setSavedCityUseCase.setSavedCity(address.city)
        setSavedGuUseCase.setSavedGu(address.gu)
        setSavedDongUseCase.setSavedDong(address.dong)
    }
}