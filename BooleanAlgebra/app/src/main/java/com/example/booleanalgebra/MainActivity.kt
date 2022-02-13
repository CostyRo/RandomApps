package com.example.booleanalgebra

import android.os.Bundle
import android.view.View
import android.widget.Spinner
import android.widget.CheckBox
import android.widget.TextView
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity

fun Boolean.toInt()=if(this) 1 else 0
// extension to bool to convert to int

class MainActivity : AppCompatActivity(){
  private var first_value: Int=0
  private var second_value: Int=0
  // initialise the values with False(0)

  private var operation: String="NOT"
  // initialise the operation with NOT

  private lateinit var result: TextView
  // define the result text view

  private lateinit var dropdown: Spinner
  // define the dropdown menu
  
  private lateinit var first_value_check: CheckBox
  private lateinit var second_value_check: CheckBox
  // define the checkboxes

  override fun onCreate(savedInstanceState: Bundle?){
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val items: Array<String> =
      arrayOf("NOT","AND","OR","IMPLY","XOR","EQUALS")
    // set the array with actions

    result=findViewById(R.id.result)
    // initialise the result text view

    dropdown=findViewById(R.id.dropdown)
    with(dropdown){
      onItemSelectedListener=object: AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>,
          view: View?,position: Int,id: Long){
          operation=items[position]
          reset_result()
        }

        override fun onNothingSelected(parent: AdapterView<*>?){}
      }
      adapter=ArrayAdapter<String>(applicationContext,
        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
        items)
    }
    // initialise the dropdown menu
    // set the adapter to a simple array adapter with given actions
    // override the item selected method
    // to selects the correct element from the actions array
    // and reset the result

    first_value_check=findViewById(R.id.first_value_check)
    first_value_check.setOnCheckedChangeListener{
      _,isChecked ->
      first_value=isChecked.toInt()
      reset_result()
    }
    // initialise the first checkbox
    // catch their value into a variable
    // and reset the result

    second_value_check=findViewById(R.id.second_value_check)
    second_value_check.setOnCheckedChangeListener{
      _,isChecked ->
      second_value=isChecked.toInt()
      reset_result()
    }
    // initialise the first checkbox
    // catch their value into a variable
    // and reset the result
  }

  private fun reset_result()="Result: ".let{result.text=it}
  // private function that reset the result

  private fun compose_result(value: Int): String=
    "$value (${if(value==1) "TRUE" else "FALSE"})"
  // private function that compose the result

  fun solve(view: View)=("Result: "+
    when(operation){
      "NOT" -> compose_result(1-first_value)
      "AND" -> compose_result(first_value and second_value)
      "OR" -> compose_result(first_value or second_value)
      "IMPLY" -> compose_result((1-first_value) or second_value)
      "XOR" -> compose_result(first_value xor second_value)
      "EQUALS" -> compose_result((first_value==second_value).toInt())
      else -> ""
    }).let{result.text=it}
  // the button's function that
  // set the result to the correct result
  // depending on the selected action
}