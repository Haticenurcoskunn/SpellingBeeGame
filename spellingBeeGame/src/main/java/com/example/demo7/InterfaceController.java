package com.example.demo7;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class InterfaceController {

    AppController appController =new AppController();
    String[] charList;
    ArrayList<String> sameList;
    public int userPoint=0;

    public List<String> pangramList() throws IOException {
        List<String>pangramList= appController.pangram();
        return  pangramList;
    }

    @FXML
    public void switchToSceneTwo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void firstInsert(ActionEvent event) throws IOException {
        boolean isAvailable;
        do{
            String randomWord= appController.chooseWord(pangramList());
            charList= appController.charsConverter(randomWord);
            ArrayList<String> myCombinationList = appController.allCombinationsLetter(6,charList);
            ArrayList<String>dictionary = appController.dictionaryList(charList);
            sameList = appController.sameWord(myCombinationList, dictionary);
            isAvailable= appController.isAvailableInsert(sameList);
        }
        while(isAvailable==false);
        if (isAvailable==true)
        {
            insertLetter();
        }
        
    }

    @FXML
    void secondInsert(ActionEvent event) throws FileNotFoundException {
        String word=userArea.getText();
        boolean isAvailable2= appController.letterAvailable(word);
        boolean isAvailable=false;
        if(word.length()==7){
            if(isAvailable2==false){
                do{
                    if(!word.isEmpty())
                    {
                        charList= appController.charsConverter(word);
                        ArrayList<String> myCombinationList = appController.allCombinationsLetter(6,charList);
                        ArrayList<String>dictionary = appController.dictionaryList(charList);
                        sameList = appController.sameWord(myCombinationList, dictionary);
                        isAvailable= appController.isAvailableInsert(sameList);
                        notification.setText("YENİDEN DENEYİNİZ");
                        notification.clear();
                        userArea.clear();
                        word=userArea.getText();
                        break;
                    }
                    else
                    {
                        notification.setText("lütfen dene ");
                    }

                }
                while(isAvailable==false);
                if (isAvailable==true)
                {
                   insertLetter();
                }
            }
            else {
                notification.setText("farklı harf giriniz");
                notification.clear();
                userArea.clear();
            }

        }
        else {
            notification.setText("karakter sayısı 7 olmalı");
            notification.clear();
            userArea.clear();
        }
    }

    @FXML
    public int  calculatePoint(String word){

        if(word.length()==4){
            userPoint+=3;
        }
        for(int i=5;i<=10;i++){
            if(word.length()==i){
                userPoint+=i;
            }
        }
        pointArea.setText(Integer.toString(userPoint));
        return userPoint;
    }


    @FXML
    void enter(){
        String word=wordArea.getText();
        if(word.length()>=4)
        {
            if(sameList.contains(word)){
                list.getItems().add(word);
                calculatePoint(word);
            }
            else
            {
                notification.setText("yeniden deneyiniz");
            }
        }
        else{
                notification.setText("kelime en az 4  harfli olabilir");
        }
        wordArea.clear();
    }


    @FXML
    void delete(){
        wordArea.setText( wordArea.getText().substring(0,wordArea.getText().length()-1));
    }

    @FXML
    void shuffle(){
        appController.shuffle(charList);
        insertLetter();
    }
    
     void insertLetter(){
         one.setText(charList[0]);
         two.setText(charList[1]);
         three.setText(charList[2]);
         four.setText(charList[3]);
         five.setText(charList[4]);
         six.setText(charList[5]);
         seven.setText(charList[6]);
     }

    @FXML
    public void button0()
    {
        wordArea.appendText(charList[0]);
    }
    public void button1(){
        wordArea.appendText(charList[1]);
    }
    public void button2(){
        wordArea.appendText(charList[2]);
    }
    public void button3(){
        wordArea.appendText(charList[3]);
    }
    public void button4(){
        wordArea.appendText(charList[4]);
    }
    public void button5(){
        wordArea.appendText(charList[5]);
    }
    public void button6(){
        wordArea.appendText(charList[6]);
    }



    @FXML
    private Text one;
    @FXML
    private Text two;
    @FXML
    private Text three;
    @FXML
    private Text four;
    @FXML
    private Text five;
    @FXML
    private Text six;
    @FXML
    private Text seven;
    @FXML
    public TextArea wordArea;
    @FXML
    public TextArea notification;
    @FXML
    public TextArea pointArea;
    @FXML
    public TextArea userArea;
    @FXML
    public ListView list;
    }

