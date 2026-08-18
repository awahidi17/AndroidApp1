package com.ahmadwahidi.androidapp1

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertThrows
import org.junit.Test

/** Unit tests for the non-repeating motivation selection rule. */
class MotivationSelectorTest {

    @Test
    fun nextIndex_returnsDifferentIndex_whenRandomFirstRepeatsCurrent() {
        val suppliedIndexes = ArrayDeque(listOf(2, 2, 1))
        val selector = MotivationSelector(itemCount = 3) { suppliedIndexes.removeFirst() }

        val result = selector.nextIndex(currentIndex = 2)

        assertEquals(1, result)
        assertNotEquals(2, result)
    }

    @Test
    fun nextIndex_returnsZero_whenOnlyOneItemExists() {
        val selector = MotivationSelector(itemCount = 1)

        assertEquals(0, selector.nextIndex(currentIndex = 0))
    }

    @Test
    fun constructor_rejectsAnEmptyList() {
        assertThrows(IllegalArgumentException::class.java) {
            MotivationSelector(itemCount = 0)
        }
    }
}
