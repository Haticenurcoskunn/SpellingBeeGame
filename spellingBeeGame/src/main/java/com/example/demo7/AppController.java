package com.example.demo7;

import java.io.*;
import java.util.*;

import javafx.fxml.FXML;

public class AppController {

    @FXML
    private void switchToPrimary() throws IOException {
    }


    public List<String> pangram() throws IOException {
        ArrayList<String> pangramMyList  = new ArrayList<String>() ;
        File file = new File("src\\main\\java\\com\\example\\demo7\\dictionary.txt");
        Scanner input = new Scanner(file);
        boolean isAvailable=false;
        int count = 0;
        while (input.hasNext()) {
            isAvailable=false;
            String word = input.next();
                word=word.toLowerCase();
                if(word.length()==7){
                    for(int i=0;i<word.length();i++){
                        for(int j=1;j<word.length();j++){
                            if(i!=j){
                                if( word.substring(i,i+1).equals(word.substring(j,j+1))){
                                    isAvailable=true;
                                    i=8;
                                    j=8;
                                }
                            }
                        }
                    }
                if(isAvailable==false){
                    pangramMyList.add(word);
                }
            }
            count = count + 1;
        }
        // System.out.println(pangramMyList);
        return pangramMyList;
    }



    public String chooseWord(List<String> pangramList){
        Random rand = new Random();
        String randomWord=pangramList.get(rand.nextInt(pangramList.size()));
        System.out.println(randomWord);
        return randomWord;
    }



    public String[] charsConverter(String randomWord)
    {
        String[] wordLetter= new String[ randomWord.length()];
        for (int i = 0; i <  randomWord.length(); i++) {
            wordLetter[i] = randomWord.substring(i, i+1);
        }
        System.out.println(Arrays.toString(wordLetter));
        return wordLetter;
    }


    static ArrayList<String> allCombinationsLetter(int X,String[] wordLetter){
        ArrayList<String> combinationList = new ArrayList<>();
        String[] ml = wordLetter;
        for(int i = 0; i < ml.length; i++)
        {
            System.out.print(ml[i] + " ");
        }
        int count = ml.length;
        for(int z = 0; z < X - 1; z++){
            Vector<String> tmp = new Vector<String>();
            for(int i = 0; i < wordLetter.length; i++){
                for(int k = 0; k < ml.length; k++){
                    if (wordLetter[i] != ml[k])
                    {
                        tmp.add(ml[k] + wordLetter[i]);
                        count += 1;
                    }
                }
            }
            for(int i = 0; i < tmp.size(); i++)
            {
              if(tmp.get(i).length()>=4
              ){combinationList.add(tmp.get(i));
              }
            }
            ml = tmp.toArray(new String[tmp.size()]);;
        }
        return combinationList;
    }



    ArrayList<String> dictionaryList(String[] letterList) throws FileNotFoundException {

        ArrayList<String> gameDictionary = new ArrayList<String>();
        File file = new File("C:\\projects\\dictionary.txt");
        Scanner input = new Scanner(file);

        int count = 0;
        while (input.hasNext()) {
            String word  = input.next();
          //  System.out.println(word);
            count = count + 1;
            if(word.length()>=4){
                if(word.contains(letterList[6])){
                    gameDictionary.add(word);
                }

            }
        }
        return gameDictionary;
    }




    ArrayList<String> sameWord(ArrayList<String>combinationList,ArrayList<String>dictionaryList) {

        ArrayList<String> sameWordList = new ArrayList<String>();
        for (int i = 0; i < combinationList.size(); i++) {
            for (int j = 0; j < dictionaryList.size(); j++) {
                if (combinationList.get(i).length() == dictionaryList.get(j).length()) {
                    if (combinationList.get(i).equals(dictionaryList.get(j))) {
                        sameWordList.add(combinationList.get(i));
                    }
                }
            }
        }
    return sameWordList;
    }


    boolean isAvailableInsert(ArrayList<String> sameList){
        if(sameList.size()>=20){
            System.out.println("true");
            System.out.println(sameList);
            return true;
        }
        else{
            System.out.println("false");
            System.out.println(sameList);
            return false;
        }

    }



    public String[] shuffle(String[] letterList){

        List<String> list= Arrays.asList(letterList);
        String removedStr = list.get(6);
        List<String> list2= list.subList(0,6);
        Collections.shuffle(list2);
        list.set(6,removedStr);
        return  list.toArray(letterList);
    }



    public boolean available=false;
    public boolean letterAvailable(String word) {
        word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            for (int j = 1; j < word.length(); j++) {
                if (i != j) {
                    if (word.substring(i, i + 1).equals(word.substring(j, j + 1))) {
                        available = true;
                        i = 8;
                        j = 8;
                    }
                }
            }
        }
        return available;
    }


}