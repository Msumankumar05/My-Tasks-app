package com.example.mytaskspro.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytaskspro.ui.viewmodel.TaskViewModel
import kotlinx.coroutines.delay

@Composable
fun PomodoroScreen(viewModel: TaskViewModel) {
    val timeLeft by viewModel.pomodoroTimeLeft.collectAsState()
    val isRunning by viewModel.isPomodoroRunning.collectAsState()
    val mode by viewModel.pomodoroMode.collectAsState()

    LaunchedEffect(isRunning, timeLeft) {
        if (isRunning && timeLeft > 0) {
            delay(1000L)
            viewModel.pomodoroTimeLeft.value = timeLeft - 1
        }
    }

    val minutes = timeLeft / 60
    val seconds = timeLeft % 60
    val timeFormatted = String.format("%02d:%02d", minutes, seconds)

    val maxTime = when (mode) {
        "SHORT_BREAK" -> 5 * 60
        "LONG_BREAK" -> 15 * 60
        else -> 25 * 60
    }
    val progress = timeLeft.toFloat() / maxTime

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "⏱️ Focus Pomodoro Timer",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Mode Switch Chips
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(
                selected = mode == "WORK",
                onClick = { viewModel.resetPomodoro("WORK") },
                label = { Text("Work (25m)") }
            )
            FilterChip(
                selected = mode == "SHORT_BREAK",
                onClick = { viewModel.resetPomodoro("SHORT_BREAK") },
                label = { Text("Short Break (5m)") }
            )
            FilterChip(
                selected = mode == "LONG_BREAK",
                onClick = { viewModel.resetPomodoro("LONG_BREAK") },
                label = { Text("Long Break (15m)") }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Circular Timer Display
        Box(
            modifier = Modifier.size(240.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxSize(),
                strokeWidth = 12.dp,
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = timeFormatted,
                    style = MaterialTheme.typography.headlineLarge.copy(fontSize = 44.sp),
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = mode.replace("_", " "),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Controls
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FloatingActionButton(
                onClick = {
                    if (isRunning) viewModel.pausePomodoro() else viewModel.startPomodoro()
                },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = if (isRunning) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = "Play/Pause"
                )
            }

            IconButton(onClick = { viewModel.resetPomodoro(mode) }) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "Reset Timer")
            }
        }
    }
}
