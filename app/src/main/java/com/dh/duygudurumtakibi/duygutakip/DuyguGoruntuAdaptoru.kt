package com.dh.duygudurumtakibi.duygutakip

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dh.duygudurumtakibi.R
import com.dh.duygudurumtakibi.databinding.DuyguGoruntuDuzeniBinding
import com.dh.duygudurumtakibi.veritabani.DuyguDurum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val ELEMAN_TIPI_BASLIK = 3
private val ELEMAN_TIPI_DUYGU = 1
private val adapterScope = CoroutineScope(Dispatchers.Default)

class DuyguGoruntuFarkGeriBildirim : DiffUtil.ItemCallback<VeriElemani>() {
    override fun areItemsTheSame(eski: VeriElemani, yeni: VeriElemani): Boolean {
        return eski.id == yeni.id
    }

    override fun areContentsTheSame(eski: VeriElemani, yeni: VeriElemani): Boolean {
        return eski == yeni
    }
}

class TiklamaTakipcisi(val tiklamaTakipcisi: (duyguId: Long) -> Unit) {
    fun tiklandi(duygu: DuyguDurum) = tiklamaTakipcisi(duygu.kimlikNumarasi)
}

sealed class VeriElemani {
    abstract val id: Long

    data class duyguDurumElemani(val duyguDurum: DuyguDurum) : VeriElemani() {
        override val id = duyguDurum.kimlikNumarasi
    }

    object baslik : VeriElemani() {
        override val id = Long.MIN_VALUE
    }
}

class BaslikGoruntuOlusturucu(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        fun from(parent: ViewGroup): BaslikGoruntuOlusturucu {

            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.text_eleman_goruntusu, parent, false)

            return BaslikGoruntuOlusturucu(view)
        }
    }
}

class DuyguGoruntuAdaptoru(val tiklamaTakipcisi: TiklamaTakipcisi) :
    ListAdapter<VeriElemani, RecyclerView.ViewHolder>(DuyguGoruntuFarkGeriBildirim()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ELEMAN_TIPI_BASLIK -> BaslikGoruntuOlusturucu.from(parent)
            ELEMAN_TIPI_DUYGU -> GoruntuOlusturucu.from(parent)

            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun onBindViewHolder(tutucu: RecyclerView.ViewHolder, veriSirasi: Int) {
        when (tutucu) {
            is GoruntuOlusturucu -> {
                val eleman = getItem(veriSirasi) as VeriElemani.duyguDurumElemani
                tutucu.bagla(eleman.duyguDurum, tiklamaTakipcisi)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is VeriElemani.baslik -> ELEMAN_TIPI_BASLIK
            is VeriElemani.duyguDurumElemani -> ELEMAN_TIPI_DUYGU
            else -> ELEMAN_TIPI_DUYGU
        }
    }

    fun addHeaderAndSubmitList(list: List<DuyguDurum>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(VeriElemani.baslik)
                else -> listOf(VeriElemani.baslik) + list.map { VeriElemani.duyguDurumElemani(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    class GoruntuOlusturucu private constructor(private val baglanti: DuyguGoruntuDuzeniBinding) :
        RecyclerView.ViewHolder(baglanti.root) {

        fun bagla(eleman: DuyguDurum, tiklamaTakipcisi: TiklamaTakipcisi) {
            baglanti.duygu = eleman
            baglanti.executePendingBindings()
            baglanti.tiklamaTakibi = tiklamaTakipcisi
        }

        companion object {
            fun from(parent: ViewGroup): GoruntuOlusturucu {
                val layoutInflater = LayoutInflater.from(parent.context)

                val baglanti =
                    DuyguGoruntuDuzeniBinding.inflate(layoutInflater, parent, false)

                return GoruntuOlusturucu(baglanti)
            }
        }
    }
}