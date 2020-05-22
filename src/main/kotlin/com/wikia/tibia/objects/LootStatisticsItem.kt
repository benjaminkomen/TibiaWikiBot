package com.wikia.tibia.objects

import com.google.common.annotations.VisibleForTesting
import org.slf4j.LoggerFactory
import java.util.*
import java.util.regex.MatchResult
import java.util.regex.Pattern
import kotlin.math.max
import kotlin.math.min
import kotlin.streams.toList

data class LootStatisticsItem(
        val itemName: String,
        val times: String,
        val amount: String? = null,
        val total: String? = null
) {
    override fun equals(other: Any?): Boolean {
        return other is LootStatisticsItem &&
                itemName == other.itemName
    }

    override fun hashCode(): Int {
        return Objects.hash(itemName)
    }

    fun add(other: LootStatisticsItem): LootStatisticsItem {
        return LootStatisticsItem(
                itemName = other.itemName,
                times = sumStringsAsInt(times, other.times),
                total = sumStringsAsInt(total, other.total),
                amount = sumAmounts(amount, other.amount)
        )
    }

    private fun sumStringsAsInt(left: String?, right: String?): String {
        if (left == null && right == null) {
            return ""
        } else if (left == null) {
            return right ?: ""
        } else if (right == null) {
            return left
        }
        return (left.toInt() + right.toInt()).toString()
    }

    private fun sumAmounts(left: String?, right: String?): String {
        if (left == null && right == null) {
            return ""
        } else if (left == null) {
            return right ?: ""
        } else if (right == null) {
            return left
        }

        // if both are a single number, take the maximum
        if (left.matches(NUMBER_REGEX) && right.matches(NUMBER_REGEX)) {
            return `compute max of two numbers`(left, right)
        }

        // if both are a range, take the lowest to the highest as new range
        if (left.matches(NUMBER_RANGE_REGEX) && right.matches(NUMBER_RANGE_REGEX)) {
            return `compute lowest to highest of two ranges`(left, right)
        }

        // if one is a single number and the other a range, compute the range taking the single number into account
        if ((left.matches(NUMBER_REGEX) && right.matches(NUMBER_RANGE_REGEX)) ||
                (left.matches(NUMBER_RANGE_REGEX) && right.matches(NUMBER_REGEX))) {
           return `compute with mix of range and number`(left, right)
        }

        logger.warn("Could not correctly sum amounts, neither left ($left) nor right ($right) is a valid number.")
        return ""
    }

    companion object {
        private val logger = LoggerFactory.getLogger(LootStatisticsItem::class.java)
        private val NUMBER_REGEX = Regex("""^(\d+)$""")
        private val NUMBER_PATTERN = NUMBER_REGEX.toPattern()
        private val NUMBER_RANGE_REGEX = Regex("""^(\d+)-(\d+)$""")
        private val NUMBER_RANGE_PATTERN = NUMBER_RANGE_REGEX.toPattern()

        @VisibleForTesting
        fun `compute max of two numbers`(left: String, right: String): String {
            val leftMatches = getMatches(NUMBER_PATTERN, left)
            val leftUpper = leftMatches[0]
            val rightMatches = getMatches(NUMBER_PATTERN, right)
            val rightUpper = rightMatches[0]
            val newUpper = max(leftUpper.toInt(), rightUpper.toInt())
            return newUpper.toString()
        }

        @VisibleForTesting
        fun `compute lowest to highest of two ranges`(left: String, right: String): String {
            var leftMatches = getMatches(NUMBER_RANGE_PATTERN, left)
            leftMatches = leftMatches[0].split(Regex("-")).toTypedArray()
            val leftLower = leftMatches[0]
            val leftUpper = leftMatches[1]
            var rightMatches = getMatches(NUMBER_RANGE_PATTERN, right)
            rightMatches = rightMatches[0].split(Regex("-")).toTypedArray()
            val rightLower = rightMatches[0]
            val rightUpper = rightMatches[1]
            val newLower = min(leftLower.toInt(), rightLower.toInt())
            val newUpper = max(leftUpper.toInt(), rightUpper.toInt())
            return "$newLower-$newUpper"
        }

        @VisibleForTesting
        fun `compute with mix of range and number`(left: String, right: String): String {
            if (left.matches(NUMBER_REGEX)) {
                val leftMatches = getMatches(NUMBER_PATTERN, left)
                val leftNumber = leftMatches[0]
                var rightMatches = getMatches(NUMBER_RANGE_PATTERN, right)
                rightMatches = rightMatches[0].split(Regex("-")).toTypedArray()
                val rightLower = rightMatches[0]
                val rightUpper = rightMatches[1]
                val newLower = min(leftNumber.toInt(), rightLower.toInt())
                val newUpper = max(leftNumber.toInt(), rightUpper.toInt())
                return "$newLower-$newUpper"
            }
            if (right.matches(NUMBER_REGEX)) {
                val rightMatches = getMatches(NUMBER_PATTERN, right)
                val rightNumber = rightMatches[0]
                var leftMatches = getMatches(NUMBER_RANGE_PATTERN, left)
                leftMatches = leftMatches[0].split(Regex("-")).toTypedArray()
                val leftLower = leftMatches[0]
                val leftUpper = leftMatches[1]
                val newLower = min(rightNumber.toInt(), leftLower.toInt())
                val newUpper = max(rightNumber.toInt(), leftUpper.toInt())
                return "$newLower-$newUpper"
            }
            return ""
        }

        private fun getMatches(pattern: Pattern, left: String): Array<String> {
            return pattern
                    .matcher(left)
                    .results()
                    .map(MatchResult::group)
                    .toList()
                    .toTypedArray()
        }
    }
}