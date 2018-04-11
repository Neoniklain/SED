package com.unesco.core.models;

public class PluginModel {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PluginModel(PluginModel pluginModel) {
        this.name = pluginModel.name;
        this.description = pluginModel.description;
    }

    public PluginModel() {
    }
}
