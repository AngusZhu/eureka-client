package com.angus.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created with eureka-client.
 * User: anguszhu
 * Date: Jun,22 2017
 * Time: 16:25
 * description:
 */

@Controller
public class HelloController {

    @RequestMapping("/name/{who:.*}")
    @ResponseBody
    public String sayHello(@PathVariable String who){
        System.out.println(" come hellocontroller "+who);
        return "Hey "+who+", what's up man!";
    }

    @RequestMapping("/download/{fileName:.*}")
    public String download(HttpServletRequest req,HttpServletResponse resp, @PathVariable String fileName){
        System.out.printf("comes download");
        try {
            fileName = URLEncoder.encode(fileName, "utf-8");
            resp.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            resp.setHeader("Content-Type", "application/octet-stream;charset=UTF-8");
            resp.setHeader("X-Accel-Redirect", "/file/"+fileName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
