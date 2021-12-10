package todawe.todawe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todawe.todawe.model.Comment;
import todawe.todawe.model.User;
import todawe.todawe.repository.CommentRepository;
import todawe.todawe.repository.UserRepository;
import todawe.todawe.token.Token;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final Token accessToken;

    public void createComment(Comment comment) {
        commentRepository.saveComment(comment);
    }
}
