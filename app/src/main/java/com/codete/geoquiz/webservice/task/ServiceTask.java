package com.codete.geoquiz.webservice.task;

import com.codete.geoquiz.webservice.Response;
import com.codete.geoquiz.webservice.ServiceEventHandler;
import com.codete.geoquiz.webservice.WebService;

public abstract class ServiceTask {
    private static final String TAG = "ServiceTask";

    public ServiceEventHandler getHandler() {
        return handler;
    }

    protected ServiceEventHandler handler;
    private final WebService service;

    public static class ServiceTaskException extends Exception {
        public ServiceTaskException() {
        }

        public ServiceTaskException(String detailMessage) {
            super(detailMessage);
        }

        public ServiceTaskException(String detailMessage, Throwable throwable) {
            super(detailMessage, throwable);
        }

        public ServiceTaskException(Throwable throwable) {
            super(throwable);
        }
    }

    public WebService getService() {
        return service;
    }

    protected ServiceTask(WebService manager, ServiceEventHandler handler) {
        this.service = manager;
        this.handler = handler;
    }

    protected abstract Response doTask() throws ServiceTaskException;

    public Response execute() throws ServiceTaskException {
        return doTask();
    }

    public static class ErrorCode{
        public static final int EMPTY_RESPONSE = 0;
        public static final int HANDLED_EXCEPTION = 1;
    }
}
