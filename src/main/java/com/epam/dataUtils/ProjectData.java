package com.epam.dataUtils;

import com.epam.models.MessageModel;
import com.epam.models.Messages;
import com.epam.models.UserModel;
import com.epam.models.Users;

import com.opencsv.CSVReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjectData {
    private static final String FILE_MESSAGES_PATH = System.getProperty("user.dir") + "/src/main/resources/messages.csv";
    private static final String FILE_USERS_PATH = System.getProperty("user.dir") + "/src/main/resources/users.xlsx";

    private Messages messages;

    @DataProvider(parallel = true)
    public static Object[][] getUsers() {
        InputStream fis = null;
        XSSFWorkbook myWorkBook = null;
        Users users = new Users();
        List<UserModel> usersList = new ArrayList<>();
        try {
            fis = new FileInputStream(new File(FILE_USERS_PATH));
            myWorkBook = new XSSFWorkbook(fis);
            Sheet mySheet = myWorkBook.getSheetAt(0);
            Iterator<Row> rowIterator = mySheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell1 = cellIterator.next();
                    Cell cell2 = cellIterator.next();
                    if(cell1.getCellType() == Cell.CELL_TYPE_STRING && cell2.getCellType() == Cell.CELL_TYPE_STRING){
                        usersList.add(new UserModel(cell1.getStringCellValue(), cell2.getStringCellValue()));
                    }
                }
                users.setUsers(usersList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<UserModel> list = users.getUsers();
        return getUsersInArray(list);
    }

//    @DataProvider(parallel = true)
//    public static Object[][] getUsers() {
//        Users users = null;
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
//            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//            users = (Users) jaxbUnmarshaller.unmarshal(new File("src/main/resources/users.xml"));
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//        List<UserModel> list = users.getUsers();
//        return getUsersInArray(list);
//    }

    private static Object[][] getUsersInArray(List<UserModel> list){
        Object[][] objects = new Object[list.size()][2];
        for (int i = 0; i < list.size(); i++){
            objects[i][0] = list.get(i).getEmail();
            objects[i][1] = list.get(i).getPassword();
        }
        return objects;
    }

//    public List<MessageModel> getMessages() {
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(Messages.class);
//            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//            messages = (Messages) jaxbUnmarshaller.unmarshal(new File("src/main/resources/messages.xml"));
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//        return messages.getMessageList();
//    }

    public List<MessageModel> getMessages() {
        List<MessageModel> messageList = new ArrayList<>();
        CSVParser parser;
        try {
            parser = CSVParser.parse(new File(FILE_MESSAGES_PATH), Charset.forName("UTF-8"), CSVFormat.RFC4180);
            for (CSVRecord csvRecord : parser) {
                messageList.add(new MessageModel(csvRecord.get(0), csvRecord.get(1), csvRecord.get(2)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (MessageModel model : messageList){
            System.out.println(model.toString());
        }
        return messageList;
    }
}
