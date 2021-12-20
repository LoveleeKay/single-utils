package utils;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 线程池，多线程
 *
 * @author liyongsheng
 * @date 2021/12/17 09:57
 **/
public class ThreadPoolUtils {
    private static ThreadPoolExecutor executor  = new ThreadPoolExecutor(10, 20, 3L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(20));
    public static void main(String[] args) {
//        CountDownLatch countDownLatch = new CountDownLatch(5);
//        List<Integer> res = new ArrayList<>(5);
//        long beginTime = System.currentTimeMillis();
//        AtomicReference<String> s = new AtomicReference<>();
//        for (int i = 0; i < 50; i++) {
//            executor.execute(() -> {
//                System.out.println(Thread.currentThread().getName() + " start");
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                int tmp = new Random().nextInt(10);
//                System.out.println("tmp=" + tmp);
//                s.set("3");
//                res.add(tmp);
//                countDownLatch.countDown();
//            });
//        }
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
////        线程池不关闭，jvm不会退出
//        executor.shutdown();
//        System.out.println("res=" + res);
//        System.out.println("s = " + s.get());
//        System.out.println("Spend time" + (System.currentTimeMillis() - beginTime) + "ms");
        testPool();
    }

    private static void testPool() {
//        for (int i = 0; i < 40; i++) {
//            executor.execute(() -> {
//                System.out.println(Thread.currentThread().getName() + " start");
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("current tim =" + Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime());
//                System.out.println(Thread.currentThread().getName() + " end");
//            });
//        }
//        try {
//            Thread.sleep(16000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        System.out.println("start second");
        for (int i = 0; i < 35; i++) {
            int tmp = i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " start " + tmp);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("current tim =" + Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime());
                System.out.println(Thread.currentThread().getName() + " end");
            });
        }

        while (true) {
            System.out.println("队列大小：" + executor.getQueue().size());
            System.out.println("总线程数："+executor.getPoolSize()+"当前活跃线程数："+executor.getActiveCount()
                    +"当前核心线程数："+executor.getCorePoolSize());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
