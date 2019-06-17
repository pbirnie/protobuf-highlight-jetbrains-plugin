package com.jumpnotzerosoftware.jetbrains.plugin;

import static com.intellij.openapi.fileTypes.FileTypeFactory.FILE_TYPE_FACTORY_EP;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.extensions.ExtensionPoint;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.fileTypes.ExtensionFileNameMatcher;
import com.intellij.openapi.fileTypes.FileNameMatcher;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import com.intellij.openapi.project.Project;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import javax.swing.event.HyperlinkEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProtoHighPluginController implements ProjectComponent {

    public static final String PLUGIN_ID = "com.jumpnotzerosoftware.proto-highlight-jetbrains-plugin";

    private static final Logger LOGGER = Logger.getInstance(ProtoHighPluginController.class);
    private final Project project;

    public ProtoHighPluginController(Project project) {
        this.project = project;
    }

    @Override
    public void projectClosed() {
        LOGGER.info("projectClosed " + project.getName());
    }

    @Override
    public void projectOpened() {
        try {

        } catch (Exception e) {
            LOGGER.error("Could not detect or disable conflicting plugins", e);
        }
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "ProtoHighPluginController";
    }

}
