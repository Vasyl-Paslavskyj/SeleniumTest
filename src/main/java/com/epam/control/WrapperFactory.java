package com.epam.control;

import org.openqa.selenium.WebElement;

public class WrapperFactory {
    public static AbstractElement createInstance(Class<AbstractElement> clazz,
                                          WebElement element) {
        try {
            return clazz.getConstructor(WebElement.class).
                    newInstance(element);
        } catch (Exception e) {
            throw new AssertionError(
                    "WebElement can't be represented as " + clazz
            );
        }
    }
}
