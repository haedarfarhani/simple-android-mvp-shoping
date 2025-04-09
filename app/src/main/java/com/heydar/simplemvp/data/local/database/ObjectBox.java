package com.heydar.simplemvp.data.local.database;

import android.content.Context;
import android.util.Log;

import com.heydar.simplemvp.data.local.database.entity.MyObjectBox;
import com.heydar.simplemvp.di.ApplicationContext;

import javax.inject.Inject;

import io.objectbox.BoxStore;

public class ObjectBox {
    private final BoxStore boxStore;

    @Inject
    public ObjectBox(@ApplicationContext Context context) {
        boxStore = MyObjectBox.builder()
                .androidContext(context.getApplicationContext())
                .build();
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }
}