package com.zzg.batch.processor.validator;


import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;

public class BeanValidator<T> implements Validator<T>, InitializingBean {


	@Override
	public void afterPropertiesSet() throws Exception {
	}

	@Override
	public void validate(T value) throws ValidationException {
	}

}
