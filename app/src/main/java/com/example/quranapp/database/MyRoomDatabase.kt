package com.example.quranapp.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.quranapp.models.Juz
import com.example.quranapp.models.Sura
import com.example.quranapp.models.Verse


@Database(entities = [Sura::class, Verse::class,Juz::class], version = 1,exportSchema = false)
@TypeConverters(TypeGsonConverter::class)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun SuraDao(): SuraDao
    abstract fun VerseDao(): VerseDao
    abstract fun JuzDao(): JuzDao

    companion object {

        private var instant: MyRoomDatabase? = null

        fun getInstant(context: Context): MyRoomDatabase {
            if (instant == null) {
                // synchronized لمنع وصول مصدرين بنفس الوقت ويعملو مشاكل
                synchronized(this) {
                    instant =
                        Room.databaseBuilder(
                            context.applicationContext, MyRoomDatabase::class.java, "database"
                        ).fallbackToDestructiveMigration()
                            .addCallback(MyRoomDatabaseCallback())
                            .build()
                }
                //.fallbackToDestructiveMigration() لتجنب مسح البيانات عند تغيير الاصدار
            }
            return instant!!
        }
    }

   private class MyRoomDatabaseCallback() :RoomDatabase.Callback(){
       override fun onOpen(db: SupportSQLiteDatabase) {
           super.onOpen(db)

           Log.d("createdatabase"," room onOpen")
       }
       override fun onCreate(db: SupportSQLiteDatabase) {
           super.onCreate(db)
           Log.d("createdatabase","room onCreate")
//           INSTANCE?.let { database ->
//               scope.launch {
//                   var wordDao = database.wordDao()
//
//                   // Delete all content here.
//                   wordDao.deleteAll()
//
//                   // Add sample words.
//                   var word = Word("Hello")
//                   wordDao.insert(word)
//                   word = Word("World!")
//                   wordDao.insert(word)
//
//                   // TODO: Add your own words!
//                   word = Word("TODO!")
//                   wordDao.insert(word)
//               }
//           }
       }
    }

 // todo https://developer.android.com/codelabs/android-room-with-a-view-kotlin#13

}