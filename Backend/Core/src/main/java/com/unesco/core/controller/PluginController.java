package com.unesco.core.controller;

import com.unesco.core.dto.PluginDTO;
import com.unesco.core.plugin.Plugin;
import com.unesco.core.pluginfactory.PluginFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PluginController {

    public ArrayList<PluginDTO> plugins() {
        ArrayList<Plugin> plugins = PluginFactory.getPlugins();
        ArrayList<PluginDTO> pluginModels = new ArrayList<>();
        for(Plugin p : plugins) {
            PluginDTO pm = new PluginDTO();
            pm.setName(p.getName());
            pm.setDescription(p.getDescription());
            pluginModels.add(pm);
        }

        return pluginModels;
    }

    public void startPlugin(String name) {
        ArrayList<Plugin> plugins = PluginFactory.getPlugins();
        for(Plugin p : plugins)
            if(p.getName().equals(name)) {
            p.invoke();
            break;
            }
    }
}
