package vertex.seed

/**
 * Lifecycle states for the minimal Vertex Seed process.
 */
enum class SeedState {
    CREATED,
    INITIALIZING,
    BOOTING,
    CORE_LOADING,
    READY,
    FAILED,
}
