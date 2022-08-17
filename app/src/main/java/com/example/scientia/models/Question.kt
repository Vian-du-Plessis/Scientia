package com.example.scientia.models

//Data Keyword = Blueprint
data class Questions (
    val id: Int,
    val question: String,
    val answers: List<String>,
    val type: String,
    val options: List<String>? = listOf("")
    )

//data class ChemQuestion (
//    val id: Int,
//    val question: String,
//    val answers: List<String>
//        )
//
//data class PhysQuestion (
//    val id: Int,
//    val question: String,
//    val answers: List<String>
//)