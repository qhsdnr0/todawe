package todawe.todawe.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todawe.todawe.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
