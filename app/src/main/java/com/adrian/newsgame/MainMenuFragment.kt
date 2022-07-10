package com.adrian.newsgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.adrian.newsgame.databinding.MainMenuBinding

class MainMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<MainMenuBinding>(inflater, R.layout.main_menu,
            container, false)

        binding.startGameButton.setOnClickListener { view: View ->
            view.findNavController().navigate(
                MainMenuFragmentDirections.actionMainMenuFragmentToQuestionFragment())
        }

        return binding.root
    }

}