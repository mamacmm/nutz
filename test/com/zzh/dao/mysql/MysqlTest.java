package com.zzh.dao.mysql;

import java.util.List;

import com.zzh.dao.DatabaseTest;
import com.zzh.dao.One;
import com.zzh.dao.Pager;
import com.zzh.dao.SimpleCondition;

public class MysqlTest extends DatabaseTest {

	public MysqlTest() {
		super();
		dsFile = "mysql.properties";
		sqlFile = "com/zzh/dao/mysql/create.sqls";
	}

	public void testQuery() {
		Pager pager = Pager.create(Pager.MySQL, 2, 3);
		List<One> list = dao.query(One.class, new SimpleCondition("id>4 AND id<19"), pager);
		assertEquals(3, list.size());
		assertEquals("one_8", list.get(1).getTxt());
		assertEquals(0,pager.getPageCount());
		assertEquals(0,pager.getRecordCount());
	}
	
}