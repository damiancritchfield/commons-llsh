package org.lscode.commons.model;

import java.util.List;
import java.util.Map;

public class CommonTree {

    private String id;
    private String name;
    private String pid;
    private String fullPath;
    private Integer level;
    private List<CommonTree> children;
    private Map<String, Object> assets;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<CommonTree> getChildren() {
        return children;
    }

    public void setChildren(List<CommonTree> children) {
        this.children = children;
    }

    public Map<String, Object> getAssets() {
        return assets;
    }

    public void setAssets(Map<String, Object> assets) {
        this.assets = assets;
    }
}
