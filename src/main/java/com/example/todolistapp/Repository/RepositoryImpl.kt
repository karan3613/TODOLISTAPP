package com.example.todolistapp.Repository

import com.example.todolistapp.Database.Task
import com.example.todolistapp.Database.TasksDao
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RepositoryImpl (private val tasksDao: TasksDao) : Repository {
    override suspend fun insert(task: Task) {
        withContext(IO){
            tasksDao.insertTask(task)
        }
    }

    override suspend fun delete(task: Task) {
        withContext(IO) {
            tasksDao.deleteTask(task)
        }
    }

    override suspend fun update(task: Task) {
        withContext(IO){
            tasksDao.editTask(task)
        }
    }
    override suspend fun getTaskById(taskId: Int): Flow<Task?> {
        return tasksDao.getTaskById(taskId)
    }

    override  fun getAllTasks(): Flow<List<Task>> {
        return tasksDao.getAllTasks()
    }
}