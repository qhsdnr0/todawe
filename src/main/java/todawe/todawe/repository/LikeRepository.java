package todawe.todawe.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todawe.todawe.model.Like;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
}