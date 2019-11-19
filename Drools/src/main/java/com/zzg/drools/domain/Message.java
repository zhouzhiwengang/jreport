package com.zzg.drools.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Message {
	public static final Integer HELLO = 0;
    public static final Integer GOODBYE = 1;

    private String message;

    private Integer status;
}
