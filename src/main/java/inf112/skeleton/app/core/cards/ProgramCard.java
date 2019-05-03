package inf112.skeleton.app.core.cards;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MoveCard.class, name = "move"),
        @JsonSubTypes.Type(value = RotateCard.class, name = "rotate"),
        @JsonSubTypes.Type(value = ProgramCard.class, name = "card"),
        @JsonSubTypes.Type(value = IProgramCard.class, name = "Icard")
})

public abstract class ProgramCard implements  IProgramCard {

    private final int priority;

            public ProgramCard(int priority) {
        this.priority = priority;
    }

    public int priority() {
        return this.priority;
    }
}
