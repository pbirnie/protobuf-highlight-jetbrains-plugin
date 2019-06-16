package com.jumpnotzerosoftware.jetbrains.plugin.settings;

import com.google.common.collect.Lists;
import com.intellij.openapi.project.Project;
import com.intellij.ui.CollectionListModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

import org.jetbrains.annotations.Nullable;

/**
 * Plugin settings form.
 *
 */
@SuppressWarnings("WeakerAccess")
public class SettingsForm {

    private final CollectionListModel<String> includePathModel;
    private final List<String> includePathListList;

    private final Project project;
    private JPanel panel;
    private com.intellij.ui.components.JBList includePathList;
    private JButton addButton;
    private JButton removeButton;
    private JLabel includePathsLabel;
    private JTextField packageInputField;
    private JCheckBox debugEnabledCheckbox;


    /**
     * Create new {@link SettingsForm} instance.
     *
     * @param settings is null if settings dialog runs without a project.
     */
    @SuppressWarnings("unchecked")
    public SettingsForm(Project project, @Nullable ProtoHighSettings settings) {
        this.project = project;
        List<String> internalIncludePathList = new ArrayList<>();
        if (settings != null) {
            internalIncludePathList.addAll(settings.getIncludePackages());
        }

        debugEnabledCheckbox.setSelected(settings.isDebugEnabled());

        includePathListList = Collections.unmodifiableList(internalIncludePathList);
        includePathModel = new CollectionListModel<>(internalIncludePathList, true);
        includePathList.setModel(includePathModel);
        addButton.addActionListener(e -> {

            String path = packageInputField.getText();
            includePathModel.add(path);
        });
        removeButton.addActionListener(e -> {
            int selectedIndex = includePathList.getSelectedIndex();
            if (selectedIndex != -1) {
                includePathModel.removeRow(selectedIndex);
            }
        });

        debugEnabledCheckbox.addActionListener(e -> {

            JCheckBox box = (JCheckBox) e.getSource();

            settings.setDebugMode(box.isSelected());

            int selectedIndex = includePathList.getSelectedIndex();
            if (selectedIndex != -1) {
                includePathModel.removeRow(selectedIndex);
            }
        });

        if (settings == null) {
            addButton.setEnabled(false);
            removeButton.setEnabled(false);
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    /**
     * Returns a copy of settings contained in the form.
     */
    public ProtoHighSettings getSettings() {
        ProtoHighSettings settings = new ProtoHighSettings();
        settings.setIncludePackages(Lists.newArrayList(includePathListList));
        return settings;
    }

    public void reset(ProtoHighSettings source) {
        includePathModel.removeAll();
        includePathModel.add(source.getIncludePackages());

    }

}
