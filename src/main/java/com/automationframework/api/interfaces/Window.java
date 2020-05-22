package com.automationframework.api.interfaces;

import java.net.URL;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 8/13/1
 *
 * Represents Browser Window and all possible actions in it
 */

public interface Window {

    String getUrl();

    String getTitle();

    void maximize();

    void back();

    void forward();

    void refresh();

    void navigateTo(String url);

    void navigateTo(URL url);
}