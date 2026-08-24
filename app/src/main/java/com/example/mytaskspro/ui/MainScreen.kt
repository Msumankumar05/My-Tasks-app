package com.example.mytaskspro.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.mytaskspro.data.Task
import com.example.mytaskspro.ui.components.AddEditTaskBottomSheet
import com.example.mytaskspro.ui.screens.*
import com.example.mytaskspro.ui.theme.MyTasksProTheme
import com.example.mytaskspro.ui.viewmodel.TaskViewModel

sealed class NavItem(val route: String, val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Tasks : NavItem("tasks", "Tasks", Icons.Default.Checklist)
    object Kanban : NavItem("kanban", "Kanban", Icons.Default.ViewColumn)
    object Calendar : NavItem("calendar", "Calendar", Icons.Default.CalendarMonth)
    object Focus : NavItem("focus", "Focus", Icons.Default.Timer)
    object Analytics : NavItem("analytics", "Analytics", Icons.Default.Analytics)
    object Settings : NavItem("settings", "Settings", Icons.Default.Settings)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: TaskViewModel) {
    val currentTheme by viewModel.currentTheme.collectAsState()
    var currentTab by remember { mutableStateOf<NavItem>(NavItem.Tasks) }
    var showAddBottomSheet by remember { mutableStateOf(false) }
    var selectedTaskForEdit by remember { mutableStateOf<Task?>(null) }

    val navItems = listOf(
        NavItem.Tasks,
        NavItem.Kanban,
        NavItem.Calendar,
        NavItem.Focus,
        NavItem.Analytics,
        NavItem.Settings
    )

    MyTasksProTheme(themeMode = currentTheme) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    navItems.forEach { item ->
                        NavigationBarItem(
                            selected = currentTab == item,
                            onClick = { currentTab = item },
                            icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                            label = { Text(item.title) }
                        )
                    }
                }
            },
            floatingActionButton = {
                if (currentTab == NavItem.Tasks || currentTab == NavItem.Kanban || currentTab == NavItem.Calendar) {
                    FloatingActionButton(
                        onClick = { showAddBottomSheet = true },
                        containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task")
                    }
                }
            }
        ) { paddingValues ->
            Surface(modifier = Modifier.padding(paddingValues)) {
                when (currentTab) {
                    NavItem.Tasks -> TasksScreen(viewModel = viewModel, onTaskClick = { selectedTaskForEdit = it })
                    NavItem.Kanban -> KanbanScreen(viewModel = viewModel, onTaskClick = { selectedTaskForEdit = it })
                    NavItem.Calendar -> CalendarScreen(viewModel = viewModel, onTaskClick = { selectedTaskForEdit = it })
                    NavItem.Focus -> PomodoroScreen(viewModel = viewModel)
                    NavItem.Analytics -> AnalyticsScreen(viewModel = viewModel)
                    NavItem.Settings -> SettingsScreen(viewModel = viewModel)
                }
            }

            if (showAddBottomSheet) {
                AddEditTaskBottomSheet(
                    onDismissRequest = { showAddBottomSheet = false },
                    onSaveTask = { title, desc, cat, priority, dueDate, dueTime, subtasksJson, reminder ->
                        viewModel.addTask(title, desc, cat, priority, dueDate, dueTime, subtasksJson, reminder)
                    }
                )
            }
        }
    }
}
