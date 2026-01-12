package com.example.rustore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val VkBlue = Color(0xFF0077FF)
val VkLightBlue = Color(0xFFE1F0FF)
val VkDarkGray = Color(0xFF2C2D2E)
val VkGray = Color(0xFF6D7885)
val VkRed = Color(0xFFFF3347)
val VkGreen = Color(0xFF4BB34B)

enum class AppCategory(val displayName: String, val color: Color) {
    FINANCE("Финансы", Color(0xFF4CAF50)),
    TOOLS("Инструменты", Color(0xFF2196F3)),
    GAMES("Игры", Color(0xFFE91E63)),
    GOVERNMENT("Государственные", Color(0xFF9C27B0)),
    TRANSPORT("Транспорт", Color(0xFFFF9800))
}

data class AppItem(
    val id: Int,
    val name: String,
    val developer: String,
    val description: String,
    val fullDescription: String,
    val category: AppCategory,
    val ageRating: String,
    val screenshots: List<String> = listOf("screen1", "screen2", "screen3")
)

object Apps {
    val list = listOf(
        AppItem(1, "ВКонтакте", "VK", "Социальная сеть №1 в России",
            "ВКонтакте — самая популярная социальная сеть в России. Общайтесь с друзьями, смотрите видео, слушайте музыку и будьте в курсе последних новостей.",
            AppCategory.TOOLS, "12+"),
        AppItem(2, "VK Музыка", "VK", "Более 100 млн треков",
            "Слушайте музыку без ограничений, создавайте плейлисты, находите новые треки и слушайте любимые подкасты.",
            AppCategory.TOOLS, "12+"),
        AppItem(3, "VK Клипы", "VK", "Платформа для коротких видео",
            "Создавайте, смотрите и делитесь короткими видео. Тренды, развлечения и творчество в одном приложении.",
            AppCategory.GAMES, "16+"),
        AppItem(4, "VK Мессенджер", "VK", "Быстрые и безопасные сообщения",
            "Общайтесь с друзьями через мгновенные сообщения, голосовые и видеозвонки в защищенном мессенджере.",
            AppCategory.TOOLS, "12+"),
        AppItem(5, "VK Видео", "VK", "Фильмы, сериалы и шоу",
            "Смотрите фильмы, сериалы, ТВ-шоу и эксклюзивный контент в высоком качестве на всех устройствах.",
            AppCategory.GAMES, "16+"),
        AppItem(6, "VK Работа", "VK", "Поиск работы и сотрудников",
            "Найдите работу своей мечты или сотрудников для вашей компании. Резюме, вакансии и собеседования онлайн.",
            AppCategory.TOOLS, "12+"),
        AppItem(7, "VK Знакомства", "VK", "Находите новых друзей",
            "Знакомьтесь с интересными людьми, находите новых друзей и общайтесь в безопасной среде.",
            AppCategory.TOOLS, "18+")
    )
}

