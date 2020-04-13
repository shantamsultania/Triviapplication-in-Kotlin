package com.example.triviapp.data

import android.os.Parcel
import android.os.Parcelable
import com.example.triviapp.models.questions
import java.lang.reflect.Constructor

public interface answerlisysync {

    public fun processfinished(questionarraylist : ArrayList<questions>)

}

