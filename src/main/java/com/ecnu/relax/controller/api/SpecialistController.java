package com.ecnu.relax.controller.api;

import com.ecnu.relax.dto.SpecialistDto;
import com.ecnu.relax.service.ISpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

}
