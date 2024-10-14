package com.example.todolistapp.Database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Task::class],
    version = 1 ,
    exportSchema = false
)
abstract class TaskDatabase  : RoomDatabase(){
    abstract val TasksDao : TasksDao
}