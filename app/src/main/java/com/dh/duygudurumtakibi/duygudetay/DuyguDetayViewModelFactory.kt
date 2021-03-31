package com.dh.duygudurumtakibi.duygudetay

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dh.duygudurumtakibi.veritabani.VeritabaniErisimNesnesi

class DuyguDetayViewModelFactory(
    private val duyguId: Long,
    private val veriKaynagi: VeritabaniErisimNesnesi
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DuyguDetayViewModel::class.java)) {
            return DuyguDetayViewModel(duyguId, veriKaynagi) as T
        }
        throw IllegalArgumentException("Bilinmeyen görüntü model sınıfı")
    }
}