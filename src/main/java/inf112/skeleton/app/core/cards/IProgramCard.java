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


public interface IProgramCard extends java.io.Serializable {

    int priority();

    IProgramCard copy();
}
