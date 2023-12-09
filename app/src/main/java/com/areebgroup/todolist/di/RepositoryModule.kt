package com.areebgroup.todolist.di

import com.areebgroup.todolist.data.repository.TodoRepositoryImp
import com.areebgroup.todolist.domain.repository.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindTodoRepository(repository: TodoRepositoryImp): TodoRepository

}