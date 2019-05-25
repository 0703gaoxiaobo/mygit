package com.greedystar.generator.task;

import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.entity.Configuration;
import com.greedystar.generator.task.base.AbstractTask;
import com.greedystar.generator.utils.*;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class QueryHelperTask extends AbstractTask {
    /**
     * 1.单表生成  2.多表时生成子表实体
     */
    public QueryHelperTask(String className, List<ColumnInfo> infos) {
        this(className, null, null, infos);
    }

    /**
     * 一对多关系生成主表实体
     */
    public QueryHelperTask(String className, String parentClassName, String foreignKey, List<ColumnInfo> tableInfos) {
        this(className, parentClassName, foreignKey, null, tableInfos);
    }

    /**
     * 多对多关系生成主表实体
     */
    public QueryHelperTask(String className, String parentClassName, String foreignKey, String parentForeignKey, List<ColumnInfo> tableInfos) {
        super(className, parentClassName, foreignKey, parentForeignKey, tableInfos);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Request填充数据
        Configuration configuration=ConfigUtil.getConfiguration();
        System.out.println("Generating " + className + "QueryHelper.java");
        Map<String, Object> queryHelperData = new HashMap<>();
        queryHelperData.put("BasePackageName", configuration.getPackageName());
        queryHelperData.put("QueryHelperPackageName", configuration.getPath().getQueryHelper());
        queryHelperData.put("Author", configuration.getAuthor());
        queryHelperData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        queryHelperData.put("ClassName", className);
        GeneratorUtil.chooseMoudle(queryHelperData,parentClassName,parentForeignKey,foreignKey,tableInfos);

        String parentProject=configuration.getParentProject();
        if(!parentProject.endsWith("\\")||!parentProject.endsWith("/")){
            parentProject=parentProject+ File.separator+StringUtil.package2Path(configuration.getSubProject().getQueryHelper());
        }else{
            parentProject=parentProject+StringUtil.package2Path(configuration.getSubProject().getQueryHelper());
        }

        String filePath = FileUtil.getSourcePath(configuration.getDefaultPath(),parentProject)
                + StringUtil.package2Path(configuration.getPackageName())
                +StringUtil.package2Path(configuration.getPath().getQueryHelper());
        String fileName = className + "QueryHelper.java";
        // 生成Request文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_QUERYHELPER, queryHelperData, filePath + fileName);
    }
}