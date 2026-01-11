package com.msd.utrip.service.auth.userdetails;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.constant.enums.Role;
import com.msd.utrip.exception.UserNotFoundException;
import com.msd.utrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailService implements CustomUserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UserNotFoundException {
        return userRepository
            .findByPhone(login)
            .orElseThrow(() -> new UserNotFoundException(ErrorCode.LOGIN_INVALID_CODE));
    }

    @Override
    public Role getSupportedRole() {
        return Role.USER;
    }
}
