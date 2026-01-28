package org.example

fun main() {
    val newGameButton = 1
    val settingsButton = 2
    val exitButton = 3
    var isFirstStart = true

    while (true) {
        printMainMenu(isFirstStart)
        val selectedItemMenu = readItemMenu()
        when (selectedItemMenu) {
            newGameButton -> runNewGame()
            settingsButton -> showSettingsManager()
            exitButton -> System.exit(0)
        }
        isFirstStart = false
    }

}

fun printMainMenu(isFirstStart: Boolean) {
    if(!isFirstStart) println()
    println(
        """1. Начать новую игру
2. Настройки
3. Выйти из игры"""
    )
    println()
}

fun readItemMenu(): Int {
    while (true) {
        println("Выберите пункт:")
        val option = readln()
        if (option !in "1".."3") println("Некорректный номер пункта меню")
        else return option.toInt()
    }
}

fun runNewGame() {
    val citiesList = arrayOf(
        "москва",
        "санкт-петербург",
        "иннополис",
        "сочи",
        "новосибирск",
        "екатеринбург",
        "казань",
        "волгоград",
        "красноярск",
        "астрахань",
        "оренбург",
        "томск",
        "рязань"
    )
    val letterNo = arrayOf('ь', 'ъ', 'ы', 'й')
    var firstSymbolOfNewCity = ""
    val namedCitiesList = Array(citiesList.size * 2) { "" }
    var namedCount = 0

    println("Начнем игру! Назовите город:")

    while (true) {
        val city = readln().lowercase()

        if (city == "не знаю") {
            println("Победил ИИ!\nИгра окончена. Спасибо за участие!")
            System.exit(0)
        }

        var exists = false
        for (c in citiesList) {
            if (c == city) {
                exists = true
                break
            }
        }

        if (!exists) {
            println("Победил ИИ!\nИгра окончена. Спасибо за участие!")
            System.exit(0)
        }

        namedCitiesList[namedCount] = city
        namedCount++

        val lastLetter = if (city.last() in letterNo) city[city.lastIndex - 1] else city.last()

        var found = false

        for (nextCity in citiesList) {
            var alreadyUsed = false
            for (i in 0 until namedCount) {
                if (namedCitiesList[i] == nextCity) {
                    alreadyUsed = true
                    break
                }
            }

            if (alreadyUsed) continue

            if (nextCity.first() != lastLetter) continue

            println(nextCity)
            namedCitiesList[namedCount] = nextCity
            namedCount++
            found = true
            break
        }

        if (!found) {
            println("Жаль, я не знаю ни одного города на букву '$lastLetter'.\nПобедил игрок!")
            System.exit(0)
        }
    }
}

fun showSettingsManager() {

}