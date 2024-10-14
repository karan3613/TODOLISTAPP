package com.example.todolistapp.Repository

import com.example.todolistapp.Database.Task
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun insert(task: Task)
    suspend fun delete(task: Task)
    suspend fun update(task : Task)
    suspend fun getTaskById(taskId: Int): Flow<Task?>
    fun getAllTasks() : Flow<List<Task>>
}