package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class RankUtilTest {

    @Test
    public void calculateAverageRank_singlePlayer_success() {
        Person player = new PersonBuilder().withRank("GOLD I").build();
        String average = RankUtil.calculateAverageRank(List.of(player));
        assertEquals("GOLD I", average);
    }

    @Test
    public void calculateAverageRank_sameRanks_success() {
        Person player1 = new PersonBuilder().withRank("GOLD I").build();
        Person player2 = new PersonBuilder().withRank("GOLD I").build();
        Person player3 = new PersonBuilder().withRank("GOLD I").build();
        String average = RankUtil.calculateAverageRank(List.of(player1, player2, player3));
        assertEquals("GOLD I", average);
    }

    @Test
    public void calculateAverageRank_differentDivisions_roundsCorrectly() {
        // GOLD I (3*4+0=12) + GOLD III (3*4+2=14) = 26/2 = 13 = GOLD II
        Person player1 = new PersonBuilder().withRank("GOLD I").build();
        Person player2 = new PersonBuilder().withRank("GOLD III").build();
        String average = RankUtil.calculateAverageRank(List.of(player1, player2));
        assertEquals("GOLD II", average);
    }

    @Test
    public void calculateAverageRank_differentTiers_success() {
        // SILVER IV (2*4+3=11) + GOLD I (3*4+0=12) = 23/2 = 11.5 -> rounds to 12 = GOLD I
        Person player1 = new PersonBuilder().withRank("SILVER IV").build();
        Person player2 = new PersonBuilder().withRank("GOLD I").build();
        String average = RankUtil.calculateAverageRank(List.of(player1, player2));
        assertEquals("GOLD I", average);
    }

    @Test
    public void calculateAverageRank_multiplePlayersWithTopRanks_success() {
        // PLATINUM IV (4*4+3=19) + DIAMOND I (5*4+0=20) + MASTER (6*4=24)
        // = (19 + 20 + 24) / 3 = 63 / 3 = 21 = 21/4 = 5, 21%4 = 1 = DIAMOND II
        Person player1 = new PersonBuilder().withRank("PLATINUM IV").build();
        Person player2 = new PersonBuilder().withRank("DIAMOND I").build();
        Person player3 = new PersonBuilder().withRank("MASTER").build();
        String average = RankUtil.calculateAverageRank(List.of(player1, player2, player3));
        assertEquals("DIAMOND II", average);
    }

    @Test
    public void calculateAverageRank_fivePlayersTeam_success() {
        Person top = new PersonBuilder().withRank("GOLD I").build();
        Person jungle = new PersonBuilder().withRank("GOLD II").build();
        Person mid = new PersonBuilder().withRank("GOLD III").build();
        Person bot = new PersonBuilder().withRank("PLATINUM I").build();
        Person support = new PersonBuilder().withRank("SILVER IV").build();

        // (12 + 13 + 14 + 16 + 11) / 5 = 66 / 5 = 13.2 -> rounds to 13 = GOLD II
        String average = RankUtil.calculateAverageRank(List.of(top, jungle, mid, bot, support));
        assertEquals("GOLD II", average);
    }

    @Test
    public void calculateAverageRank_allMaster_success() {
        Person player1 = new PersonBuilder().withRank("MASTER").build();
        Person player2 = new PersonBuilder().withRank("MASTER").build();
        Person player3 = new PersonBuilder().withRank("MASTER").build();
        String average = RankUtil.calculateAverageRank(List.of(player1, player2, player3));
        assertEquals("MASTER", average);
    }

    @Test
    public void calculateAverageRank_diamondToMaster_roundsUp() {
        // DIAMOND IV (5*4+3=23) + MASTER (6*4=24) = 47/2 = 23.5 -> rounds to 24 = MASTER
        Person player1 = new PersonBuilder().withRank("DIAMOND IV").build();
        Person player2 = new PersonBuilder().withRank("MASTER").build();
        String average = RankUtil.calculateAverageRank(List.of(player1, player2));
        assertEquals("MASTER", average);
    }

    @Test
    public void calculateAverageRank_ironToGold_success() {
        // IRON I (0*4+0=0) + GOLD I (3*4+0=12) = 12/2 = 6 = BRONZE III (1*4+2)
        Person player1 = new PersonBuilder().withRank("IRON I").build();
        Person player2 = new PersonBuilder().withRank("GOLD I").build();
        String average = RankUtil.calculateAverageRank(List.of(player1, player2));
        assertEquals("BRONZE III", average);
    }

    @Test
    public void calculateAverageRank_emptyList_returnsNA() {
        String average = RankUtil.calculateAverageRank(List.of());
        assertEquals("N/A", average);
    }
}
