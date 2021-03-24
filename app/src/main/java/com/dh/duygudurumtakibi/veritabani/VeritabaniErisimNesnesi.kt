package com.dh.duygudurumtakibi.veritabani

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao // CRUD
interface VeritabaniErisimNesnesi {
    @Insert
    suspend fun ekle(duyguDurum: DuyguDurum)

    @Query("SELECT * FROM duygu_durum_tablosu ORDER BY kimlikNumarasi DESC")
    fun tumDuyguVerisiniGetir(): LiveData<List<DuyguDurum>>

    @Query("SELECT * FROM duygu_durum_tablosu WHERE kimlikNumarasi = :kimlik")
    suspend fun getir(kimlik: Long): DuyguDurum?

    @Query("SELECT * FROM duygu_durum_tablosu ORDER BY kimlikNumarasi DESC LIMIT 1")
    suspend fun sonDuyguDurumuGetir(): DuyguDurum?

    // update tablo_ismi set baslangic = 23412345 where duyguid = 23;
    @Update
    suspend fun guncelle(duyguDurum: DuyguDurum)

    @Query("DELETE FROM duygu_durum_tablosu")
    suspend fun tumVeriyiTemizle()

    @Delete
    suspend fun sil(duyguDurum: DuyguDurum)
}

// tablolar arası ilişkiler
// https://developer.android.com/training/data-storage/room/relationships

// tüm dipnotlar
// https://developer.android.com/reference/android/arch/persistence/room/package-summary#annotations

// room detaylı dökümantasyon
// https://developer.android.com/training/data-storage/room

// eski verilerle çalışmak için
// https://stackoverflow.com/a/45600669

// 7 profesyonel ipucu
// https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1

