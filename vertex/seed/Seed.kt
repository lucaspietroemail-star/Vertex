package vertex.seed

/**
 * Central controller for the Vertex Seed lifecycle.
 */
object Seed {
    val config: SeedConfig = SeedConfig()
    val state: SeedState = SeedState.CREATED

    fun start() {
        Bootstrap.run()
    }
}
