package com.vertex.feature.memory.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vertex.core.common.model.UserMemory

@Composable
fun MemoryScreen(memories: List<UserMemory>, modifier: Modifier = Modifier) {
    Column(modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Memória e permissões")
        memories.forEach { memory ->
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text("${memory.scope}: ${memory.title}")
                    Text(memory.content)
                    Switch(checked = memory.editable, onCheckedChange = null)
                }
            }
        }
    }
}
