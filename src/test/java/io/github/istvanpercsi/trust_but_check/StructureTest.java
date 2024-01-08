package io.github.istvanpercsi.trust_but_check;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class StructureTest {

    static JavaClasses javaClasses;

    @BeforeEach
    void setUp() {
        javaClasses = new ClassFileImporter().importPackages("io.github.istvanpercsi.trust_but_check");
    }

    @Test
    void testServiceLayer() {
        classes().that().haveSimpleNameEndingWith("ServiceImpl")
                .should().beAnnotatedWith(Service.class)
                .andShould().resideInAPackage("io.github.istvanpercsi.trust_but_check.service..")
                .andShould().notBeInterfaces()
                .check(javaClasses);
        classes().that().areAnnotatedWith(Service.class)
                .should().haveSimpleNameEndingWith("ServiceImpl")
                .andShould().resideInAPackage("io.github.istvanpercsi.trust_but_check.service..")
                .andShould().notBeInterfaces()
                .check(javaClasses);
        classes().that().haveSimpleNameEndingWith("Service")
                .should().notBeAnnotatedWith(Service.class)
                .andShould().resideInAPackage("io.github.istvanpercsi.trust_but_check.service.interfaces.input..")
                .andShould().beInterfaces()
                .check(javaClasses);
    }
}
