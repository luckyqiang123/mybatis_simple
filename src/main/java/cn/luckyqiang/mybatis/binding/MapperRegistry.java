package cn.luckyqiang.mybatis.binding;

import cn.luckyqiang.mybatis.config.Configuration;
import cn.luckyqiang.mybatis.config.MappedStatement;
import cn.luckyqiang.mybatis.session.SqlSession;


import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MapperRegistry
 * @Description: TODO
 * @Author: zhangzhiqiang
 * @Date: 2020-03-15 21:55
 * @Company: www.luckyqiang.cn
 */
public class MapperRegistry {
    private final Configuration configuration;
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<Class<?>, MapperProxyFactory<?>>();

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
//        addMappers(configuration.getMappedStatementMap());
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        MapperProxyFactory<T> mapperProxyFactory = new MapperProxyFactory<T>(type);
        return mapperProxyFactory.newInstance(sqlSession);
    }

    private void addMappers(Map<String, MappedStatement> mappedStatementMap) {
        for (Map.Entry<String, MappedStatement> mappedStatementEntry : mappedStatementMap.entrySet()) {
            addMapper( mappedStatementEntry.getValue().getClass());
        }

    }

    private  <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            knownMappers.put(type, new MapperProxyFactory<T>(type));
        }
    }




}
