package com.example.mytaskspro.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mytaskspro.data.KanbanStatus
import com.example.mytaskspro.data.Task
import com.example.mytaskspro.ui.components.TaskItemCard
import com.example.mytaskspro.ui.viewmodel.TaskViewModel

@Composable
fun KanbanScreen(
    viewModel: TaskViewModel,
    onTaskClick: (Task) -> Unit
) {
    val tasks by viewModel.tasksStream.collectAsState()
    val columns = KanbanStatus.values()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "📊 Kanban Board View",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .horizontalScroll(scrollState)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            columns.forEach { status ->
                val columnTasks = tasks.filter { it.kanbanStatus == status.name }

                Surface(
                    modifier = Modifier
                        .width(300.dp)
                        .fillMaxHeight()
                        .padding(bottom = 70.dp),
                    shape = RoundedCornerShape(18.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                    tonalElevation = 2.dp
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = status.label,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Badge {
                                Text(
                                    text = columnTasks.size.toString(),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(columnTasks, key = { it.id }) { task ->
                                Column {
                                    TaskItemCard(
                                        task = task,
                                        onToggleComplete = { viewModel.toggleTaskCompletion(task) },
                                        onDeleteTask = { viewModel.deleteTask(task) },
                                        onClick = { onTaskClick(task) }
                                    )

                                    // Quick Status Move Chips
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 12.dp, vertical = 2.dp),
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        columns.filter { it != status }.forEach { targetStatus ->
                                            TextButton(
                                                onClick = { viewModel.updateKanbanStatus(task, targetStatus.name) },
                                                contentPadding = PaddingValues(horizontal = 6.dp, vertical = 0.dp)
                                            ) {
                                                Text(
                                                    text = "→ ${targetStatus.label}",
                                                    style = MaterialTheme.typography.labelSmall
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
