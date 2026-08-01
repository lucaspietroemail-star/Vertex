package com.vertex.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vertex.core.common.model.AgentProgress
import com.vertex.core.common.model.AgentStatus
import com.vertex.core.common.model.MemoryScope
import com.vertex.core.common.model.ProjectSummary
import com.vertex.core.common.model.UserMemory
import com.vertex.core.common.model.VertexFile
import com.vertex.core.designsystem.VertexTheme
import com.vertex.feature.agents.presentation.AgentsScreen
import com.vertex.feature.chat.presentation.ChatScreen
import com.vertex.feature.chat.presentation.ChatViewModel
import com.vertex.app.di.AppGraph
import com.vertex.feature.files.presentation.FilesScreen
import com.vertex.feature.memory.presentation.MemoryScreen
import com.vertex.feature.profile.presentation.ProfileScreen
import com.vertex.feature.projects.presentation.ProjectsScreen
import com.vertex.feature.settings.presentation.SettingsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { VertexApp() }
    }
}

private data class Destination(val route: String, val label: String)

@Composable
fun VertexApp() {
    val destinations = listOf(
        Destination("home", "Home"),
        Destination("chat", "Chat"),
        Destination("agents", "Agentes"),
        Destination("projects", "Projetos"),
        Destination("memory", "Memória"),
        Destination("files", "Arquivos"),
        Destination("settings", "Ajustes"),
    )
    val navController = rememberNavController()
    val appGraph = remember { AppGraph() }
    val chatViewModel = remember {
        ChatViewModel(appGraph.sendTaskUseCase, appGraph.observeTaskUseCase)
    }

    VertexTheme {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    destinations.forEach { destination ->
                        NavigationBarItem(
                            selected = false,
                            onClick = { navController.navigate(destination.route) },
                            icon = {},
                            label = { Text(destination.label) },
                        )
                    }
                }
            },
        ) { padding ->
            NavHost(navController, startDestination = "home", Modifier.padding(padding)) {
                composable("home") {
                    HomeScreen(sampleProjects(), sampleAgents(), onNewTask = { navController.navigate("chat") })
                }
                composable("chat") { ChatScreen(chatViewModel) }
                composable("agents") { AgentsScreen(sampleAgents()) }
                composable("projects") { ProjectsScreen(sampleProjects()) }
                composable("memory") { MemoryScreen(sampleMemories()) }
                composable("files") { FilesScreen(sampleFiles()) }
                composable("settings") { SettingsScreen() }
                composable("profile") { ProfileScreen() }
            }
        }
    }
}

private fun sampleAgents(): List<AgentProgress> = listOf(
    AgentProgress("planner", "Planejador", AgentStatus.Completed, "Plano aprovado", 1f, 42),
    AgentProgress("researcher", "Pesquisador", AgentStatus.Running, "Validando fontes", .8f, 64),
    AgentProgress("coder", "Programador", AgentStatus.Running, "Criando artefatos", .45f, 51),
    AgentProgress("reviewer", "Revisor", AgentStatus.Idle, "Aguardando execução", .1f, 4),
)

private fun sampleProjects(): List<ProjectSummary> = listOf(
    ProjectSummary("p1", "Sistema Multiagente", 4, 12, "Atualizado agora"),
    ProjectSummary("p2", "Assistente pessoal", 2, 5, "Hoje"),
)

private fun sampleMemories(): List<UserMemory> = listOf(
    UserMemory("m1", MemoryScope.Project, "Decisão arquitetural", "Usar app como cliente da plataforma.", true),
    UserMemory("m2", MemoryScope.Personal, "Preferência", "Responder em português.", true),
)

private fun sampleFiles(): List<VertexFile> = listOf(
    VertexFile("f1", "arquitetura.md", "text/markdown", 3),
    VertexFile("f2", "protótipo.png", "image/png", 1),
    VertexFile("f3", "agente.kt", "text/x-kotlin", 2),
)
