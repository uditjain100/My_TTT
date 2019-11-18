package udit.programmer.co.myttt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var player1turn: Boolean = true

    var rc: Int = 0

    var p1c: Int = 0
    var p2c: Int = 0

    override fun onClick(view: View) {

        when (view.id) {

            R.id.bt00 -> {
                gamelogic(btns, 0, 0)
            }

            R.id.bt01 -> {
                gamelogic(btns, 0, 1)
            }

            R.id.bt02 -> {
                gamelogic(btns, 0, 2)
            }

            R.id.bt10 -> {
                gamelogic(btns, 1, 0)
            }

            R.id.bt11 -> {
                gamelogic(btns, 1, 1)
            }

            R.id.bt12 -> {
                gamelogic(btns, 1, 2)
            }

            R.id.bt20 -> {
                gamelogic(btns, 2, 0)
            }

            R.id.bt21 -> {
                gamelogic(btns, 2, 1)
            }

            R.id.bt22 -> {
                gamelogic(btns, 2, 2)
            }
        }

    }

    lateinit var btns: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btns = arrayOf(
            arrayOf(bt00, bt01, bt02),
            arrayOf(bt10, bt11, bt12),
            arrayOf(bt20, bt21, bt22)
        )

        for (i in 0..2) {
            for (j in 0..2) {
                btns[i][j].setOnClickListener(this)
            }
        }
    }

    fun gamelogic(btns: Array<Array<Button>>, i: Int, j: Int) {



        if (btns[i][j].text != "") {
            return
        }

        if (rc % 2 == 0) {
            turn.text = "Player2turn [O]"
        } else {
            turn.text = "Player1turn [X]"
        }

        if (player1turn) {
            btns[i][j].text = "X"
        } else {
            btns[i][j].text = "O"
        }

        rc++

        if (checkwin(btns)) {

            if (player1turn) {

                Toast.makeText(this, "Player 1", Toast.LENGTH_LONG).show()
                p1c++
                pc1.text = p1c.toString()
                reset()

            } else if (!player1turn) {

                Toast.makeText(this, "Player 2", Toast.LENGTH_LONG).show()
                p2c++
                pc2.text = p2c.toString()
                reset()
            }

        } else if (rc == 9) {

            Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show()
            reset()

        } else {
            player1turn = !player1turn
        }

    }

    fun checkwin(btns: Array<Array<Button>>): Boolean {

        for (i in 0..2) {
            if (btns[i][0].text == btns[i][1].text && btns[i][1].text == btns[i][2].text && btns[i][0].text != "") {
                return true
            }
        }

        for (i in 0..2) {
            if (btns[0][i].text == btns[1][i].text && btns[1][i].text == btns[2][i].text && btns[0][i].text != "") {
                return true
            }
        }

        if (btns[0][0].text == btns[1][1].text && btns[1][1].text == btns[2][2].text && btns[0][0].text != "") {
            return true
        }

        if (btns[0][2].text == btns[1][1].text && btns[1][1].text == btns[2][0].text && btns[0][2].text != "") {
            return true
        }
        return false
    }

    fun reset() {

        for (i in 0..2) {
            for (j in 0..2) {
                btns[i][j].text = ""
            }
        }

        if (p1c > p2c) {
            win.text = "Player1"
        } else if (p1c < p2c) {
            win.text = "Player2"
        } else {
            win.text = "Draw"
        }

        rc = 0
        player1turn = true
        turn.text = ""
    }

}