@Composable
fun AppIconPlaceholder(appName: String, color: Color = VkBlue) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .background(color, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = appName.take(2).uppercase(),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

@Composable
fun AppCard(app: AppItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = 4.dp,
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ИКОНКА ПРИЛОЖЕНИЯ (ДОБАВЛЕНО)
            AppIconPlaceholder(app.name, app.category.color)
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = app.name,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = VkDarkGray
                )
                Text(
                    text = app.developer,
                    color = VkGray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = app.description,
                    style = MaterialTheme.typography.body1,
                    color = VkDarkGray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(
                        text = app.category.displayName,
                        color = app.category.color,
                        modifier = Modifier
                            .background(app.category.color.copy(alpha = 0.1f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = app.ageRating,
                        color = VkRed,
                        modifier = Modifier
                            .background(VkRed.copy(alpha = 0.1f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun OnboardingScreen(onStart: () -> Unit) {
    Surface(
        color = VkBlue,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.White, shape = MaterialTheme.shapes.medium),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "RS",
                    style = MaterialTheme.typography.h2.copy(fontSize = 40.sp),
                    color = VkBlue,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "RuStore",
                style = MaterialTheme.typography.h3,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Официальный магазин приложений\nот VK",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.9f)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = onStart,
                modifier = Modifier.padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = VkBlue
                ),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 2.dp
                )
            ) {
                Text("Начать просмотр", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun AppListScreen(onAppClick: (AppItem) -> Unit, onCategoriesClick: () -> Unit) {
    Surface(
        color = VkLightBlue,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(VkBlue)
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "RuStore",
                    style = MaterialTheme.typography.h4,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 16.dp)
                )
                Button(
                    onClick = onCategoriesClick,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = VkBlue
                    )
                ) {
                    Text("Категории")
                }
            }
            LazyColumn {
                items(Apps.list) { app ->
                    AppCard(app = app, onClick = { onAppClick(app) })
                }
            }
        }
    }
}

@Composable
fun CategoryListScreen(onCategoryClick: (AppCategory) -> Unit, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Категории", color = Color.White, fontWeight = FontWeight.Medium) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад", tint = Color.White)
                    }
                },
                backgroundColor = VkBlue,
                elevation = 4.dp
            )
        },
        backgroundColor = VkLightBlue
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            LazyColumn {
                items(AppCategory.values()) { category ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clickable { onCategoryClick(category) },
                        elevation = 2.dp,
                        backgroundColor = Color.White
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = category.displayName,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.Bold,
                                color = VkDarkGray
                            )
                            Text(
                                text = "${Apps.list.count { it.category == category }} приложений",
                                color = VkGray,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AppListByCategoryScreen(category: AppCategory, onAppClick: (AppItem) -> Unit, onBack: () -> Unit) {
    val apps = Apps.list.filter { it.category == category }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(category.displayName, color = Color.White, fontWeight = FontWeight.Medium) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад", tint = Color.White)
                    }
                },
                backgroundColor = VkBlue,
                elevation = 4.dp
            )
        },
        backgroundColor = VkLightBlue
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Text(
                text = "Приложения в категории",
                style = MaterialTheme.typography.subtitle1,
                color = VkGray,
                modifier = Modifier.padding(16.dp)
            )
            LazyColumn {
                items(apps) { app ->
                    AppCard(app = app, onClick = { onAppClick(app) })
                }
            }
        }
    }
}

@Composable
fun ScreenshotThumbnail(index: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(200.dp, 196.dp)
            .background(VkGray.copy(alpha = 0.2f))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(android.R.drawable.ic_menu_gallery),
                contentDescription = "Скриншот ${index + 1}",
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Скриншот ${index + 1}",
                textAlign = TextAlign.Center,
                color = VkGray
            )
        }
    }
}

