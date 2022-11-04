package com.example.baemin.livedata

import androidx.lifecycle.Observer


class LiveDataTestObserver<T> : Observer<T> {

    private val values: MutableList<T> = mutableListOf()

    override fun onChanged(t: T) {
        values.add(t)
    }

    fun assertValuesSequence(sequence: List<T>): LiveDataTestObserver<T> {
        var i = 0
        val actualIterator = values.iterator()
        val expectedIterator = sequence.iterator()

        var actulaNext: Boolean
        var expectedNext: Boolean

        while (true) {
            actulaNext = actualIterator.hasNext()
            expectedNext = expectedIterator.hasNext()

            if (!actulaNext || !expectedNext) break

            val actual: T = actualIterator.next()
            val expected: T = expectedIterator.next()

            if (actual != expected) {
                throw AssertionError("actual: ${actual}, expected: ${expected}, index: $i")
            }

            i++
        }

        if (actulaNext) {
            throw AssertionError("More values received than expected ($i)")
        }

        if (expectedNext) {
            throw AssertionError("Fewer values received than expected ($i)")
        }

        return this
    }
}