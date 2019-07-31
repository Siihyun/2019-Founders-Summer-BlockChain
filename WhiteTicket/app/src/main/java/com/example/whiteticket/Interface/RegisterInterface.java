package com.example.whiteticket.Interface;

public interface RegisterInterface {
    interface View{
        void setView();
    }
    interface Presenter{
        void presenterView();
        void SignUp(String id, String pw);
    }
}
