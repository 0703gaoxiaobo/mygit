package com.greedystar.generator.task;

import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.entity.Configuration;
import com.greedystar.generator.task.base.AbstractTask;
import com.greedystar.generator.utils.*;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author GreedyStar
 * Date   2019/1/24
 */
public class InterfaceTask extends AbstractTask {

    public InterfaceTask(String className, List<ColumnInfo> tableInfos) {
        super(className,tableInfos);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Service接口填充数据
        Configuration configuration=ConfigUtil.getConfiguration();
        System.out.println("Generating " + className + "Service.java");
        Map<String, String> interfaceData = new HashMap<>();
        interfaceData.put("BasePackageName", configuration.getPackageName());
        interfaceData.put("InterfacePackageName", configuration.getPath().getInterf());
        interfaceData.put("EntityPackageName", configuration.getPath().getEntity());
        interfaceData.put("Author", configuration.getAuthor());
        interfaceData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        interfaceData.put("ClassName", className);
        interfaceData.put("PrimaryKey", GeneratorUtil.getPrimaryKeyColumnInfo(tableInfos).getPropertyName());
        interfaceData.put("EntityName", StringUtil.firstToLowerCase(className));
        interfaceData.put("DaoPackageName", configuration.getPath().getDao());
        interfaceData.put("QueryHelperPackageName",configuration.getPath().getQueryHelper());
        interfaceData.put("RequestPackageName", configuration.getPath().getRequest());
        interfaceData.put("ResponsePackageName", configuration.getPath().getResponse());

        String parentProject=configuration.getParentProject();
        if(!parentProject.endsWith("\\")||!parentProject.endsWith("/")){
            parentProject=parentProject+ File.separator+StringUtil.package2Path(configuration.getSubProject().getInterf());
        }else{
            parentProject=parentProject+StringUtil.package2Path(configuration.getSubProject().getInterf());
        }

        String filePath = FileUtil.getSourcePath(configuration.getDefaultPath(),parentProject)
                + StringUtil.package2Path(configuration.getPackageName())
                + StringUtil.package2Path(configuration.getPath().getInterf());
        String fileName = className + "Service.java";
        // 生成Service接口文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_INTERFACE, interfaceData, filePath + fileName);
    }
}
