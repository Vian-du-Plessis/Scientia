package com.example.scientia.models

//Data Keyword = Blueprint
data class MathQuestion (
    val id: Int,
    val question: String,
    val answers: List<String>
    )