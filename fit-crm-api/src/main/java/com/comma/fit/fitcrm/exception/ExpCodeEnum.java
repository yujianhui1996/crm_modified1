package com.comma.fit.fitcrm.exception;


import java.io.Serializable;

import static com.comma.fit.fitcrm.util.ExpPrefixUtil.*;


/**
 * @Author magang
 * 全局的异常状态码 和 异常描述
 * <p>
 * PS:异常码一共由5位组成，前两位为固定前缀
 */
public enum ExpCodeEnum implements Serializable {

    /**
     * 通用异常
     */
    UNKNOW_ERROR(COM_EXPPRE_FIX + "000", "未知异常"),
    ERROR_404(COM_EXPPRE_FIX + "001", "没有该接口"),
    PARAM_NULL(COM_EXPPRE_FIX + "002", "参数为空"),
    NO_REPEAT(COM_EXPPRE_FIX + "003", "请勿重复提交"),
    SESSION_NULL(COM_EXPPRE_FIX + "004", "请求头中SessionId不存在"),
    HTTP_REQ_METHOD_ERROR(COM_EXPPRE_FIX + "005", "HTTP请求方法不正确"),
    JSONERROR(COM_EXPPRE_FIX + "006", "JSON解析异常"),
    VERIFICATION_CODE_ERROR(COM_EXPPRE_FIX + "007", "验证码输入有误"),

    /**
     * Market模块异常
     */
    MARKET_COUPON_SAVE_ERROR(MARKET_EXPPRE_FIX + "000", "优惠券保存失败"),
    MARKET_COUPON_EDIT_ERROR(MARKET_EXPPRE_FIX + "001", "优惠券修改失败"),
    MARKET_COUPON_DETAIL_SAVE_ERROR(MARKET_EXPPRE_FIX + "002", "优惠券保存失败"),
    MARKET_COUPON_DETAIL_EXISTS_ERROR(MARKET_EXPPRE_FIX + "003", "该用户已领优惠券，不能重复领取!"),
    MARKET_COUPON_OFFLINE_ERROR(MARKET_EXPPRE_FIX + "004", "该优惠券已下架!"),
    MARKET_USER_EXISTS_ERROR(MARKET_EXPPRE_FIX + "005", "您已经是老用户了!"),
    MARKET_COUPON_NOT_EXISTS_ERROR(MARKET_EXPPRE_FIX + "006", "优惠券不存在!"),
    MARKET_COUPON_OUT_COUNT(MARKET_EXPPRE_FIX + "007", "优惠券被抢光了!"),
    MARKET_ACT_SAVE_ERROR(MARKET_EXPPRE_FIX + "008", "活动保存失败!"),
    MARKET_ACT_EDIT_ERROR(MARKET_EXPPRE_FIX + "009", "活动修改失败!"),
    MARKET_ACT_CHANNEL_EXISTS(MARKET_EXPPRE_FIX + "010", "活动渠道已存在!"),
    MARKET_ACT_CHANNEL_SAVE_ERROR(MARKET_EXPPRE_FIX + "011", "活动渠道保存失败!"),
    MARKET_COUPON_NOT_BELONG_USER(MARKET_EXPPRE_FIX + "012", "该优惠券不属于该用户!"),
    MARKET_COUPON_USED(MARKET_EXPPRE_FIX + "013", "该优惠券已使用!"),
    MARKET_COUPON_DOT_USE(MARKET_EXPPRE_FIX + "014", "该优惠券未到使用时间!"),
    MARKET_COUPON_TIMEOUT(MARKET_EXPPRE_FIX + "015", "该优惠券已经过了使用时间!"),
    MARKET_COUPON_USED_OUT(MARKET_EXPPRE_FIX + "016", "优惠券使用数量超出限额!"),
    MARKET_COUPON_RECEIVE_ERROR(MARKET_EXPPRE_FIX + "017", "优惠券领取失败!"),
    MARKET_COUPON_RECEIVE_OUT(MARKET_EXPPRE_FIX + "018", "优惠券领取数量达到上限!"),
    MARKET_ACT_NOT_EXISTS_ERROR(MARKET_EXPPRE_FIX + "019", "活动不存在!"),
    MARKET_COURSE_NOT_EXISTS_ERROR(MARKET_EXPPRE_FIX + "020", "该课程不存在!"),
    MARKET_COURSE_USE_COUPON_ERROR(MARKET_EXPPRE_FIX + "021", "该优惠券不适用于该课程!"),
    MARKET_COURSE_ISSUE_NUM_ERROR(MARKET_EXPPRE_FIX + "022", "优惠券发放数量不能为空!"),

    /**
     * 订单模块异常
     */
    ORDER_NOT_EXISTS(ORDER_EXPPRE_FIX + "000", "订单号不存在!"),

    /**
     * 公共模块异常
     */
    COMMON_DICT_SAVE_ERROR(DICT_EXPPRE_FIX + "000", "字典保存失败!"),
    COMMON_DICT_EDIT_ERROR(DICT_EXPPRE_FIX + "001", "字典保存失败!");

    private String code;
    private String message;

    private ExpCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    ExpCodeEnum() {
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
