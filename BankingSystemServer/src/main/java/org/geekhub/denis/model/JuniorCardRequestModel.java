package org.geekhub.denis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Apilat Denis
 * Date :07.05.2023
 * Time :0:23
 * Project Name :gh-hw-denis-apilat
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JuniorCardRequestModel {
    private String pinCode;
    private String parentEmail;
}
