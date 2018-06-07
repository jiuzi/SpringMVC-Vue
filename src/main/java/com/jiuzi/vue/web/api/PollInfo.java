package com.jiuzi.vue.web.api;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author wangchongyang
 */
public class PollInfo implements Serializable {
    private static final long serialVersionUID = -7179311715832922410L;
    private String userErp;
    private String userName;
    private String voterErp;
    private String voterName;
    private String msg;

    public String getUserErp() {
        return userErp;
    }

    public void setUserErp(String userErp) {
        this.userErp = userErp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVoterErp() {
        return voterErp;
    }

    public void setVoterErp(String voterErp) {
        this.voterErp = voterErp;
    }

    public String getVoterName() {
        return voterName;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
