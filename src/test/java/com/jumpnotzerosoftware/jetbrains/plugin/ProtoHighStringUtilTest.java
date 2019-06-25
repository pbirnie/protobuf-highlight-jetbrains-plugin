package com.jumpnotzerosoftware.jetbrains.plugin;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProtoHighStringUtilTest {

    @Test
    public void shouldExtractPackage(){

        String fullName = "com.company.message.Hello";

        String packageName = ProtoHighStringUtil.extractPackage(fullName);
        assertEquals("com.company.message", packageName);
    }

}