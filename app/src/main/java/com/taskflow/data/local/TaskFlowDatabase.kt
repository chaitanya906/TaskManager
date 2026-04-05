package com.taskflow.data.local
import androidx.room.Database
import androidx.room.RoomDatabase
import com.taskflow.data.local.dao.TaskDao
import com.taskflow.data.local.entity.TaskEntity

@Database(entities=[TaskEntity::class], version=1, exportSchema=false)
abstract class TaskFlowDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    companion object { const val DB_NAME = "taskflow_db" }
}
