package com.jiuzi.vue.web.common;

import java.util.Calendar;

/**
 * @author wangchongyang
 */
public class PraiseDate {
    private Calendar instance = Calendar.getInstance();

    public int getYear() {
        return instance.get(Calendar.YEAR);
    }

    public int getQuarter() {
        int quarter = 0;
        int month = instance.get(Calendar.MONTH);

        if (month < 3) {
            quarter = 1;
        } else if (month >= 3 && month < 6) {
            quarter = 2;
        } else if (month >= 6 && month < 9) {
            quarter = 3;
        } else if (month >= 9 && month < 12) {
            quarter = 4;
        }
        return quarter;
    }

    public int getWeek() {
        return instance.get(Calendar.WEEK_OF_YEAR);
    }

}
