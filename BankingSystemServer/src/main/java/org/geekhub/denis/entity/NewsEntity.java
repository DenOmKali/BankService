package org.geekhub.denis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Apilat Denis
 * Date :28.04.2023
 * Time :13:06
 * Project Name :gh-hw-denis-apilat
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsEntity {
    private Integer id;
    private String title;
    private String content;
    private String imageUrl;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
}
