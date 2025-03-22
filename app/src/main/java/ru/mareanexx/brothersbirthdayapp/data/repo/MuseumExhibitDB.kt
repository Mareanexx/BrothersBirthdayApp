package ru.mareanexx.brothersbirthdayapp.data.repo

import ru.mareanexx.brothersbirthdayapp.data.model.MuseumExhibit

object MuseumExhibitDB {
    val repository = listOf(
        MuseumExhibit(
            id = 1,
            doorImagePath = "doors/door1.png",
            pictureImagePath = "doors/exhibits/exhibit1.png",
            caption = "Спустя 500 лет Да Винчи наконец дописал ЕГО, на этот раз не потерял брови",
            year = "(2025)",
            blackCaption = "Серхио Мон"
        ),
        MuseumExhibit(
            id = 2,
            doorImagePath = "doors/door2.png",
            pictureImagePath = "doors/exhibits/exhibit2.png",
            caption = "Гроза всех озер и рек. Рыбы с открытым ртом смотрят на его таланты и сами прыгают в лодку",
            year = "(2029)",
            blackCaption = "Экстремальная рыбалка"
        ),
        MuseumExhibit(
            id = 3,
            doorImagePath = "doors/door3.png",
            pictureImagePath = "doors/exhibits/exhibit3.png",
            caption = "Разрушитель мифа о том, что одновременно умный, сильный и красивый человек существует",
            year = "(2033)",
            blackCaption = "Сергей Макгрегор"
        ),
        MuseumExhibit(
            id = 4,
            doorImagePath = "doors/door4.png",
            pictureImagePath = "doors/exhibits/exhibit4.png",
            caption = "Долгожданный фильм по известному мультсериалу. К роли приглашены главные звезды современности!",
            year = "(2042)",
            blackCaption = "Смешиловские дети"
        ),
        MuseumExhibit(
            id = 5,
            doorImagePath = "doors/door5.png",
            pictureImagePath = "pdoors/exhibits/exhibit5.png",
            caption = "Супермен 2.0 - улучшенная версия, которая вернула Макдональдс в Россию",
            year = "(2125)",
            blackCaption = "СуперСергоглот"
        ),
        MuseumExhibit(
            id = 6,
            doorImagePath = "doors/door6.png",
            pictureImagePath = "doors/exhibits/exhibit6.png",
            caption = "Первая новогодняя речь президента после выборов. Да, в 30 уже лысина(",
            year = "(2045)",
            blackCaption = "Сергей Путин"
        ),
        MuseumExhibit(
            id = 7,
            doorImagePath = "doors/door7.png",
            pictureImagePath = "doors/exhibits/exhibit7.png",
            caption = "Здесь все про успех или как я заработал триллиард за 10 миллисекунд",
            year = "(2045)",
            blackCaption = "Деньги не главное"
        ),
        MuseumExhibit(
            id = 8,
            doorImagePath = "doors/door8.png",
            pictureImagePath = "doors/exhibits/exhibit8.png",
            caption = "Страх детства начал набирать обороты",
            year = "(2031)",
            blackCaption = "Держим улыбку"
        ),
        MuseumExhibit(
            id = 9,
            doorImagePath = "doors/door9.png",
            pictureImagePath = "doors/exhibits/exhibit9.png",
            caption = "Скибиди уа-па-па. Не знаю че сказать",
            year = "(2029)",
            blackCaption = "Сортир какой-то"
        ),
        MuseumExhibit(
            id = 10,
            doorImagePath = "doors/door10.png",
            pictureImagePath = "doors/exhibits/exhibit10.png",
            caption = "Иисус вернулся и поделился в инстаграме своим настоящим фото",
            year = "(2025)",
            blackCaption = "Второе пришествие"
        )
    )
}