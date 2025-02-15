/*
 * Copyright (C) 2021 Wave-OS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.awaken.fragments;

import android.os.Bundle;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.R;


import android.app.ActivityManagerNative;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.IWindowManager;
import android.view.View;
import android.view.WindowManagerGlobal;

import androidx.preference.ListPreference;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;

import com.android.internal.logging.nano.MetricsProto.MetricsEvent;
import com.android.settings.Utils;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settingslib.search.SearchIndexable;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.awaken.settings.preferences.CustomSeekBarPreference;
import com.awaken.settings.preferences.SystemSettingSwitchPreference;
import com.awaken.settings.preferences.SystemSettingSeekBarPreference;
import com.awaken.settings.preferences.SystemSettingListPreference;
import com.android.internal.util.arcana.ArcanaUtils;

public class DisplayCustomizations extends SettingsPreferenceFragment {

    private static final String TAG = "Display Customizations";
    private static final String SETTINGS_DASHBOARD_GMS = "settings_dashboard_gms";
    private SystemSettingListPreference mSettingsDashBoardGms;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.display_customizations);

        final ContentResolver resolver = getActivity().getContentResolver();
        final PreferenceScreen prefSet = getPreferenceScreen();
        final Context mContext = getActivity().getApplicationContext();
        final Resources res = mContext.getResources();

        
        mSettingsDashBoardGms = (SystemSettingListPreference) findPreference(SETTINGS_DASHBOARD_GMS);
        mSettingsDashBoardGms.setOnPreferenceChangeListener(this);
    
    }

    @Override
    public int getMetricsCategory() {
        return -1;
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final ContentResolver resolver = getActivity().getContentResolver();
       if (preference == mSettingsDashBoardGms) {
            ArcanaUtils.showSettingsRestartDialog(getContext());
            return true;
        }
        return false;
    }
}
