package com.example.planetquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.planetquiz.databinding.FragmentQuestionsBinding

class QuestionsFragment : Fragment() {

    private var _binding: FragmentQuestionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonQuestion1.setOnClickListener {
            // Navigate to AnswerFragment with question index 0
            val action = QuestionsFragmentDirections.actionQuestionsFragmentToAnswerFragment(1)
            findNavController().navigate(action)
        }

        binding.buttonQuestion2.setOnClickListener {
            // Navigate to AnswerFragment with question index 1
            val action = QuestionsFragmentDirections.actionQuestionsFragmentToAnswerFragment(2)
            findNavController().navigate(action)
        }

        binding.buttonQuestion3.setOnClickListener {
            // Navigate to AnswerFragment with question index 2
            val action = QuestionsFragmentDirections.actionQuestionsFragmentToAnswerFragment(3)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}