package vertex.seed

/**
 * Initial Seed configuration flags.
 */
data class SeedConfig(
    val debugMode: Boolean = false,
    val safeMode: Boolean = false,
    val performanceMode: Boolean = false,
)
