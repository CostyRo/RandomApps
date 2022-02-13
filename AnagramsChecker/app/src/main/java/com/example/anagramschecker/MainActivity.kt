package com.example.anagramschecker

import android.os.Bundle
import android.view.View
import android.text.Editable
import android.widget.TextView
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity()
{
  private lateinit var first_text_view: TextView
  private lateinit var second_text_view: TextView
  private lateinit var result_text_view: TextView
  // define the text views variables

  override fun onCreate(savedInstanceState: Bundle?){
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    first_text_view=findViewById(R.id.first_text)
    second_text_view=findViewById(R.id.second_text)
    result_text_view=findViewById(R.id.result)
    // initialise the text views variables with the correct text view

    val text_watcher: TextWatcher=object: TextWatcher{
      override fun afterTextChanged(s: Editable?){}

      override fun beforeTextChanged(s: CharSequence?,start: Int,count: Int,after: Int){}

      override fun onTextChanged(s: CharSequence?,start: Int,before: Int,count: Int)=
        "Enter 2 texts!".let{result_text_view.text=it}
    }
    // create an TextWatcher object that changes the text of the result

    first_text_view.addTextChangedListener(text_watcher)
    second_text_view.addTextChangedListener(text_watcher)
    // add the TextWatcher object to text views for anagrams
  }

  private fun sort_text_view(text_view: TextView): String=
    text_view.text.toString().toCharArray().sorted().joinToString("")
  // define a private function that receives a text view
  // and returns the sorted version of its text

  fun check_anagrams(view: View)=when{
    first_text_view.text.toString().isEmpty() || second_text_view.text.toString().isEmpty() -> {}
    sort_text_view(first_text_view)==sort_text_view(second_text_view)
    -> result_text_view.text="Anagrams!"
    else -> result_text_view.text="Not anagrams!"
  }
  // the button's function that
  // does nothing if an text is empty
  // set the result to "Anagrams!" if the sorted version of text are equals
  // and otherwise set the result to "Not anagrams!"
}