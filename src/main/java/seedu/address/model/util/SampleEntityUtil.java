package seedu.address.model.util;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.entity.Entity;

/**
 * Contains utility methods for populating {@code EntityReference} with sample data.
 */
public class SampleEntityUtil {

    private SampleEntityUtil() {} // Prevent instantiation

    /**
     * Returns a list of sample entities.
     */
    public static List<Entity> getSampleEntities() {
        List<Entity> entities = new ArrayList<>();

        entities.add(new Entity("Ahri", "/images/entities/ahri.png"));
        entities.add(new Entity("Garen", "/images/entities/garen.png"));
        entities.add(new Entity("Lux", "/images/entities/lux.png"));
        entities.add(new Entity("Ashe", "/images/entities/ashe.png"));
        entities.add(new Entity("Jinx", "/images/entities/jinx.png"));
        entities.add(new Entity("Yasuo", "/images/entities/yasuo.png"));
        entities.add(new Entity("Lee Sin", "/images/entities/leesin.png"));
        entities.add(new Entity("Thresh", "/images/entities/thresh.png"));

        return entities;
    }
}