package com.jiuzi.vue.web.common;

public enum RoleEnum {
    DEV_ROLE(0, "研发"),
    PRO_ROLE(1, "产品"),
    LEADER_ROLE(2, "Leader");

    private int key;
    private String name;

    RoleEnum(int key, String name) {
        this.key = key;
        this.name = name;
    }

    public int getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
