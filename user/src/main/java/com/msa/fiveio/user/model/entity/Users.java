package com.msa.fiveio.user.model.entity;

import com.msa.fiveio.common.auditing.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "p_users")
public class Users extends BaseEntity {
    @Id
    @Column(length = 50, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String slackId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String role = "";

    public Users() {
    }

    public Users(String username, String password,String slackId ,String email, UsersRoleEnum roleEnum) {
        this.username = username;
        this.password = password;
        this.slackId = slackId;
        this.email = email;
        this.role = roleEnum.getAuthority();
    }
}
