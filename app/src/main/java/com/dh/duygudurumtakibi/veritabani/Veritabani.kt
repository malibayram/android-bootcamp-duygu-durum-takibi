package com.dh.duygudurumtakibi.veritabani

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Singleton => factory => static
@Database(entities = [DuyguDurum::class], version = 1, exportSchema = false)
abstract class Veritabani : RoomDatabase() {
    abstract val veritabaniErisimNesnesi: VeritabaniErisimNesnesi

    companion object {
        @Volatile
        private var ORNEK_NESNE: Veritabani? = null

        fun ornegiGetir(baglam: Context): Veritabani {
            synchronized(this) {
                var ornek = ORNEK_NESNE;
                if (ornek == null) {
                    ornek = Room.databaseBuilder(
                        baglam.applicationContext,
                        Veritabani::class.java,
                        "duygu_durum_veritabani"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    ORNEK_NESNE = ornek
                }
                return ornek
            }
        }
    }

}