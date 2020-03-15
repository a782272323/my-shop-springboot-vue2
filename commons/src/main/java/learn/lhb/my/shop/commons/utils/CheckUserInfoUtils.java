//package learn.lhb.my.shop.commons.utils;
//
//import learn.lhb.my.shop.commons.dto.BaseResult;
//import org.apache.commons.lang3.StringUtils;
//
///**
// * 用户信息有效性的校验工具类
// * 配合返回结果集封装的工具类和正则表达式工具类使用
// *
// * @author 梁鸿斌
// * @date 2020/3/15.
// * @time 11:23
// */
//public class CheckUserInfoUtils<T> {
//
//    public static final String IS_BLANK_EMAIL = "邮箱不能为空,请重新输入";
//    public static final String IS_BLANK_PASSWORD = "密码不能为空,请重新输入";
//    public static final String IS_BLANK_USERNAME = "用户名不能为空,请重新输入";
//    public static final String IS_BLANK_PHONE = "手机号不能为空,请重新输入";
//
//    public static final String ERROR_EMAIL = "邮箱格式不正确，请重新输入";
//    public static final String ERROR_PHONE = "手机号码格式不正确，请重新输入";
//
//    /**
//     * 默认通过用户有效性校验
//     */
//    BaseResult baseResult = BaseResult.ok();
//
//    /**
//     * 用户有效性验证
//     * 用户名, 密码，邮箱，手机号码
//     * @param tClass
//     * @return
//     */
//    public BaseResult check1(Class<T> tClass) {
//        // 邮箱非空验证
//        if (StringUtils.isBlank(tClass.toString())) {
//            baseResult = BaseResult.error(IS_BLANK_EMAIL);
//        }
//        // 邮箱格式验证
//        else if (!IsRegexpUtils.checkEmail(tClass.toString())) {
//            baseResult = BaseResult.error(ERROR_EMAIL);
//        }
//        // 手机号码非空验证
//        else if (StringUtils.isBlank(tClass.toString())) {
//            baseResult = BaseResult.error(IS_BLANK_PHONE);
//        }
//        // 手机号码格式验证
//        else if (!IsRegexpUtils.checkPhone(tClass.toString())) {
//            baseResult = BaseResult.error(ERROR_PHONE);
//        }
//        // 用户名非空验证
//        else if (StringUtils.isBlank(tClass.toString())) {
//            baseResult = BaseResult.error(IS_BLANK_USERNAME);
//        }
//        // 密码非空验证
//        else if (StringUtils.isBlank(tClass.toString())) {
//            baseResult = BaseResult.error(IS_BLANK_PASSWORD);
//        }
//
//        return baseResult;
//    }
//
//
//
//}
