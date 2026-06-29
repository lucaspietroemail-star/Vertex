package vertex.seed

/**
 * Boundary for deterministic Seed runtime initialization steps.
 *
 * The prepared runtime base is intentionally minimal and does not operate
 * system features or Core responsibilities.
 */
object RuntimeInitializer {
    private const val DEFAULT_HEAP_LIMIT_BYTES: Long = 16L * 1024L * 1024L
    private const val MAIN_WORKER_NAME: String = "seed-main-worker"

    private var memoryBase: MemoryBase? = null
    private var threadBase: ThreadBase? = null
    private var loggingBase: LoggingBase? = null
    private var eventBase: EventBase? = null

    fun initMemory() {
        memoryBase = MemoryBase(
            heapPrepared = true,
            heapLimitBytes = DEFAULT_HEAP_LIMIT_BYTES,
        )
    }

    fun initThreads() {
        threadBase = ThreadBase(
            mainWorkerName = MAIN_WORKER_NAME,
            schedulerPrepared = true,
        )
    }

    fun initLogging() {
        loggingBase = LoggingBase(
            bootLoggingEnabled = true,
        )
    }

    fun initEvents() {
        eventBase = EventBase(
            bus = SeedEventBus(),
        )
    }

    private data class MemoryBase(
        val heapPrepared: Boolean,
        val heapLimitBytes: Long,
    )

    private data class ThreadBase(
        val mainWorkerName: String,
        val schedulerPrepared: Boolean,
    )

    private data class LoggingBase(
        val bootLoggingEnabled: Boolean,
    )

    private data class EventBase(
        val bus: SeedEventBus,
    )

    private class SeedEventBus {
        private val events = mutableListOf<String>()

        fun publish(event: String) {
            events.add(event)
        }
    }
}
 * Placeholder for deterministic Seed runtime initialization.
 *
 * Runtime behavior is intentionally not implemented in this structural step.
 */
class RuntimeInitializer
