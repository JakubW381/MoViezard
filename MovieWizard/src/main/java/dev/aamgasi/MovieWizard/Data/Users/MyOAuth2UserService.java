package dev.aamgasi.MovieWizard.Data.Users;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class MyOAuth2UserService implements OAuth2UserService {

    private UserRepo userRepo;

    public MyOAuth2UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oauth2User = new DefaultOAuth2UserService().loadUser(userRequest);

        String oauth2Id = oauth2User.getAttribute("sub");
        String email = oauth2User.getAttribute("email");
        String username = oauth2User.getAttribute("name");

        System.out.println("OAuth2 ID: " + oauth2Id);
        System.out.println("Email: " + email);
        System.out.println("Username: " + username);

        User user = userRepo.findByOauth2Id(oauth2Id).orElse(new User());
        user.setOauth2Id(oauth2Id);
        user.setEmail(email);
        user.setUsername(username);

        userRepo.save(user);

        return oauth2User;
    }
}
