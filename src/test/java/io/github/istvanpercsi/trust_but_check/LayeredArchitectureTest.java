package io.github.istvanpercsi.trust_but_check;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class LayeredArchitectureTest {

    private static final String BASE_PKG = "io.github.istvanpercsi.trust_but_check";
    private static final String CONTROLLER_PKG = BASE_PKG + ".input.controller";
    private static final String CONTROLLER_LBL = "Input.Controller";
    private static final String KAFKA_CONSUMER_PGK = BASE_PKG + ".input.kafka_consumer";
    private static final String KAFKA_CONSUMER_LBL = "Input.KafkaConsumer";
    private static final String DATABASE_PKG = BASE_PKG + ".output.database";
    private static final String DATABASE_LBL = "Output.Database";
    private static final String HTTP_CLIENT_PKG = BASE_PKG + ".output.http_client";
    private static final String HTTP_CLINET_LBL = "Output.HTTPClient";
    private static final String SERVICE_PKG = BASE_PKG + ".service";
    private static final String SERVICE_LBL = "Service";

    static JavaClasses javaClasses;

    @BeforeEach
    void setUp() {
        javaClasses = new ClassFileImporter().importPackages(BASE_PKG);
    }

    @Test
    void testLayeredArchitecture() {
        layeredArchitecture().consideringOnlyDependenciesInLayers()
                //definition of layers
                .layer(CONTROLLER_LBL).definedBy(CONTROLLER_PKG + "..")
                .layer(KAFKA_CONSUMER_LBL).definedBy(KAFKA_CONSUMER_PGK + "..")
                .optionalLayer(DATABASE_LBL).definedBy(DATABASE_PKG + "..")
                .optionalLayer(HTTP_CLINET_LBL).definedBy(HTTP_CLIENT_PKG + "..")
                .layer(SERVICE_LBL).definedBy(SERVICE_PKG + "..")
                //definition of access rules for controller layer
                .whereLayer(CONTROLLER_LBL).mayNotBeAccessedByAnyLayer()
                .whereLayer(CONTROLLER_LBL).mayOnlyAccessLayers(SERVICE_LBL)
                //definition of access rules for kafka consumer layer
                .whereLayer(KAFKA_CONSUMER_LBL).mayNotBeAccessedByAnyLayer()
                .whereLayer(KAFKA_CONSUMER_LBL).mayOnlyAccessLayers(SERVICE_LBL)
                //definition of access rules for database layer
                .whereLayer(DATABASE_LBL).mayNotAccessAnyLayer()
                .whereLayer(DATABASE_LBL).mayOnlyBeAccessedByLayers(SERVICE_LBL)
                //definition of access rules for http client layer
                .whereLayer(HTTP_CLINET_LBL).mayNotAccessAnyLayer()
                .whereLayer(HTTP_CLINET_LBL).mayOnlyBeAccessedByLayers(SERVICE_LBL)
                //definition of access rules for service layer
                .whereLayer(SERVICE_LBL).mayOnlyBeAccessedByLayers(CONTROLLER_LBL, KAFKA_CONSUMER_LBL)
                .whereLayer(SERVICE_LBL).mayOnlyAccessLayers(DATABASE_LBL, HTTP_CLINET_LBL)
                .check(javaClasses);
    }
}
