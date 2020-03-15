package cn.luckyqiang.mybatis.dao;
import cn.luckyqiang.mybatis.po.CodeSystemPO;


public interface CodeSystemDAO {

	CodeSystemPO selectByPrimaryKey(Long id);


}