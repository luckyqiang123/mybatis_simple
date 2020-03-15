package cn.luckyqiang.mybatis.reflection;

import cn.luckyqiang.mybatis.utils.StringUtil;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName: ReflectionUtil
 * @Description: 反射工具类
 * @Author: zhangzhiqiang
 * @Date: 2020-03-15 13:46
 * @Company: www.luckyqiang.cn
 */
public class ReflectionUtil {

    /**
     * 为指定的bean的propName属性的值设为value
     * @param bean
     * @param propName
     * @param value
     */
    public static void setPropToBean(Object bean, String propName, Object value) {
        Field f;
        try {
            //获得对象指定的属性
            f = bean.getClass().getDeclaredField(propName);
            //将字段设置为可通过反射进行访问
            f.setAccessible(true);
            //为属性设值
            f.set(bean, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从resultSet中读取一行数据，并填充到指定的实体bean
     * @param entity 待填充的实体bean
     * @param resultSet 从数据库加载的数据
     */
    public static void setPropToBeanFromResultset(Object entity, ResultSet resultSet) throws SQLException {
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            if (declaredFields[i].getType().getSimpleName().equals("String")) {
                setPropToBean(entity, declaredFields[i].getName(), resultSet.getString(StringUtil.humpToUnderline(declaredFields[i].getName())));
            } else if (declaredFields[i].getType().getSimpleName().equals("Integer")) {
                setPropToBean(entity, declaredFields[i].getName(), resultSet.getInt(declaredFields[i].getName()));
            } else if (declaredFields[i].getType().getSimpleName().equals("Long")) {
                setPropToBean(entity, declaredFields[i].getName(), resultSet.getLong(declaredFields[i].getName()));
            }
        }
    }
}
