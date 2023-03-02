package com.young.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.young.frame.RowMapper;
import com.young.frame.Template;

public class TestDao {
	DataSource dataSource;
	
	public TestDao() {
		MysqlDataSource jds=new MysqlDataSource();
		jds.setUrl("jdbc:mysql://localhost:3306/lecture");
		jds.setUser("scott");
		jds.setPassword("tiger");
		dataSource=jds;
	}

	public List<TestDto> selectAll(){
		String sql="select * from tybasket01 order by num desc";
		Template<TestDto> template=new Template<>(dataSource);
		template.update(sql);
		RowMapper<TestDto> mapper=new RowMapper<TestDto>(){
			@Override
			public TestDto rows(ResultSet rs) throws SQLException {
					return new TestDto(rs.getInt("num"),rs.getInt("cnt")
							, rs.getString("id"), rs.getString("sub"),rs.getString("content"));
			}
		};
		return template.queryForList(sql,mapper);
	}
	
	public int insertOne(String id,String sub,String content) {
		String sql="insert into tybasket01 (id,sub,content) values (?,?,?)";
		Template<TestDto> template=new Template<>(dataSource);
		return template.update(sql, id, sub,content);
	}

	public TestDto selectOne(int num) {
		String sql="update tybasket01 set cnt=cnt+1 where num=?";
		Template<TestDto> template=new Template<>(dataSource);
		template.update(sql,num);
		sql="select * from tybasket01 where num=?";
		template=new Template<>(dataSource);
		return template.queryForObject(sql, new RowMapper<TestDto>() {

			@Override
			public TestDto rows(ResultSet rs) throws SQLException {
				return new TestDto(rs.getInt("num"),rs.getInt("cnt")
						, rs.getString("id"), rs.getString("sub"),rs.getString("content"));
			}
		}, num);
	}
	
	public int updateOne(int num,String sub,String content) {
		String sql="update tybasket01 set sub=?,content=? where num=?";
		Template<TestDto> template=new Template<>(dataSource);
		return template.update(sql, sub,content,num);
	}

	public int deleteOne(int num) {
		String sql="delete from tybasket01 where num=?";
		Template<TestDto> template=new Template<>(dataSource);
		return template.update(sql,num);
		
	}
}
