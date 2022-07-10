package com.adrian.newsgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.adrian.newsgame.databinding.QuestionBinding
import com.adrian.newsgame.model.Question

class QuestionFragment : Fragment() {

    private lateinit var question: Question

    private var _binding: QuestionBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        question = (activity as MainActivity).getQuestion()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.question, container, false)
        setClickListeners()
        setQuestion()
        return binding.root
    }

    private fun setClickListeners() {
        binding.skipButton.setOnClickListener {
            (activity as MainActivity).incrementQuestionIndex()
            question = (activity as MainActivity).getQuestion()
            setQuestion()
        }
        binding.headlineChoice1.setOnClickListener { handleUserGuess(it, 0) }
        binding.headlineChoice2.setOnClickListener { handleUserGuess(it, 1) }
        binding.headlineChoice3.setOnClickListener { handleUserGuess(it, 2) }
    }

    private fun handleUserGuess(view: View, guessIndex: Int) {
        val isCorrect: Boolean = question.correctAnswerIndex.toInt() == guessIndex
        if (isCorrect) (activity as MainActivity).rewardPoints() else (activity as MainActivity).penalisePoints()
        view.findNavController().navigate(QuestionFragmentDirections
            .actionQuestionFragmentToAnswerFragment(isCorrect))
    }

    private fun setQuestion() {
        binding.loadingProgress.visibility = View.GONE
        binding.questionPage.visibility = View.VISIBLE

        Util.bindImage(binding.headlineImage, question.imageUrl)

        binding.headlineChoice1.text = question.headlines[0]
        binding.headlineChoice2.text = question.headlines[1]
        binding.headlineChoice3.text = question.headlines[2]

        binding.headlineSection.text = question.section
        binding.pointsEarned.text = getString(R.string.points_earned, (activity as MainActivity).points)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}