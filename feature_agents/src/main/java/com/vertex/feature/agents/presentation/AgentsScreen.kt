package com.vertex.feature.agents.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vertex.core.common.model.AgentProgress

@Composable
fun AgentsScreen(agents: List<AgentProgress>, modifier: Modifier = Modifier) {
    Column(modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Agentes trabalhando")
        agents.forEach { agent ->
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(agent.name)
                    Text("${agent.status} · ${agent.currentStep} · ${agent.elapsedSeconds}s")
                    LinearProgressIndicator(progress = { agent.progress }, modifier = Modifier.fillMaxWidth())
                    agent.partialResult?.let { Text(it) }
                }
            }
        }
    }
}
