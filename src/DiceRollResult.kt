import kotlin.random.Random

sealed class DiceRollResult

class SuccessfulDiceRollSet(private val sides: Int, rolls: Int, var padding: Int = 0) : DiceRollResult() {
    
    private val values: List<Int> = (1..rolls).map { Random.nextInt(1, sides + 1) }
    
    override fun toString(): String = asListOfStrings().joinToString("\n")
    
    fun asListOfStrings(): List<String> = values.mapIndexed { index, value -> "D$sides roll #${index + 1}:".padStart(padding) + "\t$value" }
    
    fun sumValues(): Int = values.sum()
}

class DiceRollError(private val msg: String) : DiceRollResult() {
    override fun toString(): String = msg
}

fun List<DiceRollResult>.maxStringLength(): Int = this.filterIsInstance<SuccessfulDiceRollSet>()
                                                      .flatMap(SuccessfulDiceRollSet::asListOfStrings)
                                                      .map { it.substringBefore(":").length + 1 }
                                                      .max()
                                                      ?: 0