package com.lisitede.dockerfiles.playwright.service;

import com.lisitede.framework.exception.HttpServerException;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.PlaywrightException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;


@Slf4j
@Service
public class BrowserService {

    @Value("${playwright.executable.path}")
    private String executable;

    public String version() {
        log.warn(executable);
        
        try (Playwright playwright = Playwright.create();
             Browser browser = playwright.chromium()
                     .launch(new BrowserType.LaunchOptions().setExecutablePath(Paths.get(executable)))) {
            String version = browser.version();
            return version;
        } catch (PlaywrightException e) {
            e.printStackTrace();
            throw new HttpServerException(-1, "Playwright: " + e.getMessage());
        }
    }
}
