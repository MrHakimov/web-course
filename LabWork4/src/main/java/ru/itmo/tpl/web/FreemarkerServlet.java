package ru.itmo.tpl.web;

import freemarker.template.*;
import ru.itmo.tpl.util.DataUtil;
import ru.itmo.tpl.util.DebugUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerServlet extends HttpServlet {
    private Configuration freemarkerConfiguration;
    private String mainUri;

    @Override
    public void init() throws ServletException {
        super.init();

        freemarkerConfiguration = new Configuration(Configuration.VERSION_2_3_29);

        File freemarkerDirectory = DebugUtil.getFile(getServletContext(), "WEB-INF/templates");
        try {
            freemarkerConfiguration.setDirectoryForTemplateLoading(freemarkerDirectory);
        } catch (IOException e) {
            throw new ServletException("Unable to configure freemarker configuration:"
                    + " freemarkerConfiguration.setDirectoryForTemplateLoading(freemarkerDirectory) failed"
                    + " [freemarkerDirectory=" + freemarkerDirectory + "].", e);
        }

        freemarkerConfiguration.setDefaultEncoding(StandardCharsets.UTF_8.name());
        freemarkerConfiguration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        freemarkerConfiguration.setLogTemplateExceptions(false);
        freemarkerConfiguration.setWrapUncheckedExceptions(true);
        freemarkerConfiguration.setFallbackOnNullLoopVariable(false);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        mainUri = request.getRequestURI();
        while (mainUri.endsWith("/")) {
            mainUri = mainUri.substring(0, mainUri.length() - 1);
        }
        int index = mainUri.indexOf('/');
        String uri = mainUri.substring(index == -1 ? mainUri.length() : index);

        if (uri.equals("/")) {
            mainUri += "index";
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.sendRedirect("/index");
        } else if (uri.isEmpty()) {
            mainUri += "/index";
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.sendRedirect("/index");
        }

        Template template;
        try {
            template = freemarkerConfiguration.getTemplate(
                    URLDecoder.decode(mainUri, StandardCharsets.UTF_8.name()) + ".ftlh");
        } catch (TemplateNotFoundException ignored) {
            template = freemarkerConfiguration.getTemplate("notFoundPage.ftlh");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        response.setContentType("text/html");
        Map<String, Object> data = new HashMap<>();
        putData(request, data);

        try {
            template.process(data, response.getWriter());
        } catch (TemplateException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    private void putData(HttpServletRequest request, Map<String, Object> data) {
//        if (request.getParameter("logged_user_id") != null && !request.getParameter("logged_user_id").equals("")) {
//            data.put("user", Long.valueOf(request.getParameter("logged_user_id")));
//        }
        int index = mainUri.indexOf('/');
        String uri = mainUri.substring(index == -1 ? mainUri.length() : index);
        data.put("uri", uri);
        for (Map.Entry<String, String[]> e : request.getParameterMap().entrySet()) {
            if (e.getValue() != null && e.getValue().length == 1) {
                if (e.getKey().endsWith("_id")) {
                    try {
                        long id = Long.parseLong(e.getValue()[0]);
                        data.put(e.getKey(), id);
                    } catch (NumberFormatException ex) {
                        data.put(e.getKey(), -1);
                    }
                } else {
                    System.out.println(e.getKey() + " " + e.getValue()[0]);
                    data.put(e.getKey(), e.getValue()[0]);
                }
            }
        }

        DataUtil.putData(data);
//        HttpSession session = request.getSession();
//        if (data.containsKey("logged_user_id")) {
//            session.setAttribute("logged_user_id", (String) data.get("logged_user_id"));
//        }
    }
}
