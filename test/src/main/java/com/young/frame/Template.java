package com.young.frame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class Template<T> {
	DataSource dataSource;
	
	public Template() {
	}

	public Template(DataSource dataSource) {//
		super();
		this.dataSource = dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public int update(String sql,Object ... args) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
			pstmt=conn.prepareStatement(sql);
			for(int i=0; i<args.length; i++)
				pstmt.setObject(i+1, args[i]);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	public T queryForObject(String sql,RowMapper<T> rowMapper,Object ... args) {
		return queryForList(sql, rowMapper, args).get(0);
	}
	
	public List<T> queryForList(String sql,RowMapper<T> rowMapper){
		return queryForList(sql,rowMapper,new Object[] {});
	}
	public List<T> queryForList(String sql,RowMapper<T> rowMapper,Object ... args){
		List<T> list=new ArrayList<>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			pstmt=conn.prepareStatement(sql);
			for(int i=0; i<args.length; i++)
				pstmt.setObject(i+1, args[i]);
			rs=pstmt.executeQuery();
			while(rs.next())
				list.add(rowMapper.rows(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//(list.toString()).replaceAll("\r\n", "<space>");
		//System.out.println("list.toString  :"+list.toString());
		return list;
	}
}
