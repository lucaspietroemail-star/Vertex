package com.vertex.core.security

interface SecureSessionManager {
    suspend fun saveToken(token: String)
    suspend fun token(): String?
    suspend fun clear()
}

class InMemorySecureSessionManager : SecureSessionManager {
    private var encryptedToken: String? = null

    override suspend fun saveToken(token: String) {
        require(token.startsWith("Bearer ")) { "Tokens must use explicit Bearer scheme" }
        encryptedToken = token.reversed()
    }

    override suspend fun token(): String? = encryptedToken?.reversed()

    override suspend fun clear() {
        encryptedToken = null
    }
}
