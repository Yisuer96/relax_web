package com.ecnu.relax.controller.api;

import com.ecnu.relax.dto.SpecialistDto;
import com.ecnu.relax.model.PreorderStatus;
import com.ecnu.relax.service.ISpecialistService;
import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/specialist")
public class SpecialistController extends APIBaseController{
    @Autowired
    private ISpecialistService specialistService;

    @RequestMapping(value="/getSortedSpecialistsByType", method = RequestMethod.GET)
    public List<SpecialistDto> getSortedSpecialistsByType(@RequestParam("type")Integer type, @RequestParam("sort") Integer sort){
        List<SpecialistDto> specialistDtoList = new ArrayList<>();
        specialistDtoList = specialistService.getSortedSpecialistsByType(type,sort);
        return specialistDtoList;

    }

    @RequestMapping(value="/getSpecialistBeanBySpecialistId", method = RequestMethod.GET)
    public SpecialistDto getPreOrderTableBySpecialistId(@RequestParam("specialistId")Integer specialistId){
        return specialistService.getSpecialistBeanBySpecialistId(specialistId);
    }

    @RequestMapping(value="/submitQualification", method = RequestMethod.GET)
    public Integer submitQualification(@RequestParam Map<String,Object> resume){
        return specialistService.insertSpecialist(resume);
    }
}
