package kr.ac.korea.dilab.playing.fvs.domain.user;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Koo Lee on 2014-08-30.
 */
public enum Role implements GrantedAuthority {
    USER(0), ADMIN(1);
    private final int role;

    private Role(int role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
