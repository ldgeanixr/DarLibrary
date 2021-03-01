package com.example.darlibrary.di

import com.example.darlibrary.api.BookApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    val connectTimeout: Long = 40
    val readTimeout: Long = 40
    val baseUrl = "https://dar-library.dar-dev.zone/"

    fun provideHttpClient() : OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun provideBookApi(retrofit: Retrofit): BookApi{
        return retrofit.create(BookApi::class.java)
    }

    single {
        provideHttpClient()
    }

    single {
        provideRetrofit(
            client = get()
        )
    }

    single {
        provideBookApi(
            retrofit = get()
        )
    }
}