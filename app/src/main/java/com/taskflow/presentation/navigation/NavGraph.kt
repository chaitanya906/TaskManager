package com.taskflow.presentation.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.*
import com.taskflow.presentation.ui.screens.*

@Composable
fun TaskFlowNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Tasks.route) {
        composable(Screen.Tasks.route) {
            TaskListScreen(
                onNavigateToCreate = { navController.navigate(Screen.CreateTask.route) },
                onNavigateToEdit = { navController.navigate(Screen.EditTask.createRoute(it)) },
                onNavigateToTrash = { navController.navigate(Screen.Trash.route) }
            )
        }
        composable(Screen.Trash.route) {
            TrashScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(Screen.CreateTask.route) {
            CreateEditTaskScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(
            route = Screen.EditTask.route,
            arguments = listOf(navArgument(NavArgs.TASK_ID) { type = NavType.LongType })
        ) {
            CreateEditTaskScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}
