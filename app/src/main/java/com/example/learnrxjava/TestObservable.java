package com.example.learnrxjava;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;

public class TestObservable {

    public static final String TAG = "tag";

    int start = 3;
    int count = 4;

    @SuppressLint("CheckResult")
    void createObservableWithJust() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4);
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("tag", "createObservableWithJust: " + integer);
            }
        });
    }

    @SuppressLint("CheckResult")
    void createObservableFromIterable() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Observable<Integer> observable = Observable.fromIterable(list);
        observable.subscribe(integer -> Log.d("tag", "createObservableFromIterable: " + integer));
    }

    @SuppressLint("CheckResult")
    void createObservableUsingCreate() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
                // emitter.onNext(null);
                emitter.onComplete();
            }
        });

        observable.subscribe(
                integer -> Log.d("tag", "createObservableUsingCreate: " + integer),
                error -> Log.d("tag", error.getLocalizedMessage()),
                () -> Log.d("tag", "createObservableUsingCreate: Complete")
        );

    }

    @SuppressLint("CheckResult")
    void createColdObservable() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4);

        observable.subscribe(integer -> Log.d("tag", "Observable 1 " + integer));

        observable.subscribe(integer -> Log.d("tag", "Observable 2 " + integer));
    }

    @SuppressLint("CheckResult")
    void createConnectableObservable() {
        ConnectableObservable<Integer> observable = Observable.just(1, 2, 3).publish();

        observable.subscribe(integer -> Log.d("tag", "createConnectableObservable: " + integer));
        observable.connect();
    }

    @SuppressLint("CheckResult")
    void onObservableError() {
        Observable observable = Observable.error(new Exception("Exception"));

        observable.subscribe(System.out::println, error -> System.out.println("onObservableError: " + error.hashCode()));
        observable.subscribe(System.out::println, error -> System.out.println("onObservableError: " + error.hashCode()));

    }

    @SuppressLint("CheckResult")
    void onObservableErrorUsingCallable() {
        Observable observable = Observable.error(() -> {
            System.out.println("Exception created");
            return new Exception("Exception");
        });

        observable.subscribe(System.out::println, error -> System.out.println("onObservableErrorUsingCallable: " + error.hashCode()));
        observable.subscribe(System.out::println, error -> System.out.println("onObservableErrorUsingCallable: " + error.hashCode()));

    }

    @SuppressLint("CheckResult")
    void createObservableUsingEmpty() {
        Observable observable = Observable.empty();
        observable.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("tag", "accept: " + o);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d("tag", "Throwable: " + throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.d("tag", "createObservableUsingEmpty Completed: ");
            }
        });
    }

    @SuppressLint("CheckResult")
    void createObservableUsingNever() {
        Observable observable = Observable.never();

        observable.subscribe(
                new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.d("tag", "accept: " + o);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("tag", "Throwable: " + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d("tag", "createObservableUsingNever Completed: ");
                    }
                }
        );
    }

    @SuppressLint("CheckResult")
    void onObservableRange() {
        int start = 3;
        int count = 4;
        Observable observable = Observable.range(start, count);
        observable.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("tag", "accept: " + o);
            }
        });
        count = 4;
        observable.subscribe(item -> Log.d("tag", "onObservableRange: " + item));
    }

    void onObservableDefer() {

        Observable observable = Observable.defer(() -> Observable.range(start, count));

        observable.subscribe(item -> Log.d("tag", "onObservableDefer: " + item));
        count = 10;
        observable.subscribe(item -> Log.d("tag", "onObservableDefer: " + item));
    }

    @SuppressLint("CheckResult")
    void onObservableFromCallable() {
        Observable observable = Observable.fromCallable(() -> {
            Log.d("tag", "onObservableFromCallable: ");
            return getNumber();
        });

        observable.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {

                Log.d("tag", "accept: " + o);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d("tag", "Throwable : " + throwable.getMessage());
            }
        });
    }

    private int getNumber() {
        Log.d("tag", "getNumber: ");
        return 3 / 0;
    }

    @SuppressLint("CheckResult")
    void onObservableInterval() {
        //Observable observable = Observable.interval(1, TimeUnit.SECONDS);
        //observable.subscribe(item -> Log.d("tag", "onObservableInterval: " + item));

        Observable observable = Observable.interval(1, TimeUnit.SECONDS);
        observable.subscribe(item -> System.out.println("Observable 1: " + item));
        pause(2000);
        observable.subscribe(item -> System.out.println("Observable 2: " + item));
        pause(4000);
    }

    void pause(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void createSingle() {
        Single.just("Test String").subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onSuccess(String s) {
                Log.d(TAG, "onSuccess: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }
        });
    }

    void createMaybe() {
        Maybe.empty().subscribe(new MaybeObserver<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onSuccess(Object o) {
                Log.d(TAG, "onSuccess: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });
    }

    @SuppressLint("CheckResult")
    void createCompletable() {
        Completable.fromSingle(Single.just("Test")).subscribe(() -> Log.d(TAG, "createCompletable: "));

        Completable.fromSingle(Single.just("Test")).subscribe(() -> {
            Log.d(TAG, "createCompletable: ");
            new CompletableObserver() {
                @Override
                public void onSubscribe(Disposable d) {
                    Log.d(TAG, "onSubscribe: ");
                }

                @Override
                public void onComplete() {
                    Log.d(TAG, "onComplete: ");
                }

                @Override
                public void onError(Throwable e) {
                    Log.d(TAG, "onError: ");
                }
            };
        });

        Completable.fromSingle(Single.just("Test")).subscribe(
                new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }
                });
    }

    void handleDisposable() {
        Observable observable = Observable.interval(1, TimeUnit.SECONDS);
        Disposable disposable = observable.subscribe(o -> Log.d(TAG, "accept: " + o));
        pause(4000);
        disposable.dispose();
    }

    void handleDisposableInObserver() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        Observer<Integer> observer = new Observer<Integer>() {
            Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                if(integer == 4){
                    disposable.dispose();
                }
                Log.d(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

    void onCompositeDisposable(){
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Observable observable = Observable.interval(1, TimeUnit.SECONDS);
        Disposable disposable1 = observable.subscribe(m -> Log.d(TAG, "onCompositeDisposable: 1 - " + m));
        Disposable disposable2 = observable.subscribe(m -> Log.d(TAG, "onCompositeDisposable: 2 - " + m));
        compositeDisposable.addAll(disposable1, disposable2);
        pause(2000);
        compositeDisposable.delete(disposable1);
        pause(3000);
        compositeDisposable.dispose();
        pause(1000);
    }


}
