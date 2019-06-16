package com.jumpnotzerosoftware.jetbrains.plugin.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("WeakerAccess")
@State(name = "ProtoHighSettings", storages = @Storage("tools.protobuf.xml"))
public class ProtoHighSettings implements PersistentStateComponent<ProtoHighSettings> {

    private List<String> includePackages = new ArrayList<>();

    // This is static to make lookup fast (its a global setting for this plugin
    private static boolean debugModeEnabled = false;

    // This is static to make lookup fast (its a global setting for this plugin
    private static boolean markLikeDeprecated = false;

    // This is static to make lookup fast (its a global setting for this plugin
    private static boolean showTooltip = true;

    public static ProtoHighSettings getInstance(Project project) {
        return project.getComponent(ProtoHighSettings.class);
    }

    @NotNull
    public List<String> getIncludePackages() {
        return includePackages;
    }

    public void setIncludePackages(@NotNull List<String> includePackages) {
        this.includePackages = new ArrayList<>(includePackages);
    }

    public static void setDebugModeEnabled(boolean val){
        debugModeEnabled = val;
    }


    public static boolean isProtoHighDebugEnabled() {
        return debugModeEnabled;
    }


    public static boolean isMarkLikeDeprecated() {
        return markLikeDeprecated;
    }

    public static void setMarkLikeDeprecated(boolean markLikeDeprecated) {
        ProtoHighSettings.markLikeDeprecated = markLikeDeprecated;
    }

    public static boolean isShowTooltip() {
        return showTooltip;
    }

    public static void setShowTooltip(boolean val) {
        ProtoHighSettings.showTooltip = val;
    }


    @Override
    public ProtoHighSettings getState() {
        return this;
    }

    @Override
    public void loadState(ProtoHighSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public void copyFrom(ProtoHighSettings settings) {
        setIncludePackages(settings.getIncludePackages());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProtoHighSettings)) {
            return false;
        }
        ProtoHighSettings that = (ProtoHighSettings) o;
        return Objects.equals(includePackages, that.includePackages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(includePackages);
    }
}
