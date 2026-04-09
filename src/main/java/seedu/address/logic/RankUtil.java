package seedu.address.logic;

import java.util.List;

import seedu.address.model.person.Person;
import seedu.address.model.person.Rank;
import seedu.address.model.person.Rank.Division;
import seedu.address.model.person.Rank.RankTier;

/**
 * Utility class for rank-related calculations.
 */
public class RankUtil {

    /**
     * Calculates the average rank of a list of players.
     * Each rank is converted to a numeric value based on tier and division,
     * then the average is calculated and converted back to a rank representation.
     *
     * @param players list of players
     * @return a string representation of the average rank
     */
    public static String calculateAverageRank(List<Person> players) {
        if (players.isEmpty()) {
            return "N/A";
        }

        double totalRankValue = 0;
        for (Person player : players) {
            totalRankValue += rankToNumericValue(player.getRank());
        }

        double averageValue = totalRankValue / players.size();
        return numericValueToRank(averageValue);
    }

    /**
     * Converts a rank to its numeric value.
     * Formula: tierValue * 4 + (3 - divisionValue)
     * Division I (0) is higher rank than Division IV (3), so we invert the division value.
     * This creates a continuous scale where higher numbers = better ranks.
     *
     * @param rank the rank to convert
     * @return numeric value of the rank
     */
    private static double rankToNumericValue(Rank rank) {
        double tierValue = rank.tier.getTierValue() * 4;
        double divisionValue = rank.division != null ? (3 - rank.division.getDivisionValue()) : 0;
        return tierValue + divisionValue;
    }

    /**
     * Converts a numeric rank value back to a readable rank string.
     * Rounds to the nearest valid rank and handles special cases for top ranks.
     *
     * @param value numeric rank value
     * @return rank string representation
     */
    private static String numericValueToRank(double value) {
        // Round to nearest integer
        int rounded = Math.round((float) value);

        // Calculate tier and division from rounded value
        int tierIndex = rounded / 4;
        int divisionIndex = rounded % 4;

        // Ensure bounds
        if (tierIndex < 0) {
            tierIndex = 0;
        }
        if (tierIndex >= RankTier.values().length) {
            tierIndex = RankTier.values().length - 1;
        }

        RankTier tier = RankTier.values()[tierIndex];

        // Top ranks (MASTER and above) have no divisions
        if (tier.getTierValue() >= 6) {
            return tier.toString();
        }

        // Since we use (3 - divisionValue) in the formula, we need to invert it back
        int actualDivisionIndex = Math.max(0, 3 - divisionIndex);
        Division division = Division.values()[Math.min(actualDivisionIndex, 3)];
        return tier.toString() + " " + division.toString();
    }
}
