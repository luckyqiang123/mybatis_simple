package cn.luckyqiang.mybatis.session;

import cn.luckyqiang.mybatis.binding.MapperProxy;
import cn.luckyqiang.mybatis.binding.MapperProxyFactory;
import cn.luckyqiang.mybatis.binding.MapperRegistry;
import cn.luckyqiang.mybatis.config.Configuration;
import cn.luckyqiang.mybatis.config.MappedStatement;
import cn.luckyqiang.mybatis.excutor.DefaultExecutor;
import cn.luckyqiang.mybatis.excutor.Executor;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @ClassName: DefaultSqlSession
 * @Description: mybatis默认暴露给外部的接口，实现增删改查的能力
 * 1.对外提供数据访问的api
 * 2.对内将请求转发给executor
 * 3.
 * @Author: zhangzhiqiang
 * @Date: 2020-03-15 10:37
 * @Company: www.luckyqiang.cn
 */
public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;

    private Executor executor;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.executor = new DefaultExecutor(configuration);
    }

    public <T> T selectOne(String statement, Object parameter) {
        List<Object> selectList = this.selectList(statement, parameter);
        if (null == selectList || 0 == selectList.size()) {
            return null;
        }
        if (1 == selectList.size()) {
            return (T) selectList.get(0);
        } else {
            throw new RuntimeException("Too many results");
        }
    }

    public <E> List<E> selectList(String statement, Object parameter) {
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statement);
        return executor.query(mappedStatement, parameter);
    }

    public <T> T getMapper(Class<T> type) {
        MapperRegistry mapperRegistry = new MapperRegistry(configuration);
        return mapperRegistry.getMapper(type, this);
    }
}
