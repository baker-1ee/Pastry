package com.baker1ee.pastry.domain.user.entity;

import com.baker1ee.pastry.domain.user.dto.request.UserCreateRequest;
import com.baker1ee.pastry.id.IdHolder;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "Users")
public class User {

    @Id
    private Long userSeq;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String password;

    @Column
    private Long createdBy;

    @Column
    private Long updatedBy;

    @Column
    private LocalDateTime createdDatetime;

    @Column
    private LocalDateTime updatedDatetime;

    public static User of(UserCreateRequest request) {
        return User.builder()
                .userSeq(IdHolder.nextId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .createdBy(1L)
                .updatedBy(1L)
                .createdDatetime(LocalDateTime.now())
                .updatedDatetime(LocalDateTime.now())
                .build();
    }
}
