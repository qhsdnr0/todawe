package todawe.todawe.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import todawe.todawe.model.Comment;
import todawe.todawe.model.QComment;
import todawe.todawe.model.QUser;
import todawe.todawe.model.User;
import java.util.List;



@Repository
@RequiredArgsConstructor
public class CommentQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    QComment Qcomment = QComment.comment;
    QUser Quser = QUser.user;

    public List<Comment> findByUser(User user) {
        return jpaQueryFactory.selectFrom(Qcomment).join(Qcomment.takeUser, Quser).where(Qcomment.takeUser.eq(user)).fetch();
    }
}
