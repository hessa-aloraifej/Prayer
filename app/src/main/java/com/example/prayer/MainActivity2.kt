package com.example.prayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity2 : AppCompatActivity() {
    lateinit var RVAdapter:RVAdapter2
    val vm by lazy { ViewModelProvider(this).get(MyVM::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var rv2=findViewById<RecyclerView>(R.id.rv2)
vm.getAllUsers()
        vm.getContents().observe(this,{
            contents->  RVAdapter = RVAdapter2( this,contents)
            rv2.adapter = RVAdapter
            rv2.layoutManager = LinearLayoutManager(this)
        })
    }
}