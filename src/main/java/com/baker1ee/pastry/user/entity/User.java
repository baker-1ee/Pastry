package com.baker1ee.pastry.user.entity;

import com.baker1ee.pastry.user.dto.request.UserCreateRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "Users")
public class User {

    @Id
    private Long userSeq;

    @Column
    private String name;

    public static User of(Long id, UserCreateRequest request) {
        return new User(id, request.getName());
    }
}
