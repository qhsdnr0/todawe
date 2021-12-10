package todawe.todawe.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -19449200L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final QAddInfo addInfo;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QKakaoProfile kakaoProfile;

    public final ListPath<Comment, QComment> sendComments = this.<Comment, QComment>createList("sendComments", Comment.class, QComment.class, PathInits.DIRECT2);

    public final ListPath<Like, QLike> sendLikes = this.<Like, QLike>createList("sendLikes", Like.class, QLike.class, PathInits.DIRECT2);

    public final ListPath<Comment, QComment> takeComments = this.<Comment, QComment>createList("takeComments", Comment.class, QComment.class, PathInits.DIRECT2);

    public final ListPath<Like, QLike> takeLikes = this.<Like, QLike>createList("takeLikes", Like.class, QLike.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final EnumPath<UserStatus> userStatus = createEnum("userStatus", UserStatus.class);

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.addInfo = inits.isInitialized("addInfo") ? new QAddInfo(forProperty("addInfo")) : null;
        this.kakaoProfile = inits.isInitialized("kakaoProfile") ? new QKakaoProfile(forProperty("kakaoProfile")) : null;
    }

}

