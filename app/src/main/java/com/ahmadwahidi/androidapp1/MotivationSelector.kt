package com.ahmadwahidi.androidapp1

import kotlin.random.Random

/**
 * Selects a valid motivation index and avoids showing the same item twice in a row.
 * Keeping this rule separate from the Activity makes the behaviour easy to test.
 */
class MotivationSelector(
    private val itemCount: Int,
    private val randomIndex: (Int) -> Int = { upperBound -> Random.nextInt(upperBound) }
) {
    init {
        require(itemCount > 0) { "At least one motivation is required." }
    }

    /** Returns the next index. A one-item list always returns index zero. */
    fun nextIndex(currentIndex: Int): Int {
        if (itemCount == 1) return 0

        var candidate: Int
        do {
            candidate = randomIndex(itemCount)
        } while (candidate == currentIndex)

        return candidate
    }
}
