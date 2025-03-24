package ru.mareanexx.brothersbirthdayapp.data.model.crossword

object WordDB {
    val repository = listOf(
        Word(
            number = 12,
            wordText = "ТАТЬЯНА",
            task = "Самый сырный в семье",
            startRow = 8,
            startCol = 9,
            isHorizontal = false
        ),
        Word(
            number = 13,
            wordText = "ПИРОГИ",
            task = "Что делает бабушка Надя, что у нас у всех большая жопа?",
            startRow = 10,
            startCol = 2,
            isHorizontal = false
        ),
        Word(
            number = 1,
            wordText = "МАРСИК",
            task = "Котяра Бабуси Марины",
            startRow = 3,
            startCol = 0,
            isHorizontal = true
        ),
        Word(
            number = 2,
            wordText = "ДВОЙКУ",
            task = "Продолжи фразу: «ОТЕЦ! Наш сын получил …»",
            startRow = 5,
            startCol = 2,
            isHorizontal = true
        ),
        Word(
            number = 3,
            wordText = "ТУЯ",
            task = "(Хард вопрос) Растение у нас в овальной клумбе",
            startRow = 6,
            startCol = 11,
            isHorizontal = true
        ),
        Word(
            number = 4,
            wordText = "ЧЕБОТИНА",
            task = "Песню какого исполнителя мы пели вместе в машине на Рыбачем (есть видео)?",
            startRow = 8,
            startCol = 5,
            isHorizontal = true
        ),
        Word(
            number = 5,
            wordText = "ПЯТКА",
            task = "Куда ты чаще всего меня целовал?",
            startRow = 10,
            startCol = 2,
            isHorizontal = true
        ),
        Word(
            number = 6,
            wordText = "ЛОСЬ",
            task = "Постоянно бывающее животное у нас дома",
            startRow = 13,
            startCol = 1,
            isHorizontal = true
        ),
        Word(
            number = 7,
            wordText = "НАКУЗИНА",
            task = "Девичья фамилия матушки",
            startRow = 13,
            startCol = 9,
            isHorizontal = true
        ),
        Word(
            number = 8,
            wordText = "ЕЙСК",
            task = "Мы там блевали неделю...",
            startRow = 0,
            startCol = 5,
            isHorizontal = false
        ),
        Word(
            number = 9,
            wordText = "СЕВЕР",
            task = "СНТ дедушки Юры и бабушки Веры",
            startRow = 3,
            startCol = 3,
            isHorizontal = false
        ),
        Word(
            number = 10,
            wordText = "КАШЕВАР",
            task = "Прозвище Андрея за его проделки в унитазной",
            startRow = 5,
            startCol = 6,
            isHorizontal = false
        ),
        Word(
            number = 11,
            wordText = "ЩУКА",
            task = "На что мы ловили щуку на озере на лодке?",
            startRow = 5,
            startCol = 12,
            isHorizontal = false
        ),
        Word(
            number = 14,
            wordText = "ДУКАТИ",
            task = "Название красного мотоцикла на русском",
            startRow = 12,
            startCol = 12,
            isHorizontal = false
        ),
        Word(
            number = 15,
            wordText = "ПИВО",
            task = "Любимый напиток Евгения Пермиловского (после водки)",
            startRow = 12,
            startCol = 14,
            isHorizontal = false
        )
    )
}


object CrosswordField {
    val grid: Array<Array<Letter>> = Array(18) { Array(17) { Letter() } }

    private fun addWordLettersToGrid(word: Word) {
        word.wordText.forEachIndexed { index, letter ->
            val rowNum = if (word.isHorizontal) word.startRow else word.startRow + index
            val colNum = if (word.isHorizontal) word.startCol + index else word.startCol
            grid[rowNum][colNum] = Letter(letter = letter, linkedWord = word)
        }
    }

    init {
        for (word in WordDB.repository) {
            addWordLettersToGrid(word)
        }
    }
}