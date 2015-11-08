package com.v5ent.rapid4j.test.library.jedis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
	//初始化
	JedisPoolTest test = new JedisPoolTest();
    public static void main(String[] args) {
         int count = 1000;
         CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
         ExecutorService executorService = Executors.newFixedThreadPool(count);
         for (int i = 0; i < count; i++)
              executorService.execute(new CyclicBarrierTest().new Task(cyclicBarrier));

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
         private CyclicBarrier cyclicBarrier;

         public Task(CyclicBarrier cyclicBarrier) {
              this.cyclicBarrier = cyclicBarrier;
         }

         @Override
         public void run() {
              try {
                   // 等待所有任务准备就绪
                   cyclicBarrier.await();
                   // 测试内容
                // 待测试的url
					String host = "http://172.25.2.14/seqno?";
					String para = "sysTemNo=ERP&seqName=WH-ZONE-ID&iVar=00";
					System.out.println(host + para);
					URL url = new URL(host);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					// connection.setRequestMethod("POST");
					// connection.setRequestProperty("Proxy-Connection", "Keep-Alive");
					connection.setDoOutput(true);
					connection.setDoInput(true);
					PrintWriter out = new PrintWriter(connection.getOutputStream());
					out.print(para);
					out.flush();
					out.close();
					BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					String line = "";
					String result = "";
					while ((line = in.readLine()) != null) {
						result += line;
					}
					System.out.println(result);
//                   System.out.println(test.getJedis().incr("seq"));
//             	  System.out.println(test.getShardedJedis().incr("seq"));
              } catch (Exception e) {
                   e.printStackTrace();
              }
         }
    }
}
