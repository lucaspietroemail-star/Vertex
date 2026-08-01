package com.vertex.core.common.model

enum class AgentStatus { Idle, Planning, Running, Reviewing, Completed, Failed }
enum class MemoryScope { Temporary, Project, Personal }

data class AgentProgress(
    val id: String,
    val name: String,
    val status: AgentStatus,
    val currentStep: String,
    val progress: Float,
    val elapsedSeconds: Long,
    val partialResult: String? = null,
)

data class ChatMessage(
    val id: String,
    val author: String,
    val markdown: String,
    val isStreaming: Boolean = false,
    val attachments: List<VertexFile> = emptyList(),
)

data class ExecutionPlan(
    val title: String,
    val steps: List<String>,
    val successCriteria: List<String>,
)

data class ProjectSummary(
    val id: String,
    val name: String,
    val activeAgents: Int,
    val files: Int,
    val lastUpdatedLabel: String,
)

data class VertexFile(
    val id: String,
    val name: String,
    val mimeType: String,
    val version: Int,
)

data class UserMemory(
    val id: String,
    val scope: MemoryScope,
    val title: String,
    val content: String,
    val editable: Boolean,
)

data class TaskRequest(val objective: String, val context: String = "", val projectId: String? = null)

data class TaskUpdate(
    val taskId: String,
    val messages: List<ChatMessage>,
    val plan: ExecutionPlan?,
    val agents: List<AgentProgress>,
    val finalAnswer: String? = null,
)
