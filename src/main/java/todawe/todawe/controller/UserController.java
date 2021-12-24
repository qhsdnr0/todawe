package todawe.todawe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import todawe.todawe.model.KakaoProfile;
import todawe.todawe.service.UserService;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Transactional
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public String createUser(@RequestHeader("Authorization") String kakaoToken) {
        KakaoProfile kakaoProfile = userService.getUserInfoKakaoUserByToken(kakaoToken);
        return userService.getOrCreateKakaoUser(kakaoProfile);
    }

//    @PostMapping

}
