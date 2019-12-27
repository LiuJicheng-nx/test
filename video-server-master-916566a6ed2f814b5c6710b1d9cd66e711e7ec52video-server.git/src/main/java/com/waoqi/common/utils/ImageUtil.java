package com.waoqi.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageUtil {

    /**
     * 将img标签中的src进行二次包装
     *
     * @param content     内容
     * @param replaceHttp 需要在src中加入的域名
     * @return
     */
    public static String repairContent(String content, String replaceHttp) {
        if(content.indexOf("img") < 0){
            return content;
        }
        String patternStr = "<img\\s*([^>]*)\\s*src=\\\"(.*?)\\\"\\s*([^>]*)>";
        Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        String result = content;
        while (matcher.find()) {
            String src = matcher.group(2);
            String replaceSrc = "";
            if (src.lastIndexOf(".") > 0) {
                replaceSrc = src.substring(0, src.lastIndexOf(".")) + src.substring(src.lastIndexOf("."));
            }
            if (!src.startsWith("http://") && !src.startsWith("https://")) {
                replaceSrc = replaceHttp + replaceSrc;
            }
            result = result.replaceAll(src, replaceSrc);
        }

        String regEx = " style=\"(.*?)\"";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(result);
        String okContent = null;
        if (m.find()) {
            okContent = m.replaceAll("");
        }
        return okContent;
    }


    public static void main(String args[]){
        String content = "<p>123</p>";
        // 正则表达式
        String regEx = " style=\"(.*?)\"";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(content);
        String okContent = null;
        if (m.find()) {
            okContent = m.replaceAll("");
        }
        System.out.println(okContent);

    }



}
