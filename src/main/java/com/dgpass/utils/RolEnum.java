package com.dgpass.utils;

public enum RolEnum {
    ADMIN(1),
    USER(2);
    private final int id;
    RolEnum(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
}
