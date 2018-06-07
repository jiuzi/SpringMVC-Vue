package com.jiuzi.vue.web.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangchongyang
 */
public class SpeCharUtil {
    /**
     * 校验特殊字符
     *
     * @param str
     * @return
     */
    public static boolean isConSpeChar(String str) {

        String regEx = "[`%^&*;()|{}'\\[\\]<>/]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }
}
