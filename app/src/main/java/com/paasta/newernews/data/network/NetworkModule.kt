package com.paasta.newernews.data.network

import android.content.Context
import com.paasta.newernews.data.network.nnapi.BaseUrl
import com.paasta.newernews.data.network.nnapi.NNApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val selfSigningHelperClient by lazy {
        SelfSigningHelper.getNNAPICertificate().build()
    }

    private val sslSocketFactoryHelperClient by lazy {
        SSLSocketFactoryHelper.getPinnedCertSslSocketFactory().build()
    }

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Singleton
    @Provides
    fun getRetrofit(): NNApi {
        return Retrofit.Builder()
            .baseUrl(BaseUrl.NN_API_BASE_URL)
            .client(selfSigningHelperClient)
            //.client(sslSocketFactoryHelperClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(NNApi::class.java)
    }

}
