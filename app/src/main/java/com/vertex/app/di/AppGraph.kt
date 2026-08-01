package com.vertex.app.di

import com.vertex.core.database.InMemoryLocalCache
import com.vertex.core.database.LocalCache
import com.vertex.core.network.DemoMultiAgentApiClient
import com.vertex.core.network.MultiAgentApiClient
import com.vertex.core.security.InMemorySecureSessionManager
import com.vertex.core.security.SecureSessionManager
import com.vertex.feature.chat.data.ChatRepositoryImpl
import com.vertex.feature.chat.domain.ChatRepository
import com.vertex.feature.chat.domain.ObserveTaskUseCase
import com.vertex.feature.chat.domain.SendTaskUseCase

class AppGraph {
    private val apiClient: MultiAgentApiClient = DemoMultiAgentApiClient()
    private val localCache: LocalCache = InMemoryLocalCache()
    val secureSessionManager: SecureSessionManager = InMemorySecureSessionManager()

    private val chatRepository: ChatRepository = ChatRepositoryImpl(apiClient, localCache)
    val sendTaskUseCase = SendTaskUseCase(chatRepository)
    val observeTaskUseCase = ObserveTaskUseCase(chatRepository)
}
