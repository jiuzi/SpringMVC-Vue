package com.jiuzi.vue.web.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * CREATE TABLE `poll` (
 * `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 * `user_erp` varchar(11) DEFAULT NULL,
 * `user_name` varchar(50) DEFAULT NULL,
 * `voter_erp` varchar(50) DEFAULT NULL,
 * `voter_name` varchar(50) DEFAULT NULL,
 * `week` int(10) DEFAULT NULL COMMENT '周',
 * `quarter` int(10) DEFAULT NULL COMMENT '季度',
 * `year` int(10) DEFAULT NULL COMMENT '年度',
 * `msg` varchar(255) DEFAULT NULL,
 * `create_time` datetime DEFAULT NULL COMMENT '创建时间',
 * `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
 * PRIMARY KEY (`id`),
 * KEY `index_user_id` (`user_erp`) USING BTREE,
 * KEY `index_voter_id` (`voter_erp`) USING BTREE
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 *
 * @author wangchongyang
 */
public class Poll implements Serializable {
    private static final long serialVersionUID = 8198466835850251664L;

    private long id;
    private String userErp;
    private String userName;
    private String voterErp;
    private String voterName;
    private Integer year;
    private Integer quarter;
    private Integer week;
    private String msg;
    private Integer role;
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
