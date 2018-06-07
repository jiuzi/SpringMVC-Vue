package com.jiuzi.vue.web.dao;

import com.jiuzi.vue.web.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息 DAO
 *
 * @author wangchongyang
 */
public interface UserInfoDao {

    /**
     * 查询所有的用户信息
     *
     * @return
     */
    List<UserInfo> queryAllUserInfoList(@Param("week") Integer week);


    /**
     * 根据 userErp 获取用户信息
     *
     * @param erp
     * @return
     */
    UserInfo queryUserInfoByErp(@Param("userErp") String erp);
}
