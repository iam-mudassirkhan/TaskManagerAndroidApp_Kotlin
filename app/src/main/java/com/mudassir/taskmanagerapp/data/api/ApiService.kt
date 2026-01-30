package com.mudassir.taskmanagerapp.data.api

import com.mudassir.taskmanagerapp.domain.model.Task

interface ApiService {

    suspend fun getTasks(): List<Task>

    suspend fun createTask(task: Task): Task
}