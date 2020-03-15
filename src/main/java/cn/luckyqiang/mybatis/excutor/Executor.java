package cn.luckyqiang.mybatis.excutor;

import cn.luckyqiang.mybatis.config.MappedStatement;
import sun.plugin2.main.server.ResultHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: Executor
 * @Description: Mybatis核心接口之一，定义了数据库操作最基本的方法，sqlSession的功能都是基于他来实现
 * @Author: zhangzhiqiang
 * @Date: 2020-03-15 11:26
 * @Company: www.luckyqiang.cn
 */
public interface Executor {
    /**
     * 查询接口
     * @param ms 封装sql语句的MappedStatement对象
     * @param parameter 传入sql的参数
     * @param <E>
     * @return将数据转换成指定对象集返回
     */
    <E> List<E> query(MappedStatement ms, Object parameter);
}
