package com.mudassir.taskmanagerapp.data.api

import com.mudassir.taskmanagerapp.data.FakeTaskDataSource
import com.mudassir.taskmanagerapp.domain.model.Task
import kotlinx.coroutines.delay

class FakeApiService : ApiService {

    override suspend fun getTasks(): List<Task> {
        delay(1000) // simulate network delay
        return FakeTaskDataSource.getTasks()
    }

    override suspend fun createTask(task: Task): Task {
        delay(1000)
        FakeTaskDataSource.addTask(task)
        return task
    }
}