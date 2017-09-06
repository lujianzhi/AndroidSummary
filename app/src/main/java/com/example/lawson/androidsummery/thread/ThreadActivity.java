package com.example.lawson.androidsummery.thread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.thread.diy.MyRunnable;
import com.example.lawson.androidsummery.thread.diy.MyThread;
import com.example.lawson.androidsummery.thread.diy.MyThreadPoolExecutor;
import com.example.lawson.androidsummery.thread.diy.PriorityRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadActivity extends AppCompatActivity implements View.OnClickListener {

    private Button fixedThreadPool;
    private Button singleThreadPool;
    private Button cachedThreadPool;
    private Button scheduledThreadPool;
    private Button singleThreadScheduledPool;
    private Button diy;
    private Button diy_thread_pool_executor;
    private Button thread;
    private Button runnable;
    private Button join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        fixedThreadPool = (Button) findViewById(R.id.fixedThreadPool);
        fixedThreadPool.setOnClickListener(this);
        singleThreadPool = (Button) findViewById(R.id.singleThreadPool);
        singleThreadPool.setOnClickListener(this);
        cachedThreadPool = (Button) findViewById(R.id.cachedThreadPool);
        cachedThreadPool.setOnClickListener(this);
        scheduledThreadPool = (Button) findViewById(R.id.scheduledThreadPool);
        scheduledThreadPool.setOnClickListener(this);
        singleThreadScheduledPool = (Button) findViewById(R.id.singleThreadScheduledPool);
        singleThreadScheduledPool.setOnClickListener(this);
        diy = (Button) findViewById(R.id.diy);
        diy.setOnClickListener(this);
        diy_thread_pool_executor = (Button) findViewById(R.id.diy_thread_pool_executor);
        diy_thread_pool_executor.setOnClickListener(this);
        thread = (Button) findViewById(R.id.thread);
        thread.setOnClickListener(this);
        runnable = (Button) findViewById(R.id.runnable);
        runnable.setOnClickListener(this);
        join = (Button) findViewById(R.id.join);
        join.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fixedThreadPool:
                fixedThreadPool();
                break;
            case R.id.singleThreadPool:
                singleThreadPool();
                break;
            case R.id.cachedThreadPool:
                cachedThreadPool();
                break;
            case R.id.scheduledThreadPool:
                scheduledThreadPool();
                break;
            case R.id.singleThreadScheduledPool:
                singleThreadScheduledPool();
                break;
            case R.id.diy:
                diy();
                break;
            case R.id.diy_thread_pool_executor:
                diyThreadPoolExecutor();
                break;
            case R.id.thread:
                thread();
                break;
            case R.id.runnable:
                runnable();
                break;
            case R.id.join:
                join();
                break;

            default:
                break;
        }
    }

    private void join() {
        Log.i("Ian", "主线程开始运行");

        Thread myRunnable1 = new Thread(new MyRunnable("A"));
        myRunnable1.start();
        try {
            myRunnable1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i("Ian", "主线程接着运行");
    }

    private void runnable() {
        Thread myRunnable1 = new Thread(new MyRunnable("A"));
        Thread myRunnable2 = new Thread(new MyRunnable("B"));
        myRunnable1.start();
        myRunnable2.start();
    }

    private void thread() {
        MyThread myThread1 = new MyThread("A");
//        MyThread myThread2 = new MyThread("B");
        myThread1.start();
//        myThread2.start();
    }

    private void fixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    Log.i("Ian", "线程:" + threadName + "；正在执行第" + index + "个任务");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void singleThreadPool() {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 10; i++) {
            final int index = i;
            singleThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    Log.i("Ian", "线程：" + threadName + ",正在执行第" + index + "个任务");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void cachedThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 1; i <= 10; i++) {
            final int index = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    Log.i("Ian", "线程：" + threadName + ",正在执行第" + index + "个任务");
                    try {
                        long time = index * 500;
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void scheduledThreadPool() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        //延迟2秒后执行该任务
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                Log.i("Ian", "线程：" + threadName + ",正在执行 : 延迟2秒后执行该任务");
            }
        }, 2, TimeUnit.SECONDS);
        //延迟1秒后，每隔2秒执行一次该任务
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                Log.i("Ian", "线程：" + threadName + ",正在执行 : 延迟1秒后，每隔2秒执行一次该任务");
            }
        }, 1, 2, TimeUnit.SECONDS);
    }

    private void singleThreadScheduledPool() {
        ScheduledExecutorService singleThreadScheduledPool = Executors.newSingleThreadScheduledExecutor();
        //延迟1秒后，每隔2秒执行一次该任务
        singleThreadScheduledPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                Log.i("Ian", "线程：" + threadName + ",正在执行 : 延迟1秒后，每隔2秒执行一次该任务");
            }
        }, 1, 2, TimeUnit.SECONDS);
    }

    private void diy() {
        ExecutorService priorityThreadPool = new ThreadPoolExecutor(3, 3, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<Runnable>());
        for (int i = 1; i <= 10; i++) {
            final int priority = i;
            priorityThreadPool.execute(new PriorityRunnable(priority) {
                @Override
                public void doBusiness() {
                    String threadName = Thread.currentThread().getName();
                    Log.i("Ian", "线程：" + threadName + ",正在执行" + priority);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void diyThreadPoolExecutor() {
        final ExecutorService diyThreadPoolExecutor = new MyThreadPoolExecutor(3, 3, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        diyThreadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                Log.i("Ian", "线程开始执行");
                diyThreadPoolExecutor.shutdown();
            }
        });
    }
}
