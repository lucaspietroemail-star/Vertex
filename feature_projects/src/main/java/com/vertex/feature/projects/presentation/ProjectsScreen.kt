package com.vertex.feature.projects.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vertex.core.common.model.ProjectSummary

@Composable
fun ProjectsScreen(projects: List<ProjectSummary>, modifier: Modifier = Modifier) {
    Column(modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Projetos")
        projects.forEach { project ->
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text(project.name)
                    Text("${project.activeAgents} agentes · ${project.files} arquivos · ${project.lastUpdatedLabel}")
                }
            }
        }
    }
}
