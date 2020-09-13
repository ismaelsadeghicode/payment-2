package com.dml.topup.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Ismael Sadeghi, 6/9/2019 2:38 PM
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "UserTopup")
@SequenceGenerator(
        name = "user-topup-sequence",
        sequenceName = "USER_TOPUP_SEQ",
        allocationSize = 1000, initialValue = 10000)
public class User extends DomainEntity implements Comparable<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user-topup-sequence")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @Override
    public int compareTo(User user) {
        Long code = user.getId();
        return this.id.compareTo(code);
    }
}
