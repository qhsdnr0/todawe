package todawe.todawe.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

    @Id @GeneratedValue
    private Long id;

    @Embedded
    private KakaoProfile kakaoProfile;

    @Embedded
    private UserStatus userStatus;

    @Embedded
    private AddInfo addInfo;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "sendUser")
    private List<Comment> sendComments = new ArrayList<>();

    @OneToMany(mappedBy = "takeUser")
    private List<Comment> takeComments = new ArrayList<>();

    @OneToMany(mappedBy = "sendUser")
    private List<Like> sendLikes = new ArrayList<>();

    @OneToMany(mappedBy = "takeUser")
    private List<Like> takeLikes = new ArrayList<>();
}
