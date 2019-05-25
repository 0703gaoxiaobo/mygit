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
public class ControllerTask extends AbstractTask {

    public ControllerTask(String className, List<ColumnInfo> tableInfos) {
        super(className,tableInfos);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Controller填充数据
        Configuration configuration=ConfigUtil.getConfiguration();
        System.out.println("Generating " + className + "Controller.java");
        Map<String, String> controllerData = new HashMap<>();
        controllerData.put("BasePackageName", configuration.getPackageName());
        controllerData.put("ControllerPackageName", configuration.getPath().getController());
        if (StringUtil.isBlank(configuration.getPath().getInterf())) {
            controllerData.put("ServicePackageName", configuration.getPath().getService());
        } else {
            controllerData.put("ServicePackageName", configuration.getPath().getInterf());
        }
        controllerData.put("EntityPackageName", configuration.getPath().getEntity());
        controllerData.put("Author", configuration.getAuthor());
        controllerData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        controllerData.put("ClassName", className);
        controllerData.put("EntityName", StringUtil.firstToLowerCase(className));
        controllerData.put("PrimaryKey", GeneratorUtil.getPrimaryKeyColumnInfo(tableInfos).getPropertyName());
        controllerData.put("RequestPackageName", configuration.getPath().getRequest());
        controllerData.put("ResponsePackageName", configuration.getPath().getResponse());
        String parentProject=configuration.getParentProject();
        if(!parentProject.endsWith("\\")||!parentProject.endsWith("/")){
            parentProject=parentProject+ File.separator+StringUtil.package2Path(configuration.getSubProject().getController());
        }else{
            parentProject=parentProject+StringUtil.package2Path(configuration.getSubProject().getController());
        }
        String filePath = FileUtil.getSourcePath(configuration.getDefaultPath(),parentProject)
                + StringUtil.package2Path(configuration.getPackageName())
                + StringUtil.package2Path(configuration.getPath().getController());
        String fileName = className + "Controller.java";
        // 生成Controller文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_CONTROLLER, controllerData, filePath + fileName);
    }
}
