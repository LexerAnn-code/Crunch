package com.ankit.crunch.KoinModule

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.room.Room
import com.ankit.crunch.Database.AppDataBase
import com.ankit.crunch.Database.NewsDao
import com.ankit.crunch.Repository.Repository
import com.ankit.crunch.ViewModel
import com.example.newsfinishedapp.Network.NewsApiRepository
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
val viewModelModule= module {
    viewModel {
        ViewModel(get(), get())
    }
}
val retrofitServiceModule= module {
    fun provideUserService(retrofit: Retrofit): NewsApiRepository {
        return retrofit.create(NewsApiRepository::class.java)
    }
    single { provideUserService(get()) }
}
val netWork= module {
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }
    fun hasNetwork(context:Context):Boolean?{
        var isConnected:Boolean?=false
        val connectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork:NetworkInfo?=connectivityManager.activeNetworkInfo
        if(activeNetwork!=null && activeNetwork.isConnected){
            isConnected =true
        }
        return isConnected
    }
    fun provideHttpClient(cache: Cache,context:Context): OkHttpClient {
        val okHttpClientBuiler = OkHttpClient.Builder().cache(cache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(context)!!)
                    request.newBuilder().header("Cache-Control", "public ,max-age=" + 5).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public,only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                chain.proceed(request)
            }.build()
//                val retrofit=Retrofit.Builder()
//                    .baseUrl("https://newsapi.org/")
//                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()))
//                    .client(okHttpClientBuiler)
//                    .build()

                return okHttpClientBuiler
            }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            //For making HTTP requests Retrofit uses OkHttp Library
            .baseUrl("https://newsapi.org/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }
    single { provideHttpClient(get(),get()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }
    single { provideCache(androidApplication()) }
}
val databaseModule=module {
    fun provideDataBase(application: Application): AppDataBase {
        return Room.databaseBuilder(application, AppDataBase::class.java, "leslie.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    fun provideDao(dataBase: AppDataBase): NewsDao {
        return dataBase.newsDao
    }
    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
}
val repositoryModule= module {
    fun provideUserRepository(api: NewsApiRepository,dao: NewsDao): Repository {
        return Repository(api,dao)
    }
    single { provideUserRepository(get(),get()) }
}
