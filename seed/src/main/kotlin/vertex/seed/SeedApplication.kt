package vertex.seed

import android.app.Application

/**
 * Android process entry point for Vertex Seed.
 */
class SeedApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Seed.start()
    }
}
