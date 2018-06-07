package com.jiuzi.vue.web.service;

import com.jiuzi.vue.web.common.PraiseDate;
import com.jiuzi.vue.web.dao.UserInfoDao;
import com.jiuzi.vue.web.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangchongyang
 */
@Service
public class UserInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserInfoService.class);
    @Resource
    private UserInfoDao userInfoDao;

    public UserInfo getUserInfoByErp(String erp) {
        return userInfoDao.queryUserInfoByErp(erp);
    }

    public List<UserInfo> getAllUserInfoList() {
        PraiseDate praiseDate = new PraiseDate();
        return userInfoDao.queryAllUserInfoList(praiseDate.getWeek());
    }
}
