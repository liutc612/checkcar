package com.ltc.server.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ltc.commons.ExportExcelFile;
import com.ltc.commons.JdbcUtil;
import com.ltc.commons.ParseExcel;
import com.ltc.domain.CarInfo;
import com.ltc.persist.FindCarInfoDao;
import com.ltc.persist.impl.FindCarInfoDaoImpl;
import com.ltc.server.OperateDataManager;

public class OperateDataManagerImpl implements OperateDataManager {
	//此方法用于数据备份
	@Override
	public void backupDataServer(String realPath) {
		//1.查询需要备份的数据
		FindCarInfoDao fcid = new FindCarInfoDaoImpl();
		List<CarInfo> list = fcid.findCarInfoAllPersist();
		//2.写入到特定的excel文件中
		ExportExcelFile eef = new ExportExcelFile();
		eef.exprotExcelFile(list, realPath);

	}
	//此方法用于导入备份文件
	@Override
	public void importExcel(String realPath) throws IOException, Exception {
		FindCarInfoDao fcid = new FindCarInfoDaoImpl();
		//1.解析excel文件
		ParseExcel pe = new ParseExcel();
		List<CarInfo> list = pe.createCheckCar(realPath, "xls");
		//2.操作数据库做数据恢复
		Connection conn = JdbcUtil.getConnection();
		try{
			conn.setAutoCommit(false);
			fcid.deleteCarInfoAll(conn);
			fcid.insertCarInfoAll(conn, list);
			conn.commit();
		}catch(SQLException e){
			e.printStackTrace();
			JdbcUtil.rollbackConnection(conn);
		}finally{
			JdbcUtil.closeConnection(conn);
		}
		
	}
	@Override
	//此方法用于导入外部文件
	public void importExcel(List<CarInfo> list) throws IOException, Exception {
		FindCarInfoDao fcid = new FindCarInfoDaoImpl();
		//操作数据库做数据恢复
		Connection conn = JdbcUtil.getConnection();
		try{
			conn.setAutoCommit(false);
			fcid.deleteCarInfoAll(conn);
			fcid.insertCarInfoAll(conn, list);
			conn.commit();
		}catch(SQLException e){
			e.printStackTrace();
			JdbcUtil.rollbackConnection(conn);
		}finally{
			JdbcUtil.closeConnection(conn);
		}
	}

}
