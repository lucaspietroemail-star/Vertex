package com.vertex.core.security

import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class SecureSessionManagerTest {
    @Test
    fun tokenRoundTripRequiresBearerScheme() = runBlocking {
        val manager = InMemorySecureSessionManager()

        manager.saveToken("Bearer token")

        assertEquals("Bearer token", manager.token())
    }

    @Test
    fun rejectsImplicitTokenScheme() = runBlocking {
        val manager = InMemorySecureSessionManager()

        assertFailsWith<IllegalArgumentException> { manager.saveToken("token") }
    }

    @Test
    fun clearRemovesToken() = runBlocking {
        val manager = InMemorySecureSessionManager()
        manager.saveToken("Bearer token")

        manager.clear()

        assertNull(manager.token())
    }
}
