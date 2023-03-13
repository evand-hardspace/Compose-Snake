package stdext

inline fun <T> List<T>.updatedAt(index: Int, newValue: (oldValue: T) -> T): List<T> =
    toMutableList().also { it[index] = newValue(this[index]) }

inline fun <T> List<T>.updatedAt(indices: List<Int>, newValue: (oldValue: T) -> T): List<T> =
    toMutableList().also { mutableList ->
        indices.forEach { index ->
            mutableList[index] = newValue(this[index])
        }
    }

inline fun <T> List<List<T>>.updatedAt(innerIndex: Int, outIndex: Int, newValue: (oldValue: T) -> T): List<List<T>> =
    updatedAt(outIndex) { line ->
        line.updatedAt(innerIndex) { t -> newValue(t) }
    }