package ind.gopinnath.junitdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * https://junit.org/junit5/docs/current/api/org.junit.jupiter.api/org/junit/jupiter/api/extension/package-summary.html
 * https://github.com/mockito/mockito/blob/main/subprojects/junit-jupiter/src/main/java/org/mockito/junit/jupiter/MockitoExtension.java
 */

@Slf4j
public class LoggingExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        log.info(" beforeEach {} {} ", extensionContext.getRequiredTestMethod(), extensionContext.getRequiredTestClass());
    }
}
