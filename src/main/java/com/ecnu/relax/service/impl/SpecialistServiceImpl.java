package com.ecnu.relax.service.impl;

import com.ecnu.relax.dto.SpecialistDto;
import com.ecnu.relax.mapper.SpecialistMapper;
import com.ecnu.relax.mapper.SpecialistTypeMapper;
import com.ecnu.relax.mapper.TypeMapper;
import com.ecnu.relax.mapper.UserMapper;
import com.ecnu.relax.model.Specialist;
import com.ecnu.relax.model.Type;
import com.ecnu.relax.model.User;
import com.ecnu.relax.service.ISpecialistService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialistServiceImpl extends BaseServiceImpl implements ISpecialistService {
    @Autowired
    SpecialistMapper specialistMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    SpecialistTypeMapper specialistTypeMapper;

    @Autowired
    TypeMapper typeMapper;



    @Override
    public List<SpecialistDto> getSortedSpecialistsByType(int type, int sort){
        List<Specialist> specialists = new ArrayList<>();
        List<SpecialistDto> specialistDtos = new ArrayList<>();
        if(type==0){
            switch (sort){
                case 0:
                    specialists = specialistMapper.getRatingDescSpecialists();
                    break;
                case 1:
                    specialists = specialistMapper.getOrdersNumDescDescSpecialists();
                    break;
                case 2:
                    specialists = specialistMapper.getEmployLengthDescSpecialists();
                    break;
                default:
                    break;
            }
        }
        else{
            switch (sort){
                case 0:
                    specialists = specialistMapper.getRatingDescSpecialistsByType(type);
                    break;
                case 1:
                    specialists = specialistMapper.getOrdersNumDescDescSpecialistsByType(type);
                    break;
                case 2:
                    specialists = specialistMapper.getEmployLengthDescSpecialistsByType(type);
                    break;
                default:
                    break;
            }
        }
        specialistDtos = specialists.stream().map(this::parse).collect(Collectors.toList());
        return specialistDtos;
    }

    private SpecialistDto parse(Specialist specialist) {
        SpecialistDto specialistDto = new SpecialistDto();
        try {
            BeanUtils.copyProperties(specialistDto, specialist);
            User user = userMapper.selectByPrimaryKey(specialist.getSpecialistId());
            specialistDto.setRealName(user.getRealName());
            specialistDto.setPhone(user.getPhone());
            specialistDto.setPortrait(user.getPortrait());
            List<Integer> typeIds = specialistTypeMapper.selectBySpecialistId(specialist.getSpecialistId());
            List<Type> types = new ArrayList<>();
            for(Integer typeId:typeIds){
                types.add(typeMapper.selectByPrimaryKey(typeId));
            }
            specialistDto.setTypeBeanList(types);
        } catch (NullPointerException np) {
            System.out.println("Specialist doesn't exist.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return specialistDto;
    }
}
