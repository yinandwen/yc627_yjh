package com.mryin.shyc.yc627_yjh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mryin.shyc.yc627_yjh.pojo.Desc;
import com.mryin.shyc.yc627_yjh.service.DescService;
import com.mryin.shyc.yc627_yjh.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class DescController {
    @Autowired
    private DescService descService;

    @RequestMapping("/Desc/problemDescInfo")
    public String getAll(Model model , HttpServletRequest request , @RequestParam(value="p" , defaultValue = "1") Integer page) {
        int pageSize = 3 ;
        PageHelper.startPage(page, pageSize);
        List<Desc> allDesc = descService.getAllDesc();
        PageInfo<Desc> pageInfo = new PageInfo<>(allDesc);
        List<Desc> list = pageInfo.getList();
        model.addAttribute("DescList" , list);
        StringBuffer requestURL = request.getRequestURL();
        String pageNav = Pager.getPageNav(page, pageSize, pageInfo, requestURL);
        model.addAttribute("pageNav",pageNav);
        return "afterSale/problemDescList";
    }



    @RequestMapping(value = "/Desc/deleteDesc/{id}")
    public String delete(@PathVariable("id") Integer id , Model model) {
        descService.deleteDesc(id);
        System.out.println("===========");
        List<Desc> allDesc = descService.getAllDesc();
        model.addAttribute("DescList",allDesc);
        return "redirect:/Desc/problemDescInfo";
    }


    @RequestMapping("/Desc/addProblemDesc")
    public String toAddProblemDesc() {
        return "afterSale/addProblemDesc";
    }

    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping("/Desc/addProblemDescription")
    public String addProblemDescription(Desc desc , Model model , @RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
        //图片上传成功后，将图片的地址写到数据库
        String filePath = "D:\\IDEAProject\\untitled2\\upload";//保存图片的路径,tomcat中有配置
        //获取原始图片的拓展名
        String originalFilename = file.getOriginalFilename();
        //新的文件名字，使用uuid随机生成数+原始图片名字，这样不会重复
        String newFileName = UUID.randomUUID()+originalFilename;
        File targetFile = new File(filePath,newFileName);
        file.transferTo(targetFile);
        System.out.println("==========================");
        desc.setImage(newFileName);//文件名保存到实体类对应属性上
        descService.addDesc(desc);
        return "redirect:/Desc/problemDescInfo";
    }


    @RequestMapping("/Desc/toUpdateDesc/{id}")
    public String toUpdateProblemDesc(@PathVariable("id") Integer id , Model model) {
        System.out.println(id);
        Desc desc = descService.getAllDescById(id);
        System.out.println(desc);
        model.addAttribute("desc",desc);
        return "afterSale/updateProblemDesc";
    }

    @RequestMapping("/Desc/updateProblemDescription")
    public String updateProblemDescription(Desc desc , Model model, HttpSession session) {
        Object attribute = session.getAttribute("desc");
        System.out.println(attribute);
        descService.updateDesc(desc);
        return "redirect:/Desc/problemDescInfo";
    }

}
