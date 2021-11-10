package com.example.prayer.DB

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface PrayDao {

    @Query("SELECT * FROM CityPrayTime")
    fun getPrayerTimes(): List<Pray>

    @Insert
    fun insertPrayerTime(cityPrayTime: Pray)

    @Update
    fun updatePrayerTime(cityPrayTime: Pray)

    @Delete
    fun deletePrayerTime(cityPrayTime: Pray)

}