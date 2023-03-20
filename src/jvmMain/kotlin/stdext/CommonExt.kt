package stdext

import kotlinx.coroutines.delay

suspend fun suspendLoop(durationInMillis: Long, action: () -> Unit): Nothing {
    while (true) {
        action()
        delay(durationInMillis)
    }
}