package com.dev.fullstackdemo.service;

import com.dev.fullstackdemo.domain.CustomUser;
import com.dev.fullstackdemo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * persist user
     *
     * @param customUser
     * @return generated user id
     */
    public Long saveUser(CustomUser customUser) {
        String encodedPasswd = passwordEncoder.encode(customUser.getPassword());
        customUser.setPassword(encodedPasswd);
        customUser = userRepository.save(customUser);
        return customUser.getId();
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomUser> optionalUser = userRepository.findByUsername(username);
        CustomUser user = optionalUser.get();
        return new User(user.getUsername()
                , user.getPassword()
                , user.getAuthorities());
    }


}

