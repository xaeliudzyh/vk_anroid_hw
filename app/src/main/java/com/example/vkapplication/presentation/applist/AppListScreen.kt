package com.example.vkapplication.presentation.applist

import coil.compose.AsyncImage
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkapplication.domain.model.App
import org.koin.androidx.compose.koinViewModel

private val RuStoreBlue = Color(0xFF006AF5)

@Composable
fun AppListScreen(
    onAppClick: (Int) -> Unit,
    viewModel: AppListViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is AppListEvent.ShowSnackbar -> snackbarHostState.showSnackbar(event.message)
            }
        }
    }

    AppListContent(
        state = uiState,
        snackbarHostState = snackbarHostState,
        onLogoClick = viewModel::onLogoClick,
        onAppClick = onAppClick
    )
}

@Composable
private fun AppListContent(
    state: AppListUiState,
    snackbarHostState: SnackbarHostState,
    onLogoClick: () -> Unit,
    onAppClick: (Int) -> Unit
) {
    Scaffold(
        topBar = { RuStoreHeader(onLogoClick = onLogoClick) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(state.apps, key = { it.id }) { app ->
                AppListItem(app = app, onClick = { onAppClick(app.id) })
                HorizontalDivider(
                    modifier = Modifier.padding(start = 88.dp, end = 16.dp),
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RuStoreHeader(onLogoClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(RuStoreBlue)
            .windowInsetsPadding(TopAppBarDefaults.windowInsets)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RuStoreLogo(onClick = onLogoClick)
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "А", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Composable
private fun RuStoreLogo(onClick: () -> Unit) {
    Row(
        modifier = Modifier.clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = "https://mnogo-golosov.ru/wp-content/uploads/2025/08/RuStore_logo.svg_.png",
            contentDescription = "RuStore logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(32.dp)
        )
        Text(
            text = "RuStore",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            letterSpacing = 0.5.sp
        )
    }
}

@Composable
private fun AppListItem(app: App, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIconImage(iconUrl = app.iconUrl, contentDescription = app.name)
        Spacer(modifier = Modifier.width(12.dp))
        AppInfo(app = app, modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(8.dp))
        InstallButton(isFree = app.isFree)
    }
}

@Composable
private fun AppIconImage(iconUrl: String, contentDescription: String) {
    Surface(
        modifier = Modifier
            .size(56.dp)
            .clip(RoundedCornerShape(14.dp)),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        AsyncImage(
            model = iconUrl,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun AppInfo(app: App, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = app.name,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = app.developer,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        RatingRow(rating = app.rating)
    }
}

@Composable
private fun RatingRow(rating: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Text(text = rating.toString(), fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(text = "★", fontSize = 11.sp, color = Color(0xFFFFC107))
    }
}

@Composable
private fun InstallButton(isFree: Boolean) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = RuStoreBlue
    ) {
        TextButton(onClick = {}) {
            Text(
                text = if (isFree) "Установить" else "Купить",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}
