package com.zzg.batch.processor;

import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

import com.zzg.batch.domain.AuthUser;

public class CVSItemProcessor extends ValidatingItemProcessor<AuthUser> {
	
	// 局部常量
//	public static final String YES = "1";
//	public static final String NO = "2";

	@Override
	public AuthUser process(AuthUser item) throws ValidationException {
		// TODO Auto-generated method stub
		super.process(item);
//		if("是".equalsIgnoreCase(item.getIsSuperuser())){
//			item.setIsSuperuser(YES);
//		} else {
//			item.setIsSuperuser(NO);
//		}
//		
//		if("是".equalsIgnoreCase(item.getIsActive())){
//			item.setIsActive(YES);
//		} else {
//			item.setIsActive(NO);
//		}
//		
//		if("是".equalsIgnoreCase(item.getIsStaff())){
//			item.setIsStaff(YES);
//		} else {
//			item.setIsStaff(NO);
//		}
		return item;
	}
	
}
