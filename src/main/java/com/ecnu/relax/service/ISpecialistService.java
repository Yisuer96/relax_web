package com.ecnu.relax.service;

import com.ecnu.relax.dto.SpecialistDto;

import java.util.List;

public interface ISpecialistService extends BaseService {
    List<SpecialistDto> getSortedSpecialistsByType(int type, int sort);
}
