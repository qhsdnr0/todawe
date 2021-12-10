package todawe.todawe.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import todawe.todawe.model.Like;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class LikeRepository {

    @PersistenceContext
    private final EntityManager em;

    public void saveLike(Like like) { em.persist(like); }

    public Like findLike(Long id) { return em.find(Like.class, id);}
}