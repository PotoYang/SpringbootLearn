package com.potoyang.learn.fileupload.mapper;

import com.potoyang.learn.fileupload.entity.ExcelInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelInfoMapper {
    int insert(ExcelInfo record);

    int insertSelective(ExcelInfo record);
}