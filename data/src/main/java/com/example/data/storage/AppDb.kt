package com.example.data.storage

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.storage.dao.*
import com.example.data.storage.entities.*

@Database(
    entities = [
        Diet::class,
        Category::class,
        Product::class,
        Status::class,
        ProductInDiet::class
    ],
    version = 1,
    exportSchema = false
)

abstract class AppDb : RoomDatabase() {

    abstract fun dietDao(): DietDao
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun productInDietDao(): ProductInDietDao
    abstract fun statusDao(): StatusDao

    companion object {
        @Volatile
        private var INSTANCE: AppDb? = null

        fun getDatabase(context: Context): AppDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDb::class.java,
                    "app_database"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            db.execSQL(
                                "INSERT INTO statuses (id, name) VALUES " +
                                        "(1, 'Разрешено'), " +
                                        "(2, 'Под вопросом'), " +
                                        "(3, 'Запрещено')"
                            )
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}