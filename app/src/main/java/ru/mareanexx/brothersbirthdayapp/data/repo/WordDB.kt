package ru.mareanexx.brothersbirthdayapp.data.repo

import ru.mareanexx.brothersbirthdayapp.data.model.crossword.Direction
import ru.mareanexx.brothersbirthdayapp.data.model.crossword.Word

object WordDB {
    val repository = listOf(
        Word(
            number = 1,
            actualText = "МАРСИК",
            task = "Котяра Бабуси Марины",
            startRow = 3,
            startCol = 0,
            direction = Direction.HORIZONTALLY
        ),
        Word(
            number = 2,
            actualText = "ДВОЙКУ",
            task = "Продолжи фразу: «ОТЕЦ! Наш сын получил …»",
            startRow = 5,
            startCol = 2,
            direction = Direction.HORIZONTALLY
        ),
        Word(
            number = 3,
            actualText = "ТУЯ",
            task = "(Хард вопрос) Растение у нас в овальной клумбе",
            startRow = 6,
            startCol = 11,
            direction = Direction.HORIZONTALLY
        ),
        Word(
            number = 4,
            actualText = "ЧЕБОТИНА",
            task = "Песню какого исполнителя мы пели вместе в машине на Рыбачем (есть видео)?",
            startRow = 8,
            startCol = 5,
            direction = Direction.HORIZONTALLY
        ),
        Word(
            number = 5,
            actualText = "ПЯТКА",
            task = "Куда ты чаще всего меня целовал?",
            startRow = 10,
            startCol = 2,
            direction = Direction.HORIZONTALLY
        ),
        Word(
            number = 6,
            actualText = "ЛОСЬ",
            task = "Постоянно бывающее животное у нас дома",
            startRow = 13,
            startCol = 1,
            direction = Direction.HORIZONTALLY
        ),
        Word(
            number = 7,
            actualText = "НАКУЗИНА",
            task = "Девичья фамилия матушки",
            startRow = 13,
            startCol = 9,
            direction = Direction.HORIZONTALLY
        ),
        Word(
            number = 8,
            actualText = "ЕЙСК",
            task = "Мы там блевали неделю...",
            startRow = 0,
            startCol = 5,
            direction = Direction.VERTICALLY
        ),
        Word(
            number = 9,
            actualText = "СЕВЕР",
            task = "СНТ дедушки Юры и бабушки Веры",
            startRow = 3,
            startCol = 3,
            direction = Direction.VERTICALLY
        ),
        Word(
            number = 10,
            actualText = "КАШЕВАР",
            task = "Прозвище Андрея за его проделки в унитазной",
            startRow = 5,
            startCol = 6,
            direction = Direction.VERTICALLY
        ),
        Word(
            number = 11,
            actualText = "ЩУКА",
            task = "На что мы ловили щуку на озере на лодке?",
            startRow = 5,
            startCol = 12,
            direction = Direction.VERTICALLY
        ),
        Word(
            number = 12,
            actualText = "ТАТЬЯНА",
            task = "Самый сырный в семье",
            startRow = 8,
            startCol = 9,
            direction = Direction.VERTICALLY
        ),
        Word(
            number = 13,
            actualText = "ПИРОГИ",
            task = "Что делает бабушка Надя, что у нас у всех большая жопа?",
            startRow = 10,
            startCol = 2,
            direction = Direction.VERTICALLY
        ),
        Word(
            number = 14,
            actualText = "ДУКАТИ",
            task = "Название красного мотоцикла на русском",
            startRow = 12,
            startCol = 12,
            direction = Direction.VERTICALLY
        ),
        Word(
            number = 15,
            actualText = "ПИВО",
            task = "Любимый напиток Евгения Пермиловского (после водки)",
            startRow = 12,
            startCol = 14,
            direction = Direction.VERTICALLY
        )
    )
}