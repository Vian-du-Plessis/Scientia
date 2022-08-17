package com.example.scientia.models

object Constants {

    val USER_NAME: String = "username"

    // Return All Questions
    fun getMathQuestions() : ArrayList<Questions> {
        // Creating Data
        var allQuestions = ArrayList<Questions>();

        // Question One
        val Q1 = Questions(
            1,
            "What is the only number that has the same number of letters as it's meaning?",
            listOf("Four", "4"),
            "Multiple",
            listOf("Four", "hey", "sexy")
        )

        val Q2 = Questions(
            2,
            "What number doesn't have its own Roman numeral?",
            listOf("Zero", "0"),
            "Input"
        )

        val Q3 = Questions(
            3,
            "What is the only even prime number?",
            listOf("Two", "2"),
            "Input"
        )

        val Q4 = Questions(
            4,
            "What is the smallest perfect number?",
            listOf("Six", "6"),
            "Input"
        )

        val Q5 = Questions(
            5,
            "Is Pi a rational or irrational number?",
            listOf("Irrational"),
            "Input"
        )

        val Q6 = Questions(
            6,
            "Which number is considered a 'magic number'?",
            listOf("Nine", "9"),
            "Input"
        )

        val Q7 = Questions(
            7,
            "What is the most popular lucky number?",
            listOf("Seven", "7"),
            "Input"
        )

        val Q8 = Questions(
            8,
            "What is the most popular two-digit number?",
            listOf("Thirteen", "13"),
            "Input"
        )

        val Q9 = Questions(
            9,
            "Where is four considered an unlucky number?",
            listOf("Asia"),
            "Input"
        )

        // Add Questions to the array
        allQuestions.add(Q1)
        allQuestions.add(Q2)
        allQuestions.add(Q3)
        allQuestions.add(Q4)
        allQuestions.add(Q5)
        allQuestions.add(Q6)
        allQuestions.add(Q7)
        allQuestions.add(Q8)
        allQuestions.add(Q9)

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
            "Input"
        )

        val Q2 = Questions(
            2,
            "What is the atomic number of Nitrogen?",
            listOf("Seven", "7"),
            "Input"
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