# Kotlin Dice Roller
Just a basic D&D dice roller

###Features:
- Roll arbitrary numbers of arbitrary-sided dice
- Can also sum up all rolls

###To compile:
`kotlinc src/diceroller.kt -include-runtime diceroller.jar`

###To run:
`java -jar diceroller.jar`

###Instructions:
- Dice inputs take the form of \<number of rolls (optional)\>(d or D)\<sides\>
- Example: 2d20, d10, 60D33
- Can use the `roll` or `sum` commands, but typing in dice with no command will just roll them