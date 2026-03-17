package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.statistics.Kills;
import seedu.address.model.person.statistics.Statistics;

public class JsonAdaptedStatisticsTest {

    private static final String INVALID_KILLS = "-1";
    private static final String VALID_KILLS = "10";

    @Test
    public void toModelType_validStatisticsDetails_returnsStatistics() throws Exception {
        JsonAdaptedStatistics statistics = new JsonAdaptedStatistics(VALID_KILLS);
        Statistics expectedStatistics = new Statistics.Builder().withKills(new Kills(VALID_KILLS)).build();
        assertEquals(expectedStatistics, statistics.toModelType());
    }

    @Test
    public void toModelType_nullKills_throwsIllegalValueException() {
        JsonAdaptedStatistics statistics = new JsonAdaptedStatistics((String) null);
        String expectedMessage = String.format("Statistics's kills field is missing!");
        assertThrows(IllegalValueException.class, expectedMessage, statistics::toModelType);
    }

    @Test
    public void toModelType_invalidKills_throwsIllegalValueException() {
        JsonAdaptedStatistics statistics = new JsonAdaptedStatistics(INVALID_KILLS);
        String expectedMessage = Kills.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, statistics::toModelType);
    }
}
