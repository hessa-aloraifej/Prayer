package com.example.prayer

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prayer.API.APIClient
import com.example.prayer.API.APIInterface
import com.example.prayer.Model.PrayerDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

  // val vm by lazy { ViewModelProvider(this).get(MyVM::class.java) }
   lateinit var RVAdapter:RVAdapter
    var list:ArrayList<Display> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var rv=findViewById<RecyclerView>(R.id.rv2)
        var btn=findViewById<Button>(R.id.button)
        var search= findViewById<EditText>(R.id.city)
        var go=findViewById<Button>(R.id.button2)
        go.setOnClickListener {
            val intent= Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
        checkInternet()


        RVAdapter = RVAdapter( this,list)
            rv.adapter = RVAdapter
            rv.layoutManager = LinearLayoutManager(this)

        btn.setOnClickListener {


            var apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Please wait")
            progressDialog.show()
            apiInterface?.getPhoto(search.text.toString())?.enqueue(object: Callback<PrayerDetails?> {
                override fun onResponse(call: Call<PrayerDetails?>, response: Response<PrayerDetails?>) {

                        val resposeList = response.body()!!.results
                        progressDialog.dismiss()
                        val country=resposeList.location.country
                        val city=resposeList.location.country
                        val time=resposeList.datetime[0]
                        var imsak=time.times.Imsak
                        var fajer=time.times.Fajr
                        var dhuhr=time.times.Dhuhr
                        var asr=time.times.Asr
                        println(country)
                       list.add(Display(Constants.myid+1,country,city,imsak,fajer,dhuhr,asr))
                        //contents.postValue(list)
                        RVAdapter.update(list)
                        rv.scrollToPosition(list.size - 1)

                     }



                override fun onFailure(call: Call<PrayerDetails?>, t: Throwable) {
                    println(t.message)
                }
            })

        }




    }


    fun checkInternet(){
        if(!connectedToInternet()){
            AlertDialog.Builder(this@MainActivity)
                .setTitle("Internet Connection Not Found")
                .setPositiveButton("RETRY"){_, _ -> checkInternet()}
                .show()
        }
    }

    fun connectedToInternet(): Boolean{
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}