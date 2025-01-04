package com.jackdking.root.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UUID生成器的单元测试类
 */
public class IDGenByUUIDTest {

    private IDGenByUUID idGen;

    /**
     * 在每个测试方法执行前初始化IDGenByUUID对象
     */
    @BeforeEach
    public void setUp() {
        idGen = new IDGenByUUID();
    }

    /**
     * 测试生成的UUID是否符合UUID的标准格式
     */
    @Test
    public void testGenerateUUID格式正确() {
        String uuid = idGen.generateUUID();
        // UUID标准格式：8-4-4-4-12
        assertTrue(uuid.matches("[0-9a-fA-F]{8}[0-9a-fA-F]{4}[0-9a-fA-F]{4}[0-9a-fA-F]{4}[0-9a-fA-F]{12}"));
    }

    /**
     * 测试多次生成的UUID是否唯一
     */
    @Test
    public void testGenerateUUID唯一性() {
        String uuid1 = idGen.generateUUID();
        String uuid2 = idGen.generateUUID();
        assertNotEquals(uuid1, uuid2, "生成的两个UUID应该不同");
    }

    /**
     * 测试生成的UUID长度是否为36字符（包括连字符）
     */
    @Test
    public void testGenerateUUID长度正确() {
        String uuid = idGen.generateUUID();
        assertEquals(32, uuid.length(), "UUID的总长度应该为36字符");
    }

    /**
     * 测试连续生成多个UUID，验证它们的格式和唯一性
     */
    @Test
    public void test批量生成UUID() {
        int numberOfUUIDs = 100;
        for (int i = 0; i < numberOfUUIDs; i++) {
            String uuid = idGen.generateUUID();
            assertTrue(uuid.matches("[0-9a-fA-F]{8}[0-9a-fA-F]{4}[0-9a-fA-F]{4}[0-9a-fA-F]{4}[0-9a-fA-F]{12}"));
        }

        // 验证唯一性
        String[] uuids = new String[numberOfUUIDs];
        for (int i = 0; i < numberOfUUIDs; i++) {
            uuids[i] = idGen.generateUUID();
        }

        for (int i = 0; i < numberOfUUIDs; i++) {
            for (int j = i + 1; j < numberOfUUIDs; j++) {
                assertNotEquals(uuids[i], uuids[j], "生成的UUID应该唯一");
            }
        }
    }
}

