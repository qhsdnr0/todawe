package todawe.todawe.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAddInfo is a Querydsl query type for AddInfo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAddInfo extends BeanPath<AddInfo> {

    private static final long serialVersionUID = -592209910L;

    public static final QAddInfo addInfo = new QAddInfo("addInfo");

    public final StringPath generation = createString("generation");

    public final StringPath position = createString("position");

    public final EnumPath<UserRole> role = createEnum("role", UserRole.class);

    public QAddInfo(String variable) {
        super(AddInfo.class, forVariable(variable));
    }

    public QAddInfo(Path<? extends AddInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAddInfo(PathMetadata metadata) {
        super(AddInfo.class, metadata);
    }

}

