package com.jumpnotzerosoftware.jetbrains.plugin;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiTypesUtil;
import com.jumpnotzerosoftware.jetbrains.plugin.settings.ProtoHighSettings;
import org.jetbrains.annotations.NotNull;

/**
 * Keywords annotator.
 *
 */
public class ProtoHighJavaSyntaxKeywordsAnnotator implements Annotator {

    private static final Logger LOGGER = Logger.getInstance(ProtoHighJavaSyntaxKeywordsAnnotator.class);


    private static void setHighlighting(@NotNull PsiElement element, @NotNull AnnotationHolder holder,
                                        @NotNull TextAttributesKey key) {

        if (ProtoHighSettings.isProtoHighDebugEnabled()) {
            LOGGER.info("setHighlighting=|" + element.getText() + "|");
        }

        //holder.createInfoAnnotation(element, "xxx").setEnforcedTextAttributes(TextAttributes.ERASE_MARKER);
        holder.createInfoAnnotation(element, null).setEnforcedTextAttributes(TextAttributes.ERASE_MARKER);


        if (ProtoHighSettings.isMarkLikeDeprecated()){

            holder.createInfoAnnotation(element, null).setHighlightType(ProblemHighlightType.LIKE_DEPRECATED);
        }

        String tooltip = null;

        if (ProtoHighSettings.isShowTooltip()){

            tooltip = "protobuf";
        }

        //holder.createInfoAnnotation(element, "protobuf").setHighlightType(ProblemHighlightType.LIKE_DEPRECATED);
        //holder.createInfoAnnotation(element, "xxx").setHighlightType(ProblemHighlightType.WEAK_WARNING);

        holder.createInfoAnnotation(element, tooltip).setEnforcedTextAttributes(
                         EditorColorsManager.getInstance().getGlobalScheme().getAttributes(key));

        //holder.createInfoAnnotation(element, "yyy").setEnforcedTextAttributes(
        //        EditorColorsManager.getInstance().getGlobalScheme().getAttributes(key));
    }

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {

        if (element.getLanguage().isKindOf("JAVA")) {

            if (element instanceof PsiTypeElement) {

                PsiTypeElement parameter = (PsiTypeElement) element;

                processPsiType(element, holder, parameter.getType());
            }
/*            else if (element instanceof PsiIdentifier) {

                PsiIdentifier psiIdentifier = (PsiIdentifier) element;

                LOGGER.info("\n--------psiIdentifier=" + psiIdentifier.getText());

                PsiElement parent = psiIdentifier.getParent();

                if (parent instanceof PsiReferenceExpression){

                    PsiReferenceExpression psiReferenceExpression = (PsiReferenceExpression) parent;

                    if (psiReferenceExpression != null){

                        PsiExpression psiExpression = psiReferenceExpression.getQualifierExpression();

                        if (psiExpression != null){

                            PsiType psiType = psiExpression.getType();

                            processPsiType(psiIdentifier, holder, psiType);
                        }
                    }
                }
                else {
                    LOGGER.info("PARENT of " + psiIdentifier.getText() + " is NOT a reference expression ");
                }
            }
  */        else if (element instanceof PsiIdentifier) {

                PsiIdentifier psiIdentifier = (PsiIdentifier) element;

                if (ProtoHighSettings.isProtoHighDebugEnabled()) {
                    LOGGER.info("\n-------psiIdentifier=" + psiIdentifier.getText());
                }

                PsiElement parent = psiIdentifier.getContext();

                if (ProtoHighSettings.isProtoHighDebugEnabled()) {
                    LOGGER.info("PARENT=" + parent);
                }

                PsiReference genRef = parent.getReference();

                if (genRef != null) {

                    PsiElement resolved = genRef.resolve();

                    if (ProtoHighSettings.isProtoHighDebugEnabled()) {
                        LOGGER.info("REFERENCE resolved=" + resolved);
                    }

                    if (resolved instanceof PsiClass){

                        PsiClass psiClass = (PsiClass) resolved;

                        if (ProtoHighSettings.isProtoHighDebugEnabled()) {
                            LOGGER.info("resolved to PsiClass=|" + psiClass + "|");
                        }
                        process(element, holder, psiClass);
                    }
                    else if (resolved instanceof PsiMethod){

                        PsiMethod psiMethod = (PsiMethod) resolved;

                        // TODO add change text color based on return type
                        //psiMethod.getReturnType()

                        PsiClass psiClass = psiMethod.getContainingClass();

                        if (ProtoHighSettings.isProtoHighDebugEnabled()) {
                            LOGGER.info("resolved to PsiMethod=|" + psiClass + "|");
                        }
                        process(element, holder, psiClass);
                    }
                    else {
                        PsiClass psiClass = PsiTreeUtil.getParentOfType(resolved, PsiClass.class);

                        if (ProtoHighSettings.isProtoHighDebugEnabled()) {
                            LOGGER.info("resolved using getParentOfType=|" + psiClass + "|");
                        }
                        process(element, holder, psiClass);
                    }
                }
                /*
                else if (parent instanceof PsiReferenceExpression){

                    PsiReferenceExpression psiReferenceExpression = (PsiReferenceExpression) parent;

                    if (psiReferenceExpression != null){

                        PsiExpression psiExpression = psiReferenceExpression.getQualifierExpression();

                        if (psiExpression != null){

                            PsiType psiType = psiExpression.getType();

                            if (psiType != null) {
                                processPsiType(psiIdentifier, holder, psiType);
                            }
                            else {
                                PsiType xpsiType = psiReferenceExpression.getType();
                                PsiClass psiClass = PsiTreeUtil.getParentOfType(element, PsiClass.class);

                                LOGGER.info("AAAAAAAAAAAAAAA " + psiClass);
                                process(element, holder, psiClass);
                            }
                        }
                    }
                }*/
                else {
                    if (ProtoHighSettings.isProtoHighDebugEnabled()) {
                        LOGGER.info("PARENT of " + psiIdentifier.getText() + " is NOT a reference expression ");
                    }
                }
            }
        }
    }

