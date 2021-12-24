package todawe.todawe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import todawe.todawe.exception.BadRequestException;
import todawe.todawe.model.User;
import todawe.todawe.model.UserRole;
import todawe.todawe.service.CommentService;
import todawe.todawe.service.UserService;
import todawe.todawe.util.Token;

import java.util.HashMap;

@RestController
@RequestMapping("comments")
@RequiredArgsConstructor
@Transactional
@CrossOrigin
public class CommentController {

    private final UserService userService;
    private final CommentService commentService;

    @PostMapping("/{userId}")
    public ResponseEntity<String> sendComment(@RequestHeader("Authorization") String token,
                                              @PathVariable("userId") Long userId,
                                              @RequestBody CommentForm commentForm) {

        User sendUser = userService.findUser(Token.decodeJwtToken(token));
        User takeUser = userService.findUser(userId);
        if (sendUser == takeUser) {
            throw new BadRequestException("SAME_USER");
        }
        if (takeUser == null) {
            throw new BadRequestException("USER_DOES_NOT_EXIST");
        } else if (sendUser.getAddInfo().getRole() == UserRole.STUDENT) {
            if (takeUser.getAddInfo().getRole() == UserRole.STUDENT && !sendUser.getAddInfo().getGeneration().equals(takeUser.getAddInfo().getGeneration())) {
                throw new BadRequestException("FORBIDDEN");
            }
        }
        commentService.addComment(sendUser, takeUser, commentForm);
        return ResponseEntity.ok("CREATED");
    }

    @GetMapping("")
    public HashMap<String, Object> getComments(@RequestHeader("Authorization") String token) {
        HashMap<String, Object> result = new HashMap<>();
        User user = userService.findUser(Token.decodeJwtToken(token));
        result.put("result", commentService.getComment(user));
        return result;
    }

//    @DeleteMapping("/{commentId}")
//    public ResponseEntity<String> deleteComment(@RequestHeader("Authorization") String token,
//                                                @PathVariable("commentId") Long commentId) {
//
//
//    }
}
