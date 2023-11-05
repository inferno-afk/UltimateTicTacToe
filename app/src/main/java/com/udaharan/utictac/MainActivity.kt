package com.udaharan.utictac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val stringX = "X"
    private val stringO = "O"
    private var ttMatrix = MutableList(3) { MutableList(3) { -1 } }

    private var clickCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startGame()

        val restartGameActivity = findViewById<Button>(R.id.btn_restart)
        restartGameActivity.setOnClickListener{
            startGame()
        }
    }

    private fun startGame() {

        clickCount = 0
        ttMatrix = MutableList(3) { MutableList(3) { -1 } }
        val btnList = mutableListOf<Button>()
        btnList.add(findViewById(R.id.btn_1))
        btnList.add(findViewById(R.id.btn_2))
        btnList.add(findViewById(R.id.btn_3))
        btnList.add(findViewById(R.id.btn_4))
        btnList.add(findViewById(R.id.btn_5))
        btnList.add(findViewById(R.id.btn_6))
        btnList.add(findViewById(R.id.btn_7))
        btnList.add(findViewById(R.id.btn_8))
        btnList.add(findViewById(R.id.btn_9))

        btnList.forEach {
            it.text=""
        }

        val clickListener = View.OnClickListener {
            Log.d("BtnClick", "Btn: " + it.id)
            if (btnList.contains(it)) {
                putStringToButtons(btnList[btnList.indexOf(it)], btnList.indexOf(it))
                if (checkForWin()) {
                    Log.d("BtnClick", "Check: ${checkForWin()}")
                    disableAllButtons(btnList)
                }
            }
        }

        btnList.forEach {
            it.setOnClickListener(clickListener)
        }
    }

    private fun putStringToButtons(button: Button, index: Int) {
        clickCount++
        var stringToPut = stringX
        if ((clickCount and 1) == 1) {
            stringToPut = stringX
            ttMatrix[index % 3][index / 3] = 1
        } else {
            stringToPut = stringO
            ttMatrix[index % 3][index / 3] = 0
        }
        button.text = stringToPut
        button.setOnClickListener(null)
    }

    private fun disableAllButtons(btnList: List<Button>) {
        btnList.forEach {
            it.setOnClickListener(null)
        }
    }

    private fun checkForWin(): Boolean {
        var countRightDia0 = 0
        var countRightDia1 = 0

        var countLeftDia0 = 0
        var countLeftDia1 = 0

        for (i in 0..2) {
            var countHori0 = 0
            var countHori1 = 0
            var countVert1 = 0
            var countVert0 = 0
            for (j in 0..2) {
                if (ttMatrix[i][j] == 1) {
                    countHori1++
                    if (i == j) {
                        countRightDia1++
                    }
                    if (i + j == 2) {
                        countLeftDia1++
                    }

                } else if (ttMatrix[i][j] == 0) {
                    countHori0++
                    if (i == j) {
                        countRightDia0++
                    }
                    if (i + j == 2) {
                        countLeftDia0++
                    }
                }

                if (ttMatrix[j][i] == 1) {
                    countVert1++
                } else if (ttMatrix[j][i] == 0) {
                    countVert0++
                }

            }

            if (countHori1 == 3 || countHori0 == 3 ||
                countVert0 == 3 || countVert1 == 3 ||
                countRightDia1 == 3 || countLeftDia1 == 3 ||
                countRightDia0 == 3 || countLeftDia0 == 3) {
                return true
            }
        }

        return false
    }
}