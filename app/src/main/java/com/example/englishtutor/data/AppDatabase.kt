package com.example.englishtutor.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [LessonEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun lessonDao(): LessonDao

    companion object {
        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "english_tutor_db"
            ).build()
        }
    }
}
