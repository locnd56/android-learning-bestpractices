package com.locnd.appbase.websocket;


import com.google.gson.JsonElement;

import microsoft.aspnet.signalr.client.Action;
import microsoft.aspnet.signalr.client.ConnectionState;
import microsoft.aspnet.signalr.client.ErrorCallback;
import microsoft.aspnet.signalr.client.LogLevel;
import microsoft.aspnet.signalr.client.Logger;
import microsoft.aspnet.signalr.client.MessageReceivedHandler;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.StateChangedCallback;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;

/**
 * Created by giang.ngo on 03-03-2016.
 */
public class HubConnectionFactory {

    static String TAG_LOG = "HubConnection";
    private static HubConnectionFactory mInstance;
    private HubConnection hubConnection;
    private HubProxy hubProxy;
    private Logger logger;

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public HubProxy getHubProxy() {
        return hubProxy;
    }

    public static synchronized HubConnectionFactory getInstance() {
        if (null == mInstance) {
            mInstance = new HubConnectionFactory();
        }
        return mInstance;
    }

    private void initHubConnection(String server, String hubName) {
        logInfo("Server:." + server + "\n" + "HubName:." + hubName);

        Logger logger = initLogger();

        // Create a connection to the server
        hubConnection = new HubConnection(server, "", true, logger);
        if (hubConnection == null) {
            logError("Creating a connection is error");
            return;
        }

        // Create the hub proxy
        hubProxy = hubConnection.createHubProxy(hubName);
        if (hubProxy == null) {
            logError("Creating a hubProxy is error");
            return;
        }
        // Subscribe to the events
        hubConnection.error(new ErrorCallback() {

            @Override
            public void onError(Throwable error) {
                logError(error.getMessage());
            }
        });

        hubConnection.connected(new Runnable() {

            @Override
            public void run() {
                logInfo("HubConnection is connected");
            }
        });

        hubConnection.closed(new Runnable() {

            @Override
            public void run() {
                logInfo("HubConnection is disconnected");
            }
        });

        hubConnection.received(new MessageReceivedHandler() {
            @Override
            public void onMessageReceived(JsonElement json) {
                hubConnection.getLogger().log("Receive:." + json.toString(), LogLevel.Information);
            }
        });

        hubConnection.stateChanged(new StateChangedCallback() {
            @Override
            public void stateChanged(ConnectionState oldState, ConnectionState newState) {
                hubConnection.getLogger().log("oldState:." + oldState + " newState:." + newState, LogLevel.Information);
            }
        });

    }

    private Logger initLogger() {
        // Create a new console logger
        Logger logger = new Logger() {
            @Override
            public void log(String message, LogLevel level) {
                if (HubConnectionFactory.this.logger != null) {
                    HubConnectionFactory.this.logger.log(message, level);
                }
            }
        };
        return logger;
    }

    private void logError(String msg) {
        if (logger != null) {
            logger.log(msg, LogLevel.Information);
        }
//        Log.w(TAG_LOG, msg);
    }

    private void logInfo(String msg) {
//        Log.w(TAG_LOG, msg);
    }

    public SignalRFuture<Void> connectToServer(String server, String hubName) {

        // khoi tao connection
        initHubConnection(server, hubName);

        if (hubConnection != null) {
            // Start the connection
            SignalRFuture<Void> future = hubConnection.start()
                    .done(new Action<Void>() {
                        @Override
                        public void run(Void obj) throws Exception {
                            logInfo("HubConnection is done connecting");
                        }
                    }).onError(new ErrorCallback() {
                        @Override
                        public void onError(Throwable error) {
                            logError(error.getMessage());
                        }
                    });
            return future;
        }
        return null;
    }

    public void disconnect() {
        try {
            hubProxy = null;
            hubConnection.stop();
        } catch (Exception e) {

        }
    }

    public HubConnection getHubConnection() {
        return hubConnection;
    }
}
