package todawe.todawe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todawe.todawe.model.KakaoProfile;
import todawe.todawe.service.UserService;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Transactional
@CrossOrigin
public class UserController {

    private UserService userService;

    @PostMapping("/login")
    public String createUser(String kakaoToken) {
        KakaoProfile kakaoProfile = userService.getUserInfoKakaoUserByToken(kakaoToken);
        return userService.getOrCreateKakaoUser(kakaoProfile);
    }

    @PostMapping

}
