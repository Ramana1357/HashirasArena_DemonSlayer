fun main() {
    while (true) {
        println("\t\t\tWelcome to Hashiras Arena!\n")
        println("\t\tType 'exit' to quit the game.\n")
        println("\t\tPress Enter to continue...\n")
        val input = readLine()
        if (input.equals("exit", ignoreCase = true)) {
            println("Exiting the game. Goodbye!")
            break
        }

        println("Welcome to Hashiras Arena!\n")
        println("Here are the known Hashiras:")
        for (hashira in Hashiras) {
            println("${hashira.name} - Type: ${hashira.type}, Atta ck: ${hashira.Attack}, Defense: ${hashira.Defense}, Speed: ${hashira.Speed}")
        }

        println("\n Select a Hashira to create a custom one or use an existing one.")
        println("\nEnter Hashira name to select or type 'custom' to create a new Hashira:")
        val hashiraInput = readLine() ?: "custom"

        val selectedHashira: Demonslayer = if(hashiraInput.lowercase() == "custom") {
            println("Enter the name of your custom Hashira:")
            val customName = readLine() ?: "Custom Hashira"
            println("Enter the type of your custom Hashira : ")
            for(bt in BreathingType.entries){
                println("${bt}")
            }
            println()
            val customType = BreathingType.valueOf(readLine()?.uppercase() ?: "WATER")
            println("Enter the Attack value (0-5):")
            var customAttack = readLine()?.toFloatOrNull() ?: 0f
            if (customAttack < 0 || customAttack > 5) {
                println("Invalid Attack value. Setting to default 0.")
                customAttack = 0f
            }
            println("Enter the Defense value (0-5):")
            var customDefense = readLine()?.toFloatOrNull() ?: 0f
            if (customDefense < 0 || customDefense > 5) {
                println("Invalid Defense value. Setting to default 0.")
                customDefense = 0f
            }
            println("Enter the Speed value (0-5):")
            var customSpeed = readLine()?.toFloatOrNull() ?: 0f
            if (customSpeed < 0 || customSpeed > 5) {
                println("Invalid Speed value. Setting to default 0.")
                customSpeed = 0f
            }
            CustomHashira(customName, customType, customAttack, customDefense, customSpeed)
        } else {
            val existing = Hashiras.find { it.name.equals(hashiraInput, ignoreCase = true) }
            if (existing == null) {
                println("Hashira not found.")
                continue
            }
            existing
        }

        println("Enter your Opponent Hashira name or type custom to create custom hashira opponent:")
        val opponentInput = readLine() ?: "custom"
        val selectedOpponentHashira: Demonslayer = if(opponentInput.lowercase() == "custom") {
            println("Enter the name of your custom opponent Hashira:")
            val opponentName = readLine() ?: "Custom Opponent Hashira"

            println("Enter the type of your custom opponent Hashira :")
            for(bt1 in BreathingType.entries){
                println(bt1)
            }
            println()

            val opponentType = BreathingType.valueOf(readLine()?.uppercase() ?: "FLAME")
            println("Enter the Attack value (0-5):")
            var opponentAttack = readLine()?.toFloatOrNull() ?: 0f
            if (opponentAttack < 0 || opponentAttack > 5) {
                println("Invalid Attack value. Setting to default 0.")
                opponentAttack = 0f
            }
            println("Enter the Defense value (0-5):")
            var opponentDefense = readLine()?.toFloatOrNull() ?: 0f
            if (opponentDefense < 0 || opponentDefense > 5) {
                println("Invalid Defense value. Setting to default 0.")
                opponentDefense = 0f
            }
            println("Enter the Speed value (0-5):")
            var opponentSpeed = readLine()?.toFloatOrNull() ?: 0f
            if (opponentSpeed < 0 || opponentSpeed > 5) {
                println("Invalid Speed value. Setting to default 0.")
                opponentSpeed = 0f
            }
            CustomHashira(opponentName, opponentType, opponentAttack, opponentDefense, opponentSpeed)
        } else {
            val existing = Hashiras.find { it.name.equals(opponentInput, ignoreCase = true) }
            if (existing == null) {
                println("Opponent Hashira not found.")
                continue
            }
            existing
        }

        println("The battle begins!")
        battle(selectedHashira, selectedOpponentHashira)
        println("Do you want to play again? (yes/no)")
        val playAgain = readLine()?.trim()?.lowercase() ?: "no"
        if (playAgain != "yes") {
            println("Thank you for playing! Goodbye!")
            break
        }
    }
}
