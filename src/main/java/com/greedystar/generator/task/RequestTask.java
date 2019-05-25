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


public class RequestTask extends AbstractTask {

    /**
     * 1.单表生成  2.多表时生成子表实体
     */
    public RequestTask(String className, List<ColumnInfo> infos) {
        this(className, null, null, infos);
    }

    /**
     * 一对多关系生成主表实体
     */
    public RequestTask(String className, String parentClassName, String foreignKey, List<ColumnInfo> tableInfos) {
        this(className, parentClassName, foreignKey, null, tableInfos);
    }

    /**
     * 多对多关系生成主表实体
     */
    public RequestTask(String className, String parentClassName, String foreignKey, String parentForeignKey, List<ColumnInfo> tableInfos) {
        super(className, parentClassName, foreignKey, parentForeignKey, tableInfos);
    }

    @Override
    public void run() throws IOException, TemplateException {
        Configuration configuration=ConfigUtil.getConfiguration();
        // 生成Request填充数据
        System.out.println("Generating " + className + "Req.java");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("BasePackageName", configuration.getPackageName());
        requestData.put("RequestPackageName",configuration.getPath().getRequest());
        requestData.put("Author", configuration.getAuthor());
        requestData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        requestData.put("ClassName", className);
        GeneratorUtil.chooseMoudle(requestData,parentClassName,parentForeignKey,foreignKey,tableInfos);

        String parentProject=configuration.getParentProject();
        if(!parentProject.endsWith("\\")||!parentProject.endsWith("/")){
            parentProject=parentProject+ File.separator+StringUtil.package2Path(configuration.getSubProject().getRequest());
        }else{
            parentProject=parentProject+StringUtil.package2Path(configuration.getSubProject().getRequest());
        }

        String filePath = FileUtil.getSourcePath(configuration.getDefaultPath(),parentProject)
                + StringUtil.package2Path(configuration.getPackageName())
                +StringUtil.package2Path(configuration.getPath().getRequest());
        String fileName = className + "Req.java";
        // 生成Request文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_REQUEST, requestData, filePath + fileName);
    }
}
