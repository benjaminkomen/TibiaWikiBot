package com.wikia.tibia.objects;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class LootStatisticsItem {

    private static final String NUMBER_REGEX = "^(\\d+)$";
    private static final Pattern NUMBER_PATTERN = Pattern.compile(NUMBER_REGEX);
    private static final String NUMBER_RANGE_REGEX = "^(\\d+)-(\\d+)$";
    private static final Pattern NUMBER_RANGE_PATTERN = Pattern.compile(NUMBER_RANGE_REGEX);
    private String itemName;
    private String times;
    private String amount;
    private String total;

    @Override
    public boolean equals(Object other) {
        return other instanceof LootStatisticsItem &&
                Objects.equals(itemName, ((LootStatisticsItem) other).getItemName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName);
    }

    @Override
    public String toString() {
        return "LootStatisticsItem{itemName='" + itemName + "'}";
    }

    public LootStatisticsItem add(LootStatisticsItem other) {
        return LootStatisticsItem.builder()
                .itemName(other.getItemName())
                .times(sumStringsAsInt(this.times, other.getTimes()))
                .total(sumStringsAsInt(this.total, other.getTotal()))
                .amount(sumAmounts(this.amount, other.getAmount()))
                .build();
    }

    private String sumStringsAsInt(String left, String right) {
        if (left == null && right == null) {
            return "";
        } else if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        }
        return String.valueOf(Integer.parseInt(left) + Integer.parseInt(right));
    }

    private String sumAmounts(String left, String right) {

        if (left == null && right == null) {
            return "";
        } else if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        }

        // if both are a single number, take the maximum
        if (left.matches(NUMBER_REGEX) && right.matches(NUMBER_REGEX)) {
            String[] leftMatches = getMatches(NUMBER_PATTERN, left);
            var leftUpper = leftMatches[0];

            String[] rightMatches = getMatches(NUMBER_PATTERN, right);
            var rightUpper = rightMatches[0];

            var newUpper = Math.max(Integer.parseInt(leftUpper), Integer.parseInt(rightUpper));

            return String.valueOf(newUpper);
        }

        // if both are a range, take the lowest to the highest as new range
        if (left.matches(NUMBER_RANGE_REGEX) && right.matches(NUMBER_RANGE_REGEX)) {
            String[] leftMatches = getMatches(NUMBER_RANGE_PATTERN, left);
            leftMatches = leftMatches[0].split("-");
            var leftLower = leftMatches[0];
            var leftUpper = leftMatches[1];
            String[] rightMatches = getMatches(NUMBER_RANGE_PATTERN, right);
            rightMatches = rightMatches[0].split("-");
            var rightLower = rightMatches[0];
            var rightUpper = rightMatches[1];

            var newLower = Math.min(Integer.parseInt(leftLower), Integer.parseInt(rightLower));
            var newUpper = Math.max(Integer.parseInt(leftUpper), Integer.parseInt(rightUpper));

            return String.format("%s-%s", newLower, newUpper);
        }

        // if one is a single number and the other a range, compute the range taking the single number into account
        if ((left.matches(NUMBER_REGEX) && right.matches(NUMBER_RANGE_REGEX)) ||
                (left.matches(NUMBER_RANGE_REGEX) && right.matches(NUMBER_REGEX))) {

            if (left.matches(NUMBER_REGEX)) {
                String[] leftMatches = getMatches(NUMBER_PATTERN, left);
                var leftNumber = leftMatches[0];

                String[] rightMatches = getMatches(NUMBER_RANGE_PATTERN, right);
                rightMatches = rightMatches[0].split("-");
                var rightLower = rightMatches[0];
                var rightUpper = rightMatches[1];

                var newLower = Math.min(Integer.parseInt(leftNumber), Integer.parseInt(rightLower));
                var newUpper = Math.max(Integer.parseInt(leftNumber), Integer.parseInt(rightUpper));

                return String.format("%s-%s", newLower, newUpper);
            }

            if (right.matches(NUMBER_REGEX)) {
                String[] rightMatches = getMatches(NUMBER_PATTERN, right);
                var rightNumber = rightMatches[0];

                String[] leftMatches = getMatches(NUMBER_RANGE_PATTERN, left);
                leftMatches = leftMatches[0].split("-");
                var leftLower = leftMatches[0];
                var leftUpper = leftMatches[1];

                var newLower = Math.min(Integer.parseInt(rightNumber), Integer.parseInt(leftLower));
                var newUpper = Math.max(Integer.parseInt(rightNumber), Integer.parseInt(leftUpper));

                return String.format("%s-%s", newLower, newUpper);
            }
        }

        log.warn("Could not correctly sum amounts, neither left ({}) nor right ({}) is a valid number.", left, right);
        return "";
    }

    private String[] getMatches(Pattern pattern, String left) {
        return pattern
                .matcher(left)
                .results()
                .map(MatchResult::group)
                .toArray(String[]::new);
    }
}