package com.taskflow.presentation.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taskflow.domain.model.Task
import com.taskflow.domain.usecase.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TrashUiState(val tasks: List<Task> = emptyList(), val isLoading: Boolean = true)

@HiltViewModel
class TrashViewModel @Inject constructor(private val useCases: TaskUseCases) : ViewModel() {
    private val _uiState = MutableStateFlow(TrashUiState())
    val uiState: StateFlow<TrashUiState> = _uiState.asStateFlow()
    init { viewModelScope.launch { useCases.getDeletedTasks().collect { tasks ->
        _uiState.update { it.copy(tasks=tasks, isLoading=false) } } } }
    fun onRestoreTask(id: Long) = viewModelScope.launch { useCases.restoreTask(id) }
    fun onPermanentlyDeleteTask(id: Long) = viewModelScope.launch { useCases.permanentlyDeleteTask(id) }
}
