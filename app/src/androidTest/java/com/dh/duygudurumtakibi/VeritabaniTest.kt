package com.dh.duygudurumtakibi


import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.dh.duygudurumtakibi.veritabani.DuyguDurum
import com.dh.duygudurumtakibi.veritabani.Veritabani
import com.dh.duygudurumtakibi.veritabani.VeritabaniErisimNesnesi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class VeritabaniTest {
    private lateinit var veritabaniErisimNesnesi: VeritabaniErisimNesnesi
    private lateinit var veritabani: Veritabani

    @Before
    fun veritabaniOlustur() {
        val baglam = InstrumentationRegistry.getInstrumentation().targetContext

        veritabani = Room.inMemoryDatabaseBuilder(baglam, Veritabani::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        veritabaniErisimNesnesi = veritabani.veritabaniErisimNesnesi
    }

    @After
    @Throws(IOException::class)
    fun veritabaniKapat() {
        veritabani.close()
    }

    @Test
    @Throws(Exception::class)
    fun birDuyguEkleveOku() {
        runBlocking {
            val duygu = DuyguDurum()
            veritabaniErisimNesnesi.ekle(duygu)
            val sonEklenenDuygu = veritabaniErisimNesnesi.sonDuyguDurumuGetir()
            assertEquals(sonEklenenDuygu?.durum, -1);
        }
    }
}