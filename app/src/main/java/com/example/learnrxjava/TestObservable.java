package com.example.learnrxjava;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;

public class TestObservable {

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
    void onObservableRange(){
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
        observable.subscribe(item -> Log.d("tag", "onObservableRange: "+ item));
    }

    void onObservableDefer(){

        Observable observable = Observable.defer(() -> Observable.range(start, count));

        observable.subscribe(item -> Log.d("tag", "onObservableDefer: "+ item));
        count = 10;
        observable.subscribe(item -> Log.d("tag", "onObservableDefer: " + item));
    }

    @SuppressLint("CheckResult")
    void onObservableFromCallable(){
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

    private int getNumber(){
        Log.d("tag", "getNumber: ");
        return 3/0;
    }
}
