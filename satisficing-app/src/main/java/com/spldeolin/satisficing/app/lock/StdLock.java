package com.spldeolin.satisficing.app.lock;

import java.util.concurrent.TimeUnit;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import com.spldeolin.satisficing.app.lock.exception.LockNotAcquiredException;
import lombok.extern.slf4j.Slf4j;

/**
 * 标准分布式锁（StdLock, Standard distributed Lock）
 *
 * @author Deolin 2021-09-27
 */
@Configuration
@Component
@Slf4j
public class StdLock {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 分布式同步执行（dSync, Distributed Synchronized），对 加锁-执行临界区-解锁 范式的同步执行行为的封装
     *
     * <pre>
     * 内部说明：
     * - 首先尝试获取key对应的分布式锁
     * - 如果在waitSeconds内获取不到则抛出LockNotAcquiredException
     * - - 如果请求本方法的线程对象在等待锁期间被调用了interrupt()方法，将会认为没有获取到锁并抛出LockNotAcquiredException
     * - 如果获取到则执行criticalSection，并且无论criticalSection执行多久，锁都会一直持有
     * - criticalSection执行完毕或抛出RuntimeException后，才会解锁
     *
     * 特别注意：
     * - 请确保criticalSection中的代码运行时不会发生「永远结束不了」的情况，一旦发生，将会造成死锁
     * - dSync方法本身支持事务
     * </pre>
     *
     * @param criticalSection 临界区代码
     * @param key 分布式锁的key
     * @param waitMillis 等待锁的最大毫数，小于等于0时代表不等待
     * @throws LockNotAcquiredException 没有获取到锁时抛出，如果不捕获，将会被统一异常处理当作"服务异常，请稍后重试"
     */
    public void dSync(CriticalSection criticalSection, String key, long waitMillis) throws LockNotAcquiredException {
        log.info("StdLock: key={} waitMillis={}", key, waitMillis);
        RLock lock = redissonClient.getLock(key);
        boolean gotcha = false;

        try {
            gotcha = lock.tryLock(waitMillis, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            log.warn("StdLock: interrupt to wait lock, key={}", key);
        }
        if (!gotcha) {
            log.error("StdLock: cannot get lock, key={}", key);
            throw new LockNotAcquiredException();
        }

        try {
            criticalSection.execute();
        } finally {
            lock.unlock();
        }
    }

    public interface CriticalSection {

        void execute();

    }

}