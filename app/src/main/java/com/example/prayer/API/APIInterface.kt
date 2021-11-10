package com.example.prayer.API

import com.example.prayer.Model.PrayerDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("today.json")
    fun getPhoto(@Query("city")word:String): Call<PrayerDetails?>?
}