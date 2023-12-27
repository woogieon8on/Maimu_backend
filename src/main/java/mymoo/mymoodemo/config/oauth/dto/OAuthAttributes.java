package mymoo.mymoodemo.config.oauth.dto;

import lombok.Builder;
import lombok.Getter;
import mymoo.mymoodemo.domain.Users;
import mymoo.mymoodemo.domain.enums.Role;

import java.util.Map;

@Getter
@Builder
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    public static OAuthAttributes of(String registraionId, String userNameAttributeName, Map<String, Object> attributes){
        if("naver".equals(registraionId)) {
            return ofNaver("id", attributes);
        } else if ("kakao".equals(registraionId)) {
            return ofKakao("id", attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private  static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> responese = (Map<String, Object>) attributes.get("kakao_account");
        Map<String ,Object> account = (Map<String, Object>) attributes.get("profile");

        return OAuthAttributes.builder()
                .name((String) account.get("nickname"))
                .email((String) responese.get("email"))
                .attributes(responese)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }



    public Users toEntity(){
        return Users.builder()
                .name(name)
                .email(email)
                .role(Role.USER)
                .build();
    }
}
