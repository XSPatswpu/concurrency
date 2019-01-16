package atomic;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Auther: cherry
 * @Date: 2019-01-16 15:56
 */
public class AtomicIntegerFieldUpdaterTest {
    @Test
    public void testValue1(){
        IntegerData data = new IntegerData();
        Assert.assertEquals(1,newUpdater(IntegerData.class,"value1").getAndSet(data,10));
        Assert.assertEquals(10,newUpdater(IntegerData.class,"value1").get(data));
    }

    @Test
    public void testValue2(){
        IntegerData data = new IntegerData();
        Assert.assertEquals(3,newUpdater(IntegerData.class,"value2").incrementAndGet(data));
    }

    @Test
    public void testValue3(){
        IntegerData data = new IntegerData();
        Assert.assertEquals(2,newUpdater(IntegerData.class,"value3").decrementAndGet(data));
    }

    @Test
    public void testValue4(){
        IntegerData data = new IntegerData();
        Assert.assertTrue(newUpdater(IntegerData.class,"value4").compareAndSet(data,4,10));
    }

    private AtomicIntegerFieldUpdater newUpdater(Class type, String fieldName){
        return AtomicIntegerFieldUpdater.newUpdater(type,fieldName);
    }
}
