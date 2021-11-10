package com.example.prayer.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Pray::class],version = 1,exportSchema = false)
abstract class PrayDatabase: RoomDatabase() {

    companion object{
        var instance:PrayDatabase?=null;
        fun getInstance(ctx: Context):PrayDatabase
        {
            if(instance!=null)
            {
                return  instance as PrayDatabase;
            }
            instance= Room.databaseBuilder(ctx,PrayDatabase::class.java,"Prayer.db").run { allowMainThreadQueries() }.build()
            return instance as PrayDatabase;
        }
    }
    abstract fun PrayDao():PrayDao;
}