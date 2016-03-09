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
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarFile;

/**
 * Created by Mac on 3/8/2016.
 */
public class LoadExternalJar {
    private static ConcurrentHashMap<String, Long> jarNames;

    public static void loadJars() {
        if (jarNames == null) {
            jarNames = new ConcurrentHashMap<>();
        }
        File file = new File(KitPvP.getSingleton().plugin.getDataFolder(), "kits");
        if (!file.exists()) {
            file.mkdir();
        }
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            try {
                for (File f : files) {
                    boolean modified = beenModified(f);
                    if (modified || !jarNames.containsKey(f.getName())) {
                        KitYML kit = getKitYML(new JarFile(f));
                        if (kit.getMain() != null) {
                            loadFile(f, kit, modified);
                        } else {
                            KitPvP.getSingleton().plugin.getLogger().warning("Could not load " + f.getName() + ".");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static ConcurrentHashMap<String, Long> getJarNames() {
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

    private static boolean beenModified(File file) {
        if (jarNames.containsKey(file.getName())) {
            if (jarNames.get(file.getName()) != file.lastModified()) {
                return true;
            }
        }
        return false;
    }

    private static void loadFile(File f, KitYML kit, boolean modified) {
        try {
            URL[] urls = {f.toURI().toURL()};
            URLClassLoader child = new URLClassLoader(urls, KitPvP.getSingleton().plugin.getClass().getClassLoader());
            Class classToLoad = Class.forName(kit.getMain(), true, child);
            Class<?> superClass = classToLoad.getSuperclass();
            if (superClass.getName().equals("me.exellanix.kitpvp.external_jars.ExternalKit")) {
                Method method = classToLoad.getDeclaredMethod("enable");
                Object instance = classToLoad.newInstance();
                Object result = method.invoke(instance);
                if (modified) {
                    KitPvP.getSingleton().plugin.getLogger().info("The kit \"" + kit.getName() + "\" has been modified.");
                } else {
                    KitPvP.getSingleton().plugin.getLogger().info("Loading kit \"" + kit.getName() + "\".");
                }
                jarNames.put(f.getName(), f.lastModified());
            } else {
                KitPvP.getSingleton().plugin.getLogger().warning("Could not load the kit " + kit.getName() + ". Please make sure the kit extends ExternalKit.");
            }
        } catch (MalformedURLException | IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void reloadJars() {
        if (jarNames == null) {
            jarNames = new ConcurrentHashMap<>();
        }
        File file = new File(KitPvP.getSingleton().plugin.getDataFolder(), "kits");
        if (!file.exists()) {
            file.mkdir();
        }
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            KitPvP.getSingleton().getKitManager().unregisterAllKits();
            try {
                for (File f : files) {
                    KitYML kit = getKitYML(new JarFile(f));
                    if (kit.getMain() != null) {
                        loadFile(f, kit, false);
                    } else {
                        KitPvP.getSingleton().plugin.getLogger().warning("Could not load " + f.getName() + ".");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
