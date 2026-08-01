package com.vertex.feature.chat

import com.vertex.core.common.model.TaskRequest
import com.vertex.core.database.InMemoryLocalCache
import com.vertex.core.network.DemoMultiAgentApiClient
import com.vertex.feature.chat.data.ChatRepositoryImpl
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class ChatRepositoryTest {
    @Test
    fun submitPersistsInitialMessages() = runBlocking {
        val cache = InMemoryLocalCache()
        val repository = ChatRepositoryImpl(DemoMultiAgentApiClient(), cache)

        repository.submit(TaskRequest("Criar aplicativo Android"))

        assertTrue(cache.recentMessages().isNotEmpty())
    }

    @Test
    fun submitRejectsBlankObjective() = runBlocking {
        val repository = ChatRepositoryImpl(DemoMultiAgentApiClient(), InMemoryLocalCache())

        assertFailsWith<IllegalArgumentException> { repository.submit(TaskRequest(" ")) }
    }
}
