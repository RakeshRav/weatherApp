/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.androider.weatherapp.di.module;

import android.app.Application;
import android.content.Context;

import com.androider.weatherapp.BuildConfig;
import com.androider.weatherapp.data.AppDataManager;
import com.androider.weatherapp.data.DataManager;
import com.androider.weatherapp.data.network.ApiHelper;
import com.androider.weatherapp.data.network.AppApiHelper;
import com.androider.weatherapp.data.prefs.AppPreferencesHelper;
import com.androider.weatherapp.data.prefs.PreferencesHelper;
import com.androider.weatherapp.di.ApiInfo;
import com.androider.weatherapp.di.ApplicationContext;
import com.androider.weatherapp.di.PreferenceInfo;
import com.androider.weatherapp.utility.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rao .
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

//    @Provides
//    @Singleton
//    CalligraphyConfig provideCalligraphyDefaultConfig() {
//        return new CalligraphyConfig.Builder()
//                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
//                .setFontAttrId(R.attr.fontPath)
//                .build();
//    }
}
