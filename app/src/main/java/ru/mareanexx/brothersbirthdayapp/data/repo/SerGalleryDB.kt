package ru.mareanexx.brothersbirthdayapp.data.repo

import ru.mareanexx.brothersbirthdayapp.data.model.FavouritePictureItem
import ru.mareanexx.brothersbirthdayapp.data.model.GalleryItem
import ru.mareanexx.brothersbirthdayapp.data.model.SerGalleryItem


// TODO() придумать описания для фотографий

// Поиск в репозитории любимых картинок
fun SerGalleryDB.findFavouriteImageByID(imageID: Int): GalleryItem? = this.favourites.find { it.id == imageID }

// Поиск в репозитории всех картинок
fun SerGalleryDB.findImageByID(imageID: Int): GalleryItem? = this.repository.find { it.id == imageID }

object SerGalleryDB {
    val favourites = listOf(
        FavouritePictureItem(1, "gallery/img1.png", 145, "#я_в_говне", "Я не знаю что придумать"),
        FavouritePictureItem(2, "gallery/img2.jpg", 55, "#стильный", "Я не знаю что придумать")
    )

    val repository = listOf(
        SerGalleryItem(1, "gallery/img1.png", 145, "#я_в_говне", "Я не знаю что придумать"),
        SerGalleryItem(2, "gallery/img2.jpg", 55, "#стильный", "Я не знаю что придумать"),
        SerGalleryItem(3, "gallery/img3.jpg", 233, "#угар", "Я не знаю что придумать"),
        SerGalleryItem(4, "gallery/img4.jpg", 98, "#заснул", "Я не знаю что придумать"),
        SerGalleryItem(5, "gallery/img5.jpg", 412, "#хаха", "Я не знаю что придумать"),
        SerGalleryItem(6, "gallery/img6.jpg", 321, "#модный", "Я не знаю что придумать"),
        SerGalleryItem(7, "gallery/img7.jpg", 678, "#весело", "Я не знаю что придумать"),
        SerGalleryItem(8, "gallery/img8.jpg", 215, "#улыбка", "Я не знаю что придумать"),
        SerGalleryItem(9, "gallery/img9.jpg", 187, "#детство", "Я не знаю что придумать"),
        SerGalleryItem(10, "gallery/img10.jpg", 333, "#настроение", "Я не знаю что придумать"),
        SerGalleryItem(11, "gallery/img11.jpg", 215, "#смешно", "Я не знаю что придумать"),
        SerGalleryItem(12, "gallery/img12.jpg", 92, "#праздник", "Я не знаю что придумать"),
        SerGalleryItem(13, "gallery/img13.jpg", 444, "#супер", "Я не знаю что придумать"),
        SerGalleryItem(14, "gallery/img14.jpg", 189, "#люблю", "Я не знаю что придумать"),
        SerGalleryItem(15, "gallery/img15.jpg", 621, "#жиза", "Я не знаю что придумать"),
        SerGalleryItem(16, "gallery/img16.jpg", 538, "#круто", "Я не знаю что придумать"),
        SerGalleryItem(17, "gallery/img17.jpg", 752, "#лето", "Я не знаю что придумать"),
        SerGalleryItem(18, "gallery/img18.jpg", 281, "#зима", "Я не знаю что придумать"),
        SerGalleryItem(19, "gallery/img19.jpg", 130, "#ярко", "Я не знаю что придумать"),
        SerGalleryItem(20, "gallery/img20.jpg", 995, "#огонь", "Я не знаю что придумать"),
        SerGalleryItem(21, "gallery/img21.jpg", 450, "#класс", "Я не знаю что придумать"),
        SerGalleryItem(22, "gallery/img22.jpg", 732, "#шутка", "Я не знаю что придумать"),
        SerGalleryItem(23, "gallery/img23.jpg", 298, "#тепло", "Я не знаю что придумать"),
        SerGalleryItem(24, "gallery/img24.jpg", 187, "#друзья", "Я не знаю что придумать"),
        SerGalleryItem(25, "gallery/img25.jpg", 521, "#прикол", "Я не знаю что придумать"),
        SerGalleryItem(26, "gallery/img26.jpg", 642, "#семья", "Я не знаю что придумать"),
        SerGalleryItem(27, "gallery/img27.jpg", 451, "#чудо", "Я не знаю что придумать"),
        SerGalleryItem(28, "gallery/img28.jpg", 331, "#лето", "Я не знаю что придумать"),
        SerGalleryItem(29, "gallery/img29.jpg", 432, "#дождь", "Я не знаю что придумать"),
        SerGalleryItem(30, "gallery/img30.jpg", 220, "#солнце", "Я не знаю что придумать"),
        SerGalleryItem(31, "gallery/img31.jpg", 876, "#мимими", "Я не знаю что придумать"),
        SerGalleryItem(32, "gallery/img32.jpg", 135, "#сюрприз", "Я не знаю что придумать"),
        SerGalleryItem(33, "gallery/img33.jpg", 785, "#позитив", "Я не знаю что придумать"),
        SerGalleryItem(34, "gallery/img34.jpg", 451, "#новыйгод", "Я не знаю что придумать"),
        SerGalleryItem(35, "gallery/img35.jpg", 189, "#котики", "Я не знаю что придумать"),
        SerGalleryItem(36, "gallery/img36.jpg", 999, "#любовь", "Я не знаю что придумать"),
        SerGalleryItem(37, "gallery/img37.jpg", 325, "#время", "Я не знаю что придумать"),
        SerGalleryItem(38, "gallery/img38.jpg", 417, "#игры", "Я не знаю что придумать"),
        SerGalleryItem(39, "gallery/img39.jpg", 542, "#весна", "Я не знаю что придумать"),
        SerGalleryItem(40, "gallery/img40.jpg", 312, "#осень", "Я не знаю что придумать"),    SerGalleryItem(41, "gallery/img41.jpg", 342, "#улыбка", "Этот день я запомню навсегда!"),
        SerGalleryItem(42, "gallery/img42.jpg", 876, "#весело", "Когда нашел свой старый альбом."),
        SerGalleryItem(43, "gallery/img43.jpg", 123, "#ностальгия", "Я выгляжу тут смешно, правда?"),
        SerGalleryItem(44, "gallery/img44.jpg", 658, "#детство", "Любимый костюм в детстве."),
        SerGalleryItem(45, "gallery/img45.jpg", 289, "#авантюра", "Попытка залезть на шкаф..."),
        SerGalleryItem(46, "gallery/img46.jpg", 945, "#приключение", "Этот день был просто невероятным!"),
        SerGalleryItem(47, "gallery/img47.jpg", 102, "#забавно", "Я точно не знал, что делаю."),
        SerGalleryItem(48, "gallery/img48.jpg", 777, "#лето", "Лето 2010-го, как давно это было..."),
        SerGalleryItem(49, "gallery/img49.jpg", 566, "#мороз", "Снежная битва окончена."),
        SerGalleryItem(50, "gallery/img50.jpg", 321, "#красиво", "Снимок, который мне очень нравится."),
        SerGalleryItem(51, "gallery/img51.jpg", 150, "#неожиданно", "Как же быстро меняется жизнь!"),
        SerGalleryItem(52, "gallery/img52.jpg", 930, "#смешно", "Пойман в самый неожиданный момент!"),
        SerGalleryItem(53, "gallery/img53.jpg", 645, "#старые_времена", "Когда-то я выглядел вот так."),
        SerGalleryItem(54, "gallery/img54.jpg", 284, "#детство", "Эта игрушка была моей любимой."),
        SerGalleryItem(55, "gallery/img55.jpg", 777, "#жизнь", "Интересно, о чем я тогда думал?"),
        SerGalleryItem(56, "gallery/img56.jpg", 102, "#игра", "Когда ты в самой гуще событий."),
        SerGalleryItem(57, "gallery/img57.jpg", 412, "#радость", "Мой первый велосипед!"),
        SerGalleryItem(58, "gallery/img58.jpg", 921, "#моменты", "Как же было здорово!"),
        SerGalleryItem(59, "gallery/img59.jpg", 501, "#незабываемо", "Это фото — целая история."),
        SerGalleryItem(60, "gallery/img60.jpg", 348, "#время", "Как быстро пролетели годы..."),
        SerGalleryItem(61, "gallery/img61.jpg", 837, "#семья", "Этот день был по-настоящему теплым."),
        SerGalleryItem(62, "gallery/img62.jpg", 276, "#улыбайся", "Я выгляжу счастливым, да?"),
        SerGalleryItem(63, "gallery/img63.jpg", 543, "#дети", "Этот снимок — само очарование."),
        SerGalleryItem(64, "gallery/img64.jpg", 128, "#дом", "Фото с самого уютного места."),
        SerGalleryItem(65, "gallery/img65.jpg", 673, "#классика", "Фото, которое нравится всем."),
        SerGalleryItem(66, "gallery/img66.jpg", 908, "#самые_лучшие_дни", "День, который я никогда не забуду."),
        SerGalleryItem(67, "gallery/img67.jpg", 482, "#детский_сад", "Моя первая дружба."),
        SerGalleryItem(68, "gallery/img68.jpg", 756, "#моменты", "Пойман в объектив в нужный момент!"),
        SerGalleryItem(69, "gallery/img69.jpg", 369, "#детские_годы", "Тот самый взгляд из прошлого."),
        SerGalleryItem(70, "gallery/img70.jpg", 295, "#друзья", "Когда собирались всей компанией."),
        SerGalleryItem(71, "gallery/img71.jpg", 832, "#каникулы", "Тот самый отпуск в детстве!"),
        SerGalleryItem(72, "gallery/img72.jpg", 159, "#эксперименты", "Когда решил примерить новый стиль."),
        SerGalleryItem(73, "gallery/img73.jpg", 920, "#милота", "Ну разве это не очаровательно?"),
        SerGalleryItem(74, "gallery/img74.jpg", 237, "#игрушки", "Тут у меня была огромная коллекция."),
        SerGalleryItem(75, "gallery/img75.jpg", 666, "#зима", "Снежки, ледянки и морозный воздух!"),
        SerGalleryItem(76, "gallery/img76.jpg", 482, "#чудо", "Этот снимок мне особенно дорог."),
        SerGalleryItem(77, "gallery/img77.jpg", 711, "#веселье", "Кажется, я тут был в ударе."),
        SerGalleryItem(78, "gallery/img78.jpg", 123, "#фотосессия", "Пожалуй, одно из лучших фото."),
        SerGalleryItem(79, "gallery/img79.jpg", 859, "#вечеринка", "Тот вечер был просто улет!"),
        SerGalleryItem(80, "gallery/img80.jpg", 290, "#моменты_радости", "Счастье — в простых моментах.")
    )
}