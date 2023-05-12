package org.geekhub.denis.repository;

import org.geekhub.denis.entity.UserEntity;
import org.geekhub.denis.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

/**
 * @author Apilat Denis
 * Date :29.04.2023
 * Time :21:56
 * Project Name :gh-hw-denis-apilat
 */

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Override
    public Optional<UserEntity> findUserByEmail(String userEmail) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM users WHERE email = ?",
                new Object[]{userEmail},
                (rs, rowNum) -> Optional.of(new UserEntity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                ))
        );
    }

    public Optional<UserEntity> findUserByCardUserId(String cardNumber) {
        return jdbcTemplate.queryForObject(
                "SELECT usr.id, usr.name, usr.surname, usr.phone_number, usr.email, usr.password, usr.role " +
                        "FROM users usr, cards crd " +
                        "WHERE crd.card_number =  ? " +
                        "GROUP BY usr.id, crd.user_id " +
                        "HAVING usr.id = crd.user_id ",
                new Object[]{cardNumber},
                (rs, rowNum) -> Optional.of(new UserEntity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                ))
        );
    }

    @Override
    public <S extends UserEntity> void saveUser(S userEntity) {
        jdbcTemplate.update(
                "INSERT INTO users (name, surname, phone_number, email, password, role) VALUES(?,?,?,?,?,?)",
                userEntity.getName(),
                userEntity.getSurname(),
                userEntity.getPhoneNumber(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getRole()
        );
    }

}
