package com.example.learnrxjava;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class TestOperator {

    public static final String TAG = "tag";

    @SuppressLint("CheckResult")
    void mapOperator() {

        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        observable
                //.map(item -> item*3)
                .map(item -> item + " - Hello")
                .subscribe(item -> Log.d(TAG, "mapOperator: " + item));
    }

    @SuppressLint("CheckResult")
    void filterOperator() {

        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6);
        observable
                .filter(item -> item % 3 == 0)
                .subscribe(item -> Log.d(TAG, "filterOperator: " + item));
    }

    @SuppressLint("CheckResult")
    void combineMapAndFilter() {

        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6);
        observable
                .filter(item -> item % 3 == 0)
                .map(item -> item * 2)
                .subscribe(item -> Log.d(TAG, "combineMapAndFilter: " + item));

    }

    @SuppressLint("CheckResult")
    void takeOperator() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6);
        observable
                .take(3)
                .subscribe(item -> Log.d(TAG, "combineMapAndFilter: " + item));

        Observable.interval(1, TimeUnit.SECONDS)
                .take(5, TimeUnit.SECONDS)
                .subscribe(item -> Log.d(TAG, "takeOperator: " + item));
    }

    @SuppressLint("CheckResult")
    void takeWhile() {
        Observable.just(1, 4, 4, 5, 7, 8, 2)
                .takeWhile(item -> item < 3)
                .subscribe(item -> Log.d(TAG, "takeOperator: " + item));
    }

    @SuppressLint("CheckResult")
    void skipOperator() {
        Observable.just(1, 4, 4, 5, 7, 8, 2)
                .skip(2)
                .subscribe(item -> Log.d(TAG, "skipOperator: " + item));

        Observable.just(1, 4, 4, 5, 7, 8, 2)
                .skipWhile(item -> item <=5)
                .subscribe(item -> Log.d(TAG, "skipWhileOperator: " + item));
    }

    @SuppressLint("CheckResult")
    void distinctOperator(){
        Observable.just(1, 4, 4, 5, 7, 8, 2)
                .distinct()
                .subscribe(item -> Log.d(TAG, " distinctOperator: " + item));

        Observable.just("qq", "wer", "1", "f", "efsd", "sa")
                .distinct(item -> item.length())
                .subscribe(item -> Log.d(TAG, " distinctKeyOperator: " + item));
    }

    @SuppressLint("CheckResult")
    void distinctUntilChangedOperator(){
        Observable.just(1, 1, 2, 3, 3, 4, 5, 5, 6, 2)
                .distinctUntilChanged()
                .subscribe(item -> Log.d(TAG, " distinctUntilChangedOperator: " + item));

        Observable.just("qq", "er", "1", "f", "efsd", "sa")
                .distinctUntilChanged(item ->item.length())
                .subscribe(item -> Log.d(TAG, " distinctUntilChangedKeyOperator: " + item));
    }


}
