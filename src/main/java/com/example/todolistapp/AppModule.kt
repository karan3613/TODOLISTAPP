package com.example.todolistapp

import android.content.Context
import androidx.room.Room
import com.example.todolistapp.Database.TaskDatabase
import com.example.todolistapp.Database.TasksDao
import com.example.todolistapp.Repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideMyDataBase(@ApplicationContext context : Context): TaskDatabase {
            return Room.databaseBuilder(
                context,
                TaskDatabase::class.java ,
                "MyDataBase"
            ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(mydb: TaskDatabase) : TasksDao {
        return mydb.TasksDao
    }

    @Provides
    @Singleton
    fun provideMyRepository(mydb: TasksDao) : RepositoryImpl {
        return RepositoryImpl(tasksDao = mydb)
    }



}