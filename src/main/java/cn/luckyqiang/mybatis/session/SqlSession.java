package cn.luckyqiang.mybatis.session;

import java.util.List;

/**
 * @ClassName: SqlSession
 * @Description: mybatis暴露给外部的接口，实现增删改查的能力
 * 1.对外提供数据访问的api
 * 2.对内将请求转发给executor
 *
 * @Author: zhangzhiqiang
 * @Date: 2020-03-14 23:13
 * @Company: www.luckyqiang.cn
 */
public interface SqlSession {

    /**
     * Retrieve a single row mapped from the statement key and parameter.
     * @param <T> the returned object type
     * @param statement 方法对应的sql语句坐标，namespacr + id.
     * @param parameter 要传入到sql语句中到查询参数.
     * @return Mapped object
     */
    <T> T selectOne(String statement, Object parameter);


    /**
     * Retrieve a list of mapped objects from the statement key and parameter.
     * @param <E> the returned list element type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @return List of mapped object
     */
    <E> List<E> selectList(String statement, Object parameter);

    /**
     * Retrieves a mapper.
     * @param <T> the mapper type
     * @param type Mapper interface class
     * @return a mapper bound to this SqlSession
     */
    <T> T getMapper(Class<T> type);
}
