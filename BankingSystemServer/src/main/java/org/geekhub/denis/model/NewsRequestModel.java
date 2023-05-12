package org.geekhub.denis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Apilat Denis
 * Date :02.05.2023
 * Time :13:22
 * Project Name :gh-hw-denis-apilat
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequestModel {
    private String title;
    private String content;
    private String imageUrl;
}
