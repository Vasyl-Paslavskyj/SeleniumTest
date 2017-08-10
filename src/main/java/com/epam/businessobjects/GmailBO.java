package com.epam.businessobjects;

import com.epam.control.Table;
import com.epam.models.MessageModel;
import com.epam.pages.GmailPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GmailBO{
    private GmailPage gmailPage = new GmailPage();

    public int getCountOfSelectedMessages(){
        return gmailPage.getCountOfSelectedMessages();
    }

    public void deleteSelectedMessages(){
        gmailPage.deleteSelectedMessages();
    }

    public void cancelDeleteMessages(){
        gmailPage.cancelDeleteMessages();
    }

    public String verificationThatMessagesWereNotDeleted(){
        return gmailPage.verificationThatMessagesWereNotDeleted();
    }

    public String getCancelDeleteLinkText(){
        return gmailPage.getCancelDeleteSpan().getText();
    }

    public boolean selectMessage(MessageModel messageModel){
        boolean isMessageSelected = false;
        Table table = gmailPage.getTable();
        System.out.println("TABLE !!!!!!!!!!!");

        WebElement element = table.getCellEditor(messageModel.getFrom(), messageModel.getSubject(), messageModel.getDate());
        System.out.println("WebElement !!!!!!!!!!!");
        if(!element.findElement(By.cssSelector("div.T-Jo-auh")).isSelected()){
            element.findElement(By.cssSelector("div.T-Jo-auh")).click();
            isMessageSelected = true;
        }
        return isMessageSelected;
    }

}
