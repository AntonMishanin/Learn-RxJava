package com.example.learnrxjava;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.Comparator;
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
                .skipWhile(item -> item <= 5)
                .subscribe(item -> Log.d(TAG, "skipWhileOperator: " + item));
    }

    @SuppressLint("CheckResult")
    void distinctOperator() {
        Observable.just(1, 4, 4, 5, 7, 8, 2)
                .distinct()
                .subscribe(item -> Log.d(TAG, " distinctOperator: " + item));

        Observable.just("qq", "wer", "1", "f", "efsd", "sa")
                .distinct(item -> item.length())
                .subscribe(item -> Log.d(TAG, " distinctKeyOperator: " + item));
    }

    @SuppressLint("CheckResult")
    void distinctUntilChangedOperator() {
        Observable.just(1, 1, 2, 3, 3, 4, 5, 5, 6, 2)
                .distinctUntilChanged()
                .subscribe(item -> Log.d(TAG, " distinctUntilChangedOperator: " + item));

        Observable.just("qq", "er", "1", "f", "efsd", "sa")
                .distinctUntilChanged(item -> item.length())
                .subscribe(item -> Log.d(TAG, " distinctUntilChangedKeyOperator: " + item));
    }

    @SuppressLint("CheckResult")
    void defaultIsEmptyOperator() {
        Observable.just(1, 1, 2, 3, 3, 4, 5, 5, 6, 2)
                .filter(item -> item > 10)
                .defaultIfEmpty(12)
                .subscribe(item -> Log.d(TAG, "defaultIsEmptyOperator: " + item));
    }

    @SuppressLint("CheckResult")
    void switchIsEmptyOperator() {
        Observable.just(1, 1, 2, 3, 3, 4, 5, 5, 6, 2)
                .filter(item -> item > 10)
                .switchIfEmpty(Observable.just(11, 13))
                .subscribe(item -> Log.d(TAG, "defaultIsEmptyOperator: " + item));
    }

    @SuppressLint("CheckResult")
    void repeatOperator() {
        Observable.just(1, 2, 3)
                .repeat(3)
                .subscribe(item -> Log.d(TAG, "repeatOperator: " + item));
    }

    @SuppressLint("CheckResult")
    void scanOperator() {
        Observable.just(1, 2, 3, 4)
                .scan((x, y) -> x * y)
                .subscribe(item -> Log.d(TAG, "scanOperator: " + item));

        Observable.just(1, 2, 3, 4)
                .scan(10, (x, y) -> x * y)
                .subscribe(item -> Log.d(TAG, "scanOperator: " + item));
    }

    @SuppressLint("CheckResult")
    void sortedOpertor() {
        Observable.just(4, 11, 9, -1)
                .sorted()
                .subscribe(item -> Log.d(TAG, "sortedOpertor: " + item));

        Observable.just("qw", "qrdf", "a", "sdfgh")
                .sorted((first, second) -> Integer.compare(first.length(), second.length()))
                .subscribe(item -> Log.d(TAG, "sortedOpertor: " + item));

    }

    @SuppressLint("CheckResult")
    void delayOperator() {
        Observable.just(1, 2, 3, 4, 5)
                .delay(5000, TimeUnit.MILLISECONDS)
                .subscribe(item -> Log.d(TAG, "delayOperator: " + item));
    }

}
