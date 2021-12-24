package todawe.todawe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todawe.todawe.exception.BadRequestException;
import todawe.todawe.model.Like;
import todawe.todawe.model.User;
import todawe.todawe.repository.LikeQueryRepository;
import todawe.todawe.repository.LikeRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final LikeQueryRepository likeQueryRepository;
    private final UserService userService;

    public void addLike(User sendUser, User takeUser) {
        if (likeQueryRepository.getLike(sendUser, takeUser) != null) {
            throw new BadRequestException("LIKE_ALREADY_EXIST");
        }
        Like like = new Like();
        userService.userLike(like, sendUser, takeUser);
    }

    public long getLikeCount(User user) {
        return likeQueryRepository.getLikeCount(user);
    }

    public List<User> getLikeUser(User user) {
        return likeQueryRepository.getLikeUser(user);
    }

    public void deleteLike(User sendUser, User takeUser) {
        Like like = likeQueryRepository.getLike(sendUser, takeUser);
        if (like == null) {
            throw new BadRequestException("LIKE_DOES_NOT_EXIST");
        }
        likeRepository.delete(like);
    }
}
