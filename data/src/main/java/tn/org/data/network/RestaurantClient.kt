package tn.org.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.org.data.BuildConfig
import tn.org.data.network.service.RestaurantService
import tn.org.data.utils.toVersion
import java.util.*

object RestaurantClient {

    private fun createHttpClient(): OkHttpClient {
        val httpLogger = HttpLoggingInterceptor()
        httpLogger.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient().newBuilder()
            .addInterceptor { chain ->
                val request = chain.request()
                val newUrl = request.url.newBuilder()
                    .addQueryParameter("client_id", BuildConfig.CLIENT_ID)
                    .addQueryParameter("client_secret", BuildConfig.CLIENT_SECRET)
                    .addQueryParameter("v", Date().toVersion())
                    .build()
                val newRequest = request.newBuilder()
                    .url(newUrl)
                    .build()
                chain.proceed(newRequest)
            }
            .addInterceptor(httpLogger)
            .build()
        return client
    }

    private fun createRetrofitInstance() = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(createHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val restaurantService: RestaurantService by lazy {
        createRetrofitInstance().create(RestaurantService::class.java)
    }
}
