package com.epam.tests;

import com.epam.dataUtils.DataHelps;
import com.epam.dataUtils.ProjectData;
import com.epam.businessobjects.GmailBO;
import com.epam.businessobjects.GmailLoginBO;
import com.epam.listener.CustomListener;
import com.epam.models.MessageModel;
import com.epam.models.UserModel;
import com.epam.webdriverutils.WebDriverUtils;
import org.apache.log4j.Logger;
import org.testng.*;
import org.testng.annotations.*;
import org.uncommons.reportng.HTMLReporter;

import java.util.List;

@Listeners({HTMLReporter.class, CustomListener.class})
public class SelectThreeMessagesDeleteThemAndThanCancelDeleteTest {

    public static Logger logger = Logger.getLogger(SelectThreeMessagesDeleteThemAndThanCancelDeleteTest.class);

    @BeforeMethod
    public void before() {
        logger.info("BeforeMethod");
    }

    @Test(dataProviderClass = ProjectData.class, dataProvider = "getUsers")//threadPoolSize = 2
    public void testDeleteSelectedMessagesAndThanCancelDelete(String email, String password){
        logger.info("Test" +" " +email +" + " +password);
        ProjectData data = new ProjectData();

        GmailLoginBO loginBO = new GmailLoginBO();
        GmailBO gmailBO = new GmailBO();

        UserModel userModel = new UserModel(email, password);
        logger.info(userModel.toString());
        String userInformation = loginBO.login(userModel);
        Assert.assertTrue(userInformation.contains(userModel.getEmail()));

        List<MessageModel> messageModelList = data.getMessages();
        Assert.assertTrue(gmailBO.selectMessage(messageModelList.get(0)));
        Assert.assertTrue(gmailBO.selectMessage(messageModelList.get(1)));
        Assert.assertTrue(gmailBO.selectMessage(messageModelList.get(2)));

        Assert.assertEquals(messageModelList.size(), DataHelps.SELECTED_MESSAGES);
        Assert.assertEquals(gmailBO.getCountOfSelectedMessages(), DataHelps.SELECTED_MESSAGES);

        gmailBO.deleteSelectedMessages();
        Assert.assertEquals(gmailBO.getCountOfSelectedMessages(), DataHelps.SELECTED_MESSAGES_AFTER_DELETE);

        Assert.assertEquals(gmailBO.getCancelDeleteLinkText(), DataHelps.CANCEL_DELETE_SPAN_TEXT);
        gmailBO.cancelDeleteMessages();

        Assert.assertEquals(gmailBO.verificationThatMessagesWereNotDeleted(), DataHelps.CONFIRMATION_CANCEL_DELETE);
    }

    @AfterMethod
    public void after() {
        logger.info("AfterMethod");
        WebDriverUtils.close();
    }
}
