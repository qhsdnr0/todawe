package todawe.todawe.model;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
public class AddInfo {

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String position;
    private String generation;

    protected AddInfo() {}

    public AddInfo(UserRole role, String position, String generation) {
        this.role = role;
        this.position = position;
        this.generation = generation;
    }
}
