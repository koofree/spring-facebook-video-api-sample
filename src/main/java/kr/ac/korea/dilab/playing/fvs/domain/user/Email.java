package kr.ac.korea.dilab.playing.fvs.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * Created by Koo Lee on 2014-08-30.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Email {
    @Id
    @NonNull
    private String name;

    @JoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
