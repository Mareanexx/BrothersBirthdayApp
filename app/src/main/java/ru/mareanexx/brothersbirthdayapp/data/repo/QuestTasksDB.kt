package ru.mareanexx.brothersbirthdayapp.data.repo

import ru.mareanexx.brothersbirthdayapp.data.model.QuestTaskInfo

object QuestTasksDB {
    var tempTaskNumber: Int = 0

    val tempTask: QuestTaskInfo
        get() = repository[tempTaskNumber]

    fun setNextTaskNumber() = tempTaskNumber++

    val repository = listOf(
        QuestTaskInfo(
            id = 1,
            title = "INTRODUCTION",
            text = "You are a secret agent of a secret company. Your boss gave you an important mission! " +
                    "The enemy organisation “Skibidi Toilets” wants to destroy your Birthday by changing translations and hiding presents.\n" +
                    "To save the birthday, you need to get to the main villain and kill him.",
            rightButtonText = "Next",
            imagePath = "quest/img1.png"
        ),
        QuestTaskInfo(
            id = 2,
            title = "STRANGE LETTER",
            text = "It was an normal day. But something went wrong.\nYou were working at your computer, solving important problems. " +
                    "Suddenly, your assistant brings you a strange letter. " +
                    "At first, the text seemed to mean nothing. But then you realised that there was a message encrypted in it!\n" +
                    "\n" +
                    "Your task is to understand what is written in the letter.",
            rightButtonText = "Go To Task",
            imagePath = "quest/img2.png",
            navigateTo = "questLetter"
        ),
        QuestTaskInfo(
            id = 3,
            title = "FIND A SPY",
            text = "The letter said that one of your colleagues is a secret spy. You are greatly surprised because you trusted all of them.\n" +
                    "\n" +
                    "Your task is to find out who the spy is, who is lying and who is telling the truth.",
            rightButtonText = "Go To Task",
            imagePath = "quest/img3.png",
            navigateTo = "spies"
        ),
        QuestTaskInfo(
            id = 4,
            title = "WHERE IS the BASE?",
            text = "You found out who the secret spy is. But now he asks you to let him go, in return he will tell you where the villain's main base is.",
            rightButtonText = "Next",
            imagePath = "quest/img4.png"
        ),
        QuestTaskInfo(
            id = 5,
            title = "IN THE VILLAIN’s BASE",
            text = "A spy told you where the main villain is. You flew there in a high-speed plane. \n" +
                    "Their base was huge, but you found the right door.\n" +
                    "It is strange, but you had to climb the wall. You see that a door has a lock. \n" +
                    "Your task is to figure out the code to the door.",
            rightButtonText = "Go To Task",
            imagePath = "quest/img5.png",
            navigateTo = "guessTheCode"
        ),
        QuestTaskInfo(
            id = 6,
            title = "WHAAAT?",
            text = "You opened the door and all your friends and family were inside. They are standing with a cake and holding presents.\n" +
                    "It became clear that your sister made this quest to wish you a happy birthday.\n" +
                    "Congratulations to you, secret agent!",
            rightButtonText = "Finish",
            imagePath = "quest/img6.png"
        ),
    )
}