package com.dh.duygudurumtakibi.duygutakip

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dh.duygudurumtakibi.R
import com.dh.duygudurumtakibi.duyguDurumuMetneCevir
import com.dh.duygudurumtakibi.sureyiMetneCevir
import com.dh.duygudurumtakibi.veritabani.DuyguDurum


@BindingAdapter("duyguSuresi")
fun TextView.duyguSuresiAta(duyguDurum: DuyguDurum) {
    text =
        sureyiMetneCevir(
            duyguDurum.baslamaMilisaniyesi,
            duyguDurum.bitisMilisaniyesi,
            context.resources
        )
}

@BindingAdapter("duyguDurumu")
fun TextView.duyguDurumuAta(duyguDurum: DuyguDurum) {
    text =
        duyguDurumuMetneCevir(duyguDurum.durum, context.resources)
}

@BindingAdapter("duyguIcon")
fun ImageView.setSleepImage(duyguDurum: DuyguDurum) {

    setImageResource(
        when (duyguDurum.durum) {
            0 -> R.drawable.food
            1 -> R.drawable.angry
            2 -> R.drawable.anxious
            3 -> R.drawable.crying
            5 -> R.drawable.expressionless
            6 -> R.drawable.thinking
            7 -> R.drawable.fall_in_love
            8 -> R.drawable.sleeping
            else -> R.drawable.laughing
        }
    )
}