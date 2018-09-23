package com.unesco.core.pluginfactory;

import com.unesco.core.plugin.Plugin;
import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginFactory {
    public static ArrayList<Plugin> getPlugins() {
        ArrayList<Plugin> rez = new ArrayList<Plugin>();
        File pluginDir = new File("src/plugins");
        File[] jars = pluginDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile() && file.getName().endsWith(".jar");
            }
        });
        for (File jar : jars) {
            try {
                URL jarURL = jar.toURI().toURL();
                URLClassLoader classLoader = new URLClassLoader(new URL[]{jarURL});
                JarFile jf = new JarFile(jar);
                Enumeration<JarEntry> entries = jf.entries();
                while (entries.hasMoreElements()) {
                    String e = entries.nextElement().getName();
                    if (!e.endsWith(".class")) continue;
                    e = e.replaceAll("/", ".");
                    e = e.replaceAll(".class", "");
                    Class<?> plugCan = classLoader.loadClass(e);
                    Class<?>[] interfaces = plugCan.getInterfaces();
                    for (Class interf : interfaces) {
                        if (interf.getName().endsWith(".Plugin")) {
                            Class c = classLoader.loadClass(plugCan.getName());
                            Object inst = c.newInstance();
                            rez.add((Plugin) inst);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        return rez;
    }
}
