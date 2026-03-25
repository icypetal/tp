package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RankTest {

    @Test
    public void isValidRank() {
        // null rank
        assertFalse(Rank.isValidRank(null));

        // empty rank
        assertFalse(Rank.isValidRank(""));
        assertFalse(Rank.isValidRank("   "));

        // invalid format - multiple spaces
        assertFalse(Rank.isValidRank("GOLD  I"));

        // invalid tier
        assertFalse(Rank.isValidRank("LEGENDARY I"));

        // lower ranks without division
        assertFalse(Rank.isValidRank("GOLD"));
        assertFalse(Rank.isValidRank("SILVER"));
        assertFalse(Rank.isValidRank("PLATINUM"));

        // lower ranks with invalid division
        assertFalse(Rank.isValidRank("GOLD V"));
        assertFalse(Rank.isValidRank("SILVER VIII"));

        // top ranks with division
        assertFalse(Rank.isValidRank("MASTER I"));
        assertFalse(Rank.isValidRank("GRANDMASTER II"));
        assertFalse(Rank.isValidRank("CHALLENGER IV"));

        // valid lower ranks with divisions
        assertTrue(Rank.isValidRank("IRON I"));
        assertTrue(Rank.isValidRank("IRON II"));
        assertTrue(Rank.isValidRank("IRON III"));
        assertTrue(Rank.isValidRank("IRON IV"));

        assertTrue(Rank.isValidRank("BRONZE I"));
        assertTrue(Rank.isValidRank("SILVER II"));
        assertTrue(Rank.isValidRank("GOLD III"));
        assertTrue(Rank.isValidRank("PLATINUM IV"));
        assertTrue(Rank.isValidRank("DIAMOND I"));

        // valid top ranks without divisions
        assertTrue(Rank.isValidRank("MASTER"));
        assertTrue(Rank.isValidRank("GRANDMASTER"));
        assertTrue(Rank.isValidRank("CHALLENGER"));

        // case insensitive
        assertTrue(Rank.isValidRank("gold i"));
        assertTrue(Rank.isValidRank("Gold II"));
        assertTrue(Rank.isValidRank("master"));
    }

    @Test
    public void constructor_validRank_success() {
        // lower ranks with divisions
        Rank goldI = new Rank("GOLD I");
        assertEquals(Rank.RankTier.GOLD, goldI.tier);
        assertEquals(Rank.Division.I, goldI.division);

        Rank silverII = new Rank("SILVER II");
        assertEquals(Rank.RankTier.SILVER, silverII.tier);
        assertEquals(Rank.Division.II, silverII.division);

        Rank diamondIV = new Rank("DIAMOND IV");
        assertEquals(Rank.RankTier.DIAMOND, diamondIV.tier);
        assertEquals(Rank.Division.IV, diamondIV.division);

        // top ranks without divisions
        Rank master = new Rank("MASTER");
        assertEquals(Rank.RankTier.MASTER, master.tier);
        assertEquals(null, master.division);

        Rank challenger = new Rank("CHALLENGER");
        assertEquals(Rank.RankTier.CHALLENGER, challenger.tier);
        assertEquals(null, challenger.division);
    }

    @Test
    public void constructor_invalidRank_throwsIllegalArgumentException() {
        // null rank
        assertThrows(NullPointerException.class, () -> new Rank(null));

        // invalid rank
        assertThrows(IllegalArgumentException.class, () -> new Rank(""));
        assertThrows(IllegalArgumentException.class, () -> new Rank("GOLD"));
        assertThrows(IllegalArgumentException.class, () -> new Rank("MASTER I"));
        assertThrows(IllegalArgumentException.class, () -> new Rank("GOLD V"));
    }

    @Test
    public void toString_success() {
        assertEquals("GOLD I", new Rank("GOLD I").toString());
        assertEquals("SILVER II", new Rank("SILVER II").toString());
        assertEquals("PLATINUM IV", new Rank("PLATINUM IV").toString());
        assertEquals("MASTER", new Rank("MASTER").toString());
        assertEquals("CHALLENGER", new Rank("CHALLENGER").toString());
    }

    @Test
    public void equals() {
        Rank goldI = new Rank("GOLD I");
        Rank goldI2 = new Rank("gold i");
        Rank goldII = new Rank("GOLD II");
        Rank master = new Rank("MASTER");
        Rank master2 = new Rank("master");

        // same rank
        assertEquals(goldI, goldI2);
        assertEquals(master, master2);

        // different ranks
        assertFalse(goldI.equals(goldII));
        assertFalse(goldI.equals(master));
        assertFalse(master.equals(goldI));

        // null
        assertFalse(goldI.equals(null));

        // different type
        assertFalse(goldI.equals(5));
    }

    @Test
    public void hashCode_success() {
        Rank goldI = new Rank("GOLD I");
        Rank goldI2 = new Rank("gold i");
        Rank master = new Rank("MASTER");

        // same rank should have same hashcode
        assertEquals(goldI.hashCode(), goldI2.hashCode());

        // different ranks should (likely) have different hashcode
        assertFalse(goldI.hashCode() == master.hashCode());
    }
}
