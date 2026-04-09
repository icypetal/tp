package seedu.address.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.MatchRecord;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyMatchRecord;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityStatisticMap;
import seedu.address.model.match.Match;
import seedu.address.model.match.PlayerInMatch;
import seedu.address.model.match.PlayersInMatch;
import seedu.address.model.match.Result;
import seedu.address.model.person.Email;
import seedu.address.model.person.InGameName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Rank;
import seedu.address.model.person.Role;
import seedu.address.model.person.statistics.Assists;
import seedu.address.model.person.statistics.Deaths;
import seedu.address.model.person.statistics.Kills;
import seedu.address.model.person.statistics.Statistics;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Daniel Tan"), new Phone("95726840"), new Email("dan.t@gmail.com"),
                new Role("TOP"),
                new InGameName("Revive"), new Rank("CHALLENGER"),
                getTagSet("Resurgence"),
                    new EntityStatisticMap.Builder()
                    .withEntity(new Entity("Rumble"),
                        new Statistics.Builder()
                        .withKills(new Kills("10"))
                        .withDeaths(new Deaths("3"))
                        .withAssists(new Assists("12")).build())
                    .withEntity(new Entity("Poppy"),
                        new Statistics.Builder()
                        .withKills(new Kills("1"))
                        .withDeaths(new Deaths("3"))
                        .withAssists(new Assists("1")).build())
                    .withEntity(new Entity("Kled"),
                        new Statistics.Builder()
                        .withKills(new Kills("0"))
                        .withDeaths(new Deaths("3"))
                        .withAssists(new Assists("0")).build())
                    .withEntity(new Entity("Kennen"),
                        new Statistics.Builder()
                        .withKills(new Kills("0"))
                        .withDeaths(new Deaths("5"))
                        .withAssists(new Assists("0")).build())
                    .withEntity(new Entity("Renekton"),
                        new Statistics.Builder()
                        .withKills(new Kills("4"))
                        .withDeaths(new Deaths("4"))
                        .withAssists(new Assists("9")).build())
                    .withEntity(new Entity("Gragas"),
                        new Statistics.Builder()
                        .withKills(new Kills("2"))
                        .withDeaths(new Deaths("3"))
                        .withAssists(new Assists("0")).build())
                    .build()),
            new Person(new Name("Wayne Aw"), new Phone("90590909"), new Email("wayne.a@gmail.com"),
                new Role("JUNGLE"),
                new InGameName("CraliX"), new Rank("CHALLENGER"),
                getTagSet("TeamRigel"),
                    new EntityStatisticMap.Builder()
                    .withEntity(new Entity("Xin Zhao"),
                        new Statistics.Builder()
                        .withKills(new Kills("9"))
                        .withDeaths(new Deaths("2"))
                        .withAssists(new Assists("10")).build())
                    .withEntity(new Entity("Zed"),
                        new Statistics.Builder()
                        .withKills(new Kills("5"))
                        .withDeaths(new Deaths("1"))
                        .withAssists(new Assists("2")).build())
                    .withEntity(new Entity("Trundle"),
                        new Statistics.Builder()
                        .withKills(new Kills("6"))
                        .withDeaths(new Deaths("3"))
                        .withAssists(new Assists("21")).build())
                    .build()),
            new Person(new Name("Kenneth Goh"), new Phone("90010124"), new Email("kenneth.g@gmail.com"),
                new Role("MID"),
                new InGameName("Raven"), new Rank("CHALLENGER"),
                getTagSet("Resurgence"),
                    new EntityStatisticMap.Builder()
                    .withEntity(new Entity("Zoe"),
                        new Statistics.Builder()
                        .withKills(new Kills("4"))
                        .withDeaths(new Deaths("5"))
                        .withAssists(new Assists("8")).build())
                    .withEntity(new Entity("Akali"),
                        new Statistics.Builder()
                        .withKills(new Kills("2"))
                        .withDeaths(new Deaths("5"))
                        .withAssists(new Assists("1")).build())
                    .withEntity(new Entity("Lucian"),
                        new Statistics.Builder()
                        .withKills(new Kills("6"))
                        .withDeaths(new Deaths("2"))
                        .withAssists(new Assists("4")).build())
                    .withEntity(new Entity("Sett"),
                        new Statistics.Builder()
                        .withKills(new Kills("4"))
                        .withDeaths(new Deaths("6"))
                        .withAssists(new Assists("4")).build())
                    .withEntity(new Entity("Anivia"),
                        new Statistics.Builder()
                        .withKills(new Kills("1"))
                        .withDeaths(new Deaths("6"))
                        .withAssists(new Assists("5")).build())
                    .withEntity(new Entity("Twisted Fate"),
                        new Statistics.Builder()
                        .withKills(new Kills("4"))
                        .withDeaths(new Deaths("2"))
                        .withAssists(new Assists("10")).build())
                    .build()),
            new Person(new Name("Timothy Lim"), new Phone("90330336"), new Email("timothy.l@gmail.com"),
                new Role("BOT"),
                new InGameName("Ciela"), new Rank("CHALLENGER"),
                getTagSet("Resurgence"),
                    new EntityStatisticMap.Builder()
                    .withEntity(new Entity("Lucian"),
                        new Statistics.Builder()
                        .withKills(new Kills("3"))
                        .withDeaths(new Deaths("2"))
                        .withAssists(new Assists("6")).build())
                    .withEntity(new Entity("Cassiopeia"),
                        new Statistics.Builder()
                        .withKills(new Kills("2"))
                        .withDeaths(new Deaths("4"))
                        .withAssists(new Assists("7")).build())
                    .withEntity(new Entity("Vladimir"),
                        new Statistics.Builder()
                        .withKills(new Kills("0"))
                        .withDeaths(new Deaths("0"))
                        .withAssists(new Assists("0")).build())
                    .withEntity(new Entity("Zeri"),
                        new Statistics.Builder()
                        .withKills(new Kills("2"))
                        .withDeaths(new Deaths("4"))
                        .withAssists(new Assists("0")).build())
                    .withEntity(new Entity("Ezreal"),
                        new Statistics.Builder()
                        .withKills(new Kills("6"))
                        .withDeaths(new Deaths("3"))
                        .withAssists(new Assists("18")).build())
                    .withEntity(new Entity("Swain"),
                        new Statistics.Builder()
                        .withKills(new Kills("3"))
                        .withDeaths(new Deaths("4"))
                        .withAssists(new Assists("1")).build())
                    .withEntity(new Entity("Varus"),
                        new Statistics.Builder()
                        .withKills(new Kills("6"))
                        .withDeaths(new Deaths("5"))
                        .withAssists(new Assists("5")).build())
                    .withEntity(new Entity("Caitlyn"),
                        new Statistics.Builder()
                        .withKills(new Kills("1"))
                        .withDeaths(new Deaths("3"))
                        .withAssists(new Assists("1")).build())
                    .build()),
            new Person(new Name("Charles Teo"), new Phone("94143109"), new Email("charles.t@gmail.com"),
                new Role("SUPPORT"),
                new InGameName("Kra"), new Rank("CHALLENGER"),
                getTagSet("Sovereign"),
                    new EntityStatisticMap.Builder()
                    .withEntity(new Entity("Ashe"),
                        new Statistics.Builder()
                        .withKills(new Kills("1"))
                        .withDeaths(new Deaths("3"))
                        .withAssists(new Assists("9")).build())
                    .withEntity(new Entity("Taric"),
                        new Statistics.Builder()
                        .withKills(new Kills("0"))
                        .withDeaths(new Deaths("3"))
                        .withAssists(new Assists("1")).build())
                    .withEntity(new Entity("Rakan"),
                        new Statistics.Builder()
                        .withKills(new Kills("2"))
                        .withDeaths(new Deaths("4"))
                        .withAssists(new Assists("12")).build())
                    .withEntity(new Entity("Thresh"),
                        new Statistics.Builder()
                        .withKills(new Kills("5"))
                        .withDeaths(new Deaths("8"))
                        .withAssists(new Assists("28")).build())
                    .withEntity(new Entity("Janna"),
                        new Statistics.Builder()
                        .withKills(new Kills("1"))
                        .withDeaths(new Deaths("8"))
                        .withAssists(new Assists("2")).build())
                    .withEntity(new Entity("Alistar"),
                        new Statistics.Builder()
                        .withKills(new Kills("0"))
                        .withDeaths(new Deaths("5"))
                        .withAssists(new Assists("7")).build())
                    .withEntity(new Entity("Braum"),
                        new Statistics.Builder()
                        .withKills(new Kills("2"))
                        .withDeaths(new Deaths("11"))
                        .withAssists(new Assists("24")).build())
                    .build()),
            new Person(new Name("Lee Chen Ming"), new Phone("93331874"), new Email("chenming.l@gmail.com"),
                new Role("TOP"),
                new InGameName("Blaire"), new Rank("CHALLENGER"),
                getTagSet("Sovereign"),
                    new EntityStatisticMap.Builder()
                    .withEntity(new Entity("Ornn"),
                        new Statistics.Builder()
                        .withKills(new Kills("0"))
                        .withDeaths(new Deaths("6"))
                        .withAssists(new Assists("3")).build())
                    .withEntity(new Entity("Gragas"),
                        new Statistics.Builder()
                        .withKills(new Kills("1"))
                        .withDeaths(new Deaths("4"))
                        .withAssists(new Assists("2")).build())
                    .build()),
            new Person(new Name("Chen Yi Hui"), new Phone("93101974"), new Email("chen.yh@gmail.com"),
                new Role("TOP"),
                new InGameName("CYH"), new Rank("CHALLENGER"),
                getTagSet("Impunity"),
                    new EntityStatisticMap.Builder()
                    .withEntity(new Entity("Hecarim"),
                        new Statistics.Builder()
                        .withKills(new Kills("16"))
                        .withDeaths(new Deaths("14"))
                        .withAssists(new Assists("32")).build())
                    .withEntity(new Entity("Pantheon"),
                        new Statistics.Builder()
                        .withKills(new Kills("1"))
                        .withDeaths(new Deaths("6"))
                        .withAssists(new Assists("1")).build())
                    .withEntity(new Entity("Udyr"),
                        new Statistics.Builder()
                        .withKills(new Kills("5"))
                        .withDeaths(new Deaths("15"))
                        .withAssists(new Assists("15")).build())
                    .withEntity(new Entity("Taliyah"),
                        new Statistics.Builder()
                        .withKills(new Kills("7"))
                        .withDeaths(new Deaths("9"))
                        .withAssists(new Assists("16")).build())
                    .withEntity(new Entity("Olaf"),
                        new Statistics.Builder()
                        .withKills(new Kills("20"))
                        .withDeaths(new Deaths("12"))
                        .withAssists(new Assists("24")).build())
                    .withEntity(new Entity("Lillia"),
                        new Statistics.Builder()
                        .withKills(new Kills("8"))
                        .withDeaths(new Deaths("11"))
                        .withAssists(new Assists("8")).build())
                    .build()),
            new Person(new Name("Dominic Loh Jun Keong"), new Phone("91014101"), new Email("dominic.ljk@gmail.com"),
                new Role("MID"),
                new InGameName("Arykelic"), new Rank("CHALLENGER"),
                getTagSet("Impunity"),
                    new EntityStatisticMap.Builder()
                    .withEntity(new Entity("Ahri"),
                        new Statistics.Builder()
                        .withKills(new Kills("6"))
                        .withDeaths(new Deaths("5"))
                        .withAssists(new Assists("1")).build())
                    .withEntity(new Entity("Taliyah"),
                        new Statistics.Builder()
                        .withKills(new Kills("6"))
                        .withDeaths(new Deaths("7"))
                        .withAssists(new Assists("7")).build())
                    .withEntity(new Entity("Viktor"),
                        new Statistics.Builder()
                        .withKills(new Kills("8"))
                        .withDeaths(new Deaths("13"))
                        .withAssists(new Assists("2")).build())
                    .withEntity(new Entity("Cassiopeia"),
                        new Statistics.Builder()
                        .withKills(new Kills("1"))
                        .withDeaths(new Deaths("8"))
                        .withAssists(new Assists("5")).build())
                    .withEntity(new Entity("Lissandra"),
                        new Statistics.Builder()
                        .withKills(new Kills("0"))
                        .withDeaths(new Deaths("4"))
                        .withAssists(new Assists("3")).build())
                    .withEntity(new Entity("Swain"),
                        new Statistics.Builder()
                        .withKills(new Kills("8"))
                        .withDeaths(new Deaths("1"))
                        .withAssists(new Assists("9")).build())
                    .withEntity(new Entity("Orianna"),
                        new Statistics.Builder()
                        .withKills(new Kills("0"))
                        .withDeaths(new Deaths("6"))
                        .withAssists(new Assists("2")).build())
                    .withEntity(new Entity("Galio"),
                        new Statistics.Builder()
                        .withKills(new Kills("2"))
                        .withDeaths(new Deaths("5"))
                        .withAssists(new Assists("7")).build())
                    .withEntity(new Entity("Zoe"),
                        new Statistics.Builder()
                        .withKills(new Kills("7"))
                        .withDeaths(new Deaths("3"))
                        .withAssists(new Assists("11")).build())
                    .withEntity(new Entity("Lucian"),
                        new Statistics.Builder()
                        .withKills(new Kills("5"))
                        .withDeaths(new Deaths("3"))
                        .withAssists(new Assists("12")).build())
                    .withEntity(new Entity("LeBlanc"),
                        new Statistics.Builder()
                        .withKills(new Kills("24"))
                        .withDeaths(new Deaths("10"))
                        .withAssists(new Assists("22")).build())
                    .withEntity(new Entity("Ryze"),
                        new Statistics.Builder()
                        .withKills(new Kills("1"))
                        .withDeaths(new Deaths("3"))
                        .withAssists(new Assists("1")).build())
                    .withEntity(new Entity("Zed"),
                        new Statistics.Builder()
                        .withKills(new Kills("11"))
                        .withDeaths(new Deaths("2"))
                        .withAssists(new Assists("1")).build())
                    .withEntity(new Entity("Vladimir"),
                        new Statistics.Builder()
                        .withKills(new Kills("3"))
                        .withDeaths(new Deaths("4"))
                        .withAssists(new Assists("6")).build())
                    .withEntity(new Entity("Azir"),
                        new Statistics.Builder()
                        .withKills(new Kills("9"))
                        .withDeaths(new Deaths("11"))
                        .withAssists(new Assists("16")).build())
                    .build()),
            new Person(new Name("Jordan Lum Whye Kuin"), new Phone("98701234"), new Email("Jordan.lwk@gmail.com"),
                new Role("BOT"),
                new InGameName("Kusuo"), new Rank("CHALLENGER"),
                getTagSet("Resurgence"),
                    new EntityStatisticMap.Builder()
                    .withEntity(new Entity("Nautilus"),
                        new Statistics.Builder()
                        .withKills(new Kills("2"))
                        .withDeaths(new Deaths("8"))
                        .withAssists(new Assists("5")).build())
                    .withEntity(new Entity("Yuumi"),
                        new Statistics.Builder()
                        .withKills(new Kills("0"))
                        .withDeaths(new Deaths("0"))
                        .withAssists(new Assists("18")).build())
                    .withEntity(new Entity("Thresh"),
                        new Statistics.Builder()
                        .withKills(new Kills("0"))
                        .withDeaths(new Deaths("7"))
                        .withAssists(new Assists("17")).build())
                    .withEntity(new Entity("Karma"),
                        new Statistics.Builder()
                        .withKills(new Kills("1"))
                        .withDeaths(new Deaths("2"))
                        .withAssists(new Assists("2")).build())
                    .build()),
            new Person(new Name("Tai Winston"), new Phone("91133112"), new Email("winston.t@gmail.com"),
                new Role("SUPPORT"),
                new InGameName("Shera"), new Rank("CHALLENGER"),
                getTagSet("Impunity"),
                    new EntityStatisticMap.Builder()
                    .withEntity(new Entity("Ahri"),
                        new Statistics.Builder()
                        .withKills(new Kills("6"))
                        .withDeaths(new Deaths("1"))
                        .withAssists(new Assists("12")).build())
                    .withEntity(new Entity("Sejuani"),
                        new Statistics.Builder()
                        .withKills(new Kills("0"))
                        .withDeaths(new Deaths("3"))
                        .withAssists(new Assists("11")).build())
                    .withEntity(new Entity("Neeko"),
                        new Statistics.Builder()
                        .withKills(new Kills("0"))
                        .withDeaths(new Deaths("3"))
                        .withAssists(new Assists("2")).build())
                    .withEntity(new Entity("Azir"),
                        new Statistics.Builder()
                        .withKills(new Kills("5"))
                        .withDeaths(new Deaths("9"))
                        .withAssists(new Assists("6")).build())
                    .build()),
            new Person(new Name("Teo Jia Xiang"), new Phone("91243114"), new Email("jiaxiang.t@gmail.com"),
                new Role("SUPPORT"),
                new InGameName("Blaze"), new Rank("CHALLENGER"),
                getTagSet("Impunity"),
                    new EntityStatisticMap.Builder()
                    .withEntity(new Entity("Xayah"),
                        new Statistics.Builder()
                        .withKills(new Kills("3"))
                        .withDeaths(new Deaths("1"))
                        .withAssists(new Assists("5")).build())
                    .withEntity(new Entity("Tristana"),
                        new Statistics.Builder()
                        .withKills(new Kills("3"))
                        .withDeaths(new Deaths("1"))
                        .withAssists(new Assists("12")).build())
                    .withEntity(new Entity("Zeri"),
                        new Statistics.Builder()
                        .withKills(new Kills("14"))
                        .withDeaths(new Deaths("0"))
                        .withAssists(new Assists("5")).build())
                    .withEntity(new Entity("Ezreal"),
                        new Statistics.Builder()
                        .withKills(new Kills("6"))
                        .withDeaths(new Deaths("2"))
                        .withAssists(new Assists("5")).build())
                    .withEntity(new Entity("Jinx"),
                        new Statistics.Builder()
                        .withKills(new Kills("11"))
                        .withDeaths(new Deaths("0"))
                        .withAssists(new Assists("4")).build())
                    .withEntity(new Entity("Varus"),
                        new Statistics.Builder()
                        .withKills(new Kills("6"))
                        .withDeaths(new Deaths("2"))
                        .withAssists(new Assists("6")).build())
                    .build()),
        };
    }

    public static Match[] getSampleMatches() {
        // Match 1: Resurgence Team - WIN
        PlayerInMatch danielRumble = new PlayerInMatch(
                new InGameName("Revive"),
                new Statistics.Builder()
                .withKills(new Kills("5"))
                .withDeaths(new Deaths("1"))
                .withAssists(new Assists("5")).build(),
                new Entity("Rumble"));
        PlayerInMatch wayneXinZhao = new PlayerInMatch(
                new InGameName("CraliX"),
                new Statistics.Builder()
                .withKills(new Kills("5"))
                .withDeaths(new Deaths("1"))
                .withAssists(new Assists("5")).build(),
                new Entity("Xin Zhao"));
        PlayerInMatch kennethZoe = new PlayerInMatch(
                new InGameName("Raven"),
                new Statistics.Builder()
                .withKills(new Kills("3"))
                .withDeaths(new Deaths("2"))
                .withAssists(new Assists("4")).build(),
                new Entity("Zoe"));
        PlayerInMatch timothyLucian = new PlayerInMatch(
                new InGameName("Ciela"),
                new Statistics.Builder()
                .withKills(new Kills("2"))
                .withDeaths(new Deaths("1"))
                .withAssists(new Assists("3")).build(),
                new Entity("Lucian"));
        PlayerInMatch charlesThresh = new PlayerInMatch(
                new InGameName("Kra"),
                new Statistics.Builder()
                .withKills(new Kills("2"))
                .withDeaths(new Deaths("4"))
                .withAssists(new Assists("14")).build(),
                new Entity("Thresh"));

        // Match 2: Mixed Team - LOSE
        PlayerInMatch danielPoppy = new PlayerInMatch(
                new InGameName("Revive"),
                new Statistics.Builder()
                .withKills(new Kills("1"))
                .withDeaths(new Deaths("2"))
                .withAssists(new Assists("1")).build(),
                new Entity("Poppy"));
        PlayerInMatch wayneZed = new PlayerInMatch(
                new InGameName("CraliX"),
                new Statistics.Builder()
                .withKills(new Kills("3"))
                .withDeaths(new Deaths("1"))
                .withAssists(new Assists("1")).build(),
                new Entity("Zed"));
        PlayerInMatch kennethAkali = new PlayerInMatch(
                new InGameName("Raven"),
                new Statistics.Builder()
                .withKills(new Kills("1"))
                .withDeaths(new Deaths("3"))
                .withAssists(new Assists("1")).build(),
                new Entity("Akali"));
        PlayerInMatch timothyEzreal = new PlayerInMatch(
                new InGameName("Ciela"),
                new Statistics.Builder()
                .withKills(new Kills("4"))
                .withDeaths(new Deaths("2"))
                .withAssists(new Assists("9")).build(),
                new Entity("Ezreal"));
        PlayerInMatch jordanYuumi = new PlayerInMatch(
                new InGameName("Kusuo"),
                new Statistics.Builder()
                .withKills(new Kills("0"))
                .withDeaths(new Deaths("0"))
                .withAssists(new Assists("9")).build(),
                new Entity("Yuumi"));

        // Match 3: Impunity Team - DRAW
        PlayerInMatch chenHecarim = new PlayerInMatch(
                new InGameName("CYH"),
                new Statistics.Builder()
                .withKills(new Kills("8"))
                .withDeaths(new Deaths("7"))
                .withAssists(new Assists("16")).build(),
                new Entity("Hecarim"));
        PlayerInMatch dominicLeBlanc = new PlayerInMatch(
                new InGameName("Arykelic"),
                new Statistics.Builder()
                .withKills(new Kills("12"))
                .withDeaths(new Deaths("5"))
                .withAssists(new Assists("11")).build(),
                new Entity("LeBlanc"));
        PlayerInMatch taiAhri = new PlayerInMatch(
                new InGameName("Shera"),
                new Statistics.Builder()
                .withKills(new Kills("3"))
                .withDeaths(new Deaths("1"))
                .withAssists(new Assists("6")).build(),
                new Entity("Ahri"));
        PlayerInMatch teoZeri = new PlayerInMatch(
                new InGameName("Blaze"),
                new Statistics.Builder()
                .withKills(new Kills("7"))
                .withDeaths(new Deaths("0"))
                .withAssists(new Assists("3")).build(),
                new Entity("Zeri"));
        PlayerInMatch leeOrnn = new PlayerInMatch(
                new InGameName("Blaire"),
                new Statistics.Builder()
                .withKills(new Kills("0"))
                .withDeaths(new Deaths("3"))
                .withAssists(new Assists("2")).build(),
                new Entity("Ornn"));

        PlayersInMatch match1Players = new PlayersInMatch(
                List.of(danielRumble, wayneXinZhao, kennethZoe, timothyLucian, charlesThresh));
        PlayersInMatch match2Players = new PlayersInMatch(
                List.of(danielPoppy, wayneZed, kennethAkali, timothyEzreal, jordanYuumi));
        PlayersInMatch match3Players = new PlayersInMatch(
                List.of(chenHecarim, dominicLeBlanc, taiAhri, teoZeri, leeOrnn));

        return new Match[] {
            new Match(
                    LocalDate.of(2025, 1, 1),
                    new Result("WIN"), match1Players),
            new Match(
                    LocalDate.of(2025, 2, 2),
                    new Result("LOSE"), match2Players),
            new Match(LocalDate.of(2025, 3, 3),
                    new Result("DRAW"), match3Players)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyMatchRecord getSampleMatchRecord() {
        MatchRecord sample = new MatchRecord();
        for (Match sampleMatch : getSampleMatches()) {
            sample.addMatch(sampleMatch);
        }
        return sample;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
