// package com.waoqi.api.config;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.waoqi.common.utils.AesEncryptUtils;
// import com.waoqi.common.utils.RSAUtils;
// import org.apache.commons.codec.binary.Base64;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.core.MethodParameter;
// import org.springframework.http.MediaType;
// import org.springframework.http.server.ServerHttpRequest;
// import org.springframework.http.server.ServerHttpResponse;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
// import java.text.SimpleDateFormat;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.Random;
//
// /**
// * @desc 返回数据加密
// */
// @ControllerAdvice(basePackages = {"com.waoqi.api"})
// public class EncodeResponseBodyAdvice implements ResponseBodyAdvice {
//
//    private final static Logger logger = LoggerFactory.getLogger(EncodeResponseBodyAdvice.class);
//
//    private String CLIENT_PUBLIC_KEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsX+2H8flWWUwbMm04fZYJNBp54FrQqjmqD7GZV2D0R9NdI4lokB8nKtZCFRAlfRwulNtjDdM/Mu3XWS94pExGIYjJuAqFCN3549Z0OpE8Id8lc/AJs5SykOYO2dSNVm21YthwicyYDgh0NSg0drZRRSMdbrhKMocPG4KnWlzd7wIDAQAB";
//
//    @Override
//    public boolean supports(MethodParameter methodParameter, Class aClass) {
//        return true;
//    }
//
//    @Override
//    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
//        logger.info("对方法method :【" + methodParameter.getMethod().getName() + "】返回数据进行加密");
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            objectMapper.setDateFormat(fmt);
//            String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
//
//            // 生成aes秘钥
//            String aseKey = getRandomString(16);
//            String resultJson = result.trim();
// //           logger.info("加密前的数据:" + resultJson);
//            // rsa加密
//            String encrypted = RSAUtils.encryptedDataOnJava(aseKey, CLIENT_PUBLIC_KEY);
//            logger.info(aseKey);
//            // aes加密
//            String requestData = AesEncryptUtils.encrypt(resultJson, aseKey);
//            Map<String, String> map = new HashMap<>();
//            logger.info("encrypted->" + encrypted);
//            logger.info("requestData->" + requestData);
//            logger.info("======================");
//
//            encrypted = Base64.encodeBase64String(encrypted.getBytes());
//            requestData = Base64.encodeBase64String(requestData.getBytes());
//
//            logger.info("encrypted->" + encrypted);
//            logger.info("requestData->" + requestData);
//
//            map.put("encryptKey", encrypted);
//            map.put("encryptData", requestData);
//            return map;
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("对方法method :【" + methodParameter.getMethod().getName() + "】返回数据进行解密出现异常：" + e.getMessage());
//        }
//        return body;
//    }
//
//    /**
//     * 创建指定位数的随机字符串
//     *
//     * @param length 表示生成字符串的长度
//     * @return 字符串
//     */
//    public static String getRandomString(int length) {
//        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
//        Random random = new Random();
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < length; i++) {
//            int number = random.nextInt(base.length());
//            sb.append(base.charAt(number));
//        }
//        return sb.toString();
//    }
//
// }
//
//
