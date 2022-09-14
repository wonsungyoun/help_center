package com.kakaopay.helpcenter.mapper;

import com.kakaopay.helpcenter.entity.Counsel;
import com.kakaopay.helpcenter.model.CounselData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CounselMapper {
    List<CounselData> getCounselDataList();
}
