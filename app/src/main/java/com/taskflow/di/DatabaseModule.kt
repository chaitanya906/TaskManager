package com.taskflow.di
import android.content.Context
import androidx.room.Room
import com.taskflow.data.local.TaskFlowDatabase
import com.taskflow.data.local.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module @InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides @Singleton
    fun provideDb(@ApplicationContext ctx: Context): TaskFlowDatabase =
        Room.databaseBuilder(ctx, TaskFlowDatabase::class.java, TaskFlowDatabase.DB_NAME).build()
    @Provides @Singleton
    fun provideDao(db: TaskFlowDatabase): TaskDao = db.taskDao()
}
