package com.example.movingonteste.telasCliente

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movingonteste.R
import com.example.movingonteste.databinding.FragmentClienteCronogramaBinding

class cliente_cronograma : Fragment() {

    private lateinit var binding: FragmentClienteCronogramaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClienteCronogramaBinding.inflate(inflater, container, false)
        val root:View = binding.root
        return root
    }

}