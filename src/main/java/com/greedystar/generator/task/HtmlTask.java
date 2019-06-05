package com.greedystar.generator.task;

import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.entity.Configuration;
import com.greedystar.generator.task.base.AbstractTask;
import com.greedystar.generator.utils.*;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlTask extends AbstractTask {

    public HtmlTask(String className, List<ColumnInfo> tableInfos) {
        super(className, tableInfos);
    }

    @Override
    public void run() throws IOException, TemplateException {
        Configuration configuration= ConfigUtil.getConfiguration();
        System.out.println("Generating list.html");
        System.out.println("Generating insert.html");
        System.out.println("Generating update.html");
        Map<String, String> htmlData = new HashMap<>();
        htmlData.put("ClassName", className);
        htmlData.put("EntityName", StringUtil.firstToLowerCase(className));
        htmlData.put("PrimaryKey", GeneratorUtil.getPrimaryKeyColumnInfo(tableInfos).getPropertyName());
        String parentProject=configuration.getParentProject();
        if(!parentProject.endsWith("\\")||!parentProject.endsWith("/")){
            parentProject=parentProject+ File.separator+StringUtil.package2Path(configuration.getSubProject().getHtml());
        }else{
            parentProject=parentProject+StringUtil.package2Path(configuration.getSubProject().getHtml());
        }
        String filePath = parentProject + StringUtil.package2Path(configuration.getPath().getHtml())+StringUtil.firstToLowerCase(className)+File.separator;
        String listName = "list.xml";
        String insertName = "insert.xml";
        String updateName = "update.xml";
        // 生成Mapper文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_LISTHTML, htmlData, filePath + listName);
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_INSERTHTML, htmlData, filePath + insertName);
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_UPDATEHTML, htmlData, filePath + updateName);
    }
}
