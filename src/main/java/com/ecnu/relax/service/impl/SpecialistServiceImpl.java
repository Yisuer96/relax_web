package com.ecnu.relax.service.impl;

import com.ecnu.relax.dto.CommentDto;
import com.ecnu.relax.dto.SpecialistDto;
import com.ecnu.relax.mapper.*;
import com.ecnu.relax.model.*;
import com.ecnu.relax.service.ISpecialistService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Calendar.MONDAY;

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

    @Autowired
    PreorderStatusMapper preorderStatusMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    OrderMapper orderMapper;


    @Override
    public List<SpecialistDto> getSortedSpecialistsByType(int type, int sort) {
        List<Specialist> specialists = new ArrayList<>();
        List<SpecialistDto> specialistDtos = new ArrayList<>();
        if (type == 0) {
            switch (sort) {
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
        } else {
            switch (sort) {
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

    @Override
    public List<PreorderStatus> getPreOrderTableBySpecialistId(int specialistId) {
        List<PreorderStatus> preOrderStatusList = new ArrayList<>();
        preOrderStatusList = preorderStatusMapper.getPreOrderTableBySpecialistId(specialistId);
        return preOrderStatusList;
    }

    @Override
    public List<CommentDto> getUserCommentBySpecialistId(int specialistId) {
        List<Comment> comments = commentMapper.selectCommentBySpecialistId(specialistId);
        List<CommentDto> commentDtos = comments.stream().map(this::parse).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public SpecialistDto getSpecialistBeanBySpecialistId(int specialistId) {
        Specialist specialist = specialistMapper.selectByPrimaryKey(specialistId);
        SpecialistDto specialistDto = parse(specialist);
        specialistDto.setPreOrderStatusBeanList(getPreOrderTableBySpecialistId(specialistId));
        specialistDto.setCommentBeanList(getUserCommentBySpecialistId(specialistId));
        return specialistDto;
    }

    @Override
    public int insertSpecialist(Map<String, Object> resume) {
        int userId = Integer.valueOf(resume.get("userId").toString());
        String realName = resume.get("name").toString();
        String qualification = resume.get("qualification").toString();
        int employeeLength = Integer.valueOf(resume.get("employeeLength").toString());
        String intro = resume.get("introduction").toString();
        Specialist specialist = new Specialist(userId, qualification, employeeLength, intro);
        User user = userMapper.selectByPrimaryKey(userId);
        user.setIdentity(1);
        userMapper.updateByPrimaryKey(user);
        int result = 0;
        if (specialistMapper.selectByPrimaryKey(userId) == null)
            result = specialistMapper.insertSelective(specialist);
        return result;
    }

    public SpecialistDto parse(Specialist specialist) {
        SpecialistDto specialistDto = new SpecialistDto();
        try {
            BeanUtils.copyProperties(specialistDto, specialist);
            User user = userMapper.selectByPrimaryKey(specialist.getSpecialistId());
            specialistDto.setRealName(user.getRealName());
            specialistDto.setPhone(user.getPhone());
            specialistDto.setPortrait(user.getPortrait());
            List<Integer> typeIds = specialistTypeMapper.selectBySpecialistId(specialist.getSpecialistId());
            List<Type> types = new ArrayList<>();
            for (Integer typeId : typeIds) {
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

    public CommentDto parse(Comment comment) {
        CommentDto commentDto = new CommentDto();
        try {
            BeanUtils.copyProperties(commentDto, comment);
            int orderId = comment.getOrderId();
            int userId = orderMapper.selectByPrimaryKey(orderId).getPatientId();
            String userName = userMapper.selectByPrimaryKey(userId).getNickname();
            commentDto.setCommenterName(userName);
        } catch (NullPointerException np) {
            System.out.println("Comments don't exist.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commentDto;
    }

    @Override
    public int changePreOrderStatusBySpecialistId(int specialistId, Date startTime, Date endTime, Integer isOrdered, Integer isFree) {
        Calendar start = Calendar.getInstance();
        start.setTime(startTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        List<PreorderStatus> records = new ArrayList<>();
        PreorderStatus preorderStatus;
        int temp = start.DAY_OF_WEEK - 1;
        if (temp == 0)
            temp = 7;
        switch (start.HOUR_OF_DAY) {
            case 10:
                preorderStatus = new PreorderStatus();
                preorderStatus.setTimeslotId(1);
                records.add(preorderStatus);
                break;
            case 16:
                preorderStatus = new PreorderStatus();
                preorderStatus.setTimeslotId(5);
                records.add(preorderStatus);
                break;
            case 19:
                if (end.HOUR_OF_DAY == 20) {
                    preorderStatus = new PreorderStatus();
                    preorderStatus.setTimeslotId(6);
                    records.add(preorderStatus);
                    break;
                } else {
                    for (int i = 0; i < 2; i++) {
                        preorderStatus = new PreorderStatus();
                        preorderStatus.setTimeslotId(6 + i);
                        records.add(preorderStatus);
                    }
                    break;
                }
            case 20:
                preorderStatus = new PreorderStatus();
                preorderStatus.setTimeslotId(7);
                records.add(preorderStatus);
                break;
            default:
                for (int i = 0; i < end.HOUR_OF_DAY - start.HOUR_OF_DAY; i++) {
                    preorderStatus = new PreorderStatus();
                    preorderStatus.setTimeslotId(start.HOUR_OF_DAY-11 + i);
                    records.add(preorderStatus);
                }
        }
        for(int i = 0;i<records.size();i++)
        {
            preorderStatus = records.get(i);
            preorderStatus.setDay(temp);
            preorderStatus.setSpecialistId(specialistId);
            preorderStatus.setIsOrdered(isOrdered);
            preorderStatus.setIsFree(isFree);
            preorderStatusMapper.updateByPrimaryKey(preorderStatus);
        }
        return 1;
    }
}
