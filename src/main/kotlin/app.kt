import kotlin.random.Random

fun main() {
    val cities = listOf("Бийск", "Барнаул", "Новосибирск", "Омск", "Красноярск", "Иркутск", "Нижний Новгород", "Екатеринбург", "Москва", "Санкт-Петербург", "Казань", "Самара", "Ростов-на-Дону", "Волгоград", "Сочи")
    var numPassengers = 0

    var currentStep = 1

    var continueProgram = true
    while (continueProgram) {
        println("Хотите составить план поезда? (ENTER для подтверждения)(Для выхода введите 'EXIT')")
        val userInput = readLine() ?: ""

        if (userInput.equals("EXIT", ignoreCase = true)) {
            continueProgram = false
            continue
        }

        when (currentStep) {
            1 -> createDirection(cities)
            2 -> numPassengers = sellTickets()
            3 -> formTrain(numPassengers)
            4 -> sendTrain()
        }
        currentStep++
        if(currentStep >4)
            currentStep = 1

    }
}

fun createDirection(cities: List<String>) {
    val fromCity = cities.random()
    var toCity = fromCity
    while (toCity == fromCity) {
        toCity = cities.random()
    }
    println("Создано направление: $fromCity - $toCity")
}

fun sellTickets() : Int {
    val numPassengers = Random.nextInt(5, 202)
    println("Продано $numPassengers билетов на это направление.")
    return  numPassengers
}

fun formTrain(numPassengers: Int) {
    val train = mutableListOf<Int>()

    while (train.sum() < numPassengers) {
        val wagonCapacity = Random.nextInt(5, 26)
        train.add(wagonCapacity)
    }

    println("Сформирован поезд с ${train.size} вагонами.")

    for (i in train.indices) {
        val wagonPassengers = if (i == train.size - 1) {
            numPassengers - train.subList(0, i).sum()
        } else {
            train[i]
        }
        println("Вагон $i: Вместимость - ${train[i]}, Пассажиров - $wagonPassengers")
    }
}


fun sendTrain() {
    println("Поезд отправлен.")
}
