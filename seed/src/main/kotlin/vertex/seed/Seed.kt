package vertex.seed

/**
 * Central controller for the Vertex Seed lifecycle.
 */
object Seed {
    val config: SeedConfig = SeedConfig()
    var state: SeedState = SeedState.CREATED
        private set

    fun start() {
        Bootstrap.run(config)
    }

    internal fun updateState(nextState: SeedState) {
        state = nextState
    }
}
