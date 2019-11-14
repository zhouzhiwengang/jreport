package com.zzg.batch.mapper;

import java.util.List;
import java.util.Map;
import com.zzg.batch.domain.AuthUser;

public interface AuthUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthUser record);

    int insertSelective(AuthUser record);

    AuthUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthUser record);

    int updateByPrimaryKey(AuthUser record);
    
    // 方法梳理
    List<AuthUser> selectAll(Map<String, Object> paramter);
}