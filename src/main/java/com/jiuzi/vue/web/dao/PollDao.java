package com.jiuzi.vue.web.dao;

import com.jiuzi.vue.web.domain.Poll;
import com.jiuzi.vue.web.domain.PollCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangchongyang
 */
public interface PollDao {

    /**
     * save poll info
     *
     * @param poll
     * @return
     */
    Integer insertPoll(Poll poll);

    /**
     * 查询 投票结果
     *
     * @param role
     * @param week
     * @return
     */
    List<PollCount> queryPollCount(@Param("role") String role, @Param("week") Integer week);

    /**
     * @param userErp
     * @param week
     * @return
     */
    List<Poll> queryPollByUserErpAndWeek(@Param("userErp") String userErp, @Param("week") Integer week);

    /**
     * @param erp
     * @param week
     * @return
     */
    List<Poll> queryAllPollInfoList(@Param("voterErp") String erp, @Param("week") int week);
}
