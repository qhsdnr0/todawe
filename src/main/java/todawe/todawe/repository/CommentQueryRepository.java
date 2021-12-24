package todawe.todawe.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import todawe.todawe.model.Comment;
import todawe.todawe.model.QComment;
import todawe.todawe.model.QUser;
import todawe.todawe.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;



@Repository
@EnableJpaRepositories
@RequiredArgsConstructor
public class CommentQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    QComment Qcomment = QComment.comment;
    QUser Quser = QUser.user;

    public List<Comment> findByUser(User user) {
        return jpaQueryFactory.selectFrom(Qcomment).join(Qcomment.takeUser, Quser).where(Qcomment.takeUser.eq(user)).fetch();
    }
}
