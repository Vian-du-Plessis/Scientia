package com.example.scientia.models

object Constants {

    // Return All Questions
    fun getAllQuestions() : ArrayList<Question> {
        //Creating Data
        var allQuestions = ArrayList<Question>();

        //Question One
        val Q1 = Question(
            1,
            "What is the only number that has the same number of letters as it's meaning?",
            listOf("Four", "4")
        )

        val Q2 = Question(
            2,
            "What number doesn't have its own Roman numeral?",
            listOf("Zero", "0")
        )

        val Q3 = Question(
            3,
            "What is the only even prime number?",
            listOf("Two", "2")
        )

        val Q4 = Question(
            4,
            "What is the smallest perfect number?",
            listOf("Six", "6")
        )

        val Q5 = Question(
            5,
            "Is Pi a rational or irrational number?",
            listOf("Irrational")
        )

        //Add Questions to the array
        allQuestions.add(Q1);
        allQuestions.add(Q2);
        allQuestions.add(Q3);
        allQuestions.add(Q4);
        allQuestions.add(Q5);

        //Return Data
        return allQuestions;
    }
}