package com.taskflow.data.local.dao
import androidx.room.*
import com.taskflow.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks WHERE isDeleted=0 ORDER BY CASE priority WHEN 'HIGH' THEN 0 WHEN 'MEDIUM' THEN 1 ELSE 2 END, createdAt DESC")
    fun getAllActiveTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE isDeleted=1 ORDER BY deletedAt DESC")
    fun getDeletedTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE isDeleted=0 AND (title LIKE '%'||:q||'%' OR description LIKE '%'||:q||'%')")
    fun searchTasks(q: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE isDeleted=0 AND category=:cat")
    fun getTasksByCategory(cat: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE id=:id LIMIT 1")
    suspend fun getTaskById(id: Long): TaskEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity): Long

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Query("UPDATE tasks SET isDeleted=1, deletedAt=:at WHERE id=:id")
    suspend fun softDeleteTask(id: Long, at: Long)

    @Query("UPDATE tasks SET isDeleted=0, deletedAt=NULL WHERE id=:id")
    suspend fun restoreTask(id: Long)

    @Query("DELETE FROM tasks WHERE id=:id")
    suspend fun permanentlyDeleteTask(id: Long)

    @Query("DELETE FROM tasks WHERE isDeleted=1 AND deletedAt < :threshold")
    suspend fun deleteTasksOlderThan(threshold: Long)
}
