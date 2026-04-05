package com.taskflow.presentation.ui.screens
import com.taskflow.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.taskflow.domain.model.TaskCategory
import com.taskflow.presentation.ui.components.*
import com.taskflow.presentation.ui.theme.*
import com.taskflow.presentation.viewmodel.TaskListViewModel
import java.time.LocalDate


@Composable
fun TaskListScreen(
    onNavigateToCreate: () -> Unit,
    onNavigateToEdit: (Long) -> Unit,
    onNavigateToTrash: () -> Unit,
    viewModel: TaskListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val categories = listOf<TaskCategory?>(null) + TaskCategory.values().toList()

    Scaffold(
        containerColor = BgScreen,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToCreate,
                containerColor = Primary,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.size(52.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
            }
        },
        bottomBar = {
            TaskFlowBottomBar(current = "tasks", onTasksClick = {}, onTrashClick = onNavigateToTrash)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(BgScreen)
        ) {
            // TopBar
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("My Tasks", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = TextTitle)
                /*Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                    IconButton(onClick = onNavigateToTrash) {
                        Icon(Icons.Default.Delete, contentDescription = "Trash", tint = TextBody, modifier = Modifier.size(22.dp))
                    }
                }*/
            }

            // Search bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
                    .padding(horizontal = 10.dp)
                    .background(BgCard, RoundedCornerShape(12.dp))
                    .border(1.dp, BorderCard, RoundedCornerShape(12.dp))
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = TextMuted
                )

                Spacer(modifier = Modifier.width(6.dp))

                BasicTextField(
                    value = uiState.searchQuery,
                    onValueChange = viewModel::onSearchQueryChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp),

                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        color = TextBody
                    ),

                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (uiState.searchQuery.isEmpty()) {
                                Text(
                                    "Search tasks...",
                                    fontSize = 12.sp,
                                    color = TextMuted
                                )
                            }
                            innerTextField()
                        }
                    }
                )
            }

            // Category tabs
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                items(categories) { cat ->
                    val isSelected = uiState.selectedCategory == cat
                    Surface(
                        onClick = { viewModel.onCategoryChange(cat) },
                        shape = RoundedCornerShape(20.dp),
                        color = if (isSelected) TextTitle else BgScreen,
                        border = if (!isSelected) ButtonDefaults.outlinedButtonBorder else null
                    ) {
                        Text(
                            text = cat?.displayName ?: "All",
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                            fontSize = 12.sp, fontWeight = FontWeight.Medium,
                            color = if (isSelected) Color.White else TextMuted
                        )
                    }
                }
            }

            // Task list
            when {
                uiState.isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Primary)
                }
                uiState.tasks.isEmpty() -> Box(
                    Modifier.fillMaxSize().padding(32.dp), contentAlignment = Alignment.Center
                ) {
                    Text(
                        if (uiState.searchQuery.isNotBlank()) "No tasks match your search"
                        else "No tasks yet.\nTap + to create one!",
                        fontSize = 14.sp, color = TextMuted, textAlign = TextAlign.Center
                    )
                }
                else -> {
                    val today = LocalDate.now()
                    val todayTasks = uiState.tasks.filter { it.dueDate?.toLocalDate() == today }
                    val weekTasks = uiState.tasks.filter {
                        val d = it.dueDate?.toLocalDate()
                        d != null && d.isAfter(today) && d.isBefore(today.plusDays(7))
                    }
                    val otherTasks = uiState.tasks.filter { it !in todayTasks && it !in weekTasks }

                    LazyColumn(contentPadding = PaddingValues(bottom = 80.dp)) {
                        if (todayTasks.isNotEmpty()) {
                            item { SectionLabel("Today") }
                            items(todayTasks, key = { it.id }) { task ->
                                TaskCard(task, onClick = { onNavigateToEdit(task.id) },
                                    onDeleteClick = { viewModel.onDeleteTask(task.id) })
                            }
                        }
                        if (weekTasks.isNotEmpty()) {
                            item { SectionLabel("This Week") }
                            items(weekTasks, key = { it.id }) { task ->
                                TaskCard(task, onClick = { onNavigateToEdit(task.id) },
                                    onDeleteClick = { viewModel.onDeleteTask(task.id) })
                            }
                        }
                        if (otherTasks.isNotEmpty()) {
                            item { SectionLabel("Other") }
                            items(otherTasks, key = { it.id }) { task ->
                                TaskCard(task, onClick = { onNavigateToEdit(task.id) },
                                    onDeleteClick = { viewModel.onDeleteTask(task.id) })
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SectionLabel(text: String) {
    Text(text.uppercase(), fontSize = 11.sp, fontWeight = FontWeight.SemiBold,
        color = TextMuted, letterSpacing = 1.sp,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
}

@Composable
fun TaskFlowBottomBar(current: String, onTasksClick: () -> Unit, onTrashClick: () -> Unit) {
    Column {
        HorizontalDivider(color = BorderCard, thickness = 1.dp)
        NavigationBar(containerColor = BgCard, tonalElevation = 0.dp) {

                    NavigationBarItem(
                        selected = current == "tasks",
                        onClick = onTasksClick,
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_tasks),
                                contentDescription = "Tasks",
                                modifier = Modifier.size(22.dp)
                            )
                        },
                        label = {
                            Text(
                                "Tasks",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Medium
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Primary,
                            selectedTextColor = Primary,
                            unselectedIconColor = TextMuted,
                            unselectedTextColor = TextMuted,
                            indicatorColor = Color(0xFFEEF2FF)
                        )
                    )

                    NavigationBarItem(
                        selected = current == "trash",
                        onClick = onTrashClick,
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_trash),
                                contentDescription = "Trash",
                                modifier = Modifier.size(22.dp)
                            )
                        },
                        label = {
                            Text(
                                "Trash",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Medium
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Primary,
                            selectedTextColor = Primary,
                            unselectedIconColor = TextMuted,
                            unselectedTextColor = TextMuted,
                            indicatorColor = Color(0xFFEEF2FF)
                        )
                    )
        }
    }
}
