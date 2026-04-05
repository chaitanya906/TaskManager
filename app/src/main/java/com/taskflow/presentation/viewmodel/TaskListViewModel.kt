package com.taskflow.presentation.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taskflow.domain.model.*
import com.taskflow.domain.usecase.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TaskListUiState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = true,
    val searchQuery: String = "",
    val selectedCategory: TaskCategory? = null
)

@OptIn(FlowPreview::class)
@HiltViewModel
class TaskListViewModel @Inject constructor(private val useCases: TaskUseCases) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    private val _selectedCategory = MutableStateFlow<TaskCategory?>(null)
    private val _uiState = MutableStateFlow(TaskListUiState())
    val uiState: StateFlow<TaskListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                _searchQuery.debounce(300),
                _selectedCategory,
                useCases.getActiveTasks()
            ) { query, cat, all ->
                var list = all
                if (query.isNotBlank()) list = list.filter {
                    it.title.contains(query, true) || it.description.contains(query, true)
                }
                cat?.let { c -> list = list.filter { it.category == c } }
                list.sortedBy { it.priority.order }
            }.collect { tasks ->
                _uiState.update { it.copy(tasks = tasks, isLoading = false,
                    searchQuery = _searchQuery.value, selectedCategory = _selectedCategory.value) }
            }
        }
    }

    fun onSearchQueryChange(q: String) { _searchQuery.value = q; _uiState.update { it.copy(searchQuery = q) } }
    fun onCategoryChange(cat: TaskCategory?) { _selectedCategory.value = cat; _uiState.update { it.copy(selectedCategory = cat) } }
    fun onDeleteTask(id: Long) = viewModelScope.launch { useCases.softDeleteTask(id) }
}
