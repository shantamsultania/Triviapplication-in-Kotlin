package com.example.triviapp.controller
import android.app.Application
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class AppController constructor(): Application() {
        companion object {
            @Volatile
            private var INSTANCE: AppController? = null
            fun getInstance() =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: AppController().also {
                        INSTANCE = it
                    }
                }
        }

        override fun onCreate() {
            super.onCreate()
            INSTANCE = this
        }
        val requestQueue: RequestQueue by lazy {
            Volley.newRequestQueue(this.applicationContext)
        }
        fun <T> addToRequestQueue(req: Request<T>) {

            requestQueue.add(req)
        }
}