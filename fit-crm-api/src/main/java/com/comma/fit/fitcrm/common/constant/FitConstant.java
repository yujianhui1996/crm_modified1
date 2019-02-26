package com.comma.fit.fitcrm.common.constant;

/**
 * 常量类
 * User: magang
 * Date: 2019-01-17
 * Time: 18:08
 */
public class FitConstant {
    /**
     * 代金券1
     */
    public static int COUPON_TYPE_VOUCHERS = 1;
    /**
     * 新人体验券2
     */
    public static int COUPON_TYPE_NEW_USER_EXPERIENCE = 2;
    /**
     * 课程体验券3
     */
    public static int COUPON_TYPE_COURSE_EXPERIENCE = 3;
    /**
     * 全部场馆
     */
    public static int GTM_TYPE_ALL = 1;
    public static String GTM_TYPE_ALL_NAME = "全部";
    /**
     * 团操课
     */
    public static String AIM_GROUP_EXERCISES = "1";
    /**
     * 小团体课
     */
    public static String AIM_GROUPUSCULE = "2";
    /**
     * 所有用户
     */
    public static int USER_ALL = 1;
    /**
     * 新用户
     */
    public static int USER_NEW = 2;
    /**
     * 领取后生效
     */
    public static int VALID_TYPE_RECEIVE = 1;
    /**
     * 规定时间内生效
     */
    public static int VALID_TYPE_TIME = 2;
    /**
     * 新人券有效天数
     */
    public static int COUPON_TYPE_NEW_USER_VALID_DAY = 14;
    /**
     * 老带新课程体验有效天数
     */
    public static int COUPON_TYPE_COURSE_VALID_DAY = 14;
    /**
     * 新人优惠券领取限额
     */
    public static int COUPON_LIMIT_NUM = 2;
    /**
     * 优惠金额
     */
    public static double COUPON_DEDUCTIBLE_PRICE= 19.9;
    /**
     * 优惠券已领取
     */
    public static String COUPON_DETAIL_STATUS_INIT = "1";
    /**
     * 优惠券已使用
     */
    public static String COUPON_DETAIL_STATUS_USED = "2";
    /**
     * 优惠券已过期
     */
    public static String COUPON_DETAIL_STATUS_FAILURE = "3";
    /**
     * 上架
     */
    public static int COUPON_STATUS_ONLINE = 1;
    /**
     * 下架
     */
    public static int COUPON_STATUS_OFFLINE = 2;
    /**
     * 邀请者身份
     */
    public static int COUPON_IDENTITYATTRIBUTE_INVITER = 1;
    /**
     * 被邀请者身份
     */
    public static int COUPON_IDENTITYATTRIBUTE_INVITEE = 2;
    /**
     * 系统人员id
     */
    public static int SYS_USER_ID = 0;
    /**
     * 系统人员name
     */
    public static String SYS_USER_NAME = "sys";
    /**
     * 默认渠道等级
     */
    public static int MARKET_CHANNEL_LEVEL = 1;
    /**
     * 活动状态 在线
     */
    public static int MARKET_STATUS_ONLINE = 1;
    /**
     * 活动状态 离线
     */
    public static int MARKET_STATUS_OFFLINE = 2;

    /**
     * 发放渠道：关联活动
     */
    public static int ISSUE_CHANNEL = 1;
    /**
     * 系统发放
     */
    public static int ISSUE_CHANNEL_SYS = 0;


}
