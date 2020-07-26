package com.supermy.rules.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    public static final int GOODBYE = 2;
    public static final int HELLO = 1;
    private int status;
    private String message;
}
