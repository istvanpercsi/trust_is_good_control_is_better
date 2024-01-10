package io.github.istvanpercsi.trust_but_check;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class StructureTest {

    static JavaClasses javaClasses;

    @BeforeEach
    void setUp() {
        javaClasses = new ClassFileImporter().importPackages("io.github.istvanpercsi.trust_but_check");
    }

    @Test
    void testLayeredArchitecture() {
        layeredArchitecture().consideringOnlyDependenciesInLayers()
                .layer("Input.Controller").definedBy("io.github.istvanpercsi.trust_but_check.input.controller..")
                .layer("Input.KafkaConsumer").definedBy("io.github.istvanpercsi.trust_but_check.input.kafka_consumer..")
                .optionalLayer("Output.Database").definedBy("io.github.istvanpercsi.trust_but_check.output.database..")
                .optionalLayer("Output.HttpClient").definedBy("io.github.istvanpercsi.trust_but_check.output.http_client..")
                .layer("BusinessLogic").definedBy("io.github.istvanpercsi.trust_but_check.service")
                .whereLayer("Input.Controller").mayNotAccessAnyLayer()
                .whereLayer("Input.KafkaConsumer").mayNotAccessAnyLayer()
                .check(javaClasses);
    }

    @Test
    void testServiceLayerSuffixes() {
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
        classes().that().haveSimpleNameEndingWith("Data")
                .should().resideInAPackage("io.github.istvanpercsi.trust_but_check.service.model")
                .allowEmptyShould(true)
                .check(javaClasses);
    }
}
