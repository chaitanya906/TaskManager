package com.taskflow.presentation.navigation

object NavArgs { const val TASK_ID = "taskId" }

sealed class Screen(val route: String) {
    object Tasks : Screen("tasks")
    object Trash : Screen("trash")
    object CreateTask : Screen("create_task")
    object EditTask : Screen("edit_task/{${NavArgs.TASK_ID}}") {
        fun createRoute(id: Long) = "edit_task/$id"
    }
}
