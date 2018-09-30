package com.thomaschu.tchu_feelsbook;

import java.util.ArrayList;

/*
* Generic Model class for the models used in app
* extend to a model if we need to implement one
* */
public abstract class Model<V extends TView> {
    private ArrayList<V> viewList;

    protected Model() {
        this.viewList = new ArrayList<V>();
    }

    public void addView(V view) {
        if(!this.viewList.contains(view)) {
            this.viewList.add(view);
        }
    }

    public void removeView(V view) {
        this.viewList.remove(view);
    }

    public void updateViews() {
        for(V view: this.viewList) {
            view.update(this);
        }
    }

}
