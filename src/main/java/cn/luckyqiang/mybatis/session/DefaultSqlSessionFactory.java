package cn.luckyqiang.mybatis.session;

import cn.luckyqiang.mybatis.config.Configuration;
import cn.luckyqiang.mybatis.config.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName: DefaultSqlSessionFactory
 * @Description: TODO
 * @Author: zhangzhiqiang
 * @Date: 2020-03-15 21:25
 * @Company: www.luckyqiang.cn
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration = new Configuration();
    public static final String MAPPER_CONFIG_LOCATION = "mappers";
    public static final String DB_CONFIG_FILE = "db.properties";

    public DefaultSqlSessionFactory() {
        loadDbInfo();
        loadMappersInfo();
    }

    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    /**
     * 加载数据库配置信息
     */
    private void loadDbInfo() {
        InputStream dbIn = SqlSessionFactory.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE);
        Properties properties = new Properties();
        try {
            properties.load(dbIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        configuration.setJdbcDriver(properties.get("jdbc.driver").toString());
        configuration.setJdbcUrl(properties.get("jdbc.url").toString());
        configuration.setJdbcUserName(properties.get("jdbc.username").toString());
        configuration.setJdbcPassword(properties.get("jdbc.password").toString());
    }

    /**
     * 加载指定文件夹下的所有mapper.xml
     */
    private void loadMappersInfo() {
        URL resources = null;
        resources = SqlSessionFactory.class.getClassLoader().getResource(MAPPER_CONFIG_LOCATION);
        File mappers = new File(resources.getFile());
        if (mappers.isDirectory()) {
            File[] listFiles = mappers.listFiles();
            for (File listFile : listFiles) {
                loadMapperInfo(listFile);
            }
        }
    }

    private void loadMapperInfo(File file) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //获取根节点<mapper>
        Element root = document.getRootElement();
        //获取命名空间
        String namespace = root.attribute("namespace").getData().toString();
        //获取select字节点列表
        List<Element> selects = root.elements("select");
        //遍历select节点，将信息记录到mappedStatement对象，并赋值到configurtion对象
        for (Element element : selects) {
            MappedStatement mappedStatement = new MappedStatement();
            String id = element.attribute("id").getData().toString();
            String resultType = element.attribute("resultType").getData().toString();
            String sqlStr = element.element("sql").getData().toString();
            String sourceId = namespace + "." + id;
            mappedStatement.setNamespace(namespace);
            mappedStatement.setResultType(resultType);
            mappedStatement.setSourceId(sourceId);
            mappedStatement.setSqlStr(sqlStr);
            configuration.getMappedStatementMap().put(sourceId, mappedStatement);
        }

    }
}
