package com.example.guessthenumber

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GuessViewModel : ViewModel() {

    // to store feedback messages
    private val _feedback = MutableLiveData<String>()
    val feedback: LiveData<String> = _feedback

    // number of guesses user has
    private val targetNumber = Random.nextInt(1, 101)

    // handles guesses & updates feedback
    fun makeGuess(guess: Int) {
        when {
            guess < targetNumber -> _feedback.value = "Too low, try again!"
            guess > targetNumber -> _feedback.value = "Too high, try again!"
            else -> _feedback.value = "You've got it! Number was $targetNumber"
        }
    }
}