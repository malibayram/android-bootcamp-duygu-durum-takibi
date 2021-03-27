package com.dh.duygudurumtakibi.duygutakip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dh.duygudurumtakibi.databinding.DuyguGoruntuDuzeniBinding
import com.dh.duygudurumtakibi.veritabani.DuyguDurum

class DuyguGoruntuFarkGeriBildirim : DiffUtil.ItemCallback<DuyguDurum>() {
    override fun areItemsTheSame(eski: DuyguDurum, yeni: DuyguDurum): Boolean {
        return eski.kimlikNumarasi == yeni.kimlikNumarasi
    }

    override fun areContentsTheSame(eski: DuyguDurum, yeni: DuyguDurum): Boolean {
        return eski == yeni
    }
}

class TiklamaTakipcisi(val tiklamaTakipcisi: (duyguId: Long) -> Unit) {
    fun tiklandi(duygu: DuyguDurum) = tiklamaTakipcisi(duygu.kimlikNumarasi)
}

class DuyguGoruntuAdaptoru(val tiklamaTakipcisi: TiklamaTakipcisi) :
    ListAdapter<DuyguDurum, DuyguGoruntuAdaptoru.GoruntuOlusturucu>(DuyguGoruntuFarkGeriBildirim()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoruntuOlusturucu {
        return GoruntuOlusturucu.from(parent)
    }

    override fun onBindViewHolder(tutucu: GoruntuOlusturucu, veriSirasi: Int) {
        val eleman = getItem(veriSirasi)

        tutucu.bagla(eleman, tiklamaTakipcisi)
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