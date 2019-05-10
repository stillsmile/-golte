package com.golte.common.util;

import com.golte.common.data.GetCityOutData;
import com.golte.mapper.GCentralCityMapper;
import com.golte.mapper.GCityMapper;
import com.golte.mapper.GRoleRelationshipMapper;
import com.golte.mapper.entity.GCentralCity;
import com.golte.mapper.entity.GCity;
import com.golte.mapper.entity.GRoleRelationship;
import com.golte.system.service.data.GetLoginOutData;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author 作者 GXS
 * @date 创建时间 2018年3月21日 下午5:32:04
 * @description 控制层基类
 */
public class BaseController {
	@Autowired
	private GRoleRelationshipMapper roleRelationshipMapper;
	@Autowired
	private GCityMapper cityMapper;

	@Autowired
	private GCentralCityMapper centralCityMapper;

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}

	/**
	 * 取出session
	 * 
	 * @return
	 */
	public GetLoginOutData getLoginUser(HttpServletRequest request) {
		GetLoginOutData data = (GetLoginOutData) CacheUtils.get(request.getHeader(UtilConst.LOGIN_TOKEN));
		if (Util.isEmpty(data)) {
			data = new GetLoginOutData();
			data.setLoginName("超级管理员");
			data.setEmpId(1L);
		}
		return data;
	}

	/**
	 * 获取会员所属城市列表
	 *
	 * @return
	 */
	public GetCityOutData getCityInfo(String token) {
		GetLoginOutData loginOutData = (GetLoginOutData) CacheUtils.get(token);
		if (Util.isEmpty(loginOutData)) {
			loginOutData = new GetLoginOutData();
			loginOutData.setLoginName("超级管理员");
			loginOutData.setEmpId(1L);
		}
		Example example = new Example(GRoleRelationship.class);
		example.createCriteria().andEqualTo("empId",loginOutData.getEmpId());
		List<GRoleRelationship> roleRelationships = roleRelationshipMapper.selectByExample(example);
		GetCityOutData cityOutData = new GetCityOutData();
		List<Long> cityIds = new ArrayList<>();
		for(GRoleRelationship roleRelationship:roleRelationships){
			List<GCity> cityList = new ArrayList<>();
			int roleId = roleRelationship.getRoleId().intValue();
			switch (roleId) {
				case 1:
				case 6:
				case 7:
					cityOutData.setCityIds(new ArrayList<>());
					return cityOutData;
				case 2:
					cityList = cityMapper.selectByEnterPerson(roleRelationship.getEmpId());
					for (GCity city:cityList){
						cityIds.add(city.getId());
					}
					if(Util.isEmptyList(cityIds)){
						cityIds.add(-1L);
					}
					break;
				case 3:
					cityList = cityMapper.selectByBusinessPrincipal(roleRelationship.getEmpId());
					for (GCity city:cityList){
						cityIds.add(city.getId());
					}
					if(Util.isEmptyList(cityIds)){
						cityIds.add(-1L);
					}
					break;
				case 4:
					cityList = cityMapper.selectByCityPrincipal(roleRelationship.getEmpId());
					for (GCity city:cityList){
						cityIds.add(city.getId());
					}
					if(Util.isEmptyList(cityIds)){
						cityIds.add(-1L);
					}
					break;
				case 5:
					cityList = cityMapper.selectByEmpId(roleRelationship.getEmpId());
					for (GCity city:cityList){
						cityIds.add(city.getId());
					}
					if(Util.isEmptyList(cityIds)){
						cityIds.add(-1L);
					}
					break;
				default:
					// 其他人员没有数据权限
					cityIds.add(-1L);
					break;
			}
		}
		//去除重复城市ID
		for (int i = 0 ;i < cityIds.size() - 1;i++) {
			for (int j = cityIds.size() - 1;j > i;j--) {
				if (cityIds.get(j).equals(cityIds.get(i))) {
					cityIds.remove(j);
				}
			}
		}
		cityOutData.setCityIds(cityIds);
		return cityOutData;
	}

	/**
	 * 获取会员所属中心城市列表
	 *
	 * @return
	 */
	public GetCityOutData getCentralCityInfo(HttpServletRequest request) {
		GetLoginOutData loginOutData = getLoginUser(request);
		Example example = new Example(GRoleRelationship.class);
		example.createCriteria().andEqualTo("empId",loginOutData.getEmpId());
		List<GRoleRelationship> roleRelationships = roleRelationshipMapper.selectByExample(example);
		GetCityOutData cityOutData = new GetCityOutData();
		List<Long> cityIds = new ArrayList<>();
		for(GRoleRelationship roleRelationship:roleRelationships){
			int roleId = roleRelationship.getRoleId().intValue();
			switch (roleId) {
				case 1:
				case 6:
				case 7:
					cityOutData.setCentralCityIds(new ArrayList<>());
					return cityOutData;
				case 5:
					example = new Example(GCentralCity.class);
					example.createCriteria().andEqualTo("empId", roleRelationship.getEmpId());
					List<GCentralCity> centralCities = centralCityMapper.selectByExample(example);
					centralCities.forEach(x->cityIds.add(x.getId()));
					if(Util.isEmptyList(cityIds)){
						cityIds.add(-1L);
					}
					break;
				default:
					// 其他人员没有数据权限
					cityIds.add(-1L);
					break;
			}
		}
		//去除重复城市ID
		for (int i = 0 ;i < cityIds.size() - 1;i++) {
			for (int j = cityIds.size() - 1;j > i;j--) {
				if (cityIds.get(j).equals(cityIds.get(i))) {
					cityIds.remove(j);
				}
			}
		}
		cityOutData.setCentralCityIds(cityIds);
		return cityOutData;
	}

	/**
	 * 导出Excel
	 * 
	 * @param response
	 * @param list
	 * @param header
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void exportExcel(HttpServletResponse response, List<?> list, String[] header, ExportExcelInterface export) throws Exception {
		Date dt = new Date();
		String fileName = new Long(dt.getTime()).toString();
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
		OutputStream out = response.getOutputStream();
		try {
			export.exportExcel(header, list, out, "yyyy-MM-dd");
			out.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			out.close();
		}
	}

	private String getCellValue(Cell cell){
		String firstCell="";
		if (cell == null) {
			firstCell="";
		} else if (cell.getCellType() == 0) {
			// 判断是否是日期
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat sdf = null;
				if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
					sdf = new SimpleDateFormat("HH:mm");
				} else {// 日期
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}
				Date date = cell.getDateCellValue();
				firstCell = sdf.format(date);
			} else {
				DecimalFormat df = new DecimalFormat("0");
				firstCell = df.format(cell.getNumericCellValue());
			}
		} else if (cell.getCellType() == 1) {
			firstCell= cell.getStringCellValue();
		}
		return firstCell;
	}
	
	/**
	 * 导入资源
	 * 
	 * @param uploadExcel
	 * @param sheetNo
	 * @param t
	 * @return
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("resource")
	public <T> List<T> importExcel(MultipartFile uploadExcel, Class<T> t, Integer sheetNo, Integer firstRowNum) throws IOException, IllegalAccessException,
	        IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException {
		Workbook workbook = null;
		String fileName = uploadExcel.getOriginalFilename();
		String fileEnd = fileName.substring(fileName.indexOf(".") + 1);
		Sheet sheet = null;
		// 兼容 2007及2003版本
		if ("xls".equals(fileEnd)) {
			workbook = new HSSFWorkbook(uploadExcel.getInputStream());
			sheet = (HSSFSheet) workbook.getSheetAt(sheetNo);
		} else {
			workbook = new XSSFWorkbook(uploadExcel.getInputStream());
			sheet = (XSSFSheet) workbook.getSheetAt(sheetNo);
		}
		List<T> list = new ArrayList<T>();
		for (int i = firstRowNum; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			T instance = t.newInstance();
			Field[] fields = instance.getClass().getDeclaredFields();
			int count = 0;
			int length = fields.length;
			for (int k = 0; k < length; k++) {
				Method setMethod = instance.getClass().getMethod("set" + StringUtils.capitalize(fields[k].getName()), String.class);
				if (k == length - 1) {
					String index = i + 1 + "";
					setMethod.invoke(instance, index);
					continue;
				}
				Cell cell = row.getCell(k);
				if (cell == null) {
					setMethod.invoke(instance, "");
					count ++;
				} else {
					cell.setCellType(CellType.STRING);
					if (Util.isEmpty(cell.getStringCellValue())) {
						count ++;
					}
					setMethod.invoke(instance, cell.getStringCellValue());
				}
			}
			if (count != length -1) {
				list.add(instance);
			}
		}
		return list;
	}
	
	/**
	 * 从excel生成建表sql
	 * 
	 * @param uploadExcel
	 * @param sheetNo
	 * @param
	 * @return
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("resource")
	public String importExcelSql(MultipartFile uploadExcel, Integer sheetNo) throws IOException, IllegalAccessException,
	        IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException {
		Workbook workbook = null;
		String fileName = uploadExcel.getOriginalFilename();
		String fileEnd = fileName.substring(fileName.indexOf(".") + 1);
		Sheet sheet = null;
		// 兼容 2007及2003版本
		if ("xls".equals(fileEnd)) {
			workbook = new HSSFWorkbook(uploadExcel.getInputStream());
			sheet = (HSSFSheet) workbook.getSheetAt(sheetNo);
		} else {
			workbook = new XSSFWorkbook(uploadExcel.getInputStream());
			sheet = (XSSFSheet) workbook.getSheetAt(sheetNo);
		}
		//全局的建表语句
		String allSql = "SET FOREIGN_KEY_CHECKS=0;";
		
		//一张表是否已经开始还未结束
		boolean flag = false; 
		
		//表的后半部分
		String lastTable= "";
		
		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if(row==null){
				if(flag){
					allSql+=lastTable;
					flag= false;
				}
				continue;
			}
			//第一列的内容 
			String firstCell = getCellValue(row.getCell(0));
			
			if("表名".equals(firstCell)){
				if(flag){
					allSql+=lastTable;
					flag= false;
				}
				//表头列
				String head = "DROP TABLE IF EXISTS `"+getCellValue(row.getCell(2))+"`;";
				head+="CREATE TABLE `"+getCellValue(row.getCell(2))+"` (";
				allSql+=head;
				lastTable = ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='"+getCellValue(row.getCell(5))+"';";
			}else if("序号".equals(firstCell)){
				continue;
			}else if(Util.isEmpty(firstCell)){
				//空白列，无信息
				if(flag){
					allSql+=lastTable;
					flag= false;
				}
				continue;
			}else if("结束".equals(firstCell)){
				
				break;
			}else{
				String body = "";
				//字段名
				String cell1 = getCellValue(row.getCell(1));
				//类型
				String cell3 = getCellValue(row.getCell(3));
				//非空
				String cell6 = getCellValue(row.getCell(6));
				//主键
				String cell7 = getCellValue(row.getCell(7));
				//中文描述
				String cell10 = getCellValue(row.getCell(10));
				if("Y".equalsIgnoreCase(cell7)){
					body+="`"+cell1+"` ";
					body+=""+cell3+" ";
					body+="N".equalsIgnoreCase(cell6)?"NOT NULL ":"DEFAULT NULL ";
					body+="AUTO_INCREMENT ";
					body+="COMMENT '"+cell10+"',";
					lastTable = "PRIMARY KEY (`"+cell1+"`)"+lastTable;
				}else{
					body+="`"+cell1+"` ";
					body+=""+cell3+" ";
					body+="N".equalsIgnoreCase(cell6)?"NOT NULL ":"DEFAULT NULL ";
					body+="COMMENT '"+cell10+"',";
				}
				allSql+=body;
				flag= true;
			}
		}
		System.out.println(allSql);
		return allSql;
	}

}
