# KitPvP
KitPvP is a Spigot plugin designed to dynamically load and manage kits on ExellPvP servers.

## Features

* Dynamic loading of kits so that the plugin and server do not need to be restarted.
* Region management with damage flags and kit control.
* Easy to understand API for creating new kits with full customization.
* Easy to use configuration to change kit settings.
* Built in store for buying kits.

## Dependencies

* Vault
* Economy plugin (Essentials is excellent)

## An API!

KitPvP now has an API built in which can be accessed from other plugins using Spigot's build in service provider!

Some key components you can access are:

* The region, kit, and ability managers.
* The player's individual kits.
* The player stats (deaths and kills).

Here's how you can access it from a different class:

```Java
public class ExamplePlugin extends JavaPlugin {
  private KitPvPAPI kitpvpAPI;

  @Override
  public void onEnabled() {
    if (!loadAPI()) {
      getLogger().info("Failed to find KitPvP's API!");
      getPluginManager().disablePlugin(this);
    } else {
      // Do other stuff here...
      getLogger().info("Plugin enabled!");
    }
  }
  
  @Override
  public void onDisable() {
    getLogger().info("Plugin disabled!");
  }
  
  private boolean loadAPI() {
    RegisteredServiceProvider<KitPvPAPI> rsp = getServer().getServicesManager().getRegistration(KitPvPAPI.class);
    if (rsp != null) {
      kitpvpAPI = rps.getProvider();
      return true;
    } else {
      return false;
    }
  }
}
```

Don't forget to add KitPvP as a dependency in your plugin.yml!
