package com.github.vanlla.core.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 节假日判断
 *
 * @author Vanlla
 * @since 1.0
 */
public class HolidayUtils {

    protected static Logger LOGGER = LoggerFactory.getLogger(HolidayUtils.class);

    private static final String HOLIDAY_API = "http://timor.tech/api/holiday/info/";


    /**
     * 判断是否为工作日
     *
     * @param date
     * @return
     */
    public static boolean isWorkDay(Date date) {
        String dateDay = DateUtil.format(date, "yyyy-MM-dd");
        String resultStr = HttpUtil.get(HOLIDAY_API + dateDay);
        LOGGER.debug("工作日判断API请求返回：" + resultStr);
        JSONObject result = new JSONObject(resultStr);
        if (ObjectUtil.isEmpty(result) || 0 != result.getInt("code")) {
            LOGGER.debug("工作日判断API请求失败");
            return false;
        }
        //是否节假日
        JSONObject holiday = result.getJSONObject("holiday");
        JSONObject type = result.getJSONObject("type");
        //非工作日判断是否为假期补班
        boolean isHoliday = ObjectUtil.isEmpty(holiday) || holiday.getBool("holiday");
        //工作日  或者  不是假期(例如周六补班，type: 2,holiday: false)
        if (0 == type.getInt("type") || !isHoliday) {
            return true;
        }
        return false;
    }

}
