package com.wikia.tibia.objects;

import lombok.*;

import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class LootStatisticsItem {

    private final String NUMBER_REGEX = "(\\d+)";
    private final String NUMBER_RANGE_REGEX = "(\\d+)-(\\d+)";
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
        return String.valueOf(Integer.parseInt(left) + Integer.parseInt(right));
    }

    private String sumAmounts(String left, String right) {
        // if both are a single number, take the maximum
        if (left.matches(NUMBER_REGEX) && right.matches(NUMBER_REGEX)) {
            String[] leftMatches = getMatches(left);
            var leftUpper = leftMatches[0];

            String[] rightMatches = getMatches(right);
            var rightUpper = rightMatches[0];

            var newUpper = Math.max(Integer.parseInt(leftUpper), Integer.parseInt(rightUpper));

            return String.valueOf(newUpper);
        }

        // if both are a range, take the lowest to the highest as new range
        if (left.matches(NUMBER_RANGE_REGEX) && right.matches(NUMBER_RANGE_REGEX)) {
            String[] leftMatches = getMatches(left);
            var leftLower = leftMatches[0];
            var leftUpper = leftMatches[1];
            String[] rightMatches = getMatches(right);
            var rightLower = rightMatches[0];
            var rightUpper = rightMatches[1];

            var newLower = Math.min(Integer.parseInt(leftLower), Integer.parseInt(rightLower));
            var newUpper = Math.max(Integer.parseInt(leftUpper), Integer.parseInt(rightUpper));

            return String.format("%s-%s", newLower, newUpper);
        }

        return left; // TODO this is not the best default
    }

    private String[] getMatches(String left) {
        return Pattern.compile(NUMBER_RANGE_REGEX)
                .matcher(left)
                .results()
                .map(MatchResult::group)
                .toArray(String[]::new);
    }
}