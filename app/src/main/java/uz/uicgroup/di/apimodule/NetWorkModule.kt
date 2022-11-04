package uz.uicgroup.di.apimodule

import android.content.Context
import com.mocklets.pluto.PlutoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import uz.uicgroup.BuildConfig.BASE_URL
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {

    @[Provides Singleton]
    fun okHTTPClientObject(
        @ApplicationContext context: Context,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(PlutoInterceptor())
            .build()

    @[Provides Singleton]
    fun getRetrofitObject(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()
}