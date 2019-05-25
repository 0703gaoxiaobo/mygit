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
 * Date   2018/4/20
 */
public class ResponseTask extends AbstractTask {

    /**
     * 1.单表生成  2.多表时生成子表实体
     */
    public ResponseTask(String className, List<ColumnInfo> infos) {
        this(className, null, null, infos);
    }

    /**
     * 一对多关系生成主表实体
     */
    public ResponseTask(String className, String parentClassName, String foreignKey, List<ColumnInfo> tableInfos) {
        this(className, parentClassName, foreignKey, null, tableInfos);
    }

    /**
     * 多对多关系生成主表实体
     */
    public ResponseTask(String className, String parentClassName, String foreignKey, String parentForeignKey, List<ColumnInfo> tableInfos) {
        super(className, parentClassName, foreignKey, parentForeignKey, tableInfos);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Request填充数据
        Configuration configuration=ConfigUtil.getConfiguration();
        System.out.println("Generating " + className + "Res.java");
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("BasePackageName", configuration.getPackageName());
        responseData.put("ResponsePackageName", configuration.getPath().getResponse());
        responseData.put("Author", configuration.getAuthor());
        responseData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        responseData.put("ClassName", className);
        GeneratorUtil.chooseMoudle(responseData,parentClassName,parentForeignKey,foreignKey,tableInfos);

        String parentProject=configuration.getParentProject();
        if(!parentProject.endsWith("\\")||!parentProject.endsWith("/")){
            parentProject=parentProject+ File.separator+StringUtil.package2Path(configuration.getSubProject().getResponse());
        }else{
            parentProject=parentProject+StringUtil.package2Path(configuration.getSubProject().getResponse());
        }

        String filePath = FileUtil.getSourcePath(configuration.getDefaultPath(),parentProject)
                + StringUtil.package2Path(configuration.getPackageName())
                +StringUtil.package2Path(configuration.getPath().getResponse());
        String fileName = className + "Res.java";
        // 生成Request文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_RESPONSE, responseData, filePath + fileName);
    }
}
