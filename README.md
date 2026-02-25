# PlayerInventoryHelper !
[![GitHub](https://img.shields.io/github/license/vadim-soude/PlayerInventoryHelper)](https://github.com/vadim-soude/PlayerInventoryHelper/blob/master/LICENSE) [![](https://jitpack.io/v/vadim-soude/PlayerInventoryHelper.svg)](https://jitpack.io/#vadim-soude/PlayerInventoryHelper)

## Summary
- [Description](#description)
- [Installation](#installation)
- [Usage](#usage)
- [Gradle Dependency](#gradle-dependency)
- [Maven Dependency](#maven-dependency)
- [How does it work?](#how-does-it-work)
- [License](#license)

## Description

PlayerInventoryHelper is a plugin that add player inventory events when the player opens or closes their inventory.

![img.png](src/img.png)

## Installation
1. Download the latest release from the [releases page](https://github.com/vadim-soude/PlayerInventoryHelper/releases)
2. Download the latest version of PacketEvents for `spigot/paper` from [here](https://modrinth.com/plugin/packetevents/versions)
3. Place the downloaded JAR files in your server's `plugins` directory.
4. Restart your server.

## Usage
You can listen to the following events in your plugin:
- `PlayerInventoryOpenEvent`: Triggered when a player opens their inventory.
- `PlayerInventoryCloseEvent`: Triggered when a player closes their inventory.

Example:
```java
public class BukkitListener implements Listener {

    @EventHandler
    public void onPlayerOpenInventory(PlayerInventoryEvent.PlayerOpenInventoryEvent event){
        Player player = event.player();
        player.sendMessage("You opened your inventory!");
    }

    @EventHandler
    public void onPlayerCloseInventory(PlayerInventoryEvent.PlayerCloseInventoryEvent event){
        Player player = event.player();
        player.sendMessage("You closed your inventory!");
    }

}
```

## Gradle Dependency
To use PlayerInventoryHelper in your project, add the following dependency to your `build.gradle` file:
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    compileOnly 'com.github.vadim-soude:PlayerInventoryHelper:TAG'
}
```
Replace `TAG` with the latest version of PlayerInventoryHelper.

## Maven Dependency
To use PlayerInventoryHelper in your project, add the following dependency to your `pom.xml` file:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.vadim-soude</groupId>
        <artifactId>PlayerInventoryHelper</artifactId>
        <version>TAG</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```
Replace `TAG` with the latest version of PlayerInventoryHelper.

## How does it work?
PlayerInventoryHelper sends the player a packet that adds a fake recipe to the player's recipe book.
It also sends a packet that forces the recipe book to open. That way, when the player opens their inventory, they will see the recipe and send a packet to the server telling it that they have seen the recipe.

Then, when the server receives the packet, PlayerInventoryHelper sends another packet to remove the fake recipe, so the player might only see it for a tick.

This is a workaround to detect when the player opens or closes their inventory, because there is no way to do it with the Bukkit API (since the player doesn't send any packet when they open or close their inventory).

## License
This project is licensed under the MIT License - see the [LICENSE](https://github.com/vadim-soude/PlayerInventoryHelper/blob/master/LICENSE)

