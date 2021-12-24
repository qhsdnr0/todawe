package todawe.todawe.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import todawe.todawe.controller.CommentForm;
import todawe.todawe.exception.ForbiddenException;
import todawe.todawe.exception.UnAuthorizedException;
import todawe.todawe.model.*;
import todawe.todawe.repository.CommentQueryRepository;
import todawe.todawe.repository.CommentRepository;
import todawe.todawe.repository.UserQueryRepository;
import todawe.todawe.repository.UserRepository;
import org.springframework.web.client.RestTemplate;
import todawe.todawe.util.Token;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserQueryRepository userQueryRepository;
    private final CommentRepository commentRepository;
    private final CommentQueryRepository commentQueryRepository;
    private final RestTemplate restTemplate;

    public KakaoProfile getUserInfoKakaoUserByToken(String kakaoToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + kakaoToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>("parameters", headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity("https://kapi.kakao.com/v2/user/me", entity, String.class);
            String userInfo          = response.getBody();
            JSONParser jsonParser    = new JSONParser();
            JSONObject jsonObject    = (JSONObject) jsonParser.parse(userInfo);
            JSONObject kakao_account = (JSONObject) jsonObject.get("kakao_account");
            JSONObject profile       = (JSONObject) kakao_account.get("profile");
            Long kakaoId             = (Long) jsonObject.get("id");
            String email             = (String) kakao_account.get("email");
            String name              = (String) profile.get("nickname");

            return new KakaoProfile(kakaoId, email, name);
        } catch (RestClientException | ParseException ex) {
            throw new UnAuthorizedException();
        }

    }

    public String getOrCreateKakaoUser(KakaoProfile kakaoProfile) {
        User user = userQueryRepository.findUserByKakaoId(kakaoProfile.getKakaoId());
        if (userQueryRepository.findUserByKakaoId(kakaoProfile.getKakaoId()) == null) {
            user = new User();
            user.setKakaoProfile(kakaoProfile);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            userRepository.save(user);
        }

        return Token.makeJwtToken(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void updateUser(User user, AddInfo addInfo) {
        user.setAddInfo(addInfo);
    }

    public void updateUser(User user, UserStatus userStatus) {
        user.setUserStatus(userStatus);
    }

    public void userComment(Comment comment, User sendUser, User takeUser) {
        sendUser.addSendComment(comment);
        takeUser.addTakeComment(comment);
    }

    public void userLike(Like like, User sendUser, User takeUser) {
        sendUser.addSendLike(like);
        takeUser.addSendLike(like);
    }

    public void addComment(User sendUser, User takeUser, CommentForm commentForm) {
        Comment comment = new Comment();
        comment.setContent(commentForm.getContent());
        comment.setUpdatedAt(LocalDateTime.now());
        userComment(comment, sendUser, takeUser);
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

    public void updateComment(User sendUser, Long commentId, CommentForm commentForm) {
        Comment comment = commentRepository.getById(commentId);
        if (comment.getSendUser() == sendUser) {
            comment.setContent(commentForm.getContent());
            comment.setUpdatedAt(LocalDateTime.now());
        } else {
            throw new ForbiddenException();
        }
    }
//
//    public void addLike(User sendUser, User takeUser, LikeForm likeForm) {
//        Like like = new Like();
//        userLike(like, sendUser, takeUser);
//    }
}