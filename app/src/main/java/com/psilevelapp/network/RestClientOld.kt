package com.psilevelapp.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.psilevelapp.constants.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class RestClientOld {

    companion object {
        @kotlin.jvm.JvmField
        var TAG: String = RestClientOld.toString()

        fun getClient(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build()
        }
    }

    init {
        getClient()
    }

}