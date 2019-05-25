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
public class DaoTask extends AbstractTask {

    public DaoTask(String className, List<ColumnInfo> tableInfos) {
        super(className,tableInfos);
    }

    @Override
    public void run() throws IOException, TemplateException {
        Configuration configuration=ConfigUtil.getConfiguration();
        // 生成Dao填充数据
        System.out.println("Generating " + className + "Mapper.java");
        Map<String, String> daoData = new HashMap<>();
        daoData.put("BasePackageName", configuration.getPackageName());
        daoData.put("DaoPackageName", configuration.getPath().getDao());
        daoData.put("QueryHelperPackageName", configuration.getPath().getQueryHelper());
        daoData.put("RequestPackageName", configuration.getPath().getRequest());
        daoData.put("ResponsePackageName", configuration.getPath().getResponse());
        daoData.put("EntityPackageName", configuration.getPath().getEntity());
        daoData.put("Author", configuration.getAuthor());
        daoData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        daoData.put("ClassName", className);
        daoData.put("EntityName", StringUtil.firstToLowerCase(className));
        daoData.put("PrimaryKey", GeneratorUtil.getPrimaryKeyColumnInfo(tableInfos).getPropertyName());
        String parentProject=configuration.getParentProject();

        if(!parentProject.endsWith("\\")||!parentProject.endsWith("/")){
            parentProject=parentProject+ File.separator+StringUtil.package2Path(configuration.getSubProject().getDao());
        }else{
            parentProject=parentProject+StringUtil.package2Path(configuration.getSubProject().getDao());
        }

        String filePath = FileUtil.getSourcePath(configuration.getDefaultPath(),parentProject)
                + StringUtil.package2Path(configuration.getPackageName())
                + StringUtil.package2Path(configuration.getPath().getDao());
        String fileName = className + "Mapper.java";
        // 生成dao文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_DAO, daoData, filePath + fileName);
    }
}
