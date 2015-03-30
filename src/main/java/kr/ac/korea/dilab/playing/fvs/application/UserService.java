package kr.ac.korea.dilab.playing.fvs.application;

import kr.ac.korea.dilab.playing.fvs.domain.user.Email;
import kr.ac.korea.dilab.playing.fvs.domain.user.EmailRepository;
import kr.ac.korea.dilab.playing.fvs.domain.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by Koo Lee on 2014-08-30.
 */
@Data
@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private EmailRepository emailRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Email email = emailRepository.findOne(username);
        if (email == null) throw new UsernameNotFoundException(username);
        final User user = email.getUser();
        UserDetails details = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return user.getRoles();
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public boolean isAccountNonExpired() {
                return !user.isExpired();
            }

            @Override
            public boolean isAccountNonLocked() {
                return !user.isLocked();
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return !user.isCredentialsExpired();
            }

            @Override
            public boolean isEnabled() {
                return user.isEnabled();
            }
        };

        return details;
    }
}
