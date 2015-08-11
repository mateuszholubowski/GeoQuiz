package com.codete.geoquiz.webservice;

import android.os.AsyncTask;

import com.codete.geoquiz.webservice.task.ServiceTask;

import java.util.LinkedList;
import java.util.Queue;

public class ServiceTaskRunner {
    private boolean running = false;

    private Queue<ServiceTask> queue = new LinkedList<>();

    public ServiceTaskRunner() {
    }

    public void startTask(ServiceTask task) {
        if (!isRunning()) {
            setRunning(true);
            AsyncRunner runner = new AsyncRunner(task.getHandler());
            runner.execute(task);
        } else {
            synchronized (queue) {
                queue.add(task);
            }
        }
    }

    public synchronized boolean isRunning() {
        return running;
    }

    public synchronized void setRunning(boolean isRunning) {
        this.running = isRunning;
    }

    private class AsyncRunner extends AsyncTask<ServiceTask, String, Response> {

        private Exception ex;
        private ServiceEventHandler handler;

        public AsyncRunner(ServiceEventHandler handler) {
            this.handler = handler;
        }

        @Override
        protected void onPreExecute() {
            if (handler != null)
                handler.started();
        }

        @Override
        protected Response doInBackground(ServiceTask... params) {
            try {
                Response response = params[0].execute();
                setRunning(false);
                return response;
            } catch (Exception ex) {
                ex.printStackTrace();
                setRunning(false);
                this.ex = ex;
                cancel(true);
            }
            return null;
        }

        @Override
        protected void onCancelled() {
            if (handler != null)
                handler.finishedWithError(ex.getMessage(), ServiceTask.ErrorCode
                        .HANDLED_EXCEPTION, ex);
        }

        @Override
        protected void onPostExecute(Response response) {

            if (handler != null) {
                if (response == null)
                    handler.finishedWithError("Response null", ServiceTask.ErrorCode
                            .EMPTY_RESPONSE, null);
                else if (!response.hasError())
                    handler.finished();
                else
                    handler.finishedWithError(response.getMessage(), response.getErrorCode(), null);
            }
            synchronized (queue) {
                if (!queue.isEmpty()) {
                    startTask(queue.poll());
                }
            }
        }
    }
}
