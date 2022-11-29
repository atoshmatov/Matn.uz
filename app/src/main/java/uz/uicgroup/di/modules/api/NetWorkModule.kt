package uz.uicgroup.di.modules.api

import android.content.Context
import com.google.gson.Gson
import com.mocklets.pluto.PlutoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.uicgroup.BuildConfig.BASE_URL
import uz.uicgroup.BuildConfig.Bearer
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    @[Provides Singleton]
    fun provideOkHTTPClientObject(
        @ApplicationContext context: Context,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(PlutoInterceptor())
            .addInterceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer $Bearer")
                    .build()
                it.proceed(request)
            }
            .build()

    @[Provides Singleton]
    fun provideRetrofitObject(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @[Provides Singleton]
    fun provideGson(): Gson = Gson()
}