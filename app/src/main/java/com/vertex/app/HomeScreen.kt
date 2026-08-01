package com.vertex.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vertex.core.common.model.AgentProgress
import com.vertex.core.common.model.ProjectSummary

@Composable
fun HomeScreen(
    projects: List<ProjectSummary>,
    agents: List<AgentProgress>,
    onNewTask: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Olá")
        Text("Sua equipe de IA está pronta para novas tarefas complexas.")
        Button(onClick = onNewTask) { Text("Nova tarefa +") }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Projetos ativos")
                projects.take(2).forEach { Text("• ${it.name}") }
            }
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Agentes ativos")
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    agents.take(3).forEach { AssistChip(onClick = {}, label = { Text("● ${it.name}") }) }
                }
            }
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("Tarefas recentes")
                Text("Criar arquitetura Android · Revisar artefatos · Sincronizar memória")
            }
        }
    }
}
