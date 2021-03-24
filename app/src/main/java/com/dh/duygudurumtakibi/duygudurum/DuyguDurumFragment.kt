package com.dh.duygudurumtakibi.duygudurum

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dh.duygudurumtakibi.R
import com.dh.duygudurumtakibi.databinding.FragmentDuyguDurumBinding
import com.dh.duygudurumtakibi.veritabani.Veritabani

class DuyguDurumFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val veriBagi: FragmentDuyguDurumBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_duygu_durum, container, false
        )

        val argumanlar = DuyguDurumFragmentArgs.fromBundle(requireArguments())
        val uygulama = requireNotNull(this.activity).application

        val veriKaynagi = Veritabani.ornegiGetir(uygulama).veritabaniErisimNesnesi
        val duyguDurumGoruntuModelFactory =
            DuyguDurumViewModelFactory(argumanlar.duyguAnahtar, veriKaynagi)

        val duyguDurumGoruntuModel = ViewModelProvider(
            this,
            duyguDurumGoruntuModelFactory
        ).get(DuyguDurumViewModel::class.java)

        veriBagi.duyguDurumGoruntuModel = duyguDurumGoruntuModel

        duyguDurumGoruntuModel.duyguTakibeYonlendir.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController()
                    .navigate(DuyguDurumFragmentDirections.actionDuyguDurumFragmentToDuyguTakipFragment())
                duyguDurumGoruntuModel.yonlendirmeTamamlandi()
            }
        })

        return veriBagi.root
    }
}