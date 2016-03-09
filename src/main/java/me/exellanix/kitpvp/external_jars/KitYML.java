package me.exellanix.kitpvp.external_jars;

/**
 * Created by Mac on 3/8/2016.
 */
public class KitYML {
    private String main;
    private String name;

    public KitYML(String main, String name) {
        this.main = main;
        this.name = name;
    }

    public KitYML() {}

    public String getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public void setName(String name) {
        this.name = name;
    }
}
