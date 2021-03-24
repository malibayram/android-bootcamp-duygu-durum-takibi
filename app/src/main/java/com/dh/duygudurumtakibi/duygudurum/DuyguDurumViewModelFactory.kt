package com.dh.duygudurumtakibi.duygudurum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dh.duygudurumtakibi.veritabani.VeritabaniErisimNesnesi

class DuyguDurumViewModelFactory(
    private val duyguId: Long,
    private val veriKaynagi: VeritabaniErisimNesnesi
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DuyguDurumViewModel::class.java)) {
            return DuyguDurumViewModel(duyguId, veriKaynagi) as T
        }
        throw IllegalArgumentException("Bilinmeyen görüntü model sınıfı")
    }
}