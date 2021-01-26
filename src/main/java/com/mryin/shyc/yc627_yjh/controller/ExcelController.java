package com.mryin.shyc.yc627_yjh.controller;

import com.mryin.shyc.yc627_yjh.pojo.Desc;
import com.mryin.shyc.yc627_yjh.service.DescService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


@Controller
public class ExcelController{
	
	@Autowired
	private DescService descService;
	
	@RequestMapping("/exportDescInfo")
    public void exportEmpoyeeInfo(HttpServletResponse response ) throws IOException{
        response.setCharacterEncoding("UTF-8");  
        List<Desc> descList = descService.getAllDesc();
        System.out.println(descList);
        //创建excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建sheet页
        HSSFSheet sheet = wb.createSheet("问题描述表");
        
        //创建标题行
        HSSFRow titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue("编号");
        titleRow.createCell(1).setCellValue("项目编号");
        titleRow.createCell(2).setCellValue("问题类型");
        titleRow.createCell(3).setCellValue("问题描述");
        titleRow.createCell(4).setCellValue("责任人");
        titleRow.createCell(5).setCellValue("项目开始时间");
        titleRow.createCell(6).setCellValue("上传图片");
        //遍历将数据放到excel列中
                for (Desc desc : descList) {
                    HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
                    dataRow.createCell(0).setCellValue(desc.getId());
                    dataRow.createCell(1).setCellValue(desc.getProjectId());
                    dataRow.createCell(2).setCellValue(desc.getProblemType());
                    dataRow.createCell(3).setCellValue(desc.getProblemDesc());
                    dataRow.createCell(4).setCellValue(desc.getProjectManager());
                    dataRow.createCell(5).setCellValue(desc.getStartDate());
                    dataRow.createCell(6).setCellValue(desc.getImage());
                }
                  /*   // 设置下载时客户端Excel的名称  
             String filename =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ".xls";  
                response.setContentType("application/vnd.ms-excel");  
                response.setHeader("Content-disposition", "attachment;filename=" + filename);  */

            // 设置下载时客户端Excel的名称   （上面注释的改进版本，上面的中文不支持）
                    response.setContentType("application/octet-stream;charset=utf-8");
                    response.setHeader("Content-Disposition", "attachment;filename="
                            + new String("问题描述表".getBytes(),"iso-8859-1") + ".xls");
                OutputStream ouputStream = response.getOutputStream();  
                wb.write(ouputStream);  
                ouputStream.flush();  
                ouputStream.close();
    }
	
}

