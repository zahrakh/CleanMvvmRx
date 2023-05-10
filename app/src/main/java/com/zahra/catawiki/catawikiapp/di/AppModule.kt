package com.zahra.catawiki.catawikiapp.di

import android.content.Context
import android.util.Log
import com.zahra.catawiki.BuildConfig
import com.zahra.catawiki.catawikiapp.data.remote.*
import com.zahra.catawiki.catawikiapp.data.remote.Api.Companion.BASE_URL
import com.zahra.catawiki.catawikiapp.data.repository.PokemonsRepositoryDefault
import com.zahra.catawiki.catawikiapp.domain.repository.PokemonsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor { message ->
            Log.d("okHttpLog", message)
        }.apply {
            setLevel(
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BASIC
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            )
        }

        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitApi(client: OkHttpClient): Api {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkDataSource(api: Api, stringProvider: StringProvider): NetworkDataSource {
        return NetworkDataSourceDefault(api, stringProvider)
    }

    @Provides
    fun provideStringProvider(@ApplicationContext appContext: Context): StringProvider {
        return StringProviderDefault(appContext)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(repository: PokemonsRepositoryDefault): PokemonsRepository {
        return repository
    }

}

