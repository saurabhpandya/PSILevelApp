package com.psilevelapp.network;

import android.content.Context;

import androidx.annotation.Nullable;

import com.psilevelapp.PSILevelApp;
import com.psilevelapp.R;

import java.io.IOException;
import java.net.SocketException;

import javax.net.ssl.SSLException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ConnectivityInterceptor implements Interceptor {

    private Context mContext;

    public ConnectivityInterceptor() {
        mContext = PSILevelApp.getContext();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        try {
            Request.Builder builder = chain.request().newBuilder();
            return chain.proceed(builder.build());
        } catch (Exception e) {
            throw new NoConnectivityException(e);
        }
    }

    public class NoConnectivityException extends IOException {
        private Exception exception;

        public NoConnectivityException(Exception e) {
            exception = e;
        }

        @Nullable
        @Override
        public String getMessage() {
            if (exception instanceof IOException
                    || exception instanceof SocketException
                    || exception instanceof SSLException)
                return PSILevelApp.getContext().getResources().getString(R.string.error_no_network);
            else
                return super.getMessage();
        }
    }

}
