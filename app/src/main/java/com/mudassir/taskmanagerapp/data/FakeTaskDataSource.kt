package com.mudassir.taskmanagerapp.data

import com.mudassir.taskmanagerapp.domain.model.Task

object FakeTaskDataSource {

    private val tasks = mutableListOf(
        Task(
            id = 1,
            title = "Buy groceries",
            description = "Milk, Eggs, Bread",
            latitude = 33.6844,
            longitude = 73.0479
        ),
        Task(
            id = 2,
            title = "Office Meeting",
            description = "Discuss project in Details on Monday"
        )
    )

    fun getTasks(): List<Task> = tasks

    fun addTask(task: Task) {
        tasks.add(task)
    }
}