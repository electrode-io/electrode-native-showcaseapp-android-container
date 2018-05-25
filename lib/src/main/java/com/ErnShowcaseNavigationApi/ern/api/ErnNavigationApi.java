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

package com.ErnShowcaseNavigationApi.ern.api;

import android.support.annotation.NonNull;

import com.walmartlabs.electrode.reactnative.bridge.ElectrodeBridgeEventListener;
import com.walmartlabs.electrode.reactnative.bridge.ElectrodeBridgeEvent;
import com.walmartlabs.electrode.reactnative.bridge.ElectrodeBridgeRequestHandler;
import com.walmartlabs.electrode.reactnative.bridge.ElectrodeBridgeResponseListener;
import com.walmartlabs.electrode.reactnative.bridge.None;
import java.util.*;
import java.util.UUID;

import com.ErnShowcaseNavigationApi.ern.model.ErnRoute;

public final class ErnNavigationApi {
    private static final Requests REQUESTS;
    private static final Events EVENTS;

    static {
        REQUESTS = new ErnNavigationRequests();
        EVENTS = new ErnNavigationEvents();
    }

    private ErnNavigationApi() {
    }

    @NonNull
    public static Requests requests() {
        return REQUESTS;
    }

    @NonNull
    public static Events events() {
        return EVENTS;
    }

    public interface Events {
        String EVENT_ON_NAVIGATE = "com.ErnShowcaseNavigationApi.ern.api.event.onNavigate";

        UUID addOnNavigateEventListener(@NonNull final ElectrodeBridgeEventListener<ErnRoute> eventListener);

            ElectrodeBridgeEventListener<ElectrodeBridgeEvent> removeOnNavigateEventListener(@NonNull final UUID uuid);

        void emitOnNavigate(@NonNull ErnRoute ernRoute);

    }

    public interface Requests {
        String REQUEST_NAVIGATE = "com.ErnShowcaseNavigationApi.ern.api.request.navigate";


        void registerNavigateRequestHandler(@NonNull final ElectrodeBridgeRequestHandler<ErnRoute, None> handler);

        void navigate(ErnRoute route, @NonNull final ElectrodeBridgeResponseListener<None> responseListener);

    }
}