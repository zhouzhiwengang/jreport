package com.zzg.search.component.builder.model;

import java.io.Serializable;

public enum ESQueryMode implements Serializable {
	MUST("com.zzg.search.component.builder.impl.ESMust"), MUST_NOT("com.zzg.search.component.builder.impl.ESMustNot"), SHOULD(
			"com.zzg.search.component.builder.impl.ESShould"), FILTER("com.zzg.search.component.builder.impl.ESFilter");

	private String mode;

	ESQueryMode(String mode) {
		this.mode = mode;
	}

	public String mode() {
		return mode;
	}

}
