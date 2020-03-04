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
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TestObservable testObservable = new TestObservable();
        // testObservable.createObservableWithJust();
        // testObservable.createObservableFromIterable();
        // testObservable.createObservableUsingCreate();
        //testObservable.createConnectableObservable();
        //testObservable.createColdObservable();
        //testObservable.onObservableError();
        //testObservable.onObservableErrorUsingCallable();
        //testObservable.createObservableUsingEmpty();
        //testObservable.createObservableUsingNever();
       // testObservable.onObservableRange();
        //testObservable.onObservableDefer();
       // testObservable.onObservableFromCallable();




      //  onObserver();
    }

    void onObserver() {

        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("tag", "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("tag", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("tag", "onComplete");
            }
        };

        observable.subscribe(observer);
    }
}
