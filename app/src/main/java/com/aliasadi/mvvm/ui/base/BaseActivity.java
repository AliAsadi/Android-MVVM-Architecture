package com.aliasadi.mvvm.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.viewbinding.ViewBinding;

/**
 * Created by Ali Asadi on 07/01/2019.
 */
public abstract class BaseActivity<BINDING extends ViewBinding, VM extends BaseViewModel> extends AppCompatActivity {

    protected VM viewModel;
    protected BINDING binding;

    @NonNull
    protected abstract VM createViewModel();

    @NonNull
    protected abstract BINDING createViewBinding(LayoutInflater layoutInflater);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = createViewBinding(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        viewModel = createViewModel();
    }

}
