package com.waoqi.common.utils;

public class CodeMsg {
    public static final String MANDAO_SUCCESS = "00000";
    public static final String MANDAO_RESP_CODE = "respCode";
    public static final String MANDAO_RESP_MSG = "respMsg";
    public static final String REAPAL_SUCCESS = "0000";
    public static final String REAPAL_RESP_CODE = "result_code";
    public static final String REAPAL_RESP_MSG = "result_msg";

    private int code;
    private String msg;

    public static CodeMsg SUCCESS = new CodeMsg(0, "success");


    //通用错误码
    public static CodeMsg SERVER_ERROR = new CodeMsg(500, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(5001, "参数校验异常: %s");
    public static CodeMsg SIGN_ERROR = new CodeMsg(5002, "请使用合法的请求！%s");
    public static CodeMsg NOT_SUPPORT = new CodeMsg(5003, "方法暂不支持");


    //登录模块
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(6001, "密码错误");
    public static CodeMsg LOGIN_SESSION_ERROR = new CodeMsg(6002, "登录过期");
    public static CodeMsg LOGIN_ERROR = new CodeMsg(6003, "登录错误");
    public static CodeMsg AUTHCODE_ERROR = new CodeMsg(6004, "验证码错误");
    public static CodeMsg EDIT_PASSWORD_ERROR = new CodeMsg(6005, "密码修改失败");




    //秒杀模块
    public static CodeMsg MIAOSHA_ERROR = new CodeMsg(7001, "秒杀错误信息");
    public static CodeMsg MIAOSHA_USER_NOT_AUTH = new CodeMsg(7002, "请先购买实物产品后再参加秒杀");
    public static CodeMsg MIAOSHA_ORDER_PARTICIPATE = new CodeMsg(7003, "您今日已经参加过秒杀或购买过新人专享商品，无法再次购买!");
    public static CodeMsg MIAOSHA_IS_NULL = new CodeMsg(7004, "暂无秒杀活动");
    public static CodeMsg MIAOSHA_BUY_NUMBER_ERROR = new CodeMsg(7005, "您已购买%d次新人专享或秒杀，请自提一次后再试!");




    //商品模块
    public static CodeMsg GOODS_IS_NULL = new CodeMsg(8001, "您要查询的商品不存在");
    public static CodeMsg GOODS_EXCHANGE = new CodeMsg(8002, "没有可以兑换的商品");
    public static CodeMsg GOODS_TYPE_ERROR = new CodeMsg(8003, "您查询的商品类型错误");
    public static CodeMsg GOODS_SHOPPING_ERROR = new CodeMsg(8004, "商品已下架");


    //用户模块
    public static CodeMsg REGISTER_ERROR = new CodeMsg(9001, "该手机号已经被注册，请重新输入");
    public static CodeMsg PHONE_NOT_ALREADLY = new CodeMsg(9002, "手机号号码不存在，请重新输入");
    public static CodeMsg NEWANDOLD_REPETITION = new CodeMsg(9003, "新密码不能与旧密码重复，请重新输入");
    public static CodeMsg USER_IS_NULL = new CodeMsg(9004, "用户不存在");
    public static CodeMsg USERID_IS_NULL = new CodeMsg(9004,"用户id不能为空");
    public static CodeMsg USER_ADDRESS_ADD_ERROR = new CodeMsg(9005,"添加收货地址出错");
    public static CodeMsg UASER_ADDRESS_IS_NULL = new CodeMsg(9006, "用户收货地址不存在");
    public static CodeMsg PARENT_USER_IS_NULL = new CodeMsg(9007, "没有查询到您的推荐用户！");
    public static CodeMsg USER_IS_BANNED = new CodeMsg(9008, "您已被管理员封禁！");



    //订单模块
    public static CodeMsg ORDER_IS_NULL = new CodeMsg(10001, "订单不存在");
    public static CodeMsg ORDER_IS_USER_ERROR = new CodeMsg(10002, "该订单不属于此用户");
    public static CodeMsg ORDER_RECEIVING_ERROR = new CodeMsg(10003, "确认收货失败");
    public static CodeMsg ORDER_PICKUP_ERROR = new CodeMsg(10004, "自提失败");
    public static CodeMsg ORDER_ADDRESS_IS_NULL = new CodeMsg(10005, "用户收货地址不存在");

    public static CodeMsg ORDER_STATUS_ERROR = new CodeMsg(10006, "订单状态异常，请刷新后重试!");



    //银行卡
    public static CodeMsg CARD_ID_IS_NULL = new CodeMsg(20001, "卡号不能为空");
    public static CodeMsg PAY_PWD_ERROR = new CodeMsg(20002, "支付密码不正确");
    public static CodeMsg MANBAO_ERROR = new CodeMsg(20003, "宝付接口异常");
    public static CodeMsg DUPLICATE_BIND = new CodeMsg(20004, "重复绑卡");
    public static CodeMsg CARD_ID_NOT_EXSITS = new CodeMsg(20005, "卡号不存在");
    public static CodeMsg OTHER_ERROR = new CodeMsg(20006, "异常");


    public static CodeMsg BANNER_SAVE_ERROR = new CodeMsg(30001, "已存在当前分类banner，请删除后再添加!");


    private CodeMsg() {
    }

    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
