package com.nanda.kisahnabi.data.network

import com.nanda.kisahnabi.data.KisahResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface ApiService {

    @GET("kisahnabi")
    fun getKisahNabi() : Flowable<List<KisahResponse>>
}