package com.taskflow.data.worker
import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.taskflow.domain.usecase.CleanupOldDeletedTasksUseCase
import dagger.assisted.*
import java.util.concurrent.TimeUnit

@HiltWorker
class TrashCleanupWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val cleanup: CleanupOldDeletedTasksUseCase
) : CoroutineWorker(context, params) {
    override suspend fun doWork() = try { cleanup(); Result.success() } catch (e: Exception) { Result.retry() }
    companion object {
        const val WORK_NAME = "TrashCleanup"
        fun buildRequest() = PeriodicWorkRequestBuilder<TrashCleanupWorker>(1, TimeUnit.DAYS)
            .setConstraints(Constraints.Builder().setRequiresBatteryNotLow(true).build()).build()
    }
}
