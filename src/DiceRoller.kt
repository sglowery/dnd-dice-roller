fun main() {
    loop@ while (true) {
        print("> ")
        val userInput = readLine()?.trim()?.toLowerCase()?.split(" ")?.filter { it.isNotEmpty() }
        when {
            userInput.isNullOrEmpty()  -> continue@loop
            userInput[0] == "roll"     -> println("\n${rollCommand(userInput.drop(1))}\n")
            userInput[0] == "sum"      -> println("\n${sumCommand(userInput.drop(1))}\n")
            userInput[0].contains('d') -> println("\n${rollCommand(userInput)}\n")
        }
    }
}

fun rollCommand(userInput: List<String>): String = userInput.map(::parseRoll).run(::formatRollStrings).joinToString("\n")

fun sumCommand(userInput: List<String>): String {
    var total = 0
    return userInput.map(::parseRoll)
        .also {
            it.forEach { rollResult -> total += if (rollResult is SuccessfulDiceRollSet) rollResult.sumValues() else 0 }
        }.run(::formatRollStrings).joinToString("\n").plus("\nTotal sum: $total")
}

fun parseRoll(userInput: String): DiceRollResult {
    try {
        userInput.split("d").filter(String::isNotEmpty).map(String::toInt).let {
            val numRolls = when (it.size) {
                1    -> 1
                else -> it.first()
            }
            val sides = it.last()
            if (sides * numRolls <= 0) throw Exception()
            return SuccessfulDiceRollSet(sides, numRolls)
        }
    } catch (exception: Exception) {
        return DiceRollError("Malformed dice roll: $userInput")
    }
}

fun formatRollStrings(rolls: List<DiceRollResult>): List<DiceRollResult> {
    val maxStringLength = rolls.maxStringLength()
    rolls.filterIsInstance<SuccessfulDiceRollSet>().forEach { it.padding = maxStringLength }
    return rolls
}