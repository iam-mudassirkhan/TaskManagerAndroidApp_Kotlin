package com.mudassir.taskmanagerapp.ui.viewmodels

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mudassir.taskmanagerapp.data.repository.TaskRepository
import com.mudassir.taskmanagerapp.domain.model.Task
import com.mudassir.taskmanagerapp.utills.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _state = MutableLiveData<UiState<Unit>>()
    val state: LiveData<UiState<Unit>> = _state

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _taskCreated = MutableLiveData(false)
    val taskCreated: LiveData<Boolean> = _taskCreated

    private val _imageUri = MutableLiveData<Uri?>()
    val imageUri: LiveData<Uri?> = _imageUri

    private val _location = MutableLiveData<Pair<Double, Double>?>()
    val location: LiveData<Pair<Double, Double>?> = _location

    fun createTask(task: Task) {
        viewModelScope.launch {
            _loading.value = true
            _state.value = UiState.Loading
            try {

                delay(2000) //  i added delay here to show Progressbar
                _loading.value = false
                _taskCreated.value = true

                repository.createTask(task)
                _state.value = UiState.Success(Unit)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Error creating task")
            }
        }
    }

    fun setImageUri(uri: Uri) {
        _imageUri.value = uri
    }

    fun setLocation(lat: Double, lng: Double) {
        _location.value = lat to lng
    }

    fun resetState() {

        _taskCreated.value = false
    }

}