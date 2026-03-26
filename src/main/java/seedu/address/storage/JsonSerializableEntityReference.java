package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityReference;

/**
 * An Immutable AddressBook that is serializable to JSON format {@code JsonSerializableAddressBook}.
 */
@JsonRootName(value = "entityreference")
class JsonSerializableEntityReference {

    private final List<JsonAdaptedEntity> entities = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableEntityReference} with the given entities.
     */
    @JsonCreator
    public JsonSerializableEntityReference(@JsonProperty("entities") List<JsonAdaptedEntity> entities) {
        if (entities != null) {
            this.entities.addAll(entities);
        }
    }

    /**
     * Converts a given {@code EntityReference} into this class for Jackson use.
     */
    public JsonSerializableEntityReference(EntityReference source) {
        entities.addAll(source.getEntities().stream()
                .map(JsonAdaptedEntity::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code EntityReference} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public EntityReference toModelType() throws IllegalValueException {
        List<Entity> entityList = new ArrayList<>();
        for (JsonAdaptedEntity jsonAdaptedEntity : entities) {
            entityList.add(jsonAdaptedEntity.toModelType());
        }
        return new EntityReference(entityList);
    }

}