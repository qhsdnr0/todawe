package todawe.todawe.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todawe.todawe.model.Comment;
import todawe.todawe.model.*;
import todawe.todawe.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
