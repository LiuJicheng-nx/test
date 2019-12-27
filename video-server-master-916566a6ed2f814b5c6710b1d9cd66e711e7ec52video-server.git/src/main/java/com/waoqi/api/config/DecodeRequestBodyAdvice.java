// package com.waoqi.api.config;
//
// import com.google.gson.Gson;
// import com.google.gson.reflect.TypeToken;
// import com.waoqi.common.utils.AesEncryptUtils;
// import com.waoqi.common.utils.RSAUtils;
// import org.apache.commons.io.IOUtils;
// import org.apache.commons.lang3.StringUtils;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.core.MethodParameter;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpInputMessage;
// import org.springframework.http.converter.HttpMessageConverter;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
//
// import java.io.IOException;
// import java.io.InputStream;
// import java.lang.reflect.Type;
// import java.util.Map;
//
// /**
//  * @desc 请求数据解密
//  */
// @ControllerAdvice(basePackages = {"com.waoqi.api"})
// public class DecodeRequestBodyAdvice implements RequestBodyAdvice {
//
//     private static final Logger logger = LoggerFactory.getLogger(DecodeRequestBodyAdvice.class);
//
//     // @Value("${encryption.server.private.key}")
//     // private String SERVER_PRIVATE_KEY;
//     private static final String SERVER_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAImSj36P3tnjIfvm3aihT8g0aQ9OuQGiP/xSSjAndL/ztLxi/8rCXu99WjdUbrQKF3hdCGE0YEsbO2EoyLsvoCAzVYnjU4WxrI/CvOX/nZYXs4Y7Oet4QShdis3mk8in8xD2s0NdA/kvbP23WmkjfbtCxibI0tswGvzYalSi8zs9AgMBAAECgYBv4PnDUltIVPfBNQoUZkNMrJq38SxxkLJ6j613pc7Df/z+q4AcM6AjIY0prAdvcTmPEBGNg5u/2MCcjh9YYy3Tb3CvROYwKd6eaY/2kyKRhmLJELFvJeFHGpNLQu6wgkJvUtxNDBwaekhFUeD5amnj4Iu/bKD8hQKdqln/YQNqvQJBAM5L2MA99YUQQkKBdJ9OFIKcLsA8gfw261YO3BeGHtg6oDxraIMh5oQVyF5FEYcmw6fRJah3ghpbh+s4Crl9zL8CQQCqt+X+h3VEG/U8L+7+54HinU/67+MFLRnCb2nifNZ2fsKFauyMl8mZnieV/MlXOVWc48euqBbS5mwldXMJmmsDAkEAm1CVPp8nQugryqBmai3B2Ve3BvpHLtk2MxN69qb+f/MbgoGnLNsQDy/LK9Y6TRONSMJ45K4dILT6zsfhoyeoRwJASEP8SdhRXXlwQAASbTjWlHT/DDxLnz9OLi43ZjZuGk7iTNPMOl9uaWa3XxhYqmGMyhLpzhc9Qh1kHh8viRsivQJAQLNlDkRubVumOTGrKN04BANO/cqMPFaaq0a4rwILbOhZ6b68hKT4Q3rnXdcUk+qkLvRPPXp5CCm5lMHlYVRTQQ==";
//
//     @Override
//     public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//         return true;
//     }
//
//     @Override
//     public Object handleEmptyBody(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//         return body;
//     }
//
//     @Override
//     public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
//         try {
//
//             logger.info("对方法method :【" + methodParameter.getMethod().getName() + "】返回数据进行解密");
//             return new MyHttpInputMessage(inputMessage);
//
//         } catch (Exception e) {
//             e.printStackTrace();
//             logger.error("对方法method :【" + methodParameter.getMethod().getName() + "】返回数据进行解密出现异常：" + e.getMessage());
//             return inputMessage;
//         }
//     }
//
//     @Override
//     public Object afterBodyRead(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//         return body;
//     }
//
//     class MyHttpInputMessage implements HttpInputMessage {
//         private HttpHeaders headers;
//
//         private InputStream body;
//
//         public MyHttpInputMessage(HttpInputMessage inputMessage) throws Exception {
//             this.headers = inputMessage.getHeaders();
//             this.body = IOUtils.toInputStream(easpString(IOUtils.toString(inputMessage.getBody(), "utf-8")));
//         }
//
//         @Override
//         public InputStream getBody() throws IOException {
//             return body;
//         }
//
//         @Override
//         public HttpHeaders getHeaders() {
//             return headers;
//         }
//
//         /**
//          * @param requestData
//          * @return
//          */
//         public String easpString(String requestData) {
//             if (requestData != null && !requestData.equals("")) {
//                 Map<String, String> map = new Gson().fromJson(requestData, new TypeToken<Map<String, String>>() {
//                 }.getType());
//                 // 密文  请求参数-encryptKey:{},encryptData:{}
//                 String data = map.get("encryptData");
//                 // 加密的ase秘钥
//                 String encrypted = map.get("encryptKey");
//                 if (StringUtils.isEmpty(data) || StringUtils.isEmpty(encrypted)) {
//                     throw new RuntimeException("参数【requestData】缺失异常！");
//                 } else {
//                     String content = null;
//                     String aseKey = null;
//                     try {
//                         aseKey = RSAUtils.decryptDataOnJava(encrypted, SERVER_PRIVATE_KEY);
//                     } catch (Exception e) {
//                         throw new RuntimeException("参数【aseKey】解析异常！");
//                     }
//                     try {
//                         content = AesEncryptUtils.decrypt(data, aseKey);
//                     } catch (Exception e) {
//                         throw new RuntimeException("参数【content】解析异常！");
//                     }
//                     if (StringUtils.isEmpty(content) || StringUtils.isEmpty(aseKey)) {
//                         throw new RuntimeException("参数【requestData】解析参数空指针异常!");
//                     }
//                     return content;
//                 }
//             }
//             throw new RuntimeException("参数【requestData】不合法异常！");
//         }
//     }
// }
