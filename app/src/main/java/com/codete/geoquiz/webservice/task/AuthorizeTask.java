package com.codete.geoquiz.webservice.task;


import com.codete.geoquiz.webservice.Response;
import com.codete.geoquiz.webservice.ServiceEventHandler;
import com.codete.geoquiz.webservice.WebService;

/**
 * Created by Mateusz on 2015-08-08.
 */
public class AuthorizeTask extends ServiceTask {


    private static final String name = "AuthorizeTask";
    private String login;
    private String password;


    public static AuthorizeTask createTask(String login, String password,
                                           WebService manager, ServiceEventHandler handler) {
        return new AuthorizeTask(login, password, manager, handler);
    }

    protected AuthorizeTask(String login, String password, WebService manager,
                            ServiceEventHandler handler) {
        super(manager, handler);
        this.login = login;
        this.password = password;
    }

    @Override
    protected Response doTask() throws ServiceTaskException {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Response response = new Response();

        if ("mateusz".equals(login) && "superhaslo".equals(password))
            response.setHasError(false);
        else {
            response.setHasError(true);
            response.setMessage("Wrong login or password");
        }

        return response;
    }
}
