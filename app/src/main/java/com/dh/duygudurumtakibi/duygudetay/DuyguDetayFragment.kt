package com.dh.duygudurumtakibi.duygudetay

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.dh.duygudurumtakibi.R
import com.dh.duygudurumtakibi.databinding.DuyguDetayFragmentBinding
import com.dh.duygudurumtakibi.veritabani.Veritabani

class DuyguDetayFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val veriBagi: DuyguDetayFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.duygu_detay_fragment, container, false
        )

        val argumanlar = DuyguDetayFragmentArgs.fromBundle(requireArguments())
        val uygulama = requireNotNull(this.activity).application

        val veriKaynagi = Veritabani.ornegiGetir(uygulama).veritabaniErisimNesnesi

        val duyguDetayGoruntuModelFactory =
            DuyguDetayViewModelFactory(argumanlar.duyguAnahtar, veriKaynagi)

        val duyguDetayGoruntuModel = ViewModelProvider(
            this,
            duyguDetayGoruntuModelFactory
        ).get(DuyguDetayViewModel::class.java)

        veriBagi.duyguDetayGoruntuModel = duyguDetayGoruntuModel
        veriBagi.setLifecycleOwner(this)

        duyguDetayGoruntuModel.duyguTakibeYonlendir.observe(viewLifecycleOwner, {
            if (it == true) {
                this.findNavController()
                    .navigate(DuyguDetayFragmentDirections.actionDuyguDetayFragmentToDuyguTakipFragment())

                duyguDetayGoruntuModel.yonlendirmeTamamlandi()
            }
        })

        return veriBagi.root
    }


}