package com.vertex.core.database

import com.vertex.core.common.model.ChatMessage
import com.vertex.core.common.model.ProjectSummary

interface LocalCache {
    suspend fun recentMessages(): List<ChatMessage>
    suspend fun saveMessages(messages: List<ChatMessage>)
    suspend fun projects(): List<ProjectSummary>
}

class InMemoryLocalCache : LocalCache {
    private val messages = mutableListOf<ChatMessage>()
    private val projects = mutableListOf(
        ProjectSummary("p1", "Sistema Multiagente", 3, 8, "Hoje"),
        ProjectSummary("p2", "Assistente pessoal", 2, 4, "Ontem"),
    )

    override suspend fun recentMessages(): List<ChatMessage> = messages.toList()

    override suspend fun saveMessages(messages: List<ChatMessage>) {
        this.messages += messages
    }

    override suspend fun projects(): List<ProjectSummary> = projects.toList()
}
