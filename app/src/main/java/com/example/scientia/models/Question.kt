package com.example.scientia.models

//Data Keyword = Blueprint
data class Question (
    val id: Int,
    val question: String,
    val answers: List<String>
    )