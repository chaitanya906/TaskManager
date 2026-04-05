package com.taskflow.presentation.viewmodel
import androidx.lifecycle.*
import com.taskflow.domain.model.*
import com.taskflow.domain.usecase.TaskUseCases
import com.taskflow.presentation.navigation.NavArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

data class TaskDetailUiState(
    val taskId: Long = 0L,
    val title: String = "",
    val description: String = "",
    val category: TaskCategory = TaskCategory.PERSONAL,
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val dueDate: LocalDateTime? = null,
    val status: TaskStatus = TaskStatus.PENDING,
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val titleError: String? = null
)

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val useCases: TaskUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val taskId: Long = savedStateHandle.get<Long>(NavArgs.TASK_ID) ?: 0L
    private val _uiState = MutableStateFlow(TaskDetailUiState(isLoading = taskId != 0L))
    val uiState: StateFlow<TaskDetailUiState> = _uiState.asStateFlow()

    init { if (taskId != 0L) viewModelScope.launch { useCases.getTaskById(taskId)?.let { t ->
        _uiState.update { it.copy(taskId=t.id, title=t.title, description=t.description,
            category=t.category, priority=t.priority, dueDate=t.dueDate, status=t.status, isLoading=false) }
    } ?: _uiState.update { it.copy(isLoading=false) } } }

    fun onTitleChange(v: String) = _uiState.update { it.copy(title=v, titleError=null) }
    fun onDescriptionChange(v: String) = _uiState.update { it.copy(description=v) }
    fun onCategoryChange(v: TaskCategory) = _uiState.update { it.copy(category=v) }
    fun onPriorityChange(v: TaskPriority) = _uiState.update { it.copy(priority=v) }
    fun onDueDateChange(v: LocalDateTime?) = _uiState.update { it.copy(dueDate=v) }
    fun onStatusChange(v: TaskStatus) = _uiState.update { it.copy(status=v) }

    fun saveTask() {
        val s = _uiState.value
        if (s.title.isBlank()) { _uiState.update { it.copy(titleError="Title is required") }; return }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading=true) }
            val now = LocalDateTime.now()
            if (taskId == 0L) useCases.createTask(Task(title=s.title.trim(), description=s.description.trim(),
                category=s.category, priority=s.priority, dueDate=s.dueDate, status=s.status, createdAt=now, lastModifiedAt=now))
            else useCases.getTaskById(taskId)?.let { t -> useCases.updateTask(t.copy(title=s.title.trim(),
                description=s.description.trim(), category=s.category, priority=s.priority,
                dueDate=s.dueDate, status=s.status, lastModifiedAt=now)) }
            _uiState.update { it.copy(isLoading=false, isSaved=true) }
        }
    }
}
