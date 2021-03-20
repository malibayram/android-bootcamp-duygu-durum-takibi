package com.dh.duygudurumtakibi.duygutakip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.dh.duygudurumtakibi.R
import com.dh.duygudurumtakibi.databinding.DuyguTakipFragmentBinding

class DuyguTakipFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // binding => iple bağlama
        val veriBagi: DuyguTakipFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.duygu_takip_fragment, container, false
        )



        return veriBagi.root
    }

}