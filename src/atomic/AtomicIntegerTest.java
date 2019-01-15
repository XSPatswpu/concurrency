package atomic;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    /**
     * int addAndGet(int delta)
     *           以原子方式将给定值与当前值相加。 实际上就是等于线程安全版本的i =i+delta操作。
     *
     * boolean compareAndSet(int expect, int update)
     *           如果当前值(方法调用者的值) == 预期值，则以原子方式将该值设置为：更新值。
     *           如果成功就返回true，否则返回false，并且不修改原值。
     *
     * int decrementAndGet()
     *           以原子方式将当前值减 1。 相当于线程安全版本的--i操作。
     *
     * int get()
     *           获取当前值。
     *
     * int getAndAdd(int delta)
     *           以原子方式将给定值与当前值相加。 相当于线程安全版本的t=i;i+=delta;return t;操作。
     *
     * int getAndDecrement()
     *           以原子方式将当前值减 1。 相当于线程安全版本的i--操作。
     *
     * int getAndIncrement()
     *           以原子方式将当前值加 1。 相当于线程安全版本的i++操作。
     *
     * int getAndSet(int newValue)
     *           以原子方式设置为给定值，并返回旧值。 相当于线程安全版本的t=i;i=newValue;return t;操作。
     *
     * int incrementAndGet()
     *           以原子方式将当前值加 1。 相当于线程安全版本的++i操作。
     *
     * void lazySet(int newValue)
     *
     * void set(int newValue)
     *           设置为给定值。 直接修改原始值，也就是i=newValue操作。
     *
     * boolean weakCompareAndSet(int expect, int update)
     */
    private final AtomicInteger value = new AtomicInteger(10);

    @Test
    public void testAddAndGet(){
        Assert.assertEquals(value.getAndAdd(4),10);
        Assert.assertEquals(value.addAndGet(4),18);
    }

    @Test
    public void testCompareAndSet(){
        Assert.assertTrue(value.compareAndSet(10,5));
        Assert.assertEquals(5,value.get());
    }

    @Test
    public void testDecrementAndGet(){
        Assert.assertEquals(9,value.decrementAndGet());
    }

    @Test
    public void testGetAndSet(){
        Assert.assertEquals(10,value.getAndSet(12));
        Assert.assertEquals(12,value.get());
    }

    /**
     * 测试原子性
     */
    @Test
    public void testConcurrent(){
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(value::incrementAndGet);
        }

        for (Thread thread : threads) {
            thread.start();
            try {
                //保证所有子线程运行完毕
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertEquals(20,value.get());
    }


}
