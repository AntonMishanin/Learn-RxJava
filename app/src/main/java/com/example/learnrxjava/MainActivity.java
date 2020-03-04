package com.example.learnrxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createObservableWithJust();
        createObservableFromIterable();
        createObservableUsingCreate();
    }

    @SuppressLint("CheckResult")
    private void createObservableWithJust() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4);
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("tag", "createObservableWithJust: " + integer);
            }
        });
    }

    @SuppressLint("CheckResult")
    private void createObservableFromIterable() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Observable<Integer> observable = Observable.fromIterable(list);
        observable.subscribe(integer -> Log.d("tag", "createObservableFromIterable: " + integer));
    }

    @SuppressLint("CheckResult")
    private void createObservableUsingCreate() {
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
}
