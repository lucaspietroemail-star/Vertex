package com.vertex.feature.chat.data

import com.vertex.core.common.model.TaskRequest
import com.vertex.core.common.model.TaskUpdate
import com.vertex.core.database.LocalCache
import com.vertex.core.network.MultiAgentApiClient
import com.vertex.feature.chat.domain.ChatRepository
import kotlinx.coroutines.flow.Flow

class ChatRepositoryImpl(
    private val apiClient: MultiAgentApiClient,
    private val localCache: LocalCache,
) : ChatRepository {
    override suspend fun submit(request: TaskRequest): TaskUpdate {
        require(request.objective.isNotBlank()) { "Objective is required" }
        val update = apiClient.submitTask(request)
        localCache.saveMessages(update.messages)
        return update
    }

    override fun stream(taskId: String): Flow<TaskUpdate> = apiClient.streamTask(taskId)
}
