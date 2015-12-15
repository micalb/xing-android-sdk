/*
 * Copyright (c) 2015 XING AG (http://xing.com/)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.xing.android.sdk.sample;

import android.app.Application;

import com.xing.android.sdk.network.XingController;
import com.xing.android.sdk.sample.prefs.Prefs;
import com.xing.android.sdk.sample.utils.Utils;

/**
 * @author daniel.hartwich
 */
public class SdkSampleApplication extends Application {
    /**
     * Initializing the XingRequest Controller using the oAuth Token and the oAuthSecret.
     */
    public static void initializeXingRequestController(String oAuthToken, String oAuthSecret) {
        XingController.flush();
        XingController.setup()
              .setConsumerKey(BuildConfig.OAUTH_CONSUMER_KEY)
              .setConsumerSecret(BuildConfig.OAUTH_CONSUMER_SECRET)
              .setToken(oAuthToken)
              .setTokenSecret(oAuthSecret)
              .init();
    }

    @Override
    public void onCreate() {
        Prefs prefs = Prefs.getInstance(this);
        if (Utils.isLoggedIn(this)) {
            //noinspection ConstantConditions
            initializeXingRequestController(prefs.getOauthToken(), prefs.getOauthSecret());
        }
        super.onCreate();
    }
}
