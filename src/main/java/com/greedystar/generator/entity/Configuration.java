package com.greedystar.generator.entity;

import java.io.Serializable;

/**
 * Author GreedyStar
 * Date   2018/9/7
 */
public class Configuration implements Serializable {
    private String author;
    private String packageName;
    private Path path;
    private Db db;
    private boolean defaultPath;
    private String parentProject;
    private SubProject subProject;

    public boolean getDefaultPath() {
        return defaultPath;
    }

    public void setDefaultPath(boolean defaultPath) {
        this.defaultPath = defaultPath;
    }

    public String getParentProject() {
        return parentProject==null? "":parentProject;
    }

    public void setParentProject(String parentProject) {
        this.parentProject = parentProject;
    }

    public SubProject getSubProject() {
        return subProject;
    }

    public void setSubProject(SubProject subProject) {
        this.subProject = subProject;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPackageName() {
        return packageName == null ? "" : packageName + ".";
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Db getDb() {
        return db;
    }

    public void setDb(Db db) {
        this.db = db;
    }

    public static class Db {
        private String url;
        private String username;
        private String password;

        public Db() {
        }

        public Db(String url, String username, String password) {
            this.url = url;
            this.username = username;
            this.password = password;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class Path {
        private String controller;
        private String service;
        private String interf;
        private String dao;
        private String entity;
        private String mapper;
        private String request;
        private String response;
        private String queryHelper;

        public Path() {
        }

        public Path(String controller, String service, String interf, String dao, String entity,
                    String mapper, String request, String response,String queryHelper) {
            this.controller = controller;
            this.service = service;
            this.interf = interf;
            this.dao = dao;
            this.entity = entity;
            this.mapper = mapper;
            this.request = request;
            this.response = response;
            this.queryHelper=queryHelper;
        }

        public String getQueryHelper() {
            return queryHelper==null ? "" : queryHelper;
        }

        public void setQueryHelper(String queryHelper) {
            this.queryHelper = queryHelper;
        }

        public String getRequest() {
            return request==null ? "" :request;
        }

        public void setRequest(String request) {
            this.request = request;
        }

        public String getResponse() {
            return response==null ? "" : response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

        public String getController() {
            return controller == null ? "" : controller;
        }

        public void setController(String controller) {
            this.controller = controller;
        }

        public String getService() {
            return service == null ? "" : service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getInterf() {
            return interf;
        }

        public void setInterf(String interf) {
            this.interf = interf;
        }

        public String getDao() {
            return dao == null ? "" : dao;
        }

        public void setDao(String dao) {
            this.dao = dao;
        }

        public String getEntity() {
            return entity == null ? "" : entity;
        }

        public void setEntity(String entity) {
            this.entity = entity;
        }

        public String getMapper() {
            return mapper == null ? "" : mapper;
        }

        public void setMapper(String mapper) {
            this.mapper = mapper;
        }

    }

    public static class SubProject{
        private String controller;
        private String service;
        private String interf;
        private String dao;
        private String entity;
        private String mapper;
        private String request;
        private String response;
        private String queryHelper;

        public String getQueryHelper() {
            return queryHelper==null ? "" : queryHelper;
        }

        public void setQueryHelper(String queryHelper) {
            this.queryHelper = queryHelper;
        }

        public String getRequest() {
            return request==null ? "" :request;
        }

        public void setRequest(String request) {
            this.request = request;
        }

        public String getResponse() {
            return response==null ? "" : response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

        public String getController() {
            return controller == null ? "" : controller;
        }

        public void setController(String controller) {
            this.controller = controller;
        }

        public String getService() {
            return service == null ? "" : service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getInterf() {
            return interf;
        }

        public void setInterf(String interf) {
            this.interf = interf;
        }

        public String getDao() {
            return dao == null ? "" : dao;
        }

        public void setDao(String dao) {
            this.dao = dao;
        }

        public String getEntity() {
            return entity == null ? "" : entity;
        }

        public void setEntity(String entity) {
            this.entity = entity;
        }

        public String getMapper() {
            return mapper == null ? "" : mapper;
        }

        public void setMapper(String mapper) {
            this.mapper = mapper;
        }
    }

}
