package com.adrian.newsgame

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.adrian.newsgame.databinding.AnswerBinding
import com.adrian.newsgame.model.Question

class AnswerFragment : Fragment() {

    private lateinit var question: Question

    private var _binding: AnswerBinding? = null
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
        _binding = DataBindingUtil.inflate(inflater, R.layout.answer, container, false)

        val args = AnswerFragmentArgs.fromBundle(requireArguments())
        renderOutcome(args.isCorrect)

        return binding.root
    }

    private fun renderOutcome(isCorrect: Boolean) {
        Util.bindImage(binding.headlineImage, question.imageUrl)
        binding.correctHeadline.text = question.headlines[question.correctAnswerIndex.toInt()]
        binding.nextQuestion.setOnClickListener {
            (activity as MainActivity).incrementQuestionIndex()
            findNavController().navigate(
                AnswerFragmentDirections.actionAnswerFragmentToQuestionFragment()
            )
        }
        binding.readArticle.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(question.storyUrl)))
        }
        binding.outcome.text = if (isCorrect) resources.getText(R.string.correct_text)
            else resources.getText(R.string.incorrect_text)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}