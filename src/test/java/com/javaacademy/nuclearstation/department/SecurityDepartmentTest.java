package com.javaacademy.nuclearstation.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class SecurityDepartmentTest {
    @Autowired
    private SecurityDepartment securityDepartment;

    @Test
    public void testAddAccident() {
        Assertions.assertDoesNotThrow(() -> securityDepartment.addAccident());
    }

    @Test
    public void testReset() {
        Assertions.assertDoesNotThrow(() -> securityDepartment.reset());
    }
}
