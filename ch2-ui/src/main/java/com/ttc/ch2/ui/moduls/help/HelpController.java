package com.ttc.ch2.ui.moduls.help;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;

@Controller
public class HelpController implements ServletContextAware {
    
    private static Logger logger = LoggerFactory.getLogger(HelpController.class);

    private ServletContext context;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }

    @RequestMapping(value = "/help/{FILE_NAME}", method = RequestMethod.GET)
    public void handle(HttpServletRequest request, HttpServletResponse response, @PathVariable("FILE_NAME") String filename) throws Throwable {
        logger.info("filename: {}", filename);
        String path = context.getRealPath("help/" + filename + ".pdf");
        File file = new File(path);

        logger.info("path: {}", file.getAbsoluteFile());
        if (file.exists() && file.canRead()) {
            FileInputStream in = new FileInputStream(file);
            try {
                byte[] bytes = new byte[0x1000];
                response.setContentType("application/pdf");
                ServletOutputStream out = response.getOutputStream();
                for (int n; (n = in.read(bytes)) != -1;) {
                    out.write(bytes, 0, n);
                }
                out.flush();
            } finally {
                in.close();
            }
        }
    }
    
}
