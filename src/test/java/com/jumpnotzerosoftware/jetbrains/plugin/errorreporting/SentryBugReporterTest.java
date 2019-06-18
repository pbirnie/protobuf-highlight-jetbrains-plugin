package com.jumpnotzerosoftware.jetbrains.plugin.errorreporting;

import io.sentry.context.Context;
import io.sentry.event.BreadcrumbBuilder;
import io.sentry.event.UserBuilder;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class SentryBugReporterTest {



    @Ignore
    @Test(expected = Exception.class)
    public void testSentryIntegration(){

        // Retrieve the current context.
        Context context = SentryBugReporter.sentry.getContext();

        // Record a breadcrumb in the current context. By default the last 100 breadcrumbs are kept.
        context.recordBreadcrumb(new BreadcrumbBuilder().setMessage("User made an action").build());

        // Set the user in the current context.
        context.setUser(new UserBuilder().setEmail("hello@sentry.io").build());

        // This sends a simple event to Sentry.
        SentryBugReporter.sentry.sendMessage("This is a test");

        try {
            unsafeMethod();
        } catch (Exception e) {
            // This sends an exception event to Sentry.
            SentryBugReporter.sentry.sendException(e);
        }

    }

    void unsafeMethod() {
        throw new UnsupportedOperationException("You shouldn't call this!");
    }

}