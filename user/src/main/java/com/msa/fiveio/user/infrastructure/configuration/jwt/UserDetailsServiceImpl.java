package com.msa.fiveio.user.infrastructure.configuration.jwt;

import com.msa.fiveio.user.model.entity.Users;
import com.msa.fiveio.user.model.repository.UsersRepository;
import com.msa.fiveio.user.presentation.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Not Found " + username));

        return new UserDetailsImpl(new UserDto(user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getRole()));
    }
}