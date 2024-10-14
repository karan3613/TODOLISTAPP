package com.example.todolistapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.Database.Task
import com.example.todolistapp.Repository.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyappViewModel @Inject constructor(
    private val repository: RepositoryImpl
): ViewModel()  {
    private var _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks : StateFlow<List<Task>> = _tasks.asStateFlow()

    private val _currentTask = MutableStateFlow<Task?>(null)
    val currentTask: StateFlow<Task?> = _currentTask.asStateFlow()

    init {
        getAllTasks()
    }

    fun getAllTasks() {
        viewModelScope.launch(IO){
            repository.getAllTasks().collectLatest { task->
                _tasks.tryEmit(task)
            }
        }
    }
     fun insert(task: Task) {
        viewModelScope.launch {
            repository.insert(task)
        }
    }
     fun delete(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }
    fun update(task: Task) {
        viewModelScope.launch {
            repository.update(task.copy(isCompleted = !task.isCompleted))
        }
    }
    fun getTaskById(taskId: Int) {
        viewModelScope.launch {
            repository.getTaskById(taskId).collectLatest {
                _currentTask.tryEmit(it)
            }
        }
    }


}