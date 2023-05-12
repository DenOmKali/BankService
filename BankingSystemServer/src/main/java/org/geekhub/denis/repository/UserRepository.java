package org.geekhub.denis.repository;

import org.geekhub.denis.entity.UserEntity;

import java.util.Optional;

/**
 * @author Apilat Denis
 * Date :29.04.2023
 * Time :21:55
 * Project Name :gh-hw-denis-apilat
 */

public interface UserRepository {
    Optional<UserEntity> findUserByEmail(String userEmail);
    <S extends UserEntity> void saveUser(S userEntity);
}
