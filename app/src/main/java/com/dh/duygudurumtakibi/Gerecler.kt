package com.dh.duygudurumtakibi


import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.dh.duygudurumtakibi.veritabani.DuyguDurum
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


private val BIR_DAKIKA_MILISANIYE = TimeUnit.MILLISECONDS.convert(1, TimeUnit.MINUTES)
private val BIR_SAAT_MILISANIYE = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)

fun sureyiMetneCevir(baslamaMilisaniye: Long, bitisMilisaniye: Long, kaynak: Resources): String {
    val sureMilisaniye = bitisMilisaniye - baslamaMilisaniye
    val haftaninGunu = SimpleDateFormat("EEEE", Locale.getDefault()).format(baslamaMilisaniye)

    return when {
        sureMilisaniye < BIR_DAKIKA_MILISANIYE -> {
            val saniye = TimeUnit.SECONDS.convert(sureMilisaniye, TimeUnit.MILLISECONDS)
            kaynak.getString(R.string.saniye_uzunlugu, haftaninGunu, saniye)
        }
        sureMilisaniye < BIR_SAAT_MILISANIYE -> {
            val dakika = TimeUnit.MINUTES.convert(sureMilisaniye, TimeUnit.MILLISECONDS)
            kaynak.getString(R.string.dakika_uzunlugu, haftaninGunu, dakika)
        }
        else -> {
            val saat = TimeUnit.HOURS.convert(sureMilisaniye, TimeUnit.MILLISECONDS)
            kaynak.getString(R.string.saat_uzunlugu, haftaninGunu, saat)
        }
    }
}

fun duyguDurumuMetneCevir(durum: Int, kaynaklar: Resources): String {
    var qualityString = kaynaklar.getString(R.string.durum_5)
    when (durum) {
        -1 -> qualityString = "--"
        0 -> qualityString = kaynaklar.getString(R.string.durum_0)
        1 -> qualityString = kaynaklar.getString(R.string.durum_1)
        2 -> qualityString = kaynaklar.getString(R.string.durum_2)
        3 -> qualityString = kaynaklar.getString(R.string.durum_3)
        4 -> qualityString = kaynaklar.getString(R.string.durum_4)
        6 -> qualityString = kaynaklar.getString(R.string.durum_6)
        7 -> qualityString = kaynaklar.getString(R.string.durum_7)
        8 -> qualityString = kaynaklar.getString(R.string.durum_8)
    }
    return qualityString
}

@SuppressLint("SimpleDateFormat")
fun milisaniyeyiStringTariheCevir(milisaniye: Long): String {
    return SimpleDateFormat("EEEE dd-MMM-yyyy' Time: 'HH:mm")
        .format(milisaniye).toString()
}

fun convertNumericQualityToString(duygu: Int, kaynaklar: Resources): String {
    var qualityString = kaynaklar.getString(R.string.durum_5)
    when (duygu) {
        -1 -> qualityString = "--"
        0 -> qualityString = kaynaklar.getString(R.string.durum_0)
        1 -> qualityString = kaynaklar.getString(R.string.durum_1)
        2 -> qualityString = kaynaklar.getString(R.string.durum_2)
        3 -> qualityString = kaynaklar.getString(R.string.durum_3)
        4 -> qualityString = kaynaklar.getString(R.string.durum_4)
        6 -> qualityString = kaynaklar.getString(R.string.durum_6)
        7 -> qualityString = kaynaklar.getString(R.string.durum_7)
        8 -> qualityString = kaynaklar.getString(R.string.durum_8)
    }
    return qualityString
}

fun duyguyuHtmleCevir(duygular: List<DuyguDurum>, kaynaklar: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append(kaynaklar.getString(R.string.baslik))
        duygular.forEach {
            append("<br>")
            append(kaynaklar.getString(R.string.baslama_zamani))
            append("\t${milisaniyeyiStringTariheCevir(it.baslamaMilisaniyesi)}<br>")
            if (it.bitisMilisaniyesi != it.baslamaMilisaniyesi) {
                val ms = it.bitisMilisaniyesi.minus(it.baslamaMilisaniyesi)
                val sc = ms / 1000
                val mn = sc / 60
                val hr = mn / 60

                append(kaynaklar.getString(R.string.bitis_zamani))
                append("\t${milisaniyeyiStringTariheCevir(it.bitisMilisaniyesi)}<br>")
                append(kaynaklar.getString(R.string.duygu))
                append("\t${convertNumericQualityToString(it.durum, kaynaklar)}<br>")
                append(kaynaklar.getString(R.string.duygu_suresi))
                // Hours
                append("\t ${hr}:")
                // Minutes
                append("${mn % 60}:")
                // Seconds
                append("${sc % 60}<br><br>")
            }
        }
    }
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}