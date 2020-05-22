package com.automationframework.api.interfaces;

import java.util.List;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/24/18
 */

public interface ListShould extends ElementShould {

    void haveTexts(List<String> texts);

    void haveSize(int expectedSize);
}