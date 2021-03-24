package com.dh.duygudurumtakibi.duygutakip

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dh.duygudurumtakibi.veritabani.VeritabaniErisimNesnesi
import com.dh.duygutakibi.duygutakip.DuyguTakipGoruntuModeli

class DuyguTakipViewModelFactory(
    private val veriKaynagi: VeritabaniErisimNesnesi,
    private val uygulama: Application,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DuyguTakipGoruntuModeli::class.java)) {
            return DuyguTakipGoruntuModeli(veriKaynagi, uygulama) as T
        }
        throw IllegalArgumentException("Bilinmeyen ViewModel Sınıfı")
    }
}