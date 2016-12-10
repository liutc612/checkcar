package com.ltc.server;

import java.io.IOException;
import java.util.List;

import com.ltc.domain.CarInfo;

/**
 * 数据管理的业务层接口
 * @author ltc 2016-5-21 13:57:57
 *
 */
public interface OperateDataManager {
	//此方法用于数据备份
	public void backupDataServer(String realPath);
	//此方法用于导入备份文件
	public void importExcel(String realPath) throws IOException, Exception;
	//此方法用于导入外部文件
	public void importExcel(List<CarInfo> list) throws IOException, Exception;
}
