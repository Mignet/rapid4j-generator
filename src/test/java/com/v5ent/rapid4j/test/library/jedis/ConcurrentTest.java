package com.v5ent.rapid4j.test.library.jedis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentTest {
	//初始化
	JedisPoolTest test = new JedisPoolTest();
	
    public static void main(String[] args) {
         int count = 1000;
         ExecutorService executorService = Executors.newFixedThreadPool(count);
         for (int i = 0; i < count; i++)
              executorService.execute(new ConcurrentTest().new Task());

         executorService.shutdown();
         while (!executorService.isTerminated()) {
              try {
                   Thread.sleep(10);
              } catch (InterruptedException e) {
                   e.printStackTrace();
              }
         }
    }

    public class Task implements Runnable {

         @Override
         public void run() {
              try {
                   // 测试内容
            	  System.out.println(test.getJedis().incr("seq"));
//            	  System.out.println(test.getShardedJedis().incr("seq"));
              } catch (Exception e) {
                   e.printStackTrace();
              }
         }
    }
}
