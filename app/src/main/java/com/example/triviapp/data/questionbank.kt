package com.example.triviapp.data

import android.bluetooth.BluetoothGattCallback
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.example.triviapp.controller.AppController
import com.example.triviapp.models.questions
import org.json.JSONException

class questionbank {
    var url= "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json"
    var questionarraylist : ArrayList<questions> = ArrayList<questions>()

    fun getquestions(callback : answerlisysync ) : List<questions>? {

        var jsonarrayrequest = JsonArrayRequest(Request.Method.GET, url, null, Response.Listener
        { response ->
            try {
                val length : Int = response.length()
                var i  = 0
                while ( i < length)
                {
                    var que : questions  = questions()
                    que.ans = response.getJSONArray(i).get(0).toString()
                    que.isanstrue = response.getJSONArray(i).getBoolean(1)
                    questionarraylist.add(que)
                   // Log.d("json","response is " + questionarraylist.get(2))
                    i++
                }
            } catch (e : JSONException) {
                e.printStackTrace() }

            if (null != callback)
            {
                callback.processfinished(questionarraylist)
            }


        }, Response.ErrorListener
            {
                Log.d("json", "Error")
            })
        AppController.getInstance().addToRequestQueue(jsonarrayrequest)
        return questionarraylist
    }


}