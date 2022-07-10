package com.adrian.newsgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.adrian.newsgame.databinding.ActivityMainBinding
import com.adrian.newsgame.model.Game
import com.adrian.newsgame.model.Question
import com.adrian.newsgame.network.Status

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: GameViewModel

    private var questionIndex: Int = 0
    private var questions: ArrayList<Question> = arrayListOf()
    var points: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel()
        setObserver()
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
    }

    private fun setObserver() {
        viewModel.getGame().observe(this) {
            when (it.status) {
                Status.SUCCESS -> setGameData(it.data!!)
                Status.ERROR -> showErrorMessage()
                Status.LOADING -> {}
            }
        }
    }

    private fun setGameData(game: Game) { addQuestions(game.questions) }

    private fun addQuestions(questions: List<Question>) { this.questions.addAll(questions) }

    private fun showErrorMessage() {
        Toast.makeText(this,  getString(R.string.api_fail_message),
            Toast.LENGTH_LONG).show()
    }

    fun getQuestion() : Question {
        if (questionIndex < 0 || questionIndex >= questions.size) {
            throw ArrayIndexOutOfBoundsException()
        }
        return questions[questionIndex]
    }

    fun incrementQuestionIndex() {
        if (questionIndex < questions.size - 1) {
            questionIndex++
        }
    }

    fun rewardPoints() { points += 8 }

    fun penalisePoints() { points -= 1 }

}