package todawe.todawe.model;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class KakaoProfile {

    private Long kakaoId;
    private String email;
    private String name;

    protected KakaoProfile() {}

    public KakaoProfile(Long kakaoId, String email, String name) {
        this.kakaoId = kakaoId;
        this.email = email;
        this.name = name;
    }
}
