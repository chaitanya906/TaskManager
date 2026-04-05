package com.taskflow.presentation.ui.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.taskflow.domain.model.*
import com.taskflow.presentation.ui.components.SelectableChip
import com.taskflow.presentation.ui.theme.*
import com.taskflow.presentation.viewmodel.TaskDetailViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

@Composable
fun CreateEditTaskScreen(
    onNavigateBack: () -> Unit,
    viewModel: TaskDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val isEdit = uiState.taskId != 0L

    LaunchedEffect(uiState.isSaved) { if (uiState.isSaved) onNavigateBack() }

    Column(
        modifier = Modifier.fillMaxSize().background(BgScreen).statusBarsPadding()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                IconButton(onClick = onNavigateBack, modifier = Modifier.size(36.dp)) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Primary, modifier = Modifier.size(20.dp))
                }
                Text(if (isEdit) "Edit Task" else "New Task", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextTitle)
            }
            Button(
                onClick = viewModel::saveTask,
                enabled = !uiState.isLoading,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Primary, contentColor = Color.White),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 2.dp)
            ) { Text("Save", fontSize = 14.sp, fontWeight = FontWeight.SemiBold) }
        }

        if (uiState.isLoading && isEdit && uiState.title.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Primary)
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FieldSection("TITLE *") {
                    CompactTextField(
                        value = uiState.title,
                        onValueChange = viewModel::onTitleChange,
                        placeholder = "Enter task title",
                        height = 39.dp
                    )
                }

                FieldSection("DESCRIPTION") {
                    OutlinedTextField(
                        value = uiState.description,
                        onValueChange = viewModel::onDescriptionChange,
                        modifier = Modifier.fillMaxWidth().height(64.dp),
                        placeholder = { Text("Add description (optional)", color = TextMuted, fontSize = 14.sp) },
                        shape = RoundedCornerShape(12.dp),
                        maxLines = 4,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = BgScreen, focusedContainerColor = BgScreen,
                            unfocusedBorderColor = EditTextBorderColor, focusedBorderColor = EditTextBorderColor
                        ),
                        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp, color = TextTitle)
                    )
                }

                // Category
                FieldSection("CATEGORY") {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        TaskCategory.values().forEach { cat ->
                            SelectableChip(cat.displayName, uiState.category == cat) { viewModel.onCategoryChange(cat) }
                        }
                    }
                }

                // Priority
                FieldSection("PRIORITY") {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        TaskPriority.values().forEach { pri ->
                            val color = when (pri) {
                                TaskPriority.HIGH   -> Primary
                                TaskPriority.MEDIUM -> Primary
                                TaskPriority.LOW    -> Primary
                            }
                            SelectableChip(pri.displayName, uiState.priority == pri, selectedColor = color) { viewModel.onPriorityChange(pri) }
                        }
                    }
                }

                FieldSection("DUE DATE") {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedButton(
                            onClick = {
                                val cal = Calendar.getInstance()
                                uiState.dueDate?.let { cal.set(it.year, it.monthValue - 1, it.dayOfMonth) }
                                DatePickerDialog(context, { _, y, m, d ->
                                    TimePickerDialog(context, { _, h, min ->
                                        viewModel.onDueDateChange(LocalDateTime.of(y, m + 1, d, h, min))
                                    }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show()
                                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.outlinedButtonColors(containerColor = BgScreen, contentColor = Primary),
                            border = ButtonDefaults.outlinedButtonBorder.copy(brush = SolidColor(BorderCard)),
                            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 12.dp)
                        ) {
                            Text(
                                text = uiState.dueDate?.format(
                                    DateTimeFormatter.ofPattern("MMM d, yyyy -   h:mm a")
                                ) ?: "Set due date",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start,
                                fontSize = 13.sp,
                                color = if (uiState.dueDate != null) TextBody else TextMuted
                            )
                        }
                        if (uiState.dueDate != null) {
                            IconButton(onClick = { viewModel.onDueDateChange(null) }, modifier = Modifier.size(36.dp)) {
                                Icon(Icons.Default.Close, null, tint = TextMuted, modifier = Modifier.size(18.dp))
                            }
                        }
                    }
                }

                FieldSection("STATUS") {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        TaskStatus.values().forEach { stat ->
                            SelectableChip(stat.displayName, uiState.status == stat) { viewModel.onStatusChange(stat) }
                        }
                    }
                }

                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun FieldSection(label: String, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(label, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = TextUnselected, letterSpacing = 0.8.sp)
        content()
    }
}
@Composable
fun CompactTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    height: Dp = 44.dp
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 14.sp,
            color = TextTitle
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(BgScreen, RoundedCornerShape(8.dp))
            .border(1.dp, EditTextBorderColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp),
        decorationBox = { innerTextField ->

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = TextMuted,
                        fontSize = 14.sp
                    )
                }
                innerTextField()
            }
        }
    )
}
