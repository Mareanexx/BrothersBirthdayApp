package ru.mareanexx.brothersbirthdayapp.data.repo

import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.model.RobloxImage
import ru.mareanexx.brothersbirthdayapp.data.model.RobloxQuizQuestion

object RobloxQuizDB {
    var firstAccess: Boolean = true

    val imageRepository = mutableListOf(
        RobloxImage(R.drawable.roblox_img1),
        RobloxImage(R.drawable.roblox_img2),
        RobloxImage(R.drawable.roblox_img3),
        RobloxImage(R.drawable.roblox_img4),
        RobloxImage(R.drawable.roblox_img5),
        RobloxImage(R.drawable.roblox_img6),
        RobloxImage(R.drawable.roblox_img7),
        RobloxImage(R.drawable.roblox_img8),
        RobloxImage(R.drawable.roblox_img9),
        RobloxImage(R.drawable.roblox_img10),
    )


    val questionRepository = mutableListOf(
        RobloxQuizQuestion(
            question = "Как называлась самая первая версия игры?",
            answers = listOf("Roblox", "RoBlocks", "Dynoblox", "DynaBlocks"),
            rightAnswer = 3
        ),
        RobloxQuizQuestion(
            question = "Когда вышел Roblox?",
            answers = listOf("2006", "2012", "2002", "2017"),
            rightAnswer = 0
        ),
        RobloxQuizQuestion(
            question = "Какая вещь самая дорогая в этом вопросе?",
            answers = listOf("Dominus", "Domino Crown", "Одинаково", "Blackvalk"),
            rightAnswer = 0
        ),
        RobloxQuizQuestion(
            question = "Что такое Art в Roblox?",
            answers = listOf("Вид транспорта", "Вид оружия", "Вид игры", "Название игры"),
            rightAnswer = 2
        ),
        RobloxQuizQuestion(
            question = "Как называется второй остров, который открывается в Skyblock?",
            answers = listOf("Остров приключений", "Остров теней", "Остров золота", "Остров слухов"),
            rightAnswer = 3
        ),
        RobloxQuizQuestion(
            question = "Какого цвета акула в игре Shark Bite?",
            answers = listOf("Красного", "Синего", "Желтого", "Зеленого"),
            rightAnswer = 0
        ),
        RobloxQuizQuestion(
            question = "В какой игре ты можешь быть ниндзя и драться?",
            answers = listOf("Ninja Fights", "Ninja Warrior", "Warrior Simulator", "Ninja Legends"),
            rightAnswer = 3
        ),
        RobloxQuizQuestion(
            question = "Какое оружие нельзя использовать в Phantom Forces?",
            answers = listOf("Винтовка 'AK-47'", "Пистолет 'Glock 17'", "Меч 'Link's Sword'", "Дробовик 'Remington 870'"),
            rightAnswer = 2
        ),
        RobloxQuizQuestion(
            question = "Что можно получить, достигнув уровня 100 в 'Murder Mystery 2'?",
            answers = listOf("Эксклюзивное оружие", "Больше Robux", "Специальную эмоцию", "Уникальный костюм"),
            rightAnswer = 0
        ),
        RobloxQuizQuestion(
            question = "Что выдается игрокам в день их рождения?",
            answers = listOf("Birthday Balloon", "Birthday Cake Suit", "Birthday Cake Hat", "Birthday Party Hat"),
            rightAnswer = 2
        ),
        RobloxQuizQuestion(
            question = "Что такое 'Islands' в Roblox?",
            answers = listOf("Название персонажа", "Название игрового мира", "Название игры", "Валюта в игре"),
            rightAnswer = 2
        ),
        RobloxQuizQuestion(
            question = "В какой игре можно поймать и обучать существ, похожих на покемонов?",
            answers = listOf("Adopt Me", "Loomian Legacy", "Pet Simulator X", "Bee Swarm Simulator"),
            rightAnswer = 1
        ),
        RobloxQuizQuestion(
            question = "Как называется язык программирования, на котором написана игра?",
            answers = listOf("JavaScript", "Lua", "Python", "C#"),
            rightAnswer = 1
        ),
        RobloxQuizQuestion(
            question = "Как называется бесплатная валюта в Roblox?",
            answers = listOf("Gold", "Coins", "Gems", "Tickets"),
            rightAnswer = 3
        ),
        RobloxQuizQuestion(
            question = "Как называется премиум-подписка в Roblox?",
            answers = listOf("Roblox Pro", "Roblox Deluxe", "Roblox Premium", "Roblox Plus"),
            rightAnswer = 2
        ),
        RobloxQuizQuestion(
            question = "Сколько максимум друзей может быть у человека в Roblox?",
            answers = listOf("10 000", "Бесконечно", "100", "200"),
            rightAnswer = 3
        ),
        RobloxQuizQuestion(
            question = "Какими знаками блокируются некоторые сообщения в чате Roblox?",
            answers = listOf("#", "~", "_", "*"),
            rightAnswer = 0
        ),
        RobloxQuizQuestion(
            question = "Как называется аккаунт разработчика Roblox (не builderman)?",
            answers = listOf("Admin", "DEVELOPER", "ROBLOX", "Jerry"),
            rightAnswer = 2
        ),
        RobloxQuizQuestion(
            question = "У какого аккаунта больше всего фолловеров (Followers) в Roblox?",
            answers = listOf("Stickmasterluke", "PozziBros", "Tanqr", "builderman"),
            rightAnswer = 3
        ),
        RobloxQuizQuestion(
            question = "За сколько Robux можно поменять игровое имя (ник) в Roblox?",
            answers = listOf("1000", "Бесплатно", "100", "Так нельзя"),
            rightAnswer = 0
        ),
        RobloxQuizQuestion(
            question = "Какой из этих режимов игры НЕ существует в Roblox?",
            answers = listOf("Adopt Me!", "Brookhaven", "Minecraftia", "Tower of Hell"),
            rightAnswer = 2
        ),
        RobloxQuizQuestion(
            question = "Какой из этих режимов игры основан на теме побега из тюрьмы?",
            answers = listOf("Jailbreak", "Murder Mystery 2", "Bloxburg", "MeepCity"),
            rightAnswer = 0
        ),
        RobloxQuizQuestion(
            question = "Какой максимальный уровень можно достичь в игре 'Tower of Hell'?",
            answers = listOf("30", "50", "80", "100"),
            rightAnswer = 2
        ),
        RobloxQuizQuestion(
            question = "Какой из этих режимов игры позволяет игрокам стать супергероями или злодеями?",
            answers = listOf("Super Hero Tycoon", "Villain Simulator", "Hero Havoc", "Power Fighters"),
            rightAnswer = 0
        ),
        RobloxQuizQuestion(
            question = "Какой из этих режимов игры является симулятором пчеловодства?",
            answers = listOf("Bee Swarm Simulator", "Honey Tycoon", "Bee Keeper", "Pollination Quest"),
            rightAnswer = 0
        ),
        RobloxQuizQuestion(
            question = "Какой из этих режимов игры предлагает игрокам исследовать подземелья и сражаться с монстрами?",
            answers = listOf("Dungeon Quest", "Cave Explorer", "Monster Hunter", "Dragon Slayer"),
            rightAnswer = 0
        ),
        RobloxQuizQuestion(
            question = "Какой из этих режимов игры позволяет игрокам соревноваться в гонках на автомобилях?",
            answers = listOf("Vehicle Simulator", "Car Racing Tycoon", "Speedster Challenge", "Drag Race Legends"),
            rightAnswer = 0
        ),
        RobloxQuizQuestion(
            question = "Какой жанр игры наиболее популярен на платформе Roblox?",
            answers = listOf("Шутер", "Ролевая игра", "Симулятор", "Головоломка"),
            rightAnswer = 2
        ),
        RobloxQuizQuestion(
            question = "Как называется инструмент для создания игр в Roblox?",
            answers = listOf("Roblox Studio", "Game Maker", "Roblox Unity", "Unreal Engine"),
            rightAnswer = 0
        ),
        RobloxQuizQuestion(
            question = "В каком году была выпущена игра 'Adopt Me!'?",
            answers = listOf("2017", "2018", "2019", "2020"),
            rightAnswer = 1
        ),
        RobloxQuizQuestion(
            question = "Какой из этих предметов нельзя носить в Roblox?",
            answers = listOf("Шляпа", "Куртка", "Перчатки", "Рюкзак"),
            rightAnswer = 2
        ),
        RobloxQuizQuestion(
            question = "Какой был первым официальным значком (Badge) в Roblox?",
            answers = listOf("Bricksmith", "Administrator", "Welcome to the Club", "Bloxxer"),
            rightAnswer = 3
        ),
        RobloxQuizQuestion(
            question = "Как называется первая игра, набравшая 1 миллиард посещений?",
            answers = listOf("MeepCity", "Adopt Me!", "Jailbreak", "Murder Mystery 2"),
            rightAnswer = 0
        ),
        RobloxQuizQuestion(
            question = "Какой игровой режим основан на королевской битве?",
            answers = listOf("Survive the Disasters!", "Island Royale", "Natural Disaster Survival", "Battle Arena"),
            rightAnswer = 1
        ),
        RobloxQuizQuestion(
            question = "Какой лимит на продажу одного предмета в каталоге Roblox?",
            answers = listOf("100 000 Robux", "1 000 000 Robux", "10 000 000 Robux", "Без лимита"),
            rightAnswer = 2
        ),
        RobloxQuizQuestion(
            question = "Как называется встроенный чат-бот в Roblox, который помогает новичкам?",
            answers = listOf("Roblox Assistant", "BuilderBot", "ChatHelper", "GuideBot"),
            rightAnswer = 0
        ),
        RobloxQuizQuestion(
            question = "Как называется игра, в которой игроки управляют самолетами?",
            answers = listOf("Flight Simulator", "Plane Crazy", "Skybound Tycoon", "Airline Manager"),
            rightAnswer = 1
        ),
        RobloxQuizQuestion(
            question = "Что можно сделать с ненужными вещами в 'Bloxburg'?",
            answers = listOf("Съесть", "Продать", "Подарить другу", "Оставить на улице"),
            rightAnswer = 1
        ),
        RobloxQuizQuestion(
            question = "Какой из этих игровых режимов НЕ существует?",
            answers = listOf("Build a Boat for Treasure", "Roblox Fishing World", "Super Bomb Survival", "Tower Defense Simulator"),
            rightAnswer = 1
        ),
        RobloxQuizQuestion(
            question = "Какой из этих аксессуаров был подарен игрокам в честь 10-летия Roblox?",
            answers = listOf("10-Year Anniversary Hat", "Golden Robloxian", "Party Crown", "Celebration Cake Hat"),
            rightAnswer = 0
        )
    )
}