package todawe.todawe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import todawe.todawe.model.KakaoProfile;
import todawe.todawe.service.UserService;

import java.util.HashMap;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Transactional
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public HashMap<String, Object> createUser(@RequestHeader("Authorization") String kakaoToken) {
        HashMap<String, Object> result = new HashMap<>();
        KakaoProfile kakaoProfile = userService.getUserInfoKakaoUserByToken(kakaoToken);
        result.put("token", userService.getOrCreateKakaoUser(kakaoProfile));
        return result;
    }

    

//    @PostMapping

}
