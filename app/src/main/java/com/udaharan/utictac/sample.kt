//package com.udaharan.utictac
//import android.R
//import android.graphics.Color
//import android.os.Bundle
//import android.view.View
//import android.widget.Button
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//
//
//class sample {
//}
//
//
//class MainActivity : AppCompatActivity(), View.OnClickListener {
//    var playerOneActive = false
//    private var playerOneScore: TextView? = null
//    private var playerTwoScore: TextView? = null
//    private var playerStatus: TextView? = null
//    private val buttons = arrayOfNulls<Button>(9)
//    private var reset: Button? = null
//    private var playagain: Button? = null
//    var gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
//    var winningPositions = arrayOf(
//        intArrayOf(0, 1, 2),
//        intArrayOf(3, 4, 5),
//        intArrayOf(6, 7, 8),
//        intArrayOf(0, 3, 6),
//        intArrayOf(1, 4, 7),
//        intArrayOf(2, 5, 8),
//        intArrayOf(0, 4, 8),
//        intArrayOf(2, 4, 6)
//    )
//    var rounds = 0
//    private var playerOneScoreCount = 0
//    private var playerTwoScoreCount = 0
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        playerOneScore = findViewById<TextView>(R.id.score_Player1)
//        playerTwoScore = findViewById<TextView>(R.id.score_Player2)
//        playerStatus = findViewById<TextView>(R.id.textStatus)
//        reset = findViewById<Button>(R.id.btn_reset)
//        playagain = findViewById<Button>(R.id.btn_play_again)
//        buttons[0] = findViewById<Button>(R.id.btn0)
//        buttons[1] = findViewById<Button>(R.id.btn1)
//        buttons[2] = findViewById<Button>(R.id.btn2)
//        buttons[3] = findViewById<Button>(R.id.btn3)
//        buttons[4] = findViewById<Button>(R.id.btn4)
//        buttons[5] = findViewById<Button>(R.id.btn5)
//        buttons[6] = findViewById<Button>(R.id.btn6)
//        buttons[7] = findViewById<Button>(R.id.btn7)
//        buttons[8] = findViewById<Button>(R.id.btn8)
//        for (i in buttons.indices) {
//            buttons[i].setOnClickListener(this)
//        }
//        playerOneScoreCount = 0
//        playerTwoScoreCount = 0
//        playerOneActive = true
//        rounds = 0
//    }
//
//    override fun onClick(view: View) {
//        if ((view as Button).text.toString() != "") {
//            return
//        } else if (checkWinner()) {
//            return
//        }
//        val buttonID = view.getResources().getResourceEntryName(view.getId())
//        val gameStatePointer = buttonID.substring(buttonID.length - 1, buttonID.length).toInt()
//        if (playerOneActive) {
//            view.text = "X"
//            view.setTextColor(Color.parseColor("#ffc34a"))
//            gameState[gameStatePointer] = 0
//        } else {
//            view.text = "O"
//            view.setTextColor(Color.parseColor("#70fc3a"))
//            gameState[gameStatePointer] = 1
//        }
//        rounds++
//        if (checkWinner()) {
//            if (playerOneActive) {
//                playerOneScoreCount++
//                updatePlayerScore()
//                playerStatus!!.text = "Player-1 has won"
//            } else {
//                playerTwoScoreCount++
//                updatePlayerScore()
//                playerStatus!!.text = "Player-2 has won"
//            }
//        } else if (rounds == 9) {
//            playerStatus!!.text = "No Winner"
//        } else {
//            playerOneActive = !playerOneActive
//        }
//        reset!!.setOnClickListener {
//            playAgain()
//            playerOneScoreCount = 0
//            playerTwoScoreCount = 0
//            updatePlayerScore()
//        }
//        playagain!!.setOnClickListener { playAgain() }
//    }
//
//    private fun checkWinner(): Boolean {
//        var winnerResults = false
//        for (winningPositions in winningPositions) {
//            if (gameState[winningPositions[0]] == gameState[winningPositions[1]] && gameState[winningPositions[1]] == gameState[winningPositions[2]] && gameState[winningPositions[0]] != 2) {
//                winnerResults = true
//            }
//        }
//        return winnerResults
//    }
//
//    private fun playAgain() {
//        rounds = 0
//        playerOneActive = true
//        for (i in buttons.indices) {
//            gameState[i] = 2
//            buttons[i]!!.text = ""
//        }
//        playerStatus!!.text = "Status"
//    }
//
//    private fun updatePlayerScore() {
//        playerOneScore!!.text = Integer.toString(playerOneScoreCount)
//        playerTwoScore!!.text = Integer.toString(playerTwoScoreCount)
//    }
//}