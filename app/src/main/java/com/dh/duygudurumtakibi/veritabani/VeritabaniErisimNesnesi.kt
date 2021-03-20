package com.dh.duygudurumtakibi.veritabani

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao // CRUD
interface VeritabaniErisimNesnesi {
    @Insert
    fun ekle(duyguDurum: DuyguDurum)

    @Query("SELECT * FROM duygu_durum_tablosu ORDER BY kimlikNumarasi DESC")
    fun tumDuyguVerisiniGetir(): LiveData<List<DuyguDurum>>

    @Query("SELECT * FROM duygu_durum_tablosu WHERE kimlikNumarasi = :kimlik")
    fun getir(kimlik: Long): DuyguDurum?

    @Query("SELECT * FROM duygu_durum_tablosu ORDER BY kimlikNumarasi DESC LIMIT 1")
    fun sonDuyguDurumuGetir(): DuyguDurum?

    @Update
    fun guncelle(duyguDurum: DuyguDurum)

    @Query("DELETE FROM duygu_durum_tablosu")
    fun tumVeriyiTemizle()

    @Query("DELETE FROM duygu_durum_tablosu WHERE kimlikNumarasi = :kimlik")
    fun duyguDurumuSil(kimlik: Long)

}