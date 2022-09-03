package com.design.patterns.demo.designpattern.action.template.extension;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.NoBodyResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * MyHttpServlet类
 *
 *
 * 模板模式有两大作用：复用和扩展
 *
 * 第二个作用：扩展
 *
 * 模板模式把一个算法中不变的流程抽象到父类的模板方法 templateMethod() 中，
 * 将可变的部分 method1()、method2() 留给子类 ContreteClass1 和
 * ContreteClass2 来实现。所有的子类都可以复用父类中模板方法定义的流程代码。
 *
 * HttpServlet 的 service() 方法就是一个模板方法，
 * 它实现了整个 HTTP 请求的执行流程，doGet()、doPost()
 * 是模板中可以由子类来定制的部分。实际上，这就相当于 Servlet
 * 框架提供了一个扩展点（doGet()、doPost() 方法），让框架用
 * 户在不用修改 Servlet 框架源码的情况下，将业务代码通过扩展点
 * 镶嵌到框架中执行。
 *
 *
 * @author wangjixue
 * @date 2022/9/3 2:41 PM
 */
public abstract class MyHttpServlet{
    private static final String METHOD_DELETE = "DELETE";
    private static final String METHOD_HEAD = "HEAD";
    private static final String METHOD_GET = "GET";
    private static final String METHOD_OPTIONS = "OPTIONS";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_PUT = "PUT";
    private static final String METHOD_TRACE = "TRACE";
    private static final String HEADER_IFMODSINCE = "If-Modified-Since";
    private static final String HEADER_LASTMOD = "Last-Modified";
    private static final String LSTRING_FILE = "javax.servlet.http.LocalStrings";
    private static ResourceBundle lStrings = ResourceBundle.getBundle("javax.servlet.http.LocalStrings");



    public void service(ServletRequest req, ServletResponse res)throws ServletException, IOException {
        HttpServletRequest request;
        HttpServletResponse response;

        if(!(req instanceof HttpServletRequest && res instanceof HttpServletResponse)){
            throw new ServletException("non-HTTP request or response");
        }

        request = (HttpServletRequest) req;
        response = (HttpServletResponse) res;

        service(request,response);
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        String method = request.getMethod();
        if(method.equals(METHOD_GET)){
           long lastModifed =  getLastModified(request);
           if(lastModifed == -1){
               doGet(request,response);
           }else{
               long ifModifiedSince = request.getDateHeader(HEADER_IFMODSINCE);

               if(ifModifiedSince < lastModifed){
                   maybeSetLastModified(response,lastModifed);
                   doGet(request, response);
               }
           }
        }else if(method.equals(METHOD_HEAD)){

            long lastModifed =  getLastModified(request);
            maybeSetLastModified(response,lastModifed);
            doHead(request, response);
        }else if(method.equals(METHOD_POST)){
            doPost(request, response);
        }else if(method.equals(METHOD_PUT)){
            doPut(request, response);
        }else if(method.equals(METHOD_DELETE)){
            doDelete(request, response);
        }else if(method.equals(METHOD_OPTIONS)){
            doOptions(request, response);
        }else if(method.equals(METHOD_TRACE)){
            doTrace(request, response);
        }else {
            String errorMsg = lStrings.getString("http.method_not_implemented");
            Object[] errArgs = new Object[1];
            errArgs[0] = method;
            errorMsg = MessageFormat.format(errorMsg,errArgs);
            response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED,errorMsg);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String protocol = req.getProtocol();
        String msg = lStrings.getString("http.method_get_not_supported");
        if (protocol.endsWith("1.1")) {
            resp.sendError(405, msg);
        } else {
            resp.sendError(400, msg);
        }

    }

    protected long getLastModified(HttpServletRequest req) {
        return -1L;
    }


    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NoBodyResponse response = new NoBodyResponse(resp);
        this.doGet(req, response);
        response.setContentLength();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String protocol = req.getProtocol();
        String msg = lStrings.getString("http.method_post_not_supported");
        if (protocol.endsWith("1.1")) {
            resp.sendError(405, msg);
        } else {
            resp.sendError(400, msg);
        }

    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String protocol = req.getProtocol();
        String msg = lStrings.getString("http.method_put_not_supported");
        if (protocol.endsWith("1.1")) {
            resp.sendError(405, msg);
        } else {
            resp.sendError(400, msg);
        }

    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String protocol = req.getProtocol();
        String msg = lStrings.getString("http.method_delete_not_supported");
        if (protocol.endsWith("1.1")) {
            resp.sendError(405, msg);
        } else {
            resp.sendError(400, msg);
        }

    }

    private Method[] getAllDeclaredMethods(Class<? extends HttpServlet> c) {
        Class<?> clazz = c;

        Method[] allMethods;
        for(allMethods = null; !clazz.equals(HttpServlet.class); clazz = clazz.getSuperclass()) {
            Method[] thisMethods = clazz.getDeclaredMethods();
            if (allMethods != null && allMethods.length > 0) {
                Method[] subClassMethods = allMethods;
                allMethods = new Method[thisMethods.length + allMethods.length];
                System.arraycopy(thisMethods, 0, allMethods, 0, thisMethods.length);
                System.arraycopy(subClassMethods, 0, allMethods, thisMethods.length, subClassMethods.length);
            } else {
                allMethods = thisMethods;
            }
        }

        return allMethods != null ? allMethods : new Method[0];
    }

    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Method[] methods = this.getAllDeclaredMethods(this.getClass());
        boolean ALLOW_GET = false;
        boolean ALLOW_HEAD = false;
        boolean ALLOW_POST = false;
        boolean ALLOW_PUT = false;
        boolean ALLOW_DELETE = false;
        boolean ALLOW_TRACE = true;
        boolean ALLOW_OPTIONS = true;

        for(int i = 0; i < methods.length; ++i) {
            String methodName = methods[i].getName();
            if (methodName.equals("doGet")) {
                ALLOW_GET = true;
                ALLOW_HEAD = true;
            } else if (methodName.equals("doPost")) {
                ALLOW_POST = true;
            } else if (methodName.equals("doPut")) {
                ALLOW_PUT = true;
            } else if (methodName.equals("doDelete")) {
                ALLOW_DELETE = true;
            }
        }

        StringBuilder allow = new StringBuilder();
        if (ALLOW_GET) {
            allow.append("GET");
        }

        if (ALLOW_HEAD) {
            if (allow.length() > 0) {
                allow.append(", ");
            }

            allow.append("HEAD");
        }

        if (ALLOW_POST) {
            if (allow.length() > 0) {
                allow.append(", ");
            }

            allow.append("POST");
        }

        if (ALLOW_PUT) {
            if (allow.length() > 0) {
                allow.append(", ");
            }

            allow.append("PUT");
        }

        if (ALLOW_DELETE) {
            if (allow.length() > 0) {
                allow.append(", ");
            }

            allow.append("DELETE");
        }

        if (ALLOW_TRACE) {
            if (allow.length() > 0) {
                allow.append(", ");
            }

            allow.append("TRACE");
        }

        if (ALLOW_OPTIONS) {
            if (allow.length() > 0) {
                allow.append(", ");
            }

            allow.append("OPTIONS");
        }

        resp.setHeader("Allow", allow.toString());
    }

    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String CRLF = "\r\n";
        StringBuilder buffer = (new StringBuilder("TRACE ")).append(req.getRequestURI()).append(" ").append(req.getProtocol());
        Enumeration reqHeaderEnum = req.getHeaderNames();

        while(reqHeaderEnum.hasMoreElements()) {
            String headerName = (String)reqHeaderEnum.nextElement();
            buffer.append(CRLF).append(headerName).append(": ").append(req.getHeader(headerName));
        }

        buffer.append(CRLF);
        int responseLength = buffer.length();
        resp.setContentType("message/http");
        resp.setContentLength(responseLength);
        ServletOutputStream out = resp.getOutputStream();
        out.print(buffer.toString());
    }

    private void maybeSetLastModified(HttpServletResponse resp, long lastModified) {
        if (!resp.containsHeader("Last-Modified")) {
            if (lastModified >= 0L) {
                resp.setDateHeader("Last-Modified", lastModified);
            }

        }
    }
}
