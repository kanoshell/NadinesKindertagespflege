package com.daheim.nadineskindertagespflege;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;


public class KindSet implements Set {
    int size;


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @NonNull
    @Override
    public Iterator iterator() {
        return null;
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(@NonNull Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean removeAll(@NonNull Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(@NonNull Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(@NonNull Collection c) {
        return false;
    }

    @NonNull
    @Override
    public Object[] toArray(@NonNull Object[] a) {
        return new Object[0];
    }
}
