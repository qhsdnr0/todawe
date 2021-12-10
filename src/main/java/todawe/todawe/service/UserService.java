package todawe.todawe.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import todawe.todawe.model.Comment;
import todawe.todawe.model.KakaoProfile;
import todawe.todawe.model.Like;
import todawe.todawe.model.User;
import todawe.todawe.repository.UserRepository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import todawe.todawe.token.Token;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final Token token;

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
            Long kakaoId             = (Long) jsonObject.get("id");
            String email             = (String) kakao_account.get("email");
            String name              = (String) kakao_account.get("name");

            return new KakaoProfile(kakaoId, email, name);
        } catch (RestClientException | ParseException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("Unauthorized");
        }

    }

    public String getTokenByUser(String kakaoToken) {
        KakaoProfile kakaoProfile = getUserInfoKakaoUserByToken(kakaoToken);
        User user = userRepository.findUserByKakaoId(kakaoProfile.getKakaoId());
        if (userRepository.findUserByKakaoId(kakaoProfile.getKakaoId()) == null) {
            user = new User();
            user.setKakaoProfile(kakaoProfile);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            userRepository.saveUser(user);
        }

        return token.makeJwtToken(user);
    }

    public void userComment(Comment comment, User sendUser, User takeUser) {
        sendUser.addSendComment(comment);
        takeUser.addTakeComment(comment);
    }

    public void userLike(Like like, User sendUser, User takeUser) {
        sendUser.addSendLike(like);
        takeUser.addSendLike(like);
    }
}