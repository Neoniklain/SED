package com.unesco.core.controllerWeb;

import com.unesco.core.controller.PluginController;
import com.unesco.core.models.PluginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/demo")
public class PluginControllerWeb {
    @Autowired
    PluginController pluginController;

    @RequestMapping("/plugins")
    public ArrayList<PluginDTO> plugins() {
        return pluginController.plugins();
    }

    @RequestMapping("/plugin/{name}")
    public void startPlugin(@PathVariable String name) {
        pluginController.startPlugin(name);
    }
}
