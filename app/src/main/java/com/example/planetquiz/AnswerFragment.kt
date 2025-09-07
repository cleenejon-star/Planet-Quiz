package com.example.planetquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.planetquiz.databinding.FragmentAnswerBinding // Import View Binding class

class AnswerFragment : Fragment() {

    // Setup View Binding
    private var _binding: FragmentAnswerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using View Binding
        _binding = FragmentAnswerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.textViewResult.visibility = View.GONE
        binding.textViewDetails.visibility = View.GONE


        val questionId = arguments?.getInt("question_id", -1) ?: -1


        setupQuiz(questionId)
    }

    private fun setupQuiz(questionId: Int) {
        val buttons = listOf(
            binding.buttonOptionA, binding.buttonOptionB, binding.buttonOptionC, binding.buttonOptionD,
            binding.buttonOptionE, binding.buttonOptionF, binding.buttonOptionG, binding.buttonOptionH
        )

        val planetStrings = listOf(
            R.string.mercury, R.string.venus, R.string.earth, R.string.mars,
            R.string.jupiter, R.string.saturn, R.string.uranus, R.string.neptune
        )


        buttons.forEachIndexed { index, button ->
            button.text = getString(planetStrings[index])
            // Re-enable buttons in case we are revisiting the screen
            button.isEnabled = true
        }


        val (questionRes, correctAnswerRes, detailsRes) = when (questionId) {
            1 -> Triple(R.string.question_1, R.string.jupiter, R.string.answer_detail_1)
            2 -> Triple(R.string.question_2, R.string.saturn, R.string.answer_detail_2)
            3 -> Triple(R.string.question_3, R.string.uranus, R.string.answer_detail_3)
            else -> Triple(R.string.app_name, 0, 0) // Default case
        }

        // Update the question text view
        binding.textViewQuestion.text = getString(questionRes)

        // If the question is valid, set up the listeners
        if (correctAnswerRes != 0) {
            val correctAnswer = getString(correctAnswerRes)
            setButtonListeners(buttons, correctAnswer, detailsRes)
        }
    }

    private fun setButtonListeners(
        allButtons: List<Button>,
        correctAnswer: String,
        detailsStringResId: Int
    ) {

        val clickListener = View.OnClickListener { clickedView ->

            allButtons.forEach { it.isEnabled = true }


            val clickedButton = clickedView as Button
            val selectedAnswer = clickedButton.text.toString()


            if (selectedAnswer == correctAnswer) {
                binding.textViewResult.text = getString(R.string.correct_feedback)
                binding.textViewDetails.text = getString(detailsStringResId)
            } else {
                binding.textViewResult.text = getString(R.string.wrong_ans)
                binding.textViewDetails.text = getString(R.string.wrong_feedback)
            }
            binding.textViewResult.visibility = View.VISIBLE
            binding.textViewDetails.visibility = View.VISIBLE
        }


        allButtons.forEach { button ->
            button.setOnClickListener(clickListener)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}