package com.dh.duygudurumtakibi.duygudurum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dh.duygudurumtakibi.veritabani.VeritabaniErisimNesnesi
import kotlinx.coroutines.launch

class DuyguDurumViewModel(
    private val duyguId: Long = 0L,
    val veritabani: VeritabaniErisimNesnesi
) : ViewModel() {
    private val _duyguTakibeYonlendir = MutableLiveData<Boolean?>()

    val duyguTakibeYonlendir: LiveData<Boolean?>
        get() = _duyguTakibeYonlendir

    fun yonlendirmeTamamlandi() {
        _duyguTakibeYonlendir.value = null
    }

    fun duyguDurumSecimiYap(durum: Int) {
        viewModelScope.launch {
            val duygu = veritabani.getir(duyguId) ?: return@launch
            duygu.durum = durum
            veritabani.guncelle(duygu)

            _duyguTakibeYonlendir.value = true
        }
    }
}