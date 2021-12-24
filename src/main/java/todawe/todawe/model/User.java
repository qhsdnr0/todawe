package todawe.todawe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter @Setter
public class User {

    @Id @GeneratedValue
    private Long id;

    @Embedded
    private KakaoProfile kakaoProfile;

    @Embedded
    private Status status;

    @Embedded
    private AddInfo addInfo;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "sendUser")
    @JsonBackReference
    private List<Comment> sendComments = new ArrayList<>();

    @OneToMany(mappedBy = "takeUser")
    @JsonBackReference
    private List<Comment> takeComments = new ArrayList<>();

    @OneToMany(mappedBy = "sendUser")
    @JsonBackReference
    private List<Like> sendLikes = new ArrayList<>();

    @OneToMany(mappedBy = "takeUser")
    @JsonBackReference
    private List<Like> takeLikes = new ArrayList<>();

    public void addSendComment(Comment comment) {
        sendComments.add(comment);
        comment.setSendUser(this);
    }

    public void addTakeComment(Comment comment) {
        takeComments.add(comment);
        comment.setTakeUser(this);
    }

    public void addSendLike(Like like) {
        sendLikes.add(like);
        like.setSendUser(this);
    }

    public void addTakeLike(Like like) {
        takeLikes.add(like);
        like.setTakeUser(this);
    }
}
