package seedu.address.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;

/**
 * Manages a list of Entity objects.
 * Entities are loaded from a JSON file and are not modifiable by users.
 */
public class EntityReference {

    private final List<Entity> entities;

    /**
     * Constructs an EntityReference with the given list of entities.
     * @param entities List of Entity objects to store
     */
    public EntityReference(List<Entity> entities) {
        this.entities = new ArrayList<>(entities);
    }

    /**
     * Returns an unmodifiable list of all entities.
     */
    public List<Entity> getEntities() {
        return Collections.unmodifiableList(entities);
    }

    /**
     * Finds an entity by name.
     * @param name The name of the entity to find
     * @return Optional containing the entity if found, empty otherwise
     */
    public Optional<Entity> findByName(String name) {
        return entities.stream()
                .filter(entity -> entity.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    /**
     * Checks if an entity with the given name exists.
     * @param name The name to check
     * @return true if an entity with the name exists, false otherwise
     */
    public boolean hasEntity(String name) {
        return findByName(name).isPresent();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EntityReference)) {
            return false;
        }

        EntityReference otherEntityReference = (EntityReference) other;
        return entities.equals(otherEntityReference.entities);
    }

    @Override
    public int hashCode() {
        return entities.hashCode();
    }

    @Override
    public String toString() {
        return entities.toString();
    }
}