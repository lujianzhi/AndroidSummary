package com.example.lawson.androidsummery.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.rxjava.been.RxBeen;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public class RxJava2Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private TextView textView_2;
    private Button button;
    private Button button2;
    private Button button3;
    private Button button3_2;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button9_1;

    private Observable<RxBeen> observable;
    private Observer<RxBeen> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java2);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        textView = findViewById(R.id.textView);
        button3_2 = findViewById(R.id.button3_2);
        textView_2 = findViewById(R.id.textView_2);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button9_1 = findViewById(R.id.button9_1);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button3_2.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button9_1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                normalStyle();
                break;

            case R.id.button2:
                chainStyle();
                break;

            case R.id.button3:
                aboutThread();
                break;

            case R.id.button3_2:
                aboutThread_2();
                break;

            case R.id.button4:
                map();
                break;

            case R.id.button5:
                flatMap();
                break;

            case R.id.button6:
                zip();
                break;

            case R.id.button7:
                backPressureDIY();
                break;

            case R.id.button8:
                flowable();
                break;

            case R.id.button9:
                backPressure();
                break;

            case R.id.button9_1:
                request();
                break;
        }
    }

    private Subscription subscription;

    /**
     * 请求
     */
    private void request() {
        subscription.request(128);
    }

    /**
     * 背压
     */
    private void backPressure() {
        Flowable.create(new FlowableOnSubscribe<RxBeen>() {
            @Override
            public void subscribe(FlowableEmitter<RxBeen> e) {
//                for (int i = 0; i < 1000; i++) {
                int i = 0;
                while (true) {
                    i++;
                    e.onNext(new RxBeen(i, "backPressure : " + i));
                }
            }
        }, BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<RxBeen>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.i("Ian", "onSubscribe");
                        subscription = s;
                    }

                    @Override
                    public void onNext(RxBeen rxBeen) {
                        Log.i("Ian", "onNext : " + rxBeen.getContent());
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.i("Ian", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("Ian", "onComplete");
                    }
                });
    }

    /**
     * Flowable
     */
    private void flowable() {
        Flowable.create(new FlowableOnSubscribe<RxBeen>() {
            @Override
            public void subscribe(FlowableEmitter<RxBeen> e) {
//                Log.i("Ian", "FlowableOnSubscribe-onNext : 1");
//                e.onNext(new RxBeen(1, "Flowable : 1"));
//                Log.i("Ian", "FlowableOnSubscribe-onNext : 2");
//                e.onNext(new RxBeen(2, "Flowable : 2"));
//                Log.i("Ian", "FlowableOnSubscribe-onNext : 3");
//                e.onNext(new RxBeen(3, "Flowable : 3"));
//
//                Log.i("Ian", "onComplete");
//                e.onComplete();

                int i = 0;
                while (true) {
                    i++;
                    e.onNext(new RxBeen(i, "FlowableOnSubscribe-onNext : " + i));
                }
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<RxBeen>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.i("Ian", "Subscriber-onSubscribe");
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(RxBeen rxBeen) {
                        Log.i("Ian", "Subscriber-onNext : " + rxBeen.getId());
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.i("Ian", "Subscriber-onError");
                        if (t != null) {
                            t.printStackTrace();
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.i("Ian", "Subscriber-onComplete");
                    }
                });
    }

    /**
     * 背压
     * 控制流量
     */
    private void backPressureDIY() {
        Observable.create(new ObservableOnSubscribe<RxBeen>() {
            @Override
            public void subscribe(ObservableEmitter<RxBeen> e) {
                int i = 0;
                while (true) {
                    i++;
                    e.onNext(new RxBeen(i, "背压 : " + i));
                    //方法一：限制速度
//                    Thread.sleep(1000);
                }
            }
        })
                //方法二：限制流量，过滤：但是会造成数据丢失
//                .filter(new Predicate<RxBeen>() {
//                    @Override
//                    public boolean test(RxBeen rxBeen) throws Exception {
//                        return rxBeen.getId() % 100 == 0;
//                    }
//                })
                //方法二：限制流量，取样：也会造成数据丢失
//                .sample(2,TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<RxBeen>() {
                    @Override
                    public void accept(RxBeen rxBeen) {
                        Log.i("Ian", "输出 : " + rxBeen.getId());
                    }
                });
    }

    /**
     * zip方法中报错处理
     */
    static {
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                if (throwable instanceof InterruptedIOException) {
                    Log.d("Ian", "Io interrupted");
                }
            }
        });
    }

    /**
     * zip操作符
     */
    private void zip() {
        Observable a = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                Log.i("Ian", "a-onNext:1");
                e.onNext(1);
                Thread.sleep(1000);
                Log.i("Ian", "a-onNext:2");
                e.onNext(2);
                Thread.sleep(1000);
                Log.i("Ian", "a-onNext:3");
                e.onNext(3);
                Thread.sleep(1000);
                Log.i("Ian", "a-onNext:4");
                e.onNext(4);
                Thread.sleep(1000);
                Log.i("Ian", "a-onNext:5");
                e.onNext(5);
                Thread.sleep(1000);
                Log.i("Ian", "a-onComplete");
                e.onComplete();
            }
        })
                .subscribeOn(Schedulers.io());

        Observable b = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                Log.i("Ian", "b-onNext:A");
                e.onNext("A");
                Thread.sleep(1000);
                Log.i("Ian", "b-onNext:B");
                e.onNext("B");
                Thread.sleep(1000);
                Log.i("Ian", "b-onNext:C");
                e.onNext("C");
                Thread.sleep(1000);
                Log.i("Ian", "b-onComplete");
                e.onComplete();
            }
        })
                .subscribeOn(Schedulers.io());

        Observable.zip(a, b, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer o, String o2) {
                return "合并 : " + o + "-" + o2;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("Ian", "onSubscribe");
            }

            @Override
            public void onNext(String value) {
                Log.i("Ian", "onNext : " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("Ian", "onError");
            }

            @Override
            public void onComplete() {
                Log.i("Ian", "onComplete");
            }
        });
    }

    /**
     * flatMap操作符
     */
    private void flatMap() {
        Observable.create(new ObservableOnSubscribe<RxBeen>() {
            @Override
            public void subscribe(ObservableEmitter<RxBeen> e) {
                e.onNext(new RxBeen(1, "flatMap、concatMap操作符1"));
                e.onNext(new RxBeen(2, "flatMap、concatMap操作符2"));
                e.onNext(new RxBeen(3, "flatMap、concatMap操作符3"));
                e.onComplete();
            }
        })
                //有序
                .concatMap(new Function<RxBeen, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(RxBeen rxBeen) {
                        List<Integer> list = new ArrayList<Integer>();
                        for (int i = 0; i < 3; i++) {
                            list.add(rxBeen.getId());
                        }
                        return Observable.fromIterable(list).delay(200, TimeUnit.MILLISECONDS);
                    }
                })
                //无序
