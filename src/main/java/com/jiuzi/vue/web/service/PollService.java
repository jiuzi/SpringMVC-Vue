package com.jiuzi.vue.web.service;

import com.jiuzi.vue.web.api.PollInfo;
import com.jiuzi.vue.web.common.PraiseDate;
import com.jiuzi.vue.web.common.RoleEnum;
import com.jiuzi.vue.web.common.SpeCharUtil;
import com.jiuzi.vue.web.common.exception.ServiceException;
import com.jiuzi.vue.web.dao.PollDao;
import com.jiuzi.vue.web.dao.UserInfoDao;
import com.jiuzi.vue.web.domain.Poll;
import com.jiuzi.vue.web.domain.PollCount;
import com.jiuzi.vue.web.domain.UserInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchongyang
 */
@Service
public class PollService {

    public static final int MAX_TICKETS = 5;
    public static final int DEV_TICKETS = 3;
    public static final int PRO_TICKETS = 2;
    private static final int MAX_MSG_LEN = 500;
    @Resource
    private PollDao pollDao;

    @Resource
    private UserInfoDao userInfoDao;

    public void savePollInfo(PollInfo pollInfo) {
        checkParams(pollInfo);
        PraiseDate praiseDate = new PraiseDate();

        //判断投票次数
        checkTimes(pollInfo, praiseDate.getWeek());

        doSavePollInfo(pollInfo, praiseDate);
    }

    /**
     * 1、给同一个人投票
     * 2、超过了产品的投票次数
     * 3、超过了研发的投票次数
     * 2、超过来投票总次数
     */
    private boolean checkTimes(PollInfo pollInfo, int week) {

        List<Poll> pollList = pollDao.queryPollByUserErpAndWeek(pollInfo.getUserErp(), week);
        UserInfo userInfo = userInfoDao.queryUserInfoByErp(pollInfo.getVoterErp());

        if (null == userInfo) {
            throw new ServiceException("您赞美了一个不存在的人~~~");
        }

        if (CollectionUtils.isEmpty(pollList)) {
            return true;
        }
        if (pollList.size() > MAX_TICKETS) {
            throw new ServiceException("本周您的票数已用完~~~");
        }
        int devTickets = 0;
        int proTickets = 0;

        for (Poll poll : pollList) {
            if (null == poll) {
                continue;
            }
            if (StringUtils.isNotEmpty(poll.getVoterErp()) && poll.getVoterErp().equals(pollInfo.getVoterErp())) {
                throw new ServiceException("本周你已经赞美过TA了~~~");
            }

            if (null != poll.getRole() && poll.getRole() == RoleEnum.DEV_ROLE.getKey()) {
                devTickets++;
            }

            if (null != poll.getRole() && poll.getRole() == RoleEnum.PRO_ROLE.getKey()) {
                proTickets++;
            }
        }
        if (devTickets >= DEV_TICKETS && userInfo.getRole() == RoleEnum.DEV_ROLE.getKey()) {
            throw new ServiceException("本周您赞美研发的票数已用完~~~");
        }

        if (proTickets >= PRO_TICKETS && userInfo.getRole() == RoleEnum.PRO_ROLE.getKey()) {
            throw new ServiceException("本周您赞美产品的票数已用完~~~");
        }

        return false;
    }


    private void doSavePollInfo(PollInfo pollInfo, PraiseDate praiseDate) {
        Poll poll = new Poll();
        poll.setMsg(pollInfo.getMsg());
        poll.setUserErp(pollInfo.getUserErp());
        poll.setUserName(pollInfo.getUserName());
        poll.setVoterErp(pollInfo.getVoterErp());
        poll.setVoterName(pollInfo.getVoterName());
        poll.setYear(praiseDate.getYear());
        poll.setQuarter(praiseDate.getQuarter());
        poll.setWeek(praiseDate.getWeek());
        pollDao.insertPoll(poll);
    }


    private void checkParams(PollInfo pollInfo) {
        if (null == pollInfo) {
            throw new ServiceException("入参异常");
        }

        if (StringUtils.isEmpty(pollInfo.getVoterErp())) {
            throw new ServiceException("你想对谁说呀~~");
        }

        if (StringUtils.isEmpty(pollInfo.getMsg())) {
            throw new ServiceException("说点东西呀~~");
        }

        if (SpeCharUtil.isConSpeChar(pollInfo.getMsg())) {
            throw new ServiceException("正常说话，说正常话~~");
        }

        if (pollInfo.getMsg().toCharArray().length > MAX_MSG_LEN) {
            throw new ServiceException("你是有多爱TA，说了这么多话~~");
        }


    }

    public List<PollInfo> queryAllPollInfoList(String erp) {
        PraiseDate praiseDate = new PraiseDate();

        List<Poll> pollList = pollDao.queryAllPollInfoList(erp, praiseDate.getWeek());

        List<PollInfo> pollInfoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(pollList)) {
            for (Poll poll : pollList) {
                PollInfo pollInfo = new PollInfo();
                pollInfo.setMsg(poll.getMsg());
                pollInfo.setUserName(poll.getUserName());
                pollInfo.setVoterName(poll.getVoterName());
                pollInfoList.add(pollInfo);
            }
        }
        return pollInfoList;
    }

    public List<PollCount> queryPollCountList(String role) {
        PraiseDate praiseDate = new PraiseDate();
        return pollDao.queryPollCount(role, praiseDate.getWeek());
    }
}

