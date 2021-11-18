package com.paasta.newernews.data.network

import android.util.Log
import com.paasta.NewerNewsApplication
import com.paasta.newernews.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.KeyStore
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import javax.net.ssl.*

class SSLSocketFactoryHelper {

    companion object {

        @JvmStatic fun getPinnedCertSslSocketFactory(): OkHttpClient.Builder {
            val cf: CertificateFactory = CertificateFactory.getInstance("X.509")

            val caInput = NewerNewsApplication.applicationContext().resources.openRawResource(R.raw.paasta)
            val ca = caInput.use {
                cf.generateCertificate(it) as X509Certificate
            }

            val keyStoreType = KeyStore.getDefaultType()
            val keyStore = KeyStore.getInstance(keyStoreType).apply {
                load(null, null)
                setCertificateEntry("ca", ca)
            }

            val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
            val tmf = TrustManagerFactory.getInstance(tmfAlgorithm).apply {
                init(keyStore)
            }

            val sslContext = SSLContext.getInstance("TLS").apply {
                init(null, tmf.trustManagers, null)
            }

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslContext.socketFactory, tmf.trustManagers[0] as X509TrustManager)
            builder.hostnameVerifier(HostnameVerifier { _, session ->
                HttpsURLConnection.getDefaultHostnameVerifier().run {
                    verify("newer_news_server.paas-ta.org", session)
                }
            })

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(httpLoggingInterceptor)
            return builder
        }
    }
}