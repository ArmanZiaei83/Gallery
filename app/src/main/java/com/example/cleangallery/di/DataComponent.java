package com.example.cleangallery.di;

import androidx.core.content.pm.PermissionInfoCompat;

import com.example.cleangallery.domain.DataAccessor;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component
public interface DataComponent {
    void inject(DataAccessor accessor);
}
