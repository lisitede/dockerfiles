package com.lisitede.dockerfiles.playwright.service;

import com.lisitede.framework.exception.HttpServerException;
import com.microsoft.playwright.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;


@Slf4j
@Service
public class PageService {

    @Value("${playwright.executable.path}")
    private String executable;

    public String extract(String url, String selector) {
        try (Playwright playwright = Playwright.create();
             Browser browser = playwright.chromium()
                     .launch(new BrowserType.LaunchOptions().setExecutablePath(Paths.get(executable)));
             Page page = browser.newPage()) {
            page.navigate(url);
            // page.waitForTimeout(5000);

            // String title = page.title();
            // String content = page.content();
            String html = page.locator(selector).innerHTML();

            return html;
        } catch (TimeoutError e) {
            throw new HttpServerException(-1, "Playwright: Timeout");
        }
    }
}