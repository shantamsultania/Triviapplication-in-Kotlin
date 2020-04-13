package com.example.triviapp.models

class questions
{
    public var ans : String = "Default"
    public var isanstrue : Boolean = false
    override fun toString(): String {
        return "questions(ans='$ans', isanstrue=$isanstrue)"
    }
}