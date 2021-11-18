package com.paasta.newernews.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

class SelfSigningHelper {

    companion object {

        @JvmStatic fun getNNAPICertificate(): OkHttpClient.Builder {
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
                builder.hostnameVerifier(HostnameVerifier { _, session ->
                    HttpsURLConnection.getDefaultHostnameVerifier().run {
                        verify("https://newer_news_server.paas-ta.org", session)
                    }
                })
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            } catch (e: KeyManagementException) {
                e.printStackTrace()
            }

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(httpLoggingInterceptor)
            return builder
        }
    }
}