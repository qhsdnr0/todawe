package todawe.todawe.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStatus is a Querydsl query type for Status
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QStatus extends BeanPath<Status> {

    private static final long serialVersionUID = -1567260297L;

    public static final QStatus status1 = new QStatus("status1");

    public final EnumPath<UserFeeling> feeling = createEnum("feeling", UserFeeling.class);

    public final StringPath message = createString("message");

    public final EnumPath<UserStatus> status = createEnum("status", UserStatus.class);

    public QStatus(String variable) {
        super(Status.class, forVariable(variable));
    }

    public QStatus(Path<? extends Status> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStatus(PathMetadata metadata) {
        super(Status.class, metadata);
    }

}

