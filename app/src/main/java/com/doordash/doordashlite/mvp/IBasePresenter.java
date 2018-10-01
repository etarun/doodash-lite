package com.doordash.doordashlite.mvp;


public interface IBasePresenter<ViewT> {

    void onViewActive(ViewT view);

}
