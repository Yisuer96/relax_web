package com.ecnu.relax.dto;

import com.ecnu.relax.model.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SpecialistDto {
    private Integer specialistId;

    private String phone;

    private String realName;

    private String portrait;

    private Double rating;

    private String qualification;

    private Integer employLength;

    private String introduction;

    private List<Type> typeBeanList;


}
