package cn.luckyqiang.mybatis.utils;

/**
 * @ClassName: StringUtil
 * @Description: 下划线转驼峰 驼峰转下划线
 * @Author: zhangzhiqiang
 * @Date: 2020-03-15 14:16
 * @Company: www.luckyqiang.cn
 */
public class StringUtil {
    /** 下划线转驼峰
     *       user_name  ---->  userName
     * house.user_name  ---->  userName
     *        userName   --->  userName
     * @param underlineName 带有下划线的名字
     * @return 驼峰字符串
     */
    public static String underlineToHump(String underlineName) {
        //截取下划线分成数组
        char[] charArray = underlineName.toCharArray();
        //判断上次循环的字符是否是"_"
        boolean underlineBefore = false;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0,l = charArray.length; i < l; i++) {
            //判断当前字符是否是"_",如果跳出本次循环
            if (charArray[i] == 95) {
                underlineBefore = true;
            } else if (underlineBefore) {
                //如果为true，代表上次的字符是"_",当前字符需要转成大写
                buffer.append(charArray[i] -= 32);
                underlineBefore = false;
            } else { //不是"_"后的字符就直接追加
                buffer.append(charArray[i]);
            }
        }
        return buffer.toString();
    }

    /** 驼峰转 下划线
     *       userName  ---->  user_name
     *       user_name  ---->  user_name
     * @param humpName  驼峰命名
     * @return  带下滑线的String
     */
    public static String humpToUnderline(String humpName) {
        //截取下划线分成数组，
        char[] charArray = humpName.toCharArray();
        StringBuffer buffer = new StringBuffer();
        //处理字符串
        for (int i = 0,l=charArray.length; i < l; i++) {
            if (charArray[i] >= 65 && charArray[i] <= 90) {
                buffer.append("_").append(charArray[i] += 32);
            }else {
                buffer.append(charArray[i]);
            }
        }
        return buffer.toString();
    }
}
