package cn.luckyqiang.mybatis.session;

import cn.luckyqiang.mybatis.config.Configuration;


/**
 * @ClassName: SqlSessionFactory
 * @Description: 1.实例化过程中加载配置文件到configurtion
 *               2.生产sqlsession
 * @Author: zhangzhiqiang
 * @Date: 2020-03-14 22:39
 * @Company: www.luckyqiang.cn
 */
public interface SqlSessionFactory {


    SqlSession openSession();

    Configuration getConfiguration();


}
