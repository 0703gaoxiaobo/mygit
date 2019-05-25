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
 * Author gxb
 * Date  2019/5/23
 */
public class ServiceTask extends AbstractTask {

    public ServiceTask(String className,List<ColumnInfo> tableInfos) {
        super(className,tableInfos);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Service填充数据
        Configuration configuration=ConfigUtil.getConfiguration();
        Map<String, String> serviceData = new HashMap<>();
        serviceData.put("BasePackageName", configuration.getPackageName());
        serviceData.put("ServicePackageName", configuration.getPath().getService());
        serviceData.put("EntityPackageName", configuration.getPath().getEntity());
        serviceData.put("DaoPackageName", configuration.getPath().getDao());
        serviceData.put("Author", configuration.getAuthor());
        serviceData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        serviceData.put("ClassName", className);
        serviceData.put("EntityName", StringUtil.firstToLowerCase(className));
        serviceData.put("PrimaryKey", GeneratorUtil.getPrimaryKeyColumnInfo(tableInfos).getPropertyName());
        serviceData.put("DaoPackageName", configuration.getPath().getDao());
        serviceData.put("QueryHelperPackageName", configuration.getPath().getQueryHelper());
        serviceData.put("RequestPackageName", configuration.getPath().getRequest());
        serviceData.put("ResponsePackageName", configuration.getPath().getResponse());

        String parentProject=configuration.getParentProject();
        if(!parentProject.endsWith("\\")||!parentProject.endsWith("/")){
            parentProject=parentProject+ File.separator+StringUtil.package2Path(configuration.getSubProject().getService());
        }else{
            parentProject=parentProject+StringUtil.package2Path(configuration.getSubProject().getService());
        }

        String filePath = FileUtil.getSourcePath(configuration.getDefaultPath(),parentProject)
                + StringUtil.package2Path(configuration.getPackageName())
                + StringUtil.package2Path(configuration.getPath().getService());
//        String fileName;
//        if (StringUtil.isBlank(configuration.getPath().getInterf())) { // 表示不生成Service接口文件
//            serviceData.put("Impl", "");
//            serviceData.put("Override", "");
//            serviceData.put("InterfaceImport", "");
//            fileName = className + "Service.java";
//            System.out.println("Generating " + className + "Service.java");
//        } else {
//            serviceData.put("Impl", "Impl implements " + className + "Service");
//            serviceData.put("Override", "\n    @Override");
//            serviceData.put("InterfaceImport", "import " + configuration.getPackageName() + configuration.getPath().getInterf() + "." + className + "Service;");
//        }
        String fileName = className + "ServiceImpl.java";
        System.out.println("Generating " + className + "ServiceImpl.java");
        // 生成Service文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_SERVICE, serviceData, filePath + fileName);
    }
}
