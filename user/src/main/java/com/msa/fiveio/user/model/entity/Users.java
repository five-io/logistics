package com.msa.fiveio.user.model.entity;

import com.msa.fiveio.common.auditing.BaseEntity;
import com.msa.fiveio.user.model.entity.enums.UsersRoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "p_users")
@NoArgsConstructor
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String slackId;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UsersRoleEnum role;


    @Builder
    public Users(String username, String password, String slackId, String email,
        UsersRoleEnum role) {
        this.username = username;
        this.password = password;
        this.slackId = slackId;

        this.role = role;
    }
}
