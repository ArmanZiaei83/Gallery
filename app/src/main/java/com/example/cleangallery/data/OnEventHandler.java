package com.example.cleangallery.data;

import java.security.SecureRandom;

public interface OnEventHandler {
    void onSuccess(String message);
    void onError(String error);
}
