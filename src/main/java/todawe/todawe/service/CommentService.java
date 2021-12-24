package todawe.todawe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todawe.todawe.controller.CommentForm;
import todawe.todawe.exception.ForbiddenException;
import todawe.todawe.model.Comment;
import todawe.todawe.model.User;
import todawe.todawe.repository.CommentRepository;
import todawe.todawe.repository.UserQueryRepository;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {

    private final UserQueryRepository userQueryRepository;
    private final UserService userService;
    private final CommentRepository commentRepository;

    public void createComment(Comment comment) {
        commentRepository.save(comment);
    }

    public Comment findComment(Long id) { return commentRepository.getById(id);}

    public void addComment(User sendUser, User takeUser, CommentForm commentForm) {
        Comment comment = new Comment();
        comment.setContent(commentForm.getContent());
        comment.setUpdatedAt(LocalDateTime.now());
        userService.userComment(comment, sendUser, takeUser);
        commentRepository.save(comment);
    }

    public List<Comment> getComment(User takeUser) {
        return userQueryRepository.findCommentsByUser(takeUser);
    }

    public void deleteComment(User sendUser, Long commentId) {
        Comment comment = commentRepository.getById(commentId);
        if (comment.getSendUser() == sendUser) {
            commentRepository.delete(comment);
        } else {
            throw new ForbiddenException();
        }
    }

    public void deleteComment(User sendUser, Comment comment) {
        if (comment.getSendUser() == sendUser) {
            commentRepository.delete(comment);
        } else {
            throw new ForbiddenException();
        }
    }

    public void updateComment(User sendUser, Long commentId, CommentForm commentForm) {
        Comment comment = commentRepository.getById(commentId);
        if (comment.getSendUser() == sendUser) {
            comment.setContent(commentForm.getContent());
            comment.setUpdatedAt(LocalDateTime.now());
        } else {
            throw new ForbiddenException();
        }
    }

    public void updateComment(User sendUser, Comment comment, CommentForm commentForm) {
        if (comment.getSendUser() == sendUser) {
            comment.setContent(commentForm.getContent());
            comment.setUpdatedAt(LocalDateTime.now());
        } else {
            throw new ForbiddenException();
        }
    }
}
