package cn.luckyqiang.mybatis.config;

/**
 * @ClassName: MappedStatement
 * @Description: mapper.xml 封装对象
 * @Author: zhangzhiqiang
 * @Date: 2020-03-14 22:31
 * @Company: www.luckyqiang.cn
 */
public class MappedStatement {
    private String namespace;
    private String sourceId;
    private String resultType;
    private String sqlStr;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSqlStr() {
        return sqlStr;
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }
}
