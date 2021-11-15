package com.example.newernews.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class SelfSigningHelper {

    companion object {

        @JvmStatic fun getCSCAPICertificate(): OkHttpClient.Builder {
            val sc: SSLContext
            val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(object :
                X509TrustManager {
                override fun getAcceptedIssuers(): Array<X509Certificate>? {
                    return arrayOf()
                }

                override fun checkClientTrusted(certs: Array<X509Certificate>,
                                                authType: String) {
                }

                override fun checkServerTrusted(certs: Array<X509Certificate>,
                                                authType: String) {
                }
            })

            val builder = OkHttpClient.Builder()
            try {
                sc = SSLContext.getInstance("SSL")
                sc.init(null, trustAllCerts, SecureRandom())
                builder.sslSocketFactory(sc.socketFactory, trustAllCerts[0] as X509TrustManager)
                builder.hostnameVerifier(HostnameVerifier { hostname, session -> true })
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            } catch (e: KeyManagementException) {
                e.printStackTrace()
            }
            /*HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory())

            val allHostsValid = HostnameVerifier { hostname, session -> true }
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid)*/

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(httpLoggingInterceptor)
            return builder
        }
    }
}