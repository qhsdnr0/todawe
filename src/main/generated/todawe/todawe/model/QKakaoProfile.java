package todawe.todawe.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QKakaoProfile is a Querydsl query type for KakaoProfile
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QKakaoProfile extends BeanPath<KakaoProfile> {

    private static final long serialVersionUID = -1760722645L;

    public static final QKakaoProfile kakaoProfile = new QKakaoProfile("kakaoProfile");

    public final StringPath email = createString("email");

    public final NumberPath<Long> kakaoId = createNumber("kakaoId", Long.class);

    public final StringPath name = createString("name");

    public QKakaoProfile(String variable) {
        super(KakaoProfile.class, forVariable(variable));
    }

    public QKakaoProfile(Path<? extends KakaoProfile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QKakaoProfile(PathMetadata metadata) {
        super(KakaoProfile.class, metadata);
    }

}

