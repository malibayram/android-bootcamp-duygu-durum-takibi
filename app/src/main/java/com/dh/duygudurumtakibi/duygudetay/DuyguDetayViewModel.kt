package com.dh.duygudurumtakibi.duygudetay

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dh.duygudurumtakibi.veritabani.DuyguDurum
import com.dh.duygudurumtakibi.veritabani.VeritabaniErisimNesnesi
import kotlinx.coroutines.launch

class DuyguDetayViewModel(
    private val duyguId: Long = 0L,
    val veritabani: VeritabaniErisimNesnesi
) : ViewModel() {
    private val _duyguTakibeYonlendir = MutableLiveData<Boolean?>()

    private val duyguDurum: LiveData<DuyguDurum?>

    fun getDuyguDurum() = duyguDurum

    init {
        duyguDurum = veritabani.kimlikleGetir(duyguId)
    }

    val duyguTakibeYonlendir: LiveData<Boolean?>
        get() = _duyguTakibeYonlendir

    fun yonlendirmeTamamlandi() {
        _duyguTakibeYonlendir.value = null
    }

    fun duyguDurumuSil() {
        viewModelScope.launch {
            val duygu = veritabani.getir(duyguId) ?: return@launch
            veritabani.sil(duygu)

            _duyguTakibeYonlendir.value = true
        }
    }

    fun yonlendirmeTiklandi() {
        _duyguTakibeYonlendir.value = true
    }
}