package com.example.mathquiz

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import android.widget.SeekBar.OnSeekBarChangeListener

class MainActivity : AppCompatActivity(){
  private var number1: Int=0
  private var number2: Int=0
  private var points_value: Int=0
  // initialise the numbers and the points to 0

  private lateinit var timer: TextView
  private lateinit var result: TextView
  private lateinit var points: TextView
  private lateinit var equation: TextView
  private lateinit var difficulty: TextView
  // define the text views

  private lateinit var button: Button
  // define the button

  private lateinit var difficulty_value: SeekBar
  // define the seek bar

  private lateinit var downtown_seconds: CountDownTimer
  // define the CountDownTimer

  override fun onCreate(savedInstanceState: Bundle?){
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    timer=findViewById(R.id.timer)
    result=findViewById(R.id.result)
    points=findViewById(R.id.points)
    equation=findViewById(R.id.equation)
    difficulty=findViewById(R.id.difficulty)
    // initialise the text views

    button=findViewById(R.id.start_solve)
    // initialise the text views

    difficulty_value=findViewById(R.id.difficulty_value)
    difficulty_value.setOnSeekBarChangeListener(object: OnSeekBarChangeListener{
      override fun onProgressChanged(seekBar: SeekBar,progress: Int,fromUser: Boolean){
        if(button.text=="Start"){
          when(progress){
            0 -> {
              difficulty.text="Difficulty: Easy"
              timer.text="00 : 30"
            }
            1 -> {
              difficulty.text="Difficulty: Medium"
              timer.text="00 : 20"
            }
            2 -> {
              difficulty.text="Difficulty: Hard"
              timer.text="00 : 10"
            }
          }
        }
      }

      override fun onStartTrackingTouch(seekBar: SeekBar){}
      override fun onStopTrackingTouch(seekBar: SeekBar){}
    })
    // initialise the difficulty
    // set the difficulty when progress change
    // if the game isn't started yet
  }

  private fun correct_time(time: Long): String=if(time<10) "0$time" else time.toString()
  // private function that repairs the time

  private fun restart(){
    button.text="Start"
    equation.text="Equation:\n"
    timer.text=when(difficulty.text){
      "Difficulty: Easy" -> "00 : 30"
      "Difficulty: Medium" -> "00 : 20"
      "Difficulty: Hard" -> "00 : 10"
      else -> ""
    }
  }
  // private function that resets everything for a new game

  private fun show(message: String)=Toast.makeText(applicationContext,
    message,Toast.LENGTH_SHORT).show()
  // private function that displays a given message on the screen

  private fun generate_numbers()=when(difficulty.text){
      "Difficulty: Easy" -> {
        number1=(1..10).random()
        number2=(1..10).random()
      }
      "Difficulty: Medium" -> {
        number1=(6..15).random()
        number2=(6..15).random()
      }
      "Difficulty: Hard" -> {
        number1=(11..20).random()
        number2=(11..20).random()
      }
      else -> {
        number1=0
        number2=0
      }
    }
  // private function that generates the 2 numbers
  // depending on the difficulty

  fun start_solve(view: View)=if(button.text=="Start"){
    generate_numbers()
    // generate the numbers

    button.text="Solve"
    equation.text="${equation.text}${number1}*${number2}=???"
    // set the texts

    downtown_seconds=object: CountDownTimer(
      timer.text.toString().takeLast(2).toInt()*1000.toLong(),
      1000){
      override fun onTick(millisUntilFinished: Long)=
        "00 : ${correct_time(millisUntilFinished/1000)}".let{timer.text=it}

      override fun onFinish(){
        show("Time over!")

        restart()
        points_value-=30
        points.text="Points: $points_value"
      }
    }.start()
    // initialise the downtown timer with a CountDownTimer objects
    // that have the time of last 2 characters
    // of the timer's text converted to int multiplied with 1000
    // and the tick of 1 second
    // at every tick update the timer' text
    // and when is finished
    // displays a message that says that time is over
    // restarts everything
    // subtract 30 points and update its
    // and starts it
  }
  else{
    downtown_seconds.cancel()
    // cancel the timer

    if(result.text.toString().toIntOrNull()==number1*number2){
      show("Correct!")
      // show that the result is correct

      points_value+=timer.text.toString().takeLast(2).toInt()*when(difficulty.text){
        "Difficulty: Easy" -> 1
        "Difficulty: Medium" -> 5
        "Difficulty: Hard" -> 10
        else -> 0
      }
      // add the points depending on the remaining seconds
      // and multiply value for current difficulty
    }
    else{
      show("Wrong!")
      // show that the result is correct

      points_value-=(timer.text.toString().takeLast(2).toInt()*when(difficulty.text){
        "Difficulty: Easy" -> 1.0
        "Difficulty: Medium" -> 1.5
        "Difficulty: Hard" -> 3.0
        else -> 0.0
      }).toInt()
      // subtract the points depending on the remaining seconds
      // and multiply value for current difficulty
    }

    result.text=""
    // reset the result

    points.text="Points: $points_value"
    // update the points

    restart()
    // and reset everything
  }
  // button's function
}