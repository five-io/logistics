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
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Entity
@Table(name = "p_users")
@AllArgsConstructor
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가
    @Column(nullable = false)
    private Long userId;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String slackId;

    @Column(nullable = false)
    private UUID hub_id;

    @Enumerated(EnumType.STRING) // ENUM 타입 매핑
    @Column(nullable = false)
    private UsersRoleEnum role;

    public Users() {
    }

    public Users(String username, String password,String slackId ,UUID hub_id, UsersRoleEnum role) {
        this.username = username;
        this.password = password;
        this.slackId = slackId;
        this.hub_id = hub_id;
        this.role = UsersRoleEnum.valueOf(role.getAuthority());
    }
}
