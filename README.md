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

* join https://gitter.im/IntelliJ-Plugin-Developers/Lobby

Understand the PSI (Program Structure Interface) file represents a hierarchy of PSI elements (so-called PSI trees). A single PSI file (itself being a PSI element) may contain several PSI trees in specific programming languages. A PSI element, in its turn, can have child PSI elements.

PSI elements and operations at the level of individual PSI elements are used to explore the internal structure of source code as it is interpreted by the IntelliJ Platform. For example, you can use PSI elements to perform code analysis, such as code inspections or intention actions.

The PsiElement class is the common base class for PSI elements.

A file view provider (see the FileViewProvider class) was introduced in IntelliJ IDEA 6.0. Its main purpose is to manage access to multiple PSI trees within a single file.

For example, a JSPX page has a separate PSI tree for the Java code in it (PsiJavaFile), a separate tree for the XML code (XmlFile), and a separate tree for JSP as a whole JspFile).

Each of the PSI trees covers the entire contents of the file, and contains special “outer language elements” in the places where contents in a different language can be found.

A FileViewProvider instance corresponds to a single VirtualFile, a single Document, and can be used to retrieve multiple PsiFile instances.


from https://www.jetbrains.org/intellij/sdk/docs/basics/indexing_and_psi_stubs.html


Parsing files in IntelliJ Platform is a two-step process. First, an abstract syntax tree (AST) is built, defining the structure of the program. AST nodes are created internally by the IDE and are represented by instances of the ASTNode class. Each AST node has an associated element type IElementType instance, and the element types are defined by the language plugin. The top-level node of the AST tree for a file needs to have a special element type, implementing the IFileElementType interface.

The AST nodes have a direct mapping to text ranges in the underlying document. The bottom-most nodes of the AST match individual tokens returned by the lexer, and higher level nodes match multiple-token fragments. Operations performed on nodes of the AST tree, such as inserting, removing, reordering nodes and so on, are immediately reflected as changes to the text of the underlying document.

Second, a PSI, or Program Structure Interface, tree is built on top of the AST, adding semantics and methods for manipulating specific language constructs. Nodes of the PSI tree are represented by classes implementing the PsiElement interface and are created by the language plugin in the ParserDefinition.createElement()method. The top-level node of the PSI tree for a file needs to implement the PsiFile interface, and is created in the ParserDefinition.createFile() method.

https://www.jetbrains.org/intellij/sdk/docs/reference_guide/custom_language_support/implementing_parser_and_psi.html 

easiest way to see the plugins that can be created is by looking at the plugin.xml for a well written plugin
for example here: https://github.com/protostuff/protobuf-jetbrains-plugin/blob/5316dfacbca5aafabf5e3d89878e24f4ead12ba8/src/main/resources/META-INF/plugin.xml
 
https://www.jetbrains.com/help/idea/psi-viewer.html

