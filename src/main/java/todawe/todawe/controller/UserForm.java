package todawe.todawe.controller;

import lombok.Getter;
import lombok.Setter;
import todawe.todawe.model.UserFeeling;
import todawe.todawe.model.UserPosition;
import todawe.todawe.model.UserRole;
import todawe.todawe.model.UserStatus;

@Getter @Setter
public class UserForm {

    private UserRole role;

    private UserPosition position;

    private String generation;

    private UserStatus status;

    private UserFeeling feeling;

    private String message;
}
