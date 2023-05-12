package org.geekhub.denis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Apilat Denis
 * Date :02.05.2023
 * Time :21:35
 * Project Name :gh-hw-denis-apilat
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUserModel {
    private Integer id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String role;
}