//                .flatMap(new Function<RxBeen, ObservableSource<Integer>>() {
//                    @Override
//                    public ObservableSource<Integer> apply(RxBeen rxBeen) throws Exception {
//                        List<Integer> list = new ArrayList<Integer>();
//                        for (int i = 0; i < 3; i++) {
//                            list.add(rxBeen.getId());
//                        }
//                        return Observable.fromIterable(list).delay(200, TimeUnit.MILLISECONDS);
//                    }
//                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        Log.i("Ian", "id : " + integer);
                    }
                });
    }

    /**
     * map操作符
     */
    private void map() {
        Observable.create(new ObservableOnSubscribe<RxBeen>() {
            @Override
            public void subscribe(ObservableEmitter<RxBeen> e) {
                e.onNext(new RxBeen(1, "map操作符1"));
                e.onNext(new RxBeen(2, "map操作符2"));
                e.onNext(new RxBeen(3, "map操作符3"));
                e.onComplete();
            }
        })
                .map(new Function<RxBeen, String>() {
                    @Override
                    public String apply(RxBeen rxBeen) {
                        return rxBeen.getContent();
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        Log.i("Ian", s);
                    }
                });
    }

    /**
     * 线程控制
     */
    private void aboutThread_2() {

        Observable observable = Observable.create(new ObservableOnSubscribe<RxBeen>() {
            @Override
            public void subscribe(ObservableEmitter<RxBeen> e) {
                Log.i("Ian", "Observable : subscribe - thread : " + Thread.currentThread().getName());
                Log.i("Ian", "Observable : e.onNext 1");
                e.onNext(new RxBeen(1, "线程控制1"));
            }
        });

        Consumer<RxBeen> consumer = new Consumer<RxBeen>() {
            @Override
            public void accept(RxBeen rxBeen) {
                Log.i("Ian", "Consumer : accept1 - thread : " + Thread.currentThread().getName());
                Log.i("Ian", "Consumer : accept2 - rxBeen : " + rxBeen.toString());
            }
        };

        observable.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.newThread())
                .doOnNext(new Consumer<RxBeen>() {
                    @Override
                    public void accept(RxBeen rxBeen) {
                        Log.i("Ian", "Consumer : accept2 - thread : " + Thread.currentThread().getName());
                        Log.i("Ian", "Consumer : accept2 - rxBeen : " + rxBeen.toString());
                    }
                })
                .observeOn(Schedulers.newThread())
                .doOnNext(new Consumer<RxBeen>() {
                    @Override
                    public void accept(RxBeen rxBeen) {
                        Log.i("Ian", "Consumer : accept3 - thread : " + Thread.currentThread().getName());
                        Log.i("Ian", "Consumer : accept2 - rxBeen : " + rxBeen.toString());
                    }
                }).subscribe(consumer);

    }

    /**
     * 线程控制
     */
    private void aboutThread() {
        Observable.create(new ObservableOnSubscribe<RxBeen>() {
            @Override
            public void subscribe(ObservableEmitter<RxBeen> e) {
                Log.i("Ian", "Observable : subscribe - thread : " + Thread.currentThread().getName());
                RxBeen rxBeen = new RxBeen(1, "线程控制");
                Log.i("Ian", "Observable : subscribe - onNext : " + rxBeen.toString());
                e.onNext(rxBeen);
                RxBeen rxBeen2 = new RxBeen(2, "线程控制2");
                Log.i("Ian", "Observable : subscribe - onNext : " + rxBeen.toString());
                e.onNext(rxBeen2);
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .doOnNext(new Consumer<RxBeen>() {
                    @Override
                    public void accept(RxBeen rxBeen) {
                        Log.i("Ian", "第一次doOnNext : " + rxBeen.getId());
                    }
                })
                .doOnNext(new Consumer<RxBeen>() {
                    @Override
                    public void accept(RxBeen rxBeen) {
                        Log.i("Ian", "第二次doOnNext : " + rxBeen.getId());
                    }
                })
                .subscribe(new Consumer<RxBeen>() {
                    @Override
                    public void accept(RxBeen rxBeen) {
                        Log.i("Ian", "Consumer : accept - thread : " + Thread.currentThread().getName());
                        Log.i("Ian", "Consumer : accept - rxBeen : " + rxBeen.toString());
                        textView.setText(rxBeen.toString());
                    }
                });
    }

    /**
     * 链式
     */
    private void chainStyle() {

        Observable.create(new ObservableOnSubscribe<RxBeen>() {
            @Override
            public void subscribe(ObservableEmitter<RxBeen> e) {
                Log.i("Ian", "Observable - onNext : 1");
                e.onNext(new RxBeen(1, "我是链式1"));
                Log.i("Ian", "Observable - onNext : 2");
                e.onNext(new RxBeen(2, "我是链式2"));
                Log.i("Ian", "Observable - onNext : 3");
                e.onNext(new RxBeen(3, "我是链式3"));
                Log.i("Ian", "Observable - onNext : 4");
                e.onNext(new RxBeen(4, "我是链式4"));
//                e.onError(new MyError("调用onError"));
                Log.i("Ian", "Observable - onNext : 5");
                e.onNext(new RxBeen(5, "我是链式5"));
                Log.i("Ian", "Observable - onNext : onComplete");
                e.onComplete();
                Log.i("Ian", "Observable - onNext : 6");
                e.onNext(new RxBeen(6, "我是链式6"));
            }
        })

                //subscribe的不同重载方法
//        .subscribe(new Consumer<RxBeen>() {
//            @Override
//            public void accept(RxBeen rxBeen) throws Exception {
//                Log.i("Ian", "Consumer - accept : RxBeen->" + rxBeen.toString());
//            }
//        })

                //subscribe的不同重载方法
                .subscribe(new Observer<RxBeen>() {

                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("Ian", "Observer - onSubscribe : Disposable->" + d.toString());
                        disposable = d;
                    }

                    @Override
                    public void onNext(RxBeen value) {
                        Log.i("Ian", "Observer - onNext : RxBeen->" + value.toString());
                        if (value.getId() == 2) {
                            disposable.dispose();
                            Log.i("Ian", "Observer - onNext : disposable->" + disposable.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Ian", "Observer - onError : Throwable->" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("Ian", "Observer - onComplete");
                    }
                })
        ;
    }

    /**
     * 非链式
     */
    private void normalStyle() {
        initObservable();
        initObserver();
        //建立连接
        observable.subscribe(observer);
    }

    /**
     * 创建被观察者
     */
    private void initObservable() {
        observable = Observable.create(new ObservableOnSubscribe<RxBeen>() {
            @Override
            public void subscribe(ObservableEmitter<RxBeen> e) {
                e.onNext(new RxBeen(1, "我是非链式1"));
                e.onNext(new RxBeen(2, "我是非链式2"));
                e.onNext(new RxBeen(3, "我是非链式3"));
                e.onNext(new RxBeen(4, "我是非链式4"));
//                e.onError(new MyError("调用onError"));
                e.onNext(new RxBeen(5, "我是非链式5"));
                e.onComplete();
                e.onNext(new RxBeen(6, "我是非链式6"));
            }
        });
    }

    /**
     * 创建观察者
     */
    private void initObserver() {
        observer = new Observer<RxBeen>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("Ian", "onSubscribe : Disposable->" + d.toString());
            }

            @Override
            public void onNext(RxBeen value) {
                Log.i("Ian", "onNext : RxBeen->" + value.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.i("Ian", "onError : Throwable->" + e.toString());
            }

            @Override
            public void onComplete() {
                Log.i("Ian", "onComplete");
            }
        };
    }

    private class MyError extends Throwable {
        public MyError(String message) {
            super(message);
        }
    }
}
