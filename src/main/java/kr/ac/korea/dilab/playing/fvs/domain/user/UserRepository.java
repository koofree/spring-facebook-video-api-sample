package kr.ac.korea.dilab.playing.fvs.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Koo Lee on 2014-08-29.
 */
public interface UserRepository extends JpaRepository<User, String> {
}
