package com.mu.dao;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author MUZUKI
 */
public class DbProperties extends Properties{
	/**
	 * 静态，类一加载就执行
	 */

	private static DbProperties dbproperties=new DbProperties();

	private DbProperties() {
		try(InputStream fis=DbProperties.class.getClassLoader().getResourceAsStream("db.properties");
		){
			super.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 对外提供唯一的操作方法，来获取一次实例
	 */

	public static DbProperties getInstance() {
		if(dbproperties==null) {
			dbproperties=new DbProperties();
		}
		return dbproperties;
	}
}

