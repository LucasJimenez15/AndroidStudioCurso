package com.example.aceleradorconviewmodel;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private int puntaje = 0;

    protected void incrementar(){
        puntaje++;
    }

    protected void decrementar(){
        puntaje--;
    }

    protected int getPuntaje(){
        return puntaje;
    }
}
