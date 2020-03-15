package cn.luckyqiang.mybatis.test;

import cn.luckyqiang.mybatis.dao.CodeSystemDAO;
import cn.luckyqiang.mybatis.po.CodeSystemPO;
import cn.luckyqiang.mybatis.session.DefaultSqlSessionFactory;
import cn.luckyqiang.mybatis.session.SqlSession;
import cn.luckyqiang.mybatis.session.SqlSessionFactory;

/**
 * @ClassName: TestMybatis
 * @Description: 测试类
 * @Author: zhangzhiqiang
 * @Date: 2020-03-15 10:41
 * @Company: www.luckyqiang.cn
 */
public class TestMybatis {
    public static void main(String[] args) {
        //实例化sqlsession
        DefaultSqlSessionFactory factory = new DefaultSqlSessionFactory();
        SqlSession session = factory.openSession();
        //通过动态代理获取dao
        CodeSystemDAO codeSystemDAO = session.getMapper(CodeSystemDAO.class);
        CodeSystemPO codeSystemPO = codeSystemDAO.selectByPrimaryKey(3L);
        System.out.println("sql执行结果为：" + codeSystemPO.toString());

    }
}
