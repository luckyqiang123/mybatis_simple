package cn.luckyqiang.mybatis.excutor;

import cn.luckyqiang.mybatis.config.Configuration;
import cn.luckyqiang.mybatis.config.MappedStatement;
import cn.luckyqiang.mybatis.reflection.ReflectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName: DefaultExecutor
 * @Description: 默认执行器
 * @Author: zhangzhiqiang
 * @Date: 2020-03-15 11:41
 * @Company: www.luckyqiang.cn
 */
public class DefaultExecutor implements Executor {

    private final Configuration configuration;

    public DefaultExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    public <E> List<E> query(MappedStatement ms, Object parameter) {
        List<E> ret = new ArrayList<E>();
        try {
            Class.forName(configuration.getJdbcDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(configuration.getJdbcUrl(), configuration.getJdbcUserName(), configuration.getJdbcPassword());
            preparedStatement = connection.prepareStatement(ms.getSqlStr());
            parameterize(preparedStatement, parameter);
            System.out.println("sql语句为：" + preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();
            handleResultSet(resultSet, ret, ms.getResultType());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    private void parameterize(PreparedStatement preparedStatement, Object parametor) throws SQLException {
        if (parametor instanceof Integer) {
            preparedStatement.setInt(1, (Integer) parametor);
        } else if (parametor instanceof Long) {
            preparedStatement.setLong(1, (Long) parametor);
        } else if (parametor instanceof String) {
            preparedStatement.setString(1, (String) parametor);
        }

    }

    private <E> void handleResultSet(ResultSet resultSet, Collection<E> ret, String className) {
        Class<E> clazz = null;
        try {
            clazz = (Class<E>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                Object entity = clazz.newInstance();
                ReflectionUtil.setPropToBeanFromResultSet(entity, resultSet);
                ret.add((E) entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
