## Highlight code using protobuf java classes for JetBrains IDEs

[Protobuf Highlight Plugin](https://plugins.jetbrains.com/plugin/12567-protobuf-highlight) for IntelliJ IDEA & other JetBrains products.

Latest plugin release is compatible with IntelliJ IDEA 2017.1 (or later)  

Other JetBrains IDEs of the same or higher version should be supported as well. 

### Installation

You can install plugin manually by opening "Plugins" settings, 
"Browse repositories..." - search for "Protobuf Highlight".

Plugin page: https://plugins.jetbrains.com/plugin/12567-protobuf-highlight

### Configuration

The plugin need to know which package the java code generated from protobuf files exists in 

There is a configration pannel under File->Settings

   ![image](https://user-images.githubusercontent.com/4040120/28202438-0fbe29ca-687e-11e7-964a-bb1f10dfcd4f.png)



### Roadmap

https://github.com/pbirnie/protobuf-highlight-jetbrains-plugin/wiki/Roadmap

### Build

Run following command in the shell:

```
./gradlew build
```

It should be possible to run build on any platform (Linux, Windows, MacOS) where
[Gradle](https://gradle.org/) is supported.

JDK 8 must be installed and available on PATH in order to build plugin.

### Run IntelliJ IDEA with enabled plugin (for development)

```
./gradlew runIdea
```

### Screenshots

![image](https://github.com/protostuff/protostuff-jetbrains-plugin/wiki/sample-2016-04-11.png)


