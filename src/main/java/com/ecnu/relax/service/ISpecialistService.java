package com.ecnu.relax.service;

import com.ecnu.relax.dto.CommentDto;
import com.ecnu.relax.dto.SpecialistDto;
import com.ecnu.relax.model.PreorderStatus;

import java.util.List;

public interface ISpecialistService extends BaseService {
    List<SpecialistDto> getSortedSpecialistsByType(int type, int sort);

    List<PreorderStatus> getPreOrderTableBySpecialistId(int specialistId);

    List<CommentDto> getUserCommentBySpecialistId(int specialistId);

    SpecialistDto getSpecialistBeanBySpecialistId(int specialistId);
}
