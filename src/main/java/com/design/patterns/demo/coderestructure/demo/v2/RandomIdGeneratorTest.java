package com.design.patterns.demo.coderestructure.demo.v2;

import org.junit.Assert;
import org.junit.Test;

/**
 * RandomIdGeneratorTest类 重构三：编写UT
 *
 * @author wangjixue
 * @date 5/3/22 7:44 PM
 */
public class RandomIdGeneratorTest {

    @Test
    public void testGetLastSubstrSplittedByDot(){
        RandomIdGenerator generator = new RandomIdGenerator();
        String actualSubstr = generator.getLastSubstrSplittedByDot("field1.field2.field3");
        Assert.assertEquals("field3",actualSubstr);

        actualSubstr = generator.getLastSubstrSplittedByDot("field1");
        Assert.assertEquals("field1",actualSubstr);

        actualSubstr = generator.getLastSubstrSplittedByDot("field1#field2#field3");
        Assert.assertEquals("field1#field2#field3",actualSubstr);
    }

    @Test
    public void testGetLastSubstrSplittedByDot_nullOrEmpty(){
        RandomIdGenerator generator = new RandomIdGenerator();
        String actualSubstr = generator.getLastSubstrSplittedByDot("");
        Assert.assertEquals("",actualSubstr);

        actualSubstr = generator.getLastSubstrSplittedByDot(null);
        Assert.assertNull(actualSubstr);
    }

    @Test
    public void testGenerateRandomAlphameric(){
        RandomIdGenerator generator = new RandomIdGenerator();
        String randomString = generator.generateRandomAlphameric(8);
        Assert.assertNotNull(randomString);
        Assert.assertEquals(8,randomString.length());
        for (char s : randomString.toCharArray()) {
            Assert.assertTrue((s >= '0' && s < '9') || (s >= 'a' && s < 'z') || (s >= 'A' && s < 'Z'));
        }
    }

    @Test
    public void testGenerateRandomAlphameric_lengthEqualsOrLessThanZero(){
        RandomIdGenerator generator = new RandomIdGenerator();
        String randomString = generator.generateRandomAlphameric(0);
        Assert.assertEquals("",randomString);

        randomString = generator.generateRandomAlphameric(-1);
        Assert.assertNull(randomString);
    }

    /**
     * generate() 函数的功能定义为：“生成唯一 ID，格式为：{主机名 substr}-{时间戳}-{8 位随机数}
     */
    @Test
    public void testGenerate(){
        RandomIdGenerator generator = new RandomIdGenerator();
        String id1 = generator.generate();
        Assert.assertNotNull(id1);
        Assert.assertTrue(id1.length() > 0);
        for (char c: id1.toCharArray()) {
            Assert.assertTrue((c == '-')||(c >= '0' && c < '9') || (c >= 'a' && c < 'z') || (c >= 'A' && c < 'Z'));

        }
        String id2 = generator.generate();
        Assert.assertNotNull(id1);
        Assert.assertTrue(id1.length() > 0);
        for (char c: id2.toCharArray()) {
            Assert.assertTrue((c == '-')||(c >= '0' && c < '9') || (c >= 'a' && c < 'z') || (c >= 'A' && c < 'Z'));

        }

        Assert.assertNotEquals(id1,id2);
    }

    /**
     * generate() 函数在主机名获取失败时，返回：null-{时间戳}-{8 位随机数}”,测试生成的 ID 是否完全符合格式要求。
     */
    @Test
    public void testGenerate_withoutSubHostName(){
        RandomIdGenerator generator = new RandomIdGenerator();
        generator.getLastSubstrSplittedByDot(null);

        String id = generator.generate();
        Assert.assertNotNull(id);
        Assert.assertTrue(id.length() > 0);
        String[] split = id.split("-");
        Assert.assertTrue(split[0] == null);
        Assert.assertTrue(System.currentTimeMillis() == Long.valueOf(split[1]));
        for (char c: split[2].toCharArray()) {
            Assert.assertTrue((c >= '0' && c < '9') || (c >= 'a' && c < 'z') || (c >= 'A' && c < 'Z'));

        }
    }
}