@Composable
fun AppDetailScreen(app: AppItem, onBack: () -> Unit, onScreenshotClick: (Int) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(app.name, color = Color.White, fontWeight = FontWeight.Medium) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад", tint = Color.White)
                    }
                },
                backgroundColor = VkBlue,
                elevation = 4.dp
            )
        },
        backgroundColor = VkLightBlue
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                elevation = 4.dp,
                backgroundColor = Color.White
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    // ЗАГОЛОВОК С ИКОНКОЙ (ДОБАВЛЕНО)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        AppIconPlaceholder(app.name, app.category.color)
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = app.name,
                                style = MaterialTheme.typography.h4,
                                fontWeight = FontWeight.Bold,
                                color = VkDarkGray
                            )
                            Text(
                                text = "Разработчик: ${app.developer}",
                                color = VkGray,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }

                    Row(Modifier.padding(bottom = 8.dp)) {
                        Text(
                            text = app.category.displayName,
                            color = app.category.color,
                            modifier = Modifier
                                .background(app.category.color.copy(alpha = 0.1f))
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = app.ageRating,
                            color = VkRed,
                            modifier = Modifier
                                .background(VkRed.copy(alpha = 0.1f))
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Скриншоты",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        items(app.screenshots) { screenshot ->
                            ScreenshotThumbnail(
                                index = app.screenshots.indexOf(screenshot),
                                onClick = { onScreenshotClick(app.screenshots.indexOf(screenshot)) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Описание",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = app.fullDescription,
                        style = MaterialTheme.typography.body1,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = VkGreen,
                            contentColor = Color.White
                        ),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 4.dp,
                            pressedElevation = 2.dp
                        )
                    ) {
                        Text("Установить", fontSize = 18.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun FullscreenScreenshotsScreen(
    appName: String,
    screenshotCount: Int,
    initialIndex: Int,
    onBack: () -> Unit
) {
    var currentIndex by remember { mutableStateOf(initialIndex) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "$appName - ${currentIndex + 1}/$screenshotCount",
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад", tint = Color.White)
                    }
                },
                backgroundColor = Color.Black,
                elevation = 0.dp
            )
        },
        backgroundColor = Color.Black
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.Black)
                .clickable {
                    currentIndex = (currentIndex + 1) % screenshotCount
                }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(android.R.drawable.ic_menu_gallery),
                    contentDescription = "Скриншот ${currentIndex + 1}",
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Скриншот ${currentIndex + 1}",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Приложение: $appName",
                    color = VkGray,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Нажмите на экран для следующего скриншота",
                    color = VkGray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 32.dp),
                    textAlign = TextAlign.Center
                )
            }

            // Кнопки навигации
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        currentIndex = if (currentIndex - 1 >= 0) currentIndex - 1 else screenshotCount - 1
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.5f))
                        .size(48.dp)
                ) {
                    Icon(
                        painter = painterResource(android.R.drawable.ic_media_previous),
                        contentDescription = "Предыдущий",
                        tint = Color.White
                    )
                }
                IconButton(
                    onClick = {
                        currentIndex = (currentIndex + 1) % screenshotCount
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.5f))
                        .size(48.dp)
                ) {
                    Icon(
                        painter = painterResource(android.R.drawable.ic_media_next),
                        contentDescription = "Следующий",
                        tint = Color.White
                    )
                }
            }

            // Индикатор страниц
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(screenshotCount) { index ->
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(
                                color = if (index == currentIndex) VkBlue else Color.White.copy(alpha = 0.5f),
                                shape = CircleShape
                            )
                            .padding(horizontal = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RuStoreApp() {
    MaterialTheme(
        colors = lightColors(
            primary = VkBlue,
            primaryVariant = VkBlue,
            secondary = VkGreen,
            background = VkLightBlue,
            surface = Color.White,
            onPrimary = Color.White,
            onSecondary = Color.White,
            onBackground = VkDarkGray,
            onSurface = VkDarkGray
        )
    ) {
        var showOnboarding by remember { mutableStateOf(true) }
        var selectedApp by remember { mutableStateOf<AppItem?>(null) }
        var showCategories by remember { mutableStateOf(false) }
        var selectedCategory by remember { mutableStateOf<AppCategory?>(null) }
        var showFullscreenScreenshots by remember { mutableStateOf(false) }
        var selectedScreenshotIndex by remember { mutableStateOf(0) }

        if (showOnboarding) {
            OnboardingScreen(onStart = { showOnboarding = false })
        } else if (showCategories) {
            CategoryListScreen(
                onCategoryClick = { category ->
                    selectedCategory = category
                    showCategories = false
                },
                onBack = { showCategories = false }
            )
        } else if (selectedCategory != null) {
            AppListByCategoryScreen(
                category = selectedCategory!!,
                onAppClick = { app -> selectedApp = app },
                onBack = { selectedCategory = null }
            )
        } else if (showFullscreenScreenshots && selectedApp != null) {
            FullscreenScreenshotsScreen(
                appName = selectedApp!!.name,
                screenshotCount = selectedApp!!.screenshots.size,
                initialIndex = selectedScreenshotIndex,
                onBack = {
                    showFullscreenScreenshots = false
                    selectedScreenshotIndex = 0
                }
            )
        } else if (selectedApp != null) {
            AppDetailScreen(
                app = selectedApp!!,
                onBack = { selectedApp = null },
                onScreenshotClick = { index ->
                    selectedScreenshotIndex = index
                    showFullscreenScreenshots = true
                }
            )
        } else {
            AppListScreen(
                onAppClick = { app -> selectedApp = app },
                onCategoriesClick = { showCategories = true }
            )
        }
    }
}