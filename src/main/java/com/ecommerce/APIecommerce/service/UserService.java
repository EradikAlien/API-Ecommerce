package com.ecommerce.APIecommerce.service;


import com.ecommerce.APIecommerce.model.AuthenticationToken;
import com.ecommerce.APIecommerce.model.AuthenticationType;
import com.ecommerce.APIecommerce.model.User;
import com.ecommerce.APIecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationService authenticationService;

    public void updateAuthenticationType(String username, String oauth2ClientName) {
        User existUser = userRepository.getUserByUsername(username);

        if (existUser == null) {
            AuthenticationType authType = AuthenticationType.valueOf(oauth2ClientName.toUpperCase());
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setAuthType(authType);
            newUser.setEnabled(true);

            userRepository.save(newUser);

            final AuthenticationToken authenticationToken = new AuthenticationToken(newUser);

            authenticationService.saveConfirmationToken(authenticationToken);

            System.out.println("Created new user: " + username);
        }
    }
    }
