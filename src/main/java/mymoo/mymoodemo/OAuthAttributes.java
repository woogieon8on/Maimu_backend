package mymoo.mymoodemo;

import lombok.Builder;
import lombok.Getter;
import mymoo.mymoodemo.domain.Users;

import java.util.Map;

@Getter
@Builder
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    public static OAuthAttributes of(String registraionId, String userNameAttributeName, Map<String, Object> attributes){

    }



    public Users toEntity(){
        return Users.builder()
                .name(name)
                .email(email)
                .role(Role.USER)
                .build();
    }
}
