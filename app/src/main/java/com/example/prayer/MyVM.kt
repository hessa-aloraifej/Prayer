package com.example.prayer
//
import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.prayer.API.APIClient
import com.example.prayer.API.APIInterface
import com.example.prayer.DB.Pray
import com.example.prayer.DB.PrayDatabase
import com.example.prayer.Model.PrayerDetails
import com.example.prayer.Model.Results
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 class MyVM(application: Application): AndroidViewModel(application) {

    private var contents: MutableLiveData<List<Pray>> = MutableLiveData()
    private val prayDao = PrayDatabase.getInstance(application).PrayDao()

  fun getContents() = contents

    fun getAllUsers(){
        CoroutineScope(Dispatchers.IO).launch {
            var list=prayDao.getPrayerTimes()
            contents.postValue(list)
        }
    }

    fun addPrayerTime(cityPrayTime: Pray){
        CoroutineScope(Dispatchers.IO).launch {

            prayDao.insertPrayerTime(cityPrayTime)
            getAllUsers()

        }

    }

    fun updatePrayerTime(cityPrayTime: Pray){
        CoroutineScope(Dispatchers.IO).launch {

            prayDao.updatePrayerTime(cityPrayTime)


        }
    }

    fun deletePrayerTime(cityPrayTime: Pray){
        CoroutineScope(Dispatchers.IO).launch {

            prayDao.deletePrayerTime(cityPrayTime)

        }
    }



}

