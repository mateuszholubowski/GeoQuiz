package com.codete.geoquiz.webservice;

/**
 * Created by Mateusz on 2015-08-08.
 */
public interface ServiceEventHandler {
    void started();
    void finishedWithError(String message, int errorCode, Throwable throwable);
    void finished();
}
