package com.dh.duygudurumtakibi.duygudetay

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dh.duygudurumtakibi.R

class DuyguDetayFragment : Fragment() {

    companion object {
        fun newInstance() = DuyguDetayFragment()
    }

    private lateinit var viewModel: DuyguDetayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.duygu_detay_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DuyguDetayViewModel::class.java)
        // TODO: Use the ViewModel
    }

}