package com.vertex.core.network

import com.vertex.core.common.model.AgentProgress
import com.vertex.core.common.model.AgentStatus
import com.vertex.core.common.model.ChatMessage
import com.vertex.core.common.model.ExecutionPlan
import com.vertex.core.common.model.TaskRequest
import com.vertex.core.common.model.TaskUpdate
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MultiAgentApiClient {
    suspend fun submitTask(request: TaskRequest): TaskUpdate
    fun streamTask(taskId: String): Flow<TaskUpdate>
}

class DemoMultiAgentApiClient : MultiAgentApiClient {
    override suspend fun submitTask(request: TaskRequest): TaskUpdate = TaskUpdate(
        taskId = "task-demo",
        messages = listOf(ChatMessage("m1", "user", request.objective)),
        plan = ExecutionPlan(
            title = "Plano multiagente",
            steps = listOf("Classificar", "Planejar", "Executar", "Revisar", "Avaliar"),
            successCriteria = listOf("Resposta útil", "Riscos explícitos", "Artefatos anexados"),
        ),
        agents = demoAgents(0.15f),
    )

    override fun streamTask(taskId: String): Flow<TaskUpdate> = flow {
        val checkpoints = listOf(0.25f, 0.55f, 0.85f, 1f)
        for (progress in checkpoints) {
            delay(80)
            emit(
                TaskUpdate(
                    taskId = taskId,
                    messages = listOf(
                        ChatMessage("assistant-$progress", "assistant", "Atualização ${'$'}{(progress * 100).toInt()}%", progress < 1f),
                    ),
                    plan = null,
                    agents = demoAgents(progress),
                    finalAnswer = if (progress == 1f) "Resultado revisado e avaliado pela equipe de agentes." else null,
                ),
            )
        }
    }

    private fun demoAgents(progress: Float): List<AgentProgress> = listOf(
        AgentProgress("planner", "Planejador", AgentStatus.Planning, "Quebrando tarefa", progress.coerceAtMost(1f), 32),
        AgentProgress("researcher", "Pesquisador", AgentStatus.Running, "Coletando evidências", progress * .9f, 28),
        AgentProgress("coder", "Programador", AgentStatus.Running, "Gerando artefatos", progress * .7f, 20),
        AgentProgress("reviewer", "Revisor", if (progress == 1f) AgentStatus.Completed else AgentStatus.Idle, "Aguardando", progress, 8),
    )
}
