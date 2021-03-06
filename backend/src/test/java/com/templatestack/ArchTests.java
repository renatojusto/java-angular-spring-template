package com.templatestack;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import javax.persistence.Entity;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@AnalyzeClasses(packages = "com.templatestack", importOptions = {
    ImportOption.DoNotIncludeArchives.class,
    ImportOption.DoNotIncludeJars.class,
    ImportOption.DoNotIncludeTests.class
})
public class ArchTests {

    @ArchTest
    public void checkLayeredApplication(JavaClasses importedClasses) {
        Architectures.LayeredArchitecture layeredArch =
            Architectures
                .layeredArchitecture()
                .layer("Domain")
                .definedBy("com.templatestack.domain..")
                .layer("Repository")
                .definedBy("com.templatestack.repository..")
                .layer("Service")
                .definedBy("com.templatestack.service..")
                .layer("Controller")
                .definedBy("com.templatestack.resource..");
        ;

        ArchRule rule = layeredArch
            .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service");

        rule.check(importedClasses);
    }

    @ArchTest
    public void archShouldBeFreeOfCycles(JavaClasses importedClasses) {
        ArchRule rule = slices()
            .matching("com.templatestack.(*)..").should().beFreeOfCycles();

        rule.check(importedClasses);
    }

    @ArchTest
    public void repositoryOnlyHaveDependentInServiceOrRepository(JavaClasses importedClasses) {
        ArchRule rule = classes()
            .that().resideInAPackage("..repository..")
            .should().onlyHaveDependentClassesThat().resideInAnyPackage("..repository..", "..service..");

        rule.check(importedClasses);
    }

    @ArchTest
    public void servicesShouldOnlyBeAccessedByControllers(JavaClasses importedClasses) {
        ArchRule rule = classes()
            .that().resideInAPackage("..service..")
            .should().onlyBeAccessed().byAnyPackage("..resource..", "..service..");

        rule.check(importedClasses);
    }

    @ArchTest
    public void entitiesMustResideInDomainPackage(JavaClasses importedClasses) {
        ArchRule rule = classes()
            .that().areAnnotatedWith(Entity.class)
            .should().resideInAPackage("..domain..");

        rule.check(importedClasses);
    }

    @ArchTest
    public void controllersMustResideInResourcePackage(JavaClasses importedClasses) {
        ArchRule rule = classes()
            .that().areAnnotatedWith(RestController.class)
            .should().resideInAPackage("..resource..");

        rule.check(importedClasses);
    }

    @ArchTest
    public void serviceMustResideInServicePackage(JavaClasses importedClasses) {
        ArchRule rule = classes()
            .that().areAnnotatedWith(Service.class)
            .should().resideInAPackage("..service..");

        rule.check(importedClasses);
    }

    @ArchTest
    public void mapperMustResideInServiceDtoMapperPackage(JavaClasses importedClasses) {
        ArchRule rule = classes()
            .that().areAnnotatedWith(Mapper.class)
            .should().resideInAPackage("..service.dto.mapper..");

        rule.check(importedClasses);
    }

    @ArchTest
    public void classesMustNotBeSuffixedWithDto(JavaClasses importedClasses) {
        ArchRule rule = noClasses().should()
            .haveSimpleNameEndingWith("Dto")
            .orShould().haveSimpleNameEndingWith("DTO");

        rule.check(importedClasses);
    }
}
