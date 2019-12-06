package com.psilevelapp;

import androidx.databinding.BaseObservable;

public abstract class BaseVIewModel extends BaseObservable {

    public abstract void subScribe();

    public abstract void clear();

    public abstract void dispose();

}
