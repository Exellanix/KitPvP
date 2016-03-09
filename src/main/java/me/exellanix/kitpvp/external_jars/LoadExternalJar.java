package me.exellanix.kitpvp.external_jars;

import me.exellanix.kitpvp.KitPvP;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.jar.JarFile;

/**
 * Created by Mac on 3/8/2016.
 */
public class LoadExternalJar {
    private static ArrayList<String> jarNames;

    public static void loadJars() {
        if (jarNames == null) {
            jarNames = new ArrayList<>();
        }
        File file = new File(KitPvP.plugin.getDataFolder(), "kits");
        if (!file.exists()) {
            file.mkdir();
        }
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            try {
                for (File f : files) {
                    KitYML kit = getKitYML(new JarFile(f));
                    if (kit.getMain() != null) {

                        URL[] urls = {f.toURI().toURL()};
                        URLClassLoader child = new URLClassLoader(urls, KitPvP.plugin.getClass().getClassLoader());
                        Class classToLoad = Class.forName(kit.getMain(), true, child);
                        Class<?> superClass = classToLoad.getSuperclass();
                        if (superClass.getName().equals("me.exellanix.kitpvp.external_jars.ExternalKit")) {
                            Method method = classToLoad.getDeclaredMethod("enable");
                            Object instance = classToLoad.newInstance();
                            Object result = method.invoke(instance);
                        } else {
                            KitPvP.plugin.getLogger().warning("Could not load the kit " + kit.getName() + ". Please make sure the kit extends ExternalKit.");
                        }

                    } else {
                        KitPvP.plugin.getLogger().warning("Could not load " + f.getName() + ".");
                    }
                }
            } catch (IOException | IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public static ArrayList<String> getJarNames() {
        return jarNames;
    }

    private static KitYML getKitYML(JarFile file) {
        KitYML kit = new KitYML();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(file.getInputStream(file.getEntry("kit.yml"))));
            while (in.ready()) {
                String current = in.readLine();
                if (current.startsWith("main: ")) {
                    kit.setMain(current.substring(current.indexOf(" ") + 1));
                } else if (current.startsWith("name: ")) {
                    kit.setName(current.substring(current.indexOf(" ") + 1));
                }
            }
            in.close();
        } catch (IOException e) {}
        return kit;
    }

}
