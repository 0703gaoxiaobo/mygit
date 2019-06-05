package com.greedystar.generator.utils;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

/**
 * Author GreedyStar
 * Date   2018/4/19
 */
public class FileUtil {

    /**
     * @param type     使用模板类型
     * @param data     填充数据
     * @param filePath 输出文件
     * @throws IOException
     * @throws TemplateException
     */
    public static void generateToJava(int type, Object data, String filePath) throws IOException, TemplateException {
        System.out.println(filePath);
//        if(!StringUtil.isBlank(filePath)){
//            return;
//        }
        File file = new File(filePath);
        //判断文件是否已经存在
        if (file.exists()) {
            System.err.println("ERROR: " + file.getPath().substring(file.getPath().lastIndexOf("\\") + 1, file.getPath().length()) + " 已存在，请手动修改");
            return;
        }else {
            //如果文件不存在则创建
            String dir=filePath.substring(0,filePath.lastIndexOf("\\"));
            File mkdir=new File(dir);
            if(!mkdir.exists()){
                mkdir.mkdirs();
            }
            file.createNewFile();
        }
        Template tpl = getTemplate(type); // 获取模板文件
        // 填充数据
        StringWriter writer = new StringWriter();
        tpl.process(data, writer);
        writer.flush();
        // 写入文件
        FileOutputStream fos = new FileOutputStream(filePath);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw, 1024);
        tpl.process(data, bw);
        fos.close();
    }

    /**
     * 获取模板
     *
     * @param type 模板类型
     * @return
     * @throws IOException
     */
    private static Template getTemplate(int type) throws IOException {
        switch (type) {
            case FreemarketConfigUtils.TYPE_ENTITY:
                return FreemarketConfigUtils.getInstance().getTemplate("Entity.ftl");
            case FreemarketConfigUtils.TYPE_DAO:
                return FreemarketConfigUtils.getInstance().getTemplate("Dao.ftl");
            case FreemarketConfigUtils.TYPE_SERVICE:
                return FreemarketConfigUtils.getInstance().getTemplate("Service.ftl");
            case FreemarketConfigUtils.TYPE_CONTROLLER:
                return FreemarketConfigUtils.getInstance().getTemplate("Controller.ftl");
            case FreemarketConfigUtils.TYPE_MAPPER:
                return FreemarketConfigUtils.getInstance().getTemplate("Mapper.ftl");
            case FreemarketConfigUtils.TYPE_INTERFACE:
                return FreemarketConfigUtils.getInstance().getTemplate("Interface.ftl");
            case FreemarketConfigUtils.TYPE_REQUEST:
                return FreemarketConfigUtils.getInstance().getTemplate("Req.ftl");
            case FreemarketConfigUtils.TYPE_RESPONSE:
                return FreemarketConfigUtils.getInstance().getTemplate("Res.ftl");
            case FreemarketConfigUtils.TYPE_QUERYHELPER:
                return FreemarketConfigUtils.getInstance().getTemplate("QueryHelper.ftl");
            case FreemarketConfigUtils.TYPE_LISTHTML:
                return FreemarketConfigUtils.getInstance().getTemplate("list.ftl");
            case FreemarketConfigUtils.TYPE_INSERTHTML:
                return FreemarketConfigUtils.getInstance().getTemplate("insert.ftl");
            case FreemarketConfigUtils.TYPE_UPDATEHTML:
                return FreemarketConfigUtils.getInstance().getTemplate("update.ftl");
            default:
                return null;
        }
    }

    private static String getBasicProjectPath(boolean defaultPath,String parentProject) {
        String path="";
        StringBuilder sb = new StringBuilder();
        if(defaultPath){
             path= new File(FileUtil.class.getClassLoader().getResource("").getFile()).getPath() + File.separator;
             sb.append(path.substring(0, path.indexOf("target"))).append("src").append(File.separator).append("main").append(File.separator);
        }else{
            sb.append("src").append(File.separator).append("main").append(File.separator);
        }
        return sb.toString();
    }

    /**
     * 获取源码路径
     *
     * @return
     */
    public static String getSourcePath(boolean defaultPath,String parentProject) {
        StringBuilder sb = new StringBuilder();
        if(!defaultPath){
            sb.append(parentProject);
        }
        sb.append(getBasicProjectPath(defaultPath,parentProject)).append("java").append(File.separator);

        return sb.toString();
    }

    /**
     * 获取资源文件路径
     *
     * @return
     */
    public static String getResourcePath(boolean defaultPath,String parentProject) {
        StringBuilder sb = new StringBuilder();
        sb.append(getBasicProjectPath(defaultPath,parentProject)).append("resources").append(File.separator);
        return sb.toString();
    }

}
