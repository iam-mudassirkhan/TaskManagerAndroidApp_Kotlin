package com.mudassir.taskmanagerapp.data.repository

import com.mudassir.taskmanagerapp.domain.model.Task

interface TaskRepository {

    suspend fun fetchTasks(): List<Task>

    suspend fun createTask(task: Task): Task
}