package com.ecnu.relax.service;

import com.ecnu.relax.dto.CommentDto;
import com.ecnu.relax.dto.SpecialistDto;
import com.ecnu.relax.model.PreorderStatus;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ISpecialistService extends BaseService {
    List<SpecialistDto> getSortedSpecialistsByType(int type, int sort);

    List<PreorderStatus> getPreOrderTableBySpecialistId(int specialistId);

    List<CommentDto> getUserCommentBySpecialistId(int specialistId);

    SpecialistDto getSpecialistBeanBySpecialistId(int specialistId);

    int insertSpecialist(Map<String,Object> specialistDto);

}
