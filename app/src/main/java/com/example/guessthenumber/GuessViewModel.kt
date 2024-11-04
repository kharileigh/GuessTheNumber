package com.example.guessthenumber

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GuessViewModel : ViewModel() {

    // to store feedback messages
    private val _feedback = MutableLiveData<String>()
    val feedback: LiveData<String> = _feedback

    // remaining attempts
    private val _remainingAttempts = MutableLiveData<Int>()
    val remainingAttempts: LiveData<Int> = _remainingAttempts

    // track if game is won
    private val _hasWon = MutableLiveData<Boolean>()
    val hasWon: LiveData<Boolean> = _hasWon

    // target number for user to guess
    private var targetNumber = Random.nextInt(1, 101)

    // maximum number of attempts allowed
    private val maxAttempts = 5


    init {
        resetGame()
    }


    // HANDLES GUESSES
    fun makeGuess(guess: Int) {

        // end game if won or no attempts left
        if (_hasWon.value == true || _remainingAttempts.value == 0) return

        // update remaining attempts
        _remainingAttempts.value = (_remainingAttempts.value ?: maxAttempts) - 1

        // check guess
        when {
            guess < targetNumber -> _feedback.value = "Too low, try again!"
            guess > targetNumber -> _feedback.value = "Too high, try again!"
            else -> {
                _feedback.value = "You've got it! Number was $targetNumber"
                _hasWon.value = true
            }
        }

        // end game if no attempts left
        if (_remainingAttempts.value == 0 && _hasWon.value == false) {
            _feedback.value = "Game over! Number was $targetNumber"
        }

    }


    // RESET GAME STATE
   fun resetGame() {
        targetNumber = Random.nextInt(1, 101)
        _remainingAttempts.value = maxAttempts
        _feedback.value = "Guess the number between 1 and 100"
        _hasWon.value = false
    }



}