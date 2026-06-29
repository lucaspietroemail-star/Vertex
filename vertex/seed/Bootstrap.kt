package vertex.seed

/**
 * Orchestrates the fixed Seed bootstrap sequence.
 *
 * Runtime and Core loading details are intentionally delegated to their
 * dedicated boundaries.
 */
object Bootstrap {
    fun run() {
        try {
            Seed.updateState(SeedState.INITIALIZING)
            Seed.updateState(SeedState.BOOTING)
            RuntimeInitializer.initMemory()
            RuntimeInitializer.initThreads()
            RuntimeInitializer.initLogging()
            RuntimeInitializer.initEvents()

            Seed.updateState(SeedState.CORE_LOADING)
            CoreLoader.loadCore()
            Seed.updateState(SeedState.READY)
        } catch (failure: Throwable) {
            Seed.updateState(SeedState.FAILED)
            throw failure
        }
    }
}
