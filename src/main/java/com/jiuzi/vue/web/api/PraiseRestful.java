package com.jiuzi.vue.web.api;

import com.jiuzi.vue.web.common.RoleEnum;
import com.jiuzi.vue.web.common.exception.ServiceException;
import com.jiuzi.vue.web.domain.PollCount;
import com.jiuzi.vue.web.domain.UserInfo;
import com.jiuzi.vue.web.service.PollService;
import com.jiuzi.vue.web.service.UserInfoService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息Restful
 *
 * @author wangchongyang
 */
@RestController
@RequestMapping("/api")
public class PraiseRestful {

    private final static Logger LOGGER = LoggerFactory.getLogger(PraiseRestful.class);


    @Resource
    private UserInfoService userInfoService;

    @Resource
    private PollService pollService;

    @RequestMapping(value = "/getUserInfoByErp/{erp}", method = {RequestMethod.GET})
    public UserInfo getUserInfoByErp(@PathVariable("erp") String erp) {
        if (StringUtils.isEmpty(erp)) {
            throw new ServiceException("入参ERP为空");
        }
        UserInfo userInfo = userInfoService.getUserInfoByErp(erp);

        if (null == userInfo) {
            throw new ServiceException("根据ERP：" + erp + " 获取用户信息为空");
        }

        return userInfo;

    }

    @RequestMapping(value = "/getAllUserInfoList/{erp}", method = {RequestMethod.GET})
    public List<UserInfo> getAllUserInfoList(@PathVariable("erp") String erp) {
        List<UserInfo> userInfoList = userInfoService.getAllUserInfoList();
        List<UserInfo> result = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(userInfoList)) {
            for (UserInfo userInfo : userInfoList) {
                if (null == userInfo || userInfo.getRole() == RoleEnum.LEADER_ROLE.getKey() || (StringUtils.isNotEmpty(userInfo.getErp()) && userInfo.getErp().equals(erp))) {
                    continue;
                }
                result.add(userInfo);
            }
        }

        return result;
    }

    @RequestMapping(value = "/savePollInfo", method = {RequestMethod.POST})
    public Result savePollInfo(PollInfo pollInfo) {
        LOGGER.info("begin invoking savePollInfo:{}", pollInfo);
        Result result = new Result();
        result.setResultCode("-1");
        try {
            pollService.savePollInfo(pollInfo);
            result.setResultCode("1");
            result.setMessage("赞美成功~~~");
        } catch (ServiceException se) {
            result.setMessage(se.getMessage());
        } catch (Exception e) {
            LOGGER.error("savePollInfo exception", e);
            result.setMessage("系统异常，请联系管理员！");
        }
        return result;
    }

    @RequestMapping(value = "/queryAllPollInfoList/{erp}", method = {RequestMethod.GET})
    public List<PollInfo> queryAllPollInfoList(@PathVariable("erp") String erp) {
        return pollService.queryAllPollInfoList(erp);
    }

    @RequestMapping(value = "/queryPollCountList/{role}")
    public List<PollCount> queryPollCountList(@PathVariable("role") String role) {
        return pollService.queryPollCountList("null".equals(role) ? null : role);
    }

}
