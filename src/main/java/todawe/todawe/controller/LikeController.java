package todawe.todawe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import todawe.todawe.exception.BadRequestException;
import todawe.todawe.exception.ForbiddenException;
import todawe.todawe.model.User;
import todawe.todawe.service.LikeService;
import todawe.todawe.service.UserService;
import todawe.todawe.util.Token;

import java.util.HashMap;

@RestController
@RequestMapping("likes")
@RequiredArgsConstructor
@Transactional
@CrossOrigin
public class LikeController {

    private final UserService userService;
    private final LikeService likeService;

    @PostMapping("/{userId}")
    public ResponseEntity<String> sendLike(@RequestHeader("Authorization") String token,
                                           @PathVariable("userId") Long userId) {

        User sendUser = userService.findUser(Token.decodeJwtToken(token));
        User takeUser = userService.findUser(userId);
        if (takeUser == null) {
            throw new BadRequestException("USER_DOES_NOT_EXIST");
        }
        likeService.addLike(sendUser, takeUser);
        return ResponseEntity.ok("CREATED");
    }

    @GetMapping("/count/{userId}")
    public HashMap<String, Object> getLikeCount(@PathVariable("userId") Long userId) {
        HashMap<String, Object> result = new HashMap<>();
        User takeUser = userService.findUser(userId);
        result.put("count", likeService.getLikeCount(takeUser));
        return result;
    }

    @GetMapping("/userList/{userId}")
    public HashMap<String, Object> getLikeUserList(@RequestHeader("Authorization") String token,
                                                @PathVariable("userId") Long userId) {
        HashMap<String, Object> result = new HashMap<>();
        User takeUser = userService.findUser(userId);
        User user = userService.findUser(Token.decodeJwtToken(token));
        if (user != takeUser) {
            throw new ForbiddenException();
        }
        result.put("userList", likeService.getLikeUser(takeUser));
        return result;
    }
}
