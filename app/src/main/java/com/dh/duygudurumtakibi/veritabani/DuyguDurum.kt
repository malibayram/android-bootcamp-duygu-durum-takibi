package com.dh.duygudurumtakibi.veritabani

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "duygu_durum_tablosu")
data class DuyguDurum(
    @PrimaryKey(autoGenerate = true)
    var kimlikNumarasi: Long = 0L,
    @ColumnInfo(name = "baslama_milisaniyesi")
    val baslamaMilisaniyesi: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "bitis_milisaniyesi")
    var bitisMilisaniyesi: Long = baslamaMilisaniyesi,
    @ColumnInfo(name = "durum")
    var durum: Int = -1
)