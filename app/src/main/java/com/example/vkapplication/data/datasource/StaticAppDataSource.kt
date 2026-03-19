package com.example.vkapplication.data.datasource

import com.example.vkapplication.data.dto.AppDto

object StaticAppDataSource {
    val apps: List<AppDto> = listOf(
        AppDto(
            id = 1,
            name = "VK: социальная сеть",
            developer = "VK.com",
            category = "Социальные сети",
            rating = 4.5f,
            reviewCount = "5,2 млн",
            downloadsCount = "500 млн+",
            size = "34 МБ",
            description = "ВКонтакте — крупнейшая социальная сеть в России и странах СНГ. Общайтесь с друзьями, смотрите видео, слушайте музыку и следите за новостями.",
            iconUrl = "https://img.icons8.com/color/480/vk-com.png"
        ),
        AppDto(
            id = 2,
            name = "Telegram",
            developer = "Telegram FZ-LLC",
            category = "Мессенджеры",
            rating = 4.7f,
            reviewCount = "10,1 млн",
            downloadsCount = "1 млрд+",
            size = "52 МБ",
            description = "Telegram — быстрый и безопасный мессенджер. Создавайте групповые чаты, каналы, делитесь файлами любого размера.",
            iconUrl = "https://img.icons8.com/color/480/telegram-app.png"
        ),
        AppDto(
            id = 3,
            name = "Яндекс Карты",
            developer = "Яндекс",
            category = "Навигация",
            rating = 4.6f,
            reviewCount = "3,8 млн",
            downloadsCount = "100 млн+",
            size = "89 МБ",
            description = "Яндекс Карты — подробные карты городов и стран мира, удобная навигация, маршруты на любой транспорт, пробки онлайн.",
            iconUrl = "https://static.rustore.ru/imgproxy/uk4eQOt5jhw-yCSHwIZYwyGLHRduRvjqAhvysY__Wmc/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/586431/content/ICON/a5f2fe7d-cd63-4f3f-a2f8-40d997c1d6f4.png@webp"
        ),
        AppDto(
            id = 4,
            name = "Сбербанк Онлайн",
            developer = "Сбербанк России",
            category = "Финансы",
            rating = 4.4f,
            reviewCount = "7,3 млн",
            downloadsCount = "100 млн+",
            size = "76 МБ",
            description = "Сбербанк Онлайн — управляйте счетами, переводите деньги, оплачивайте услуги и следите за финансами прямо со смартфона.",
            iconUrl = "https://static.rustore.ru/imgproxy/lQKIdJKRbtJBX0dxbZueqU-a5TEP_-_yKjFjWljOsaE/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/462271/content/ICON/f1b3c68a-b734-48ce-b62f-490208d3fa0e.png@webp"
        ),
        AppDto(
            id = 5,
            name = "Spotify",
            developer = "Spotify AB",
            category = "Музыка и аудио",
            rating = 4.3f,
            reviewCount = "9,0 млн",
            downloadsCount = "1 млрд+",
            size = "44 МБ",
            description = "Spotify — миллионы треков, подкастов и аудиокниг. Слушайте любимую музыку онлайн и офлайн.",
            iconUrl = "https://img.icons8.com/color/480/spotify.png"
        ),
        AppDto(
            id = 6,
            name = "WhatsApp",
            developer = "WhatsApp LLC",
            category = "Мессенджеры",
            rating = 4.1f,
            reviewCount = "148 млн",
            downloadsCount = "5 млрд+",
            size = "37 МБ",
            description = "WhatsApp — простой, надёжный и конфиденциальный мессенджер для общения с друзьями и семьёй.",
            iconUrl = "https://img.icons8.com/color/480/whatsapp.png"
        ),
        AppDto(
            id = 7,
            name = "YouTube",
            developer = "Google LLC",
            category = "Видео и ТВ",
            rating = 4.4f,
            reviewCount = "116 млн",
            downloadsCount = "10 млрд+",
            size = "149 МБ",
            description = "YouTube — смотрите видео, музыкальные клипы, новости и многое другое. Подписывайтесь на каналы и создавайте собственный контент.",
            iconUrl = "https://img.icons8.com/color/480/youtube-play.png"
        ),
        AppDto(
            id = 8,
            name = "Instagram",
            developer = "Instagram",
            category = "Фото и видео",
            rating = 4.2f,
            reviewCount = "43 млн",
            downloadsCount = "5 млрд+",
            size = "58 МБ",
            description = "Instagram — делитесь фото и видео, смотрите истории, общайтесь с друзьями и открывайте для себя новых авторов.",
            iconUrl = "https://img.icons8.com/color/480/instagram-new.png"
        )
    )
}

