package com.example.timer

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import android.widget.SeekBar.OnSeekBarChangeListener

class MainActivity : AppCompatActivity(){
  private var minutes: Int=0
  private var seconds: Int=0
  // define the variables for minutes and seconds and initialise them with 0

  private var timer_on: Boolean=false
  // define a variable that tracks if timer is on

  private lateinit var timer: TextView
  // define the text view of timer

  private lateinit var minutes_bar: SeekBar
  private lateinit var seconds_bar: SeekBar
  // define the seek bars that change the time

  private lateinit var restart_button: Button
  // define the restart button

  private lateinit var downtown_seconds: CountDownTimer
  // define a countdown timer

  override fun onCreate(savedInstanceState: Bundle?){
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    timer=findViewById(R.id.timer)
    restart_button=findViewById(R.id.restart)
    // initialise the timer's text view and the restart button

    minutes_bar=findViewById(R.id.minutes)
    minutes_bar.setOnSeekBarChangeListener(object: OnSeekBarChangeListener{
      override fun onStopTrackingTouch(seekBar: SeekBar){}
      override fun onStartTrackingTouch(seekBar: SeekBar){}
      override fun onProgressChanged(seekBar: SeekBar,progress: Int,fromUser: Boolean){
          minutes=progress
          update_time()
      }
    })
    // initialise seek bar for minutes
    // override progress changed and update the minutes

    seconds_bar=findViewById(R.id.seconds)
    seconds_bar.setOnSeekBarChangeListener(object: OnSeekBarChangeListener{
       override fun onStopTrackingTouch(seekBar: SeekBar){}
       override fun onStartTrackingTouch(seekBar: SeekBar){}
       override fun onProgressChanged(seekBar: SeekBar,progress: Int,fromUser: Boolean){
           seconds=progress
           update_time()
       }
    })
    // initialise seek bar for seconds
    // override progress changed and update the seconds
  }

  private fun correct_time(time: Int): String=if(time<10) "0$time" else time.toString()
  // private function that repairs the time

  private fun update_time()=
    "${correct_time(minutes)} : ${correct_time(seconds)}".let{timer.text=it}
  // private function that changes the text of timer to the remaining time

  fun set_timer(view: View){
    if(minutes+seconds!=0 && !timer_on){
      timer_on=true
      // set the timer on

      false.let{
        minutes_bar.isEnabled=it
        seconds_bar.isEnabled=it
      }
      // block the seek bars

      restart_button.text="   Stop   "
      // set the restart button's text to stop

      downtown_seconds=object: CountDownTimer((minutes*60000+seconds*1000).toLong(),
        1000){
        override fun onTick(millisUntilFinished: Long){
          if(seconds!=0) seconds-=1
          else{
            seconds=59
            minutes-=1
          }

          update_time()
        }
        override fun onFinish()=false.let{timer_on=it}
      }.start()
      // initialise downtown timer to an CountDownTimer object
      // with the number of time equals the time in milliseconds
      // and the tick at 1 second
      // at every second subtract a second from time
      // and update the time
      // when downtown timer is finished set timer to off
      // and start the CountDownTimer object
    }
  }
  // start the button's function that
  // start the downtown timer
  // if it isn't started yet

  private fun reset_time()=0.let{
    seconds_bar.progress=it
    minutes_bar.progress=it
    minutes=it
    seconds=it
  }
  // private function that resets the time

  fun restart_timer(view: View)=if(timer_on){
    timer_on=false
    // set the timer to off

    restart_button.text="Restart"
    // set the button to restart

    true.let{
      minutes_bar.isEnabled=it
      seconds_bar.isEnabled=it
    }
    // enable the seek bars

    downtown_seconds.cancel()
    // cancel the downtown timer
  }
  else{
    restart_button.text="   Stop   "
    // set the button to stop

    reset_time()
    // reset the time to 0

    update_time()
    // update the time
  }
  // restart the button's function that
  // stop the timer if it is on
  // otherwise restart everything
}