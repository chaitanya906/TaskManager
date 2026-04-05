package com.taskflow.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.taskflow.domain.model.Task
import com.taskflow.presentation.ui.theme.*
import com.taskflow.presentation.viewmodel.TrashViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Composable
fun TrashScreen(
    onNavigateBack: () -> Unit,
    viewModel: TrashViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = BgScreen,
        bottomBar = {
            TaskFlowBottomBar(
                current = "trash",
                onTasksClick = onNavigateBack,
                onTrashClick = {}
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(BgScreen)
        ) {

            // Header Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Trash",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextTitle
                )
                Spacer(modifier = Modifier.size(36.dp))
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = BorderCard
                )
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFFFFF8E1)
                ) {
                    Text(
                        text = "Auto-deleted after 7 days",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFF57F17),
                        textAlign = TextAlign.Center
                    )
                }
            }

            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Primary)
                    }
                }

                uiState.tasks.isEmpty() -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Trash is empty",
                            fontSize = 14.sp,
                            color = TextMuted
                        )
                    }
                }

                else -> {
                    LazyColumn(contentPadding = PaddingValues(bottom = 24.dp)) {
                        items(items = uiState.tasks, key = { it.id }) { task ->
                            TrashCard(
                                task = task,
                                onRestore = { viewModel.onRestoreTask(task.id) },
                                onDelete = { viewModel.onPermanentlyDeleteTask(task.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TrashCard(task: Task, onRestore: () -> Unit, onDelete: () -> Unit) {

    val daysElapsed = task.deletedAt?.let {
        ChronoUnit.DAYS.between(it.toLocalDate(), LocalDate.now())
    } ?: 0L
    val daysLeft = (7 - daysElapsed).coerceAtLeast(0)

    val (badgeBg, badgeFg) = when {
        daysLeft <= 2 -> Color(0xFFFEE2E2) to Color(0xFFDC2626)
        daysLeft <= 5 -> Color(0xFFFFF0E0) to Color(0xFFE07820)
        else          -> Color(0xFFD1FAE5) to Color(0xFF059669)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = BgScreen),
        elevation = CardDefaults.cardElevation(0.dp),
        border = CardDefaults.outlinedCardBorder().copy(brush = SolidColor(BorderCard))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = task.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextMuted,
                        textDecoration = TextDecoration.LineThrough,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    task.deletedAt?.let {
                        Spacer(Modifier.height(1.dp))
                        Text(
                            text = "Deleted ${it.format(DateTimeFormatter.ofPattern("MMM d, yyyy"))}",
                            fontSize = 11.sp,
                            color = Color(0xFFBBBBBB)
                        )
                    }
                }

                Spacer(Modifier.width(8.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = badgeBg
                ) {
                    Text(
                        text = "${daysLeft}d left",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = badgeFg
                    )
                }
            }

            Spacer(Modifier.height(4.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(
                    onClick = onRestore,
                    modifier = Modifier.weight(1f).
                    height(36.dp).
                    defaultMinSize(minHeight = 1.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = RestoreBtnBackground,
                        contentColor = RestoreBtnTextColor
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = SolidColor(RestoreBtnBackground)
                    ),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    Text("Restore", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }

                OutlinedButton(
                    onClick = onDelete,
                    modifier = Modifier
                        .weight(1f)
                        .height(36.dp)
                        .defaultMinSize(minHeight = 1.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = DeleteBtnBackground,
                        contentColor = DeleteeBtnTextColor
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = SolidColor(DeleteBtnBackground)
                    ),
                    contentPadding = PaddingValues(vertical = 4.dp)
                ) {
                    Text(
                        "Delete",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}