package com.mudassir.taskmanagerapp.data.repository

import com.mudassir.taskmanagerapp.data.api.ApiService
import com.mudassir.taskmanagerapp.domain.model.Task
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : TaskRepository {

    override suspend fun fetchTasks(): List<Task> {
        return apiService.getTasks()
    }

    override suspend fun createTask(task: Task): Task {
        return apiService.createTask(task)
    }
}