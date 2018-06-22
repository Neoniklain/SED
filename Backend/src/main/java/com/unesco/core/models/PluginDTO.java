package com.unesco.core.models;

public class PluginDTO {
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

    public PluginDTO(PluginDTO pluginModel) {
        this.name = pluginModel.name;
        this.description = pluginModel.description;
    }

    public PluginDTO() {
    }
}
