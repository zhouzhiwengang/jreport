package com.zzg.search.component.builder.factory;

import com.zzg.search.component.builder.IESQueryBuilders;
import com.zzg.search.component.builder.model.ESQueryMode;

public class ESQueryBuilderFactory {
	public static IESQueryBuilders creatESQueryBuilder(ESQueryMode mode)
			throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		Class<?> clazz = Class.forName(mode.mode());
		return (IESQueryBuilders) clazz.newInstance();
	}

}
