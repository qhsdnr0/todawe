package todawe.todawe.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import todawe.todawe.config.QuerydslConfig;
import todawe.todawe.model.Comment;
import todawe.todawe.model.*;
import todawe.todawe.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    QUser Quser = QUser.user;
    QComment Qcomment = QComment.comment;

    public List<User> findByUpdatedAt(String generation, int limit, int offset) {
        return jpaQueryFactory.selectFrom(Quser)
                .where(Quser.updatedAt.between(LocalDateTime.now().minusDays(1), LocalDateTime.now()).and(Quser.addInfo.generation.eq(generation)))
                .orderBy(Quser.updatedAt.desc()).limit(limit).offset(offset).fetch();
    }

    public User findUserByKakaoId(Long kakaoId) {
        return jpaQueryFactory.selectFrom(Quser).where(Quser.kakaoProfile.kakaoId.eq(kakaoId)).fetchOne();
    }

//    public List<User> findByGeneration(String generation, String sort) {
//
//        return jpaQueryFactory.selectFrom(Quser).where(Quser.addInfo.generation.eq(generation)).orderBy(Quser.).fetch();
//    }

    public List<User> findByPosition(UserPosition position) {
        return jpaQueryFactory.selectFrom(Quser).where(Quser.addInfo.position.eq(position)).fetch();
    }

    public List<Comment> findCommentsByUser(User takeUser) {
        return jpaQueryFactory.selectFrom(Qcomment).where(Qcomment.takeUser.eq(takeUser)).fetch();
    }
}