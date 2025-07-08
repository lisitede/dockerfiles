package com.lisitede.dockerfiles.playwright.controller;

import com.lisitede.dockerfiles.playwright.service.BrowserService;
import com.lisitede.dockerfiles.playwright.service.PageService;
import com.lisitede.framework.vo.RestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@Slf4j
@RequestMapping(value = "/api")
@RestController
public class ApiController {

    @Autowired
    private BrowserService browserService;

    @Autowired
    private PageService pageService;

    @RequestMapping(value = "/chromium-version", method = RequestMethod.GET)
    public RestData chromiumVersion() {
        String version = browserService.version();
        return new RestData("version", version);
    }

    @RequestMapping(value = "/extract-selector", method = RequestMethod.POST)
    public String extractSelector(@RequestBody HashMap<String, String> form) {
        String url = form.get("url");
        String selector = form.get("selector");
        String html = pageService.extract(url, selector);
        return "<html><head></head><body>" + html + "</body></html>";
    }

    @RequestMapping(value = "/extract-body", method = RequestMethod.POST)
    public String extractBody() {
        return pageService.extract("https://httpbin.org/html", "body");
    }
}