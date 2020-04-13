package com.example.triviapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.triviapp.data.questionbank
import com.example.triviapp.models.questions
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess
import com.example.triviapp.data.answerlisysync as answerlisysync1

class MainActivity : AppCompatActivity() {

    private var curentindex : Int = 0
    private lateinit var questionlist : List<questions>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        questionlist = questionbank().getquestions(object : answerlisysync1 {
            override fun processfinished(questionarraylist: ArrayList<questions>) {
                question_textview.setText(questionarraylist.get(curentindex).ans)

                counter.setText(curentindex.toString() + "/" + questionarraylist.size)



                //    Log.d("main","response"+questionarraylist)
            }
        })!!


        next.setOnClickListener {

            if (questionlist != null) {
                curentindex = (curentindex +1) % questionlist.size
                updatequestion()
            }
        }

        previous.setOnClickListener{

            if (questionlist != null) {
                curentindex = (curentindex - 1) % questionlist.size
                updatequestion()
            }
        }

        truebtn.setOnClickListener {
            checkanswer(true)
        }
        falsebtn.setOnClickListener {
checkanswer(false)
        }


    }

    private fun checkanswer(choice: Boolean) {

        var ansisture : Boolean = questionlist.get(curentindex).isanstrue

        if (choice == ansisture)
        {
            Toast.makeText(this,"congratulation right ans",Toast.LENGTH_SHORT).show()
            curentindex = (curentindex +1) % questionlist.size
            updatequestion()
        }
        else
        {
            Toast.makeText(this,"Wrong ans",Toast.LENGTH_SHORT).show()

        }

    }

    private fun updatequestion() {
        var question :String = questionlist.get(curentindex).ans
        question_textview.setText(question)
        counter.setText(curentindex.toString() + " / " + questionlist.size)

    }
}



