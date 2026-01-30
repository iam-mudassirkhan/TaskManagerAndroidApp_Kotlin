package com.mudassir.taskmanagerapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mudassir.taskmanagerapp.data.repository.TaskRepository
import com.mudassir.taskmanagerapp.domain.model.Task
import com.mudassir.taskmanagerapp.utills.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _tasksState = MutableLiveData<UiState<List<Task>>>()
    val tasksState: LiveData<UiState<List<Task>>> = _tasksState

    init {
        fetchTasks()
    }

    fun fetchTasks() {
        viewModelScope.launch {
            _tasksState.value = UiState.Loading
            try {
                val tasks = repository.fetchTasks()
                _tasksState.value = UiState.Success(tasks)
            } catch (e: Exception) {
                _tasksState.value = UiState.Error("Failed to load tasks")
            }
        }
    }
}
