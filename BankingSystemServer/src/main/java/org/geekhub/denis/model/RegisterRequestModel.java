package org.geekhub.denis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Apilat Denis
 * Date :29.04.2023
 * Time :23:44
 * Project Name :gh-hw-denis-apilat
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestModel {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;
}
