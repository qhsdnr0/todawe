package todawe.todawe.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import todawe.todawe.model.Like;
import todawe.todawe.model.User;
import todawe.todawe.model.QLike;
import todawe.todawe.model.QUser;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LikeQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    QUser Quser = QUser.user;
    QLike Qlike = QLike.like;

    public long getLikeCount(User user) {
        return jpaQueryFactory.select(Qlike).where(Qlike.takeUser.eq(user)).fetchCount();
    }

    public List<User> getLikeUser(User user) {
        return jpaQueryFactory.selectFrom(Qlike.sendUser).where(Qlike.takeUser.eq(user)).fetch();
    }

    public Like getLike(User sendUser, User takeUser) {
        return jpaQueryFactory.selectFrom(Qlike).where(Qlike.sendUser.eq(sendUser).and(Qlike.takeUser.eq(takeUser))).fetchOne();
    }
}