    private void processPsiType(@NotNull PsiElement element, @NotNull AnnotationHolder holder, PsiType psiType) {

        if (ProtoHighSettings.isProtoHighDebugEnabled()) {
            LOGGER.info("processPsiType=|" + psiType + "|");
        }

        PsiClass psiClass = PsiTypesUtil.getPsiClass(psiType);

        if (psiClass != null){

            process(element, holder, psiClass);
        }
        else {

            if (ProtoHighSettings.isProtoHighDebugEnabled()) {
                LOGGER.info("unable to resolve " + psiType);
            }
        }
    }

    private void process(@NotNull PsiElement element, @NotNull AnnotationHolder holder, PsiClass psiClass) {

        if (psiClass != null) {

            String fullClassName = psiClass.getQualifiedName();

            if (fullClassName != null) {

                boolean matched = false;

                if (ProtoHighSettings.isProtoHighDebugEnabled()) {
                    LOGGER.info("implOf=|" + psiClass.getQualifiedName() + "|");
                }

                if (matchesFilter(psiClass)) {

                    setHighlighting(element, holder, DefaultLanguageHighlighterColors.METADATA);
                    matched = true;
                }

                /*
                if (!matched){

                    LOGGER.info("no match, attempting match based on processPsiType");

                    for (PsiClassType implOf : psiClass.getImplementsListTypes()){

                        PsiClass implClass = PsiTypesUtil.getPsiClass(implOf);

                        if (implClass != null){

                            LOGGER.info("implOf=|" + implClass.getQualifiedName() + "|");

                            if (matchesFilter(psiClass)){

                                setHighlighting(element, holder, DefaultLanguageHighlighterColors.METADATA);
                                matched = true;
                            }
                        }
                        else {
                            LOGGER.info("unable to resolve=|" + implOf.getClassName() + "|");
                        }
                    }
                }*/
            }
        }
    }

    private boolean matchesFilter(PsiClass psiClass) {

        ProtoHighSettings protoHighSettings = getSettings(psiClass);

        boolean matchFound = false;

        String fullClassName = psiClass.getQualifiedName();

        if (protoHighSettings != null){

            for (String pathToMatch : protoHighSettings.getIncludePackages()){

                if (fullClassName.startsWith(pathToMatch)){

                    matchFound = true;

                    if (ProtoHighSettings.isProtoHighDebugEnabled()) {
                        LOGGER.info("MATCH found |" + fullClassName + "|");
                    }
                    break;
                }
                else {
                    if (ProtoHighSettings.isProtoHighDebugEnabled()) {
                        LOGGER.info("NO MATCH found |" + fullClassName + "| against |" + pathToMatch + "|");
                    }
                }
            }
        }
        else {

            if (ProtoHighSettings.isProtoHighDebugEnabled()) {
                LOGGER.warn("ProtoHighSettings not found");
            }
        }

        return matchFound;
    }

    private ProtoHighSettings getSettings(PsiClass psiClass) {

        ProtoHighSettings protoHighSettings = null;
        Module module = ModuleUtilCore.findModuleForPsiElement(psiClass);

        if (module != null){

            Project project = module.getProject();

            if (project != null){

                protoHighSettings = ProtoHighSettings.getInstance(project);
            }
        }

        return protoHighSettings;
    }
}
