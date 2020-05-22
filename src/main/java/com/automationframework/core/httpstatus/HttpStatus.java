package com.automationframework.core.httpstatus;

import com.automationframework.api.driver.Driver;
import com.automationframework.core.reporter.Report;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 11/7/18
 *
 * Checking the status of URL
 */
public class HttpStatus {

    private String url;
    private int invalidImageCount;

    public HttpStatus(String url) {

        this.url = url;
    }

    public HttpStatus() {

    }

    private HttpResponse newRequest(String url) {

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            return client.execute(request);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public int GET() {

        return newRequest(url).getStatusLine().getStatusCode();
    }

    public String getStatusMessage() {

       return newRequest(url).getStatusLine().getReasonPhrase();
    }

    private void verifyImageActive(String url) {

        try {
            HttpResponse response = newRequest(url);
            // verifying response code he HttpStatus should be 200 if not,
            // increment as invalid images count
            if (response.getStatusLine().getStatusCode() != 200) {
                Report.error(response.getStatusLine().getStatusCode() + " - " + response.getStatusLine().getReasonPhrase() + " - " + url);
                invalidImageCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validateImagesPresent() {

        try {
            invalidImageCount = 0;
            Driver driver = new Driver();
            List<WebElement> imageList = driver.getDriver().findElements(By.tagName("img"));
            for (WebElement element : imageList) {
                String image = element.getAttribute("src");
                if (image != null) {
                    verifyImageActive(image);
                }
            }
            Report.error("Total number of invalid images with photo is " + invalidImageCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkForBrokenLinks(String tagName, String attribute) {

        Driver driver = new Driver();
        List<WebElement> links = driver.getDriver().findElements(By.tagName(tagName));
        System.out.println(links.size());
        for (WebElement element : links) {
            String url = element.getAttribute(attribute);
            if (url != null && url.contains("https")) {
                verifyLink(url);
            }
        }
    }

    private void verifyLink(String urlLink) {

        try {
            URL link = new URL(urlLink);
            HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() != 200) {
                Report.error(httpURLConnection.getResponseCode() + " - " + httpURLConnection.getResponseMessage() + " - " + urlLink);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}