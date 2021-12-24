package todawe.todawe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import todawe.todawe.exception.BadRequestException;
import todawe.todawe.model.*;
import todawe.todawe.service.LikeService;
import todawe.todawe.service.UserService;
import todawe.todawe.util.Token;

import java.util.HashMap;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Transactional
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final LikeService likeService;

    @PostMapping("/login")
    public HashMap<String, String> createUser(@RequestHeader("Authorization") String kakaoToken) {
        KakaoProfile kakaoProfile = userService.getUserInfoKakaoUserByToken(kakaoToken);
        return userService.getOrCreateKakaoUser(kakaoProfile);
    }

    @PutMapping("/addinfo")
    public ResponseEntity<String> updateAddInfo(@RequestHeader("Authorization") String token, @RequestBody UserForm userForm) {
        User user = userService.findUser(Token.decodeJwtToken(token));
        userService.updateUserAddInfo(user, userForm);
        return ResponseEntity.ok("UPDATED");
    }

    @PutMapping("/status")
    public ResponseEntity<String> updateStatus(@RequestHeader("Authorization") String token, @RequestBody UserForm userForm) {
        User user = userService.findUser(Token.decodeJwtToken(token));
        userService.updateUserStatus(user, userForm);
        return ResponseEntity.ok("UPDATED");
    }

    @DeleteMapping("")
    public ResponseEntity<String> updateStatus(@RequestHeader("Authorization") String token) {
        User user = userService.findUser(Token.decodeJwtToken(token));
        userService.deleteUser(user);
        return ResponseEntity.ok("DELETED");
    }
}
