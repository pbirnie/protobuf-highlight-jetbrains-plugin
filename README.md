## Highlight code using protobuf java classes for JetBrains IDEs

[Protobuf Highlight Plugin](https://plugins.jetbrains.com/plugin/12567-protobuf-highlight) for IntelliJ IDEA & other JetBrains products.

### Features
        
Java code using generated <a href="https://developers.google.com/protocol-buffers/">protobuf</a> classes is highlighted within JetBrains products.<br>
        This makes it easier to see when a method or class you are using is actually a protobuf / <a href="https://grpc.io/">protobuf</a> stub

![image](https://github.com/pbirnie/protobuf-highlight-jetbrains-plugin/raw/master/image/result.png)


### Supported IDE Versions

Latest plugin release is compatible with IntelliJ IDEA 2017.1 (or later)  

Other JetBrains IDEs of the same or higher version should be supported as well. 

### Installation

You can install plugin manually by opening "Plugins" settings, 
"Browse repositories..." - search for "Protobuf Highlight".

Plugin page: https://plugins.jetbrains.com/plugin/12567-protobuf-highlight

### Configuration

#### Which packages are protobuf?

The plugin needs to know which package the java code generated from protobuf files exists in 

There is a configuration panel under File->Settings

   ![image](https://github.com/pbirnie/protobuf-highlight-jetbrains-plugin/raw/master/image/settings.png?raw=true)
   
#### What color to use for the highlighting? 

   In the current version, method calls and parameter references are highlighted using the IDEs default metadata color
   
   you can change it here:
   
   Editor | Color Schema | Language Defaults | Metadata

![image](https://github.com/pbirnie/protobuf-highlight-jetbrains-plugin/raw/master/image/metadatacolor.png)

### Roadmap

https://github.com/pbirnie/protobuf-highlight-jetbrains-plugin/wiki/Roadmap

### Build

Install the locally build zip from 

```protobuf-highlight-jetbrains-plugin/build/distributions/x.zip```


Run following command in the shell:

```
./gradlew build
```

It should be possible to run build on any platform (Linux, Windows, MacOS) where
[Gradle](https://gradle.org/) is supported.

JDK 8 must be installed and available on PATH in order to build plugin.

### Development tips

* log and monitor ide log file - see Help->Show Log in Explorer 

* Install psiviewer plugin

* base your code on another open source plugin

* Run IntelliJ IDEA with enabled plugin (for development)

```
./gradlew runIdea
```

* read https://www.jetbrains.org/intellij/sdk/docs/tutorials/custom_language_support/quick_fix.html





