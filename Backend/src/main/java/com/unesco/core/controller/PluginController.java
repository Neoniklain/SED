package com.unesco.core.controller;

import com.unesco.core.models.PluginModel;
import com.unesco.core.plugin.Plugin;
import com.unesco.core.pluginfactory.PluginFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/demo")
public class PluginController {
    @RequestMapping("/plugins")
    public ArrayList<PluginModel> plugins() {
        ArrayList<Plugin> plugins = PluginFactory.getPlugins();
        ArrayList<PluginModel> pluginModels = new ArrayList<>();
        for(Plugin p : plugins) {
            PluginModel pm = new PluginModel();
            pm.setName(p.getName());
            pm.setDescription(p.getDescription());
            pluginModels.add(pm);
        }

        return pluginModels;
    }

    @RequestMapping("/plugin/{name}")
    public void startPlugin(@PathVariable String name) {
        ArrayList<Plugin> plugins = PluginFactory.getPlugins();
        for(Plugin p : plugins)
            if(p.getName().equals(name)) {
            p.invoke();
            break;
            }
    }
}
