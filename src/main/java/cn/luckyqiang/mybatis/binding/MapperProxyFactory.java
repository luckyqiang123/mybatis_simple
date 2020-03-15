package cn.luckyqiang.mybatis.binding;

import cn.luckyqiang.mybatis.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * @ClassName: MapperProxyFactory
 * @Description: TODO
 * @Author: zhangzhiqiang
 * @Date: 2020-03-15 21:40
 * @Company: www.luckyqiang.cn
 */
public class MapperProxyFactory<T> {

    private final Class<T> daoInterface;

    public MapperProxyFactory(Class<T> daoInterface) {
        this.daoInterface = daoInterface;
    }

    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<T>(sqlSession);
        return newInstance(mapperProxy);
    }

    private T newInstance(MapperProxy<T> mapperProxy) {
        return (T) Proxy.newProxyInstance(daoInterface.getClassLoader(), new Class[] { daoInterface }, mapperProxy);
    }


}
