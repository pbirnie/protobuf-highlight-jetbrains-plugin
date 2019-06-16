package com.jumpnotzerosoftware.jetbrains.plugin.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import java.util.Objects;
import javax.swing.JComponent;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

public class ProtoHighSettingsConfigurable implements Configurable {

    private final ProtoHighSettings settings;
    private final Project project;

    @Nullable
    private SettingsForm settingsForm;


    public ProtoHighSettingsConfigurable(Project project) {
        this.project = project;
        this.settings = ProtoHighSettings.getInstance(project);
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Protobuf Highlighter";
    }

    @Override
    public String getHelpTopic() {
        return null;
    }

    @Override
    public JComponent createComponent() {
        settingsForm = new SettingsForm(project, settings);
        return settingsForm.getPanel();
    }

    @Override
    public boolean isModified() {
        return settingsForm != null
                && !Objects.equals(settings, settingsForm.getSettings());
    }

    @Override
    public void apply() throws ConfigurationException {
        if (settingsForm != null) {
            settings.copyFrom(settingsForm.getSettings());
        }
    }

    @Override
    public void reset() {
        if (settingsForm != null) {
            settingsForm.reset(settings);
        }
    }

    @Override
    public void disposeUIResources() {
        settingsForm = null;
    }


}
