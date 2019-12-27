// package com.waoqi.api.config;
//
//
// import com.alibaba.fastjson.JSONObject;
// import com.waoqi.common.utils.*;
// import org.apache.commons.codec.binary.Base64;
// import org.apache.commons.lang3.StringUtils;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
//
// import javax.servlet.*;
// import javax.servlet.annotation.WebFilter;
// import javax.servlet.http.HttpServletRequest;
// import java.io.IOException;
// import java.io.PrintWriter;
// import java.util.Map;
//
// /**
// * 解密参数
// */
// @WebFilter(filterName = "EncodeResponse", urlPatterns = "/ds/v1/*")
// public class EncodeResponse implements Filter {
//    private static final Logger logger = LoggerFactory.getLogger(EncodeResponse.class);
//
//    // @Value("${encryption.server.private.key}")
//    // private String SERVER_PRIVATE_KEY;
//
//     private static final String SERVER_PRIVATE_KEY="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAImSj36P3tnjIfvm3aihT8g0aQ9OuQGiP/xSSjAndL/ztLxi/8rCXu99WjdUbrQKF3hdCGE0YEsbO2EoyLsvoCAzVYnjU4WxrI/CvOX/nZYXs4Y7Oet4QShdis3mk8in8xD2s0NdA/kvbP23WmkjfbtCxibI0tswGvzYalSi8zs9AgMBAAECgYBv4PnDUltIVPfBNQoUZkNMrJq38SxxkLJ6j613pc7Df/z+q4AcM6AjIY0prAdvcTmPEBGNg5u/2MCcjh9YYy3Tb3CvROYwKd6eaY/2kyKRhmLJELFvJeFHGpNLQu6wgkJvUtxNDBwaekhFUeD5amnj4Iu/bKD8hQKdqln/YQNqvQJBAM5L2MA99YUQQkKBdJ9OFIKcLsA8gfw261YO3BeGHtg6oDxraIMh5oQVyF5FEYcmw6fRJah3ghpbh+s4Crl9zL8CQQCqt+X+h3VEG/U8L+7+54HinU/67+MFLRnCb2nifNZ2fsKFauyMl8mZnieV/MlXOVWc48euqBbS5mwldXMJmmsDAkEAm1CVPp8nQugryqBmai3B2Ve3BvpHLtk2MxN69qb+f/MbgoGnLNsQDy/LK9Y6TRONSMJ45K4dILT6zsfhoyeoRwJASEP8SdhRXXlwQAASbTjWlHT/DDxLnz9OLi43ZjZuGk7iTNPMOl9uaWa3XxhYqmGMyhLpzhc9Qh1kHh8viRsivQJAQLNlDkRubVumOTGrKN04BANO/cqMPFaaq0a4rwILbOhZ6b68hKT4Q3rnXdcUk+qkLvRPPXp5CCm5lMHlYVRTQQ==";
//
//
//    @Override
//    public void doFilter(ServletRequest request,
//                         ServletResponse response,
//                         FilterChain chain) throws IOException, ServletException {
//
//        System.out.println("开始过滤请求参数");
//
//
//
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String method = (httpServletRequest.getMethod());
//        Map<String, String[]> map = httpServletRequest.getParameterMap();
//        if (map.size() > 0) {
//
//
//            //密文
//            if (map.get("encryptData") == null || map.get("encryptData").length == 0 || map.get("encryptData")[0] == null || map.get("encryptData")[0] == "") {
//                logger.error("密文为空");
//                this.returnJson(response, "请使用合法的请求!");
//                return;
//            }
//            //秘钥
//            if (map.get("encryptKey") == null || map.get("encryptKey").length == 0 || map.get("encryptKey")[0] == null || map.get("encryptKey")[0] == "") {
//                logger.error("秘钥为空");
//                this.returnJson(response, "请使用合法的请求!");
//                return;
//            }
//
// //            String encrypted = map.get("encrypted")[0] ;
// //            String data = map.get("requestData")[0];
// //            map.put("encryptKey", encrypted);
// //            map.put("encryptData", requestData);
// //            System.out.println(encrypted);
//
//            byte[] encryptedBytes = Base64.decodeBase64(map.get("encryptKey")[0].getBytes());
//            byte[] requestDataByytes = Base64.decodeBase64(map.get("encryptData")[0].getBytes());
//            String encrypted = new String(encryptedBytes);
//            String data = new String(requestDataByytes);
//
//            System.out.println(encrypted);
//
//            String content = null;
//            String aseKey = null;
//            try {
//                aseKey = RSAUtils.decryptDataOnJava(encrypted, SERVER_PRIVATE_KEY);
//                if (aseKey == null || aseKey == "") {
//                    throw new Exception();
//                }
//            } catch (Exception e) {
//                logger.error("参数【aseKey】解析异常！");
//                this.returnJson(response, "请使用合法的请求! 解析异常");
//                return;
//            }
//            try {
//                content = AesEncryptUtils.decrypt(data, aseKey);
//            } catch (Exception e) {
//                logger.error("参数【content】解析异常！");
//                this.returnJson(response, "参数【content】解析异常！");
//                return;
//            }
//            if (StringUtils.isEmpty(content) || StringUtils.isEmpty(aseKey)) {
//                logger.error("解析内容异常");
//                this.returnJson(response, "解析异常");
//                return;
//            }
//            //清除key
//            String newContent = content.replace(aseKey, "");
//            Map<String, Object> jsonToMap = JSONUtils.jsonToMap(newContent);
//            RequestParameterWrapper requestParameterWrapper = new RequestParameterWrapper(httpServletRequest);
//            for (String key : jsonToMap.keySet()) {
//                System.out.println(key + "->" + jsonToMap.get(key));
//                requestParameterWrapper.addParameter(key, jsonToMap.get(key));
//            }
//            chain.doFilter(requestParameterWrapper, response);
//        }else{
//            chain.doFilter(request, response);
//        }
//    }
//
//
//    private void returnJson(ServletResponse response, String msg) {
//        String json = JSONObject.toJSONString(Result.error(CodeMsg.SIGN_ERROR, msg));
//        PrintWriter writer = null;
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html; charset=utf-8");
//        try {
//            writer = response.getWriter();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        writer.print(json);
//    }
//
//
//    @Override
//    public void destroy() {
//    }
//
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//
// }
