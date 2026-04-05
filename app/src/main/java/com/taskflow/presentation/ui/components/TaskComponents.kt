package com.taskflow.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taskflow.domain.model.*
import com.taskflow.presentation.ui.theme.*
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskCard(
    task: Task,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            containerColor = BgCard,
            title = { Text("Move to Trash", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = TextTitle) },
            text = { Text("Move \"${task.title}\" to trash?", fontSize = 14.sp, color = TextBody) },
            confirmButton = {
                TextButton(onClick = { showDialog = false; onDeleteClick() }) {
                    Text("Move to Trash", color = Color(0xFFEF4444), fontWeight = FontWeight.SemiBold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) { Text("Cancel", color = TextMuted) }
            }
        )
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .border(1.dp, BorderCard, RoundedCornerShape(14.dp))
            .combinedClickable(
                onClick = onClick,
                onLongClick = { showDialog = true }
            ),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = BgScreen),
        elevation = CardDefaults.cardElevation(0.dp)
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
                        color = if (task.status == TaskStatus.COMPLETED) TextMuted else TextTitle,
                        textDecoration = if (task.status == TaskStatus.COMPLETED)
                            TextDecoration.LineThrough else TextDecoration.None,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(Modifier.width(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    PriorityBadge(task.priority)

                    IconButton(
                        onClick = { showDialog = true },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color(0xFFEF4444).copy(alpha = 0.7f),
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.height(2.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = task.category?.displayName ?: "Work",
                    fontSize = 11.sp,
                    color = TextMuted
                )

                Spacer(Modifier.width(12.dp))

                task.dueDate?.let {
                    Text(
                        text = "Due: ${it.format(DateTimeFormatter.ofPattern("MMM d, h:mm a"))}",
                        fontSize = 11.sp,
                        color = TextMuted
                    )
                }

                Spacer(Modifier.width(12.dp))

                Text(
                    text = task.status.name
                        .lowercase()
                        .replace("_", " ")
                        .replaceFirstChar { it.uppercase() },
                    fontSize = 11.sp,
                    color = TextMuted
                )
            }
        }
    }
}

@Composable
fun PriorityBadge(priority: TaskPriority) {
    val (bg, fg, label) = when (priority) {
        TaskPriority.HIGH   -> Triple(Color(0xFFFFEBEE), Color(0xFFD32F2F), "HIGH")
        TaskPriority.MEDIUM -> Triple(Color(0xFFFFF8E1), Color(0xFFF57F17), "MED")
        TaskPriority.LOW    -> Triple(Color(0xFFE3F2FD), Color(0xFF1565C0), "LOW")
    }
    Surface(shape = RoundedCornerShape(6.dp), color = bg) {
        Text(label, modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
            fontSize = 10.sp, fontWeight = FontWeight.Bold, color = fg, letterSpacing = 0.5.sp)
    }
}

@Composable
fun CategoryBadge(category: TaskCategory) {
    val (bg, fg) = when (category) {
        TaskCategory.WORK     -> Pair(Color(0xFFEEF2FF), Color(0xFF4F46E5))
        TaskCategory.PERSONAL -> Pair(Color(0xFFF0FDF4), Color(0xFF16A34A))
        TaskCategory.SHOPPING -> Pair(Color(0xFFFFF7ED), Color(0xFFEA580C))
    }
    Surface(shape = RoundedCornerShape(10.dp), color = bg) {
        Text(category.displayName, modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp),
            fontSize = 11.sp, fontWeight = FontWeight.Medium, color = fg)
    }
}

@Composable
fun StatusBadge(status: TaskStatus) {
    val (bg, fg) = when (status) {
        TaskStatus.PENDING     -> Pair(Color(0xFFF3F4F6), Color(0xFF6B7280))
        TaskStatus.IN_PROGRESS -> Pair(Color(0xFFEFF6FF), Color(0xFF2563EB))
        TaskStatus.COMPLETED   -> Pair(Color(0xFFF0FDF4), Color(0xFF16A34A))
    }
    Surface(shape = RoundedCornerShape(10.dp), color = bg) {
        Text(status.displayName, modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp),
            fontSize = 11.sp, fontWeight = FontWeight.Medium, color = fg)
    }
}

@Composable
fun SelectableChip(label: String, selected: Boolean, selectedColor: Color = Primary, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        color = if (selected) selectedColor else BgScreen,
        border = if (!selected) BorderStroke(1.dp, MaterialTheme.colorScheme.outline) else null
    ) {
        Text(label, modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
            fontSize = 12.sp, fontWeight = FontWeight.Medium,
            color = if (selected) Color.White else TextUnselected)
    }
}
