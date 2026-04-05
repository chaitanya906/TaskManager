package com.taskflow.presentation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.taskflow.data.worker.TrashCleanupWorker
import com.taskflow.presentation.navigation.TaskFlowNavGraph
import com.taskflow.presentation.ui.theme.TaskFlowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            TrashCleanupWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            TrashCleanupWorker.buildRequest()
        )
        setContent {
            TaskFlowTheme {
                val navController = rememberNavController()
                TaskFlowNavGraph(navController = navController)
            }
        }
    }
}
