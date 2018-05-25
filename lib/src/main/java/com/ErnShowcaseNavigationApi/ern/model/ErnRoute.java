/*
* Copyright 2017 WalmartLabs
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.ErnShowcaseNavigationApi.ern.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.List;

import com.walmartlabs.electrode.reactnative.bridge.Bridgeable;

import static com.walmartlabs.electrode.reactnative.bridge.util.BridgeArguments.*;

public class ErnRoute implements Parcelable, Bridgeable {

    private String path;
    private String arguments;
    private String payload;
    private NavBar navBar;
    private Boolean animated;
    private PopType popType;

    private ErnRoute() {}

    private ErnRoute(Builder builder) {
        this.path = builder.path;
        this.arguments = builder.arguments;
        this.payload = builder.payload;
        this.navBar = builder.navBar;
        this.animated = builder.animated;
        this.popType = builder.popType;
    }

    private ErnRoute(Parcel in) {
        this(in.readBundle());
    }

    public ErnRoute(@NonNull Bundle bundle) {
        if(!bundle.containsKey("path")){
            throw new IllegalArgumentException("path property is required");
        }

        this.path = bundle.getString("path");
        this.arguments = bundle.getString("arguments");
        this.payload = bundle.getString("payload");
        this.navBar = bundle.containsKey("navBar") ? new NavBar(bundle.getBundle("navBar")) : null;
        this.animated = bundle.containsKey("animated") ? bundle.getBoolean("animated") : null;
        this.popType = bundle.containsKey("popType") ? new PopType(bundle.getBundle("popType")) : null;
    }

    public static final Creator<ErnRoute> CREATOR = new Creator<ErnRoute>() {
        @Override
        public ErnRoute createFromParcel(Parcel in) {
            return new ErnRoute(in);
        }

        @Override
        public ErnRoute[] newArray(int size) {
            return new ErnRoute[size];
        }
    };

    /**
    * Path of the Route
    *
    * @return String
    */
    @NonNull
    public String getPath() {
        return path;
    }

    /**
    * Arguments for the path
    *
    * @return String
    */
    @Nullable
    public String getArguments() {
        return arguments;
    }

    /**
    * Payload for the screen
    *
    * @return String
    */
    @Nullable
    public String getPayload() {
        return payload;
    }

    @Nullable
    public NavBar getNavBar() {
        return navBar;
    }

    /**
    * describe if the screen want to be animated
    *
    * @return Boolean
    */
    @Nullable
    public Boolean getAnimated() {
        return animated;
    }

    @Nullable
    public PopType getPopType() {
        return popType;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBundle(toBundle());
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("path", this.path);
        if(arguments != null) {
            bundle.putString("arguments", this.arguments );
        }
        if(payload != null) {
            bundle.putString("payload", this.payload );
        }
        if(this.navBar != null) {
            bundle.putBundle("navBar", this.navBar.toBundle());
        }
        if(this.animated != null) {
            bundle.putBoolean("animated", this.animated);
        }
        if(this.popType != null) {
            bundle.putBundle("popType", this.popType.toBundle());
        }
        return bundle;
    }

    @Override
    public String toString() {
        return "{"
        + "path:" + (path != null ? "\"" + path + "\"" : null)+ ","
        + "arguments:" + (arguments != null ? "\"" + arguments + "\"" : null)+ ","
        + "payload:" + (payload != null ? "\"" + payload + "\"" : null)+ ","
        + "navBar:" + (navBar != null ? navBar.toString() : null)+ ","
        + "animated:" + animated+ ","
        + "popType:" + (popType != null ? popType.toString() : null)
        + "}";
    }

    public static class Builder {
        private final String path;
        private String arguments;
        private String payload;
        private NavBar navBar;
        private Boolean animated;
        private PopType popType;

        public Builder(@NonNull String path) {
            this.path = path;
        }

        @NonNull
        public Builder arguments(@Nullable String arguments) {
            this.arguments = arguments;
            return this;
        }
        @NonNull
        public Builder payload(@Nullable String payload) {
            this.payload = payload;
            return this;
        }
        @NonNull
        public Builder navBar(@Nullable NavBar navBar) {
            this.navBar = navBar;
            return this;
        }
        @NonNull
        public Builder animated(@Nullable Boolean animated) {
            this.animated = animated;
            return this;
        }
        @NonNull
        public Builder popType(@Nullable PopType popType) {
            this.popType = popType;
            return this;
        }

        @NonNull
        public ErnRoute build() {
            return new ErnRoute(this);
        }
    }
}
