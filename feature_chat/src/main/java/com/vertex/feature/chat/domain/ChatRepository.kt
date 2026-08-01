package com.vertex.feature.chat.domain

import com.vertex.core.common.model.TaskRequest
import com.vertex.core.common.model.TaskUpdate
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun submit(request: TaskRequest): TaskUpdate
    fun stream(taskId: String): Flow<TaskUpdate>
}

class SendTaskUseCase(private val repository: ChatRepository) {
    suspend operator fun invoke(objective: String, projectId: String? = null): TaskUpdate = repository.submit(
        TaskRequest(objective = objective.trim(), projectId = projectId),
    )
}

class ObserveTaskUseCase(private val repository: ChatRepository) {
    operator fun invoke(taskId: String): Flow<TaskUpdate> = repository.stream(taskId)
}
