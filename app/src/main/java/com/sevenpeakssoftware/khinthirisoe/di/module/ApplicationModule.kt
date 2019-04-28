package com.sevenpeakssoftware.khinthirisoe.di.module

import android.content.Context
import com.google.gson.Gson
import com.sevenpeakssoftware.khinthirisoe.data.network.ApiService
import com.sevenpeakssoftware.khinthirisoe.data.network.ApiUrl
import com.sevenpeakssoftware.khinthirisoe.di.App
import com.sevenpeakssoftware.khinthirisoe.di.context.ApplicationContext
import com.sevenpeakssoftware.khinthirisoe.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class ApplicationModule(private val app: App) {

    @Provides
    fun app(): App = app

    @Provides
    @ApplicationContext
    fun provideApplicationContext(): Context {
        return app
    }

    @Provides
    @Singleton
    fun apiService(): ApiService {

        val maxSize = (10 * 1024 * 1024).toLong() // 10MB Cache size
        val cache = Cache(app.cacheDir, maxSize)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .addHeader("CarsContent-Type", "application/json")

                if (NetworkUtils.hasNetwork(app)) {
                    val maxAge = 60
                    requestBuilder.header("Cache-Control", "public, max-age=$maxAge")
                        .build()
                } else {
                    val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
                    requestBuilder.header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=$maxStale"
                    ).build()
                }

                val request = requestBuilder.build()
                chain.proceed(request)
            }
        client.connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .cache(cache)

        return Retrofit.Builder().baseUrl(ApiUrl.BASE_URL)
            .client(client.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(ApiService::class.java)
    }

}
