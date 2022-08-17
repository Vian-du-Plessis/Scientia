package com.example.scientia.models

object Constants {

    val USER_NAME: String = "username"

    val MATH_SCORE: String = "mathScore"
    val CHEM_SCORE: String = "chemScore"
    val PHYS_SCORE: String = "physScore"

    val MATH_COMPLETED: String = "mathCompleted"
    val CHEM_COMPLETED: String = "chemCompleted"
    val PHYS_COMPLETED: String = "physCompleted"

    // Return All Questions
    fun getMathQuestions() : ArrayList<Questions> {
        // Creating Data
        var allQuestions = ArrayList<Questions>();

        // Question One
        val Q1 = Questions(
            1,
            "If x is a positive integer in the equation 12x = q, then q must be",
            listOf("a positive even integer"),
            "Multiple",
            listOf("a positive even integer", "a negative even integer", "zero", "a positive odd integer", "a negative odd integer")
        )

        val Q2 = Questions(
            2,
            "What is the approximate value of the square root of 1596?",
            listOf("40"),
            "Multiple",
            listOf("10", "20", "30", "40", "50")
        )

        val Q3 = Questions(
            3,
            "What is the only even prime number?",
            listOf("Two", "2"),
            "Input"
        )

//        val Q4 = Questions(
//            4,
//            "What is the smallest perfect number?",
//            listOf("Six", "6"),
//            "Input"
//        )
//
//        val Q5 = Questions(
//            5,
//            "Is Pi a rational or irrational number?",
//            listOf("Irrational"),
//            "Multiple",
//            listOf("Irrational", "Rational")
//        )
//
//        val Q6 = Questions(
//            6,
//            "Which number is considered a 'magic number'?",
//            listOf("Nine", "9"),
//            "Input"
//        )
//
//        val Q7 = Questions(
//            7,
//            "What is the most popular lucky number?",
//            listOf("Seven", "7"),
//            "Input"
//        )
//
//        val Q8 = Questions(
//            8,
//            "What is the most popular two-digit number?",
//            listOf("Thirteen", "13"),
//            "Input"
//        )
//
//        val Q9 = Questions(
//            9,
//            "Where is four considered an unlucky number?",
//            listOf("Asia"),
//            "Input"
//        )

        // Add Questions to the array
        allQuestions.add(Q1)
        allQuestions.add(Q2)
        allQuestions.add(Q3)
//        allQuestions.add(Q4)
//        allQuestions.add(Q5)
//        allQuestions.add(Q6)
//        allQuestions.add(Q7)
//        allQuestions.add(Q8)
//        allQuestions.add(Q9)

        // Return Data
        return allQuestions
    }

    fun getChemQuestions() : ArrayList<Questions> {
        // Create data
        var allQuestions = ArrayList<Questions>()

        // Questions
        val Q1 = Questions(
            1,
            "Situated at the beginning of the table, which is the lightest element on the periodic table?",
            listOf("Hydrogen"),
            "Multiple",
            listOf("Hydrogen", "Beryllium", "Boron", "Sodium")
        )

        val Q2 = Questions(
            2,
            "What is the atomic number of Nitrogen?",
            listOf("Seven", "7"),
            "Multiple",
            listOf("7", "9", "20", "21")
        )

        val Q3 = Questions(
            3,
            "What is the name of the first Noble Gas?",
            listOf("Helium"),
            "Input"
        )

        val Q4 = Questions(
            4,
            "The Periodic Table is organized in the order of increasing what?",
            listOf("Atomic number"),
            "Input"
        )

        val Q5 = Questions(
            5,
            "Which is the heaviest naturally occurring element?",
            listOf("Uranium"),
            "Input"
        )

        val Q6 = Questions(
            6,
            "Which element is found abundantly in milk?",
            listOf("Calcium"),
            "Multiple",
            listOf("Calcium", "Copper", "Hydrogen", "Helium")
        )

        // Add Questions to the array
        allQuestions.add(Q1)
        allQuestions.add(Q2)
        allQuestions.add(Q3)
        allQuestions.add(Q4)
        allQuestions.add(Q5)
        allQuestions.add(Q6)

        // Return Data
        return allQuestions
    }

    fun getPhysQuestions() : ArrayList<Questions> {
        // Create data
        var allQuestions = ArrayList<Questions>()

        // Questions
        val Q1 = Questions(
            1,
            "Which Renaissance scientist is credited with the discovery of the pendulum?",
            listOf(" Galileo Galilei"),
            "Input"
        )

        val Q2 = Questions(
            2,
            "What name is given to the science of sound?",
            listOf("Acoustics"),
            "Input"
        )

        val Q3 = Questions(
            3,
            "Name the SI unit of mass?",
            listOf("Kilogram"),
            "Input"
        )

        val Q4 = Questions(
            4,
            "Name the instrument used to measure gas pressure?",
            listOf("Manometer"),
            "Input"
        )

        val Q5 = Questions(
            5,
            "Can you name the tube used to produce X-rays?",
            listOf("Coolidge", "Coolidge tube"),
            "Input"
        )

        val Q6 = Questions(
            6,
            "What is a word used to describe a solid whose arrangement of atoms and molecules has no definite pattern?",
            listOf("Amorphous"),
            "Input"
        )

        // Add Questions to the array
        allQuestions.add(Q1)
        allQuestions.add(Q2)
        allQuestions.add(Q3)
        allQuestions.add(Q4)
        allQuestions.add(Q5)
        allQuestions.add(Q6)

        // Return Data
        return allQuestions
    }
}