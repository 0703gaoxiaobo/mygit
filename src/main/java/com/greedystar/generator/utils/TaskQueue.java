package com.greedystar.generator.utils;

import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.task.*;
import com.greedystar.generator.task.base.AbstractTask;

import java.util.LinkedList;
import java.util.List;

/**
 * Author GreedyStar
 * Date   2018-11-27
 */
public class TaskQueue {

    private LinkedList<AbstractTask> taskQueue = new LinkedList<>();

    private void initCommonTasks(String className,List<ColumnInfo> tableInfos) {
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getController())) {
            taskQueue.add(new ControllerTask(className,tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getService())) {
            taskQueue.add(new ServiceTask(className,tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getInterf())) {
            taskQueue.add(new InterfaceTask(className,tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getDao())) {
            taskQueue.add(new DaoTask(className,tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getHtml())) {
            taskQueue.add(new HtmlTask(className,tableInfos));
        }

    }

    public void initSingleTasks(String className, String tableName, List<ColumnInfo> tableInfos) {
        initCommonTasks(className,tableInfos);
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getEntity())) {
            taskQueue.add(new EntityTask(className, tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getQueryHelper())) {
            taskQueue.add(new QueryHelperTask(className, tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getRequest())) {
            taskQueue.add(new RequestTask(className,tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getResponse())) {
            taskQueue.add(new ResponseTask(className,tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getMapper())) {
            taskQueue.add(new MapperTask(className, tableName, tableInfos));
        }
    }

    public void initOne2ManyTasks(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, List<ColumnInfo> tableInfos, List<ColumnInfo> parentTableInfos) {
        initCommonTasks(className,tableInfos);
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getEntity())) {
            taskQueue.add(new EntityTask(className, parentClassName, foreignKey, tableInfos));
            taskQueue.add(new EntityTask(parentClassName, parentTableInfos));
            taskQueue.add(new QueryHelperTask(className, tableInfos));
            taskQueue.add(new RequestTask(className,tableInfos));
            taskQueue.add(new ResponseTask(className,tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getMapper())) {
            taskQueue.add(new MapperTask(tableName, className, parentTableName, parentClassName, foreignKey, tableInfos, parentTableInfos));
        }
    }

    public void initMany2ManyTasks(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, String parentForeignKey, String relationalTableName, List<ColumnInfo> tableInfos, List<ColumnInfo> parentTableInfos) {
        initCommonTasks(className,tableInfos);
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getEntity())) {
            taskQueue.add(new EntityTask(className, parentClassName, foreignKey, parentForeignKey, tableInfos));
            taskQueue.add(new EntityTask(parentClassName, parentTableInfos));
            taskQueue.add(new QueryHelperTask(className, tableInfos));
            taskQueue.add(new RequestTask(className,tableInfos));
            taskQueue.add(new ResponseTask(className,tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getMapper())) {
            taskQueue.add(new MapperTask(tableName, className, parentTableName, parentClassName, foreignKey, parentForeignKey, relationalTableName, tableInfos, parentTableInfos));
        }
    }

    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }

    public AbstractTask poll() {
        return taskQueue.poll();
    }

}
