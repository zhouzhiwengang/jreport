package com.zzg.reptile.service;

import java.util.List;
import java.util.Map;
import com.zzg.jreport.common.BaseService;
import com.zzg.jreport.common.page.PageData;
import com.zzg.jreport.common.page.PageParam;
import com.zzg.reptile.domain.AuthUser;

public interface AuthUserService extends BaseService<AuthUser>{
	 // 方法梳理
    List<AuthUser> selectAll(Map<String, Object> paramter);
    
    PageData<AuthUser> selectAllPage(Map<String,Object> parame, PageParam rb);
}
