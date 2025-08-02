val Attackforms_lowerlimit_list: List<Float> = listOf(
    0f, 31f, 61f, 91f, 200f
)

val Attackforms_upperlimit_list: List<Float> = listOf(
    30f, 60f, 90f, 150f, 300f
)

enum class BreathingType {
    FLAME, WATER, WIND, STONE, THUNDER, MOON, SUN,LOVE,SERPENT, BEAST, SOUND, FLOWER, INSECT,MIST
}

interface Hashira {
    val name: String
    val type: BreathingType
    val Attack: Float
    val Defense: Float
    val Speed: Float
}

data class Demonslayer(
    override val name: String,
    override val type: BreathingType,
    override val Attack: Float,
    override val Defense: Float,
    override val Speed: Float
): Hashira {
    val Attackforms: MutableMap<String, Pair<Float, Int>> = mutableMapOf(
        "firstform" to Pair(((Attackforms_upperlimit_list[0] - Attackforms_lowerlimit_list[0]) / 5) * Attack + Attackforms_lowerlimit_list[0], 5),
        "secondform" to Pair(((Attackforms_upperlimit_list[1] - Attackforms_lowerlimit_list[1]) / 5) * Attack + Attackforms_lowerlimit_list[1], 4),
        "thirdform" to Pair(((Attackforms_upperlimit_list[2] - Attackforms_lowerlimit_list[2]) / 5) * Attack + Attackforms_lowerlimit_list[2], 3),
        "fourthform" to Pair(((Attackforms_upperlimit_list[3] - Attackforms_lowerlimit_list[3]) / 5) * Attack + Attackforms_lowerlimit_list[3], 2),
        "finalform" to Pair(((Attackforms_upperlimit_list[4] - Attackforms_lowerlimit_list[4]) / 5) * Attack + Attackforms_lowerlimit_list[4], 1)
    )
}

val Hashiras: List<Demonslayer> = listOf(
    Demonslayer("Giyu Tomioka", BreathingType.WATER, 5f, 4.9f, 4.9f),
    Demonslayer("Shinobu Kocho", BreathingType.INSECT, 4.0f, 4.4f, 5f),
    Demonslayer("Kyojuro Rengoku", BreathingType.FLAME, 5f, 4.9f, 5f),
    Demonslayer("Tengen Uzui", BreathingType.SOUND, 4.7f, 4.7f, 4.7f),
    Demonslayer("Sanemi Shinazugawa", BreathingType.WIND, 5f, 5f, 5f),
    Demonslayer("Gyomei Himejima", BreathingType.STONE, 5f, 5f, 5f),
    Demonslayer("Muichiro Tokito", BreathingType.MIST, 4.9f, 4.9f, 4.9f),
    Demonslayer("Obonai Iguro", BreathingType.SERPENT, 4.6f, 4.6f, 4.6f)
)

fun CustomHashira(
    name: String,
    type: BreathingType,
    Attack: Float,
    Defense: Float,
    Speed: Float
): Demonslayer {
    return Demonslayer(name, type, Attack, Defense, Speed)
}

fun battle(player: Demonslayer, opponent: Demonslayer): String {
    var playerHP = player.Defense * 100
    var opponentHP = opponent.Defense * 100

    while (true) {
        println("\n${player.name} vs ${opponent.name}")
        println("${player.name} HP: $playerHP, ${opponent.name} HP: $opponentHP")

        println("\n${player.name}, choose your attack form:")
        player.Attackforms.forEach { (name, info) ->
            println("$name - Attack Power: ${info.first}, Limit: ${info.second}")
        }
        val playerAttackForm = readLine()?.trim()?.lowercase() ?: "thirdform"
        val playerAttack = player.Attackforms[playerAttackForm]
        if (playerAttack == null || playerAttack.second <= 0) {
            if (player.Attackforms.values.all { it.second <= 0 }) {
                println("${player.name} has no attack forms left! ${opponent.name} wins!")
                return "${opponent.name} wins!"
            } else {
                println("${player.name} cannot use $playerAttackForm! Choose another attack form.")
                continue
            }
        }

        val opponentAttackEntry = opponent.Attackforms.entries.shuffled().find { it.value.second > 0 }
        if (opponentAttackEntry == null) {
            println("${opponent.name} has no attack forms left! ${player.name} wins!")
            return "${player.name} wins!"
        }

        player.Attackforms[playerAttackForm] = Pair(playerAttack.first, playerAttack.second - 1)
        opponent.Attackforms[opponentAttackEntry.key] = Pair(opponentAttackEntry.value.first, opponentAttackEntry.value.second - 1)

        if (player.Speed >= opponent.Speed) {
            println("${player.name} attacks first!")
            opponentHP -= playerAttack.first
            println("${player.name} attacks ${opponent.name} with $playerAttackForm for ${playerAttack.first} damage!")
            if (opponentHP <= 0) {
                println("${opponent.name} has been defeated!")
                return "${player.name} wins!"
            }
            playerHP -= opponentAttackEntry.value.first
            println("${opponent.name} attacks ${player.name} with ${opponentAttackEntry.key} for ${opponentAttackEntry.value.first} damage!")
            if (playerHP <= 0) {
                println("${player.name} has been defeated!")
                return "${opponent.name} wins!"
            }
        } else {
            println("${opponent.name} attacks first!")
            playerHP -= opponentAttackEntry.value.first
            println("${opponent.name} attacks ${player.name} with ${opponentAttackEntry.key} for ${opponentAttackEntry.value.first} damage!")
            if (playerHP <= 0) {
                println("${player.name} has been defeated!")
                return "${opponent.name} wins!"
            }
            opponentHP -= playerAttack.first
            println("${player.name} attacks ${opponent.name} with $playerAttackForm for ${playerAttack.first} damage!")
            if (opponentHP <= 0) {
                println("${opponent.name} has been defeated!")
                return "${player.name} wins!"
            }
        }
    }
}    
