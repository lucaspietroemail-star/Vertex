package com.vertex.feature.chat.presentation

import com.vertex.core.common.model.AgentProgress
import com.vertex.core.common.model.ChatMessage
import com.vertex.core.common.model.ExecutionPlan
import com.vertex.feature.chat.domain.ObserveTaskUseCase
import com.vertex.feature.chat.domain.SendTaskUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ChatUiState(
    val input: String = "",
    val messages: List<ChatMessage> = emptyList(),
    val plan: ExecutionPlan? = null,
    val agents: List<AgentProgress> = emptyList(),
    val isSending: Boolean = false,
    val error: String? = null,
)

class ChatViewModel(
    private val sendTask: SendTaskUseCase,
    private val observeTask: ObserveTaskUseCase,
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate),
) {
    private val mutableState = MutableStateFlow(ChatUiState())
    val state: StateFlow<ChatUiState> = mutableState

    fun onInputChanged(value: String) {
        mutableState.update { it.copy(input = value) }
    }

    fun send() {
        val objective = state.value.input.trim()
        if (objective.isBlank()) return
        scope.launch {
            mutableState.update { it.copy(isSending = true, error = null) }
            runCatching { sendTask(objective) }
                .onSuccess { firstUpdate ->
                    mutableState.update {
                        it.copy(
                            input = "",
                            messages = it.messages + firstUpdate.messages,
                            plan = firstUpdate.plan,
                            agents = firstUpdate.agents,
                        )
                    }
                    observeTask(firstUpdate.taskId).collect { update ->
                        mutableState.update {
                            it.copy(
                                messages = it.messages + update.messages,
                                agents = update.agents,
                                isSending = update.finalAnswer == null,
                            )
                        }
                    }
                }
                .onFailure { throwable ->
                    mutableState.update { it.copy(isSending = false, error = throwable.message) }
                }
        }
    }
}
