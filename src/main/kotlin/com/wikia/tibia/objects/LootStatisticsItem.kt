package com.wikia.tibia.objects

import com.google.common.annotations.VisibleForTesting
import org.slf4j.LoggerFactory
import java.util.Objects
import kotlin.math.max
import kotlin.math.min

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
            return computeMax(left, right)
        }

        // if both are a range, take the lowest to the highest as new range
        if (left.matches(NUMBER_RANGE_REGEX) && right.matches(NUMBER_RANGE_REGEX)) {
            return computeLowestToHighest(left, right)
        }

        // if one is a single number and the other a range, compute the range taking the single number into account
        if ((left.matches(NUMBER_REGEX) && right.matches(NUMBER_RANGE_REGEX)) ||
            (left.matches(NUMBER_RANGE_REGEX) && right.matches(NUMBER_REGEX))
        ) {
            return computeWithMixOfRangeAndNumber(left, right)
        }

        logger.warn("Could not correctly sum amounts, neither left ($left) nor right ($right) is a valid number.")
        return ""
    }

    companion object {
        private val logger = LoggerFactory.getLogger(LootStatisticsItem::class.java)
        private val NUMBER_REGEX = Regex("""^(\d+)$""")
        private val NUMBER_RANGE_REGEX = Regex("""^(\d+)-(\d+)$""")

        @VisibleForTesting
        fun computeMax(left: String, right: String): String {
            val leftMatches = getMatches(NUMBER_REGEX, left)
            val leftUpper = leftMatches[0]
            val rightMatches = getMatches(NUMBER_REGEX, right)
            val rightUpper = rightMatches[0]
            val newUpper = max(leftUpper.toInt(), rightUpper.toInt())
            return newUpper.toString()
        }

        @VisibleForTesting
        fun computeLowestToHighest(left: String, right: String): String {
            var leftMatches = getMatches(NUMBER_RANGE_REGEX, left)
            leftMatches = leftMatches[0].split(Regex("-"))
            val leftLower = leftMatches[0]
            val leftUpper = leftMatches[1]
            var rightMatches = getMatches(NUMBER_RANGE_REGEX, right)
            rightMatches = rightMatches[0].split(Regex("-"))
            val rightLower = rightMatches[0]
            val rightUpper = rightMatches[1]
            val newLower = min(leftLower.toInt(), rightLower.toInt())
            val newUpper = max(leftUpper.toInt(), rightUpper.toInt())
            return "$newLower-$newUpper"
        }

        @VisibleForTesting
        fun computeWithMixOfRangeAndNumber(left: String, right: String): String {
            if (left.matches(NUMBER_REGEX)) {
                val leftMatches = getMatches(NUMBER_REGEX, left)
                val leftNumber = leftMatches[0]
                var rightMatches = getMatches(NUMBER_RANGE_REGEX, right)
                rightMatches = rightMatches[0].split(Regex("-"))
                val rightLower = rightMatches[0]
                val rightUpper = rightMatches[1]
                val newLower = min(leftNumber.toInt(), rightLower.toInt())
                val newUpper = max(leftNumber.toInt(), rightUpper.toInt())
                return "$newLower-$newUpper"
            }
            if (right.matches(NUMBER_REGEX)) {
                val rightMatches = getMatches(NUMBER_REGEX, right)
                val rightNumber = rightMatches[0]
                var leftMatches = getMatches(NUMBER_RANGE_REGEX, left)
                leftMatches = leftMatches[0].split(Regex("-"))
                val leftLower = leftMatches[0]
                val leftUpper = leftMatches[1]
                val newLower = min(rightNumber.toInt(), leftLower.toInt())
                val newUpper = max(rightNumber.toInt(), leftUpper.toInt())
                return "$newLower-$newUpper"
            }
            return ""
        }

        private fun getMatches(regex: Regex, left: String): List<String> {
            return regex
                .findAll(left)
                .map { it.value }
                .toList()
        }
    }
}
