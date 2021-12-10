package todawe.todawe.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import todawe.todawe.model.QUser;
import todawe.todawe.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    @PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;
    QUser Quser = QUser.user;

    public void saveUser(User user) { em.persist(user);}

    public User findUser(Long id) { return em.find(User.class, id); }

    public List<User> findByUpdatedAt() {
        return jpaQueryFactory.selectFrom(Quser).where(Quser.updatedAt.between(LocalDateTime.now().minusDays(1), LocalDateTime.now()))
                .orderBy(Quser.updatedAt.desc()).limit(8).offset(0).fetch();
    }

}