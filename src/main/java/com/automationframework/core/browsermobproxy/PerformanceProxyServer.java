package com.automationframework.core.browsermobproxy;

import com.automationframework.core.properties.SystemEnvironmentalProperties;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 8/9/18
 */

public class PerformanceProxyServer {

    //TODO if this trows Null pointer crete those methods as static or find a way to initiate userBrowserProxy
    private static BrowserMobProxy useBrowserProxy = new BrowserMobProxyServer();


    public static void startBrowserMobProxy(MutableCapabilities caps) {

        useBrowserProxy.setTrustAllServers(true);
        useBrowserProxy.start();
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(useBrowserProxy);
        caps.setCapability(CapabilityType.PROXY, seleniumProxy);

        useBrowserProxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        useBrowserProxy.newHar(SystemEnvironmentalProperties.getInstance().getElementsPath() + ".har");
    }

    public static void getHarFile() throws IOException {

        Har har = useBrowserProxy.getHar();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy HH:mm:ss");
        File harFile = new File("./logs/performance/Aprimo/Aprimo_IdeaLab_Smoke_Test_" + dateFormat.format(date) + "_" + SystemEnvironmentalProperties.getInstance().getBrowser() + ".har");
        har.writeTo(harFile);
        useBrowserProxy.stop();
    }

}