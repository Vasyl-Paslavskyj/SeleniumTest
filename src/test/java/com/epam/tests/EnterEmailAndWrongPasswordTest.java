package com.epam.tests;

import com.epam.businessobjects.GmailLoginBO;
import com.epam.dataUtils.DataHelps;
import com.epam.listener.CustomListener;
import com.epam.models.UserModel;
import com.epam.webdriverutils.WebDriverUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.uncommons.reportng.HTMLReporter;

@Listeners({HTMLReporter.class, CustomListener.class})
public class EnterEmailAndWrongPasswordTest {

    public static Logger logger = Logger.getLogger(EnterEmailAndWrongPasswordTest.class);

    @BeforeMethod
    public void before() {
        logger.info("BeforeMethod");
    }

    @Test()
    public void testEnterEmailAndWrongPasswordAndThanFall(){//I am not using
        GmailLoginBO loginBO = new GmailLoginBO();
        UserModel userModel = new UserModel(DataHelps.EMAIL_FOR_FALL_TEST, DataHelps.PASSWORD_FOR_FALL_TEST);
        logger.info(userModel.toString());
        String userInformation = loginBO.login(userModel);
    }

    @AfterMethod
    public void after(){
        logger.info("AfterMethod");
        WebDriverUtils.close();
    }
}
