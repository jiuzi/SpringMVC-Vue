package com.jiuzi.vue.web.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * CREATE TABLE `user_info` (
 * `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
 * `erp` varchar(50) NOT NULL COMMENT 'erp账号',
 * `user_name` varchar(50) NOT NULL COMMENT '用户名称',
 * `photo_url` varchar(100) DEFAULT NULL COMMENT '图片地址',
 * `role` tinyint(4) DEFAULT NULL COMMENT '角色：0研发 1产品 2Leader',
 * `is_vote` tinyint(4) DEFAULT NULL COMMENT '操作类型：1：可被投票，0：不能被投票',
 * `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';
 *
 * @author wangchongyang
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 232257791403676108L;

    private long id;
    private String erp;
    private String userName;
    private String photoUrl;
    private byte role;
    private byte isVote;
    private Integer count;
    private String email;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getErp() {
        return erp;
    }

    public void setErp(String erp) {
        this.erp = erp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public byte getRole() {
        return role;
    }

    public void setRole(byte role) {
        this.role = role;
    }

    public byte getIsVote() {
        return isVote;
    }

    public void setIsVote(byte isVote) {
        this.isVote = isVote;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
