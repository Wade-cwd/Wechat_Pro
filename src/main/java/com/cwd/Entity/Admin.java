package com.cwd.Entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class Admin implements Serializable {
    private String userName;
    private String password;
}
