import kotlin.math.roundToInt

const val TAVERN_NAME = "Taernyl's Folly"
const val CASK = 5
const val PINTS_ONE_GALLON = 1 / .125
const val GOLDS_ONE_DRAGON_COIN = 1.43

var playerGold = 10
var playerSilver = 10
var playerDragonCoin = 5.0

var remainingQuantityOfDragonBreathInPints = (PINTS_ONE_GALLON * CASK).toInt()

fun main(args: Array<String>) {

    placeOrder("shandy,Dragon's Breath,5.91")
//    placeOrder("elixir,Shirley's Temple,4.12")

    println("Number of pints left in the cask: $remainingQuantityOfDragonBreathInPints")
}

private fun placeOrder(menuData: String) {

    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)

    println("Madrigal speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "Madrigal buys a $name ($type) for $price"

    println(message)

    for (i in 1..2) {
        performPurchase(price.toDouble())
    }

    val phrase = if (name == "Dragon's Breath") {
        remainingQuantityOfDragonBreathInPints--
        "Madrigal exclaims: ${toDragonSpeak("Ah, delicious $name!")}" + "\n" +
                "Madrigal exclaims: ${toDragonSpeak("DRAGON'S BREATH: IT'S GOT WHAT ADVENTURERS CRAVE!")}"
    } else {
        "Madrigal says: Thanks for the $name"
    }

    println(phrase)
}

private fun toDragonSpeak(phrase: String) = phrase.replace(Regex("[aeiouAEIOU]")) {

    when (it.value.toLowerCase()) {

        "a" -> "4"
        "e" -> "3"
        "i" -> "1"
        "o" -> "0"
        "u" -> "|_|"
        else -> it.value
    }
}

fun performPurchase(price: Double) {

    displayBalance()

//    val totalPurse = playerGold + (playerSilver / 100.0)
    val totalPurse = GOLDS_ONE_DRAGON_COIN * playerDragonCoin

    println("Total purse: ${"%.2f".format(totalPurse)}")
    println("Purchasing item for $price")

    if (totalPurse < price) {
        println("The customer is short on gold.")
        return
    }

    val remainingBalance = totalPurse - price

    println("Remaining balance: ${"%.2f".format(remainingBalance)}")

    val remainingGold = remainingBalance.toInt()
    val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
    val remainingDragonCoin = remainingBalance / GOLDS_ONE_DRAGON_COIN

    playerGold = remainingGold
    playerSilver = remainingSilver
    playerDragonCoin = remainingDragonCoin

    displayBalance()
}

fun displayBalance() {

//    println("Player's purse balance: Gold: $playerGold , Silver: $playerSilver")
    println("Player's purse balance: DragonCoin: ${"%.4f".format(playerDragonCoin)}")
}
