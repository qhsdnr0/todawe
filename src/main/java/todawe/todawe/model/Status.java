package todawe.todawe.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
public class Status {

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(length = 2000)
    @Enumerated(EnumType.STRING)
    private UserFeeling feeling;

    private String message;

    protected Status() {}

    public Status(UserStatus status, UserFeeling feeling, String message) {
        this.status = status;
        this.feeling = feeling;
        this.message = message;
    }
}
