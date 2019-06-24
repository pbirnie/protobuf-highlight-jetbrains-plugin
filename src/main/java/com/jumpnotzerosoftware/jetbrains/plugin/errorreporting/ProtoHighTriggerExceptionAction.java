package com.jumpnotzerosoftware.jetbrains.plugin.errorreporting;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.SystemProperties;

/**
 * This class facilitates testing of the sentry.io reporting
 * A runtime exception when the action is invoked (from the tools menu)
 */
public class ProtoHighTriggerExceptionAction extends AnAction {

    private static String ENV_VAR = "SHOW_TRIGGER_BUTTON";

    private static final Logger LOGGER = Logger.getInstance(ProtoHighTriggerExceptionAction.class);



    @Override
    public void actionPerformed(AnActionEvent e) {

        String fakeException = "Protohigh test exception.";

        LOGGER.error("faking UI exception");
        throw new RuntimeException(fakeException);
    }

    @Override
    public void update(AnActionEvent e) {

        boolean visible = false;

        if (System.getenv(ENV_VAR) != null){
            visible = true;
        }

        LOGGER.info("ProtoHigh update " + ENV_VAR + "=" + visible);

        e.getPresentation().setEnabledAndVisible(visible);
    }
}
