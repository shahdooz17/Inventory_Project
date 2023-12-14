package Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {

    private String fileName;
    private final String dbLocation = "D:\\Inventory_Project\\src\\Files\\";

    public Database() throws Exception {
        this("temp");
    }

    public Database(String fileName) throws Exception {
        this.fileName = fileName;
    }

    public boolean writeFile(String text) throws Exception {
        try (FileWriter writeFile = new FileWriter(dbLocation + fileName+ ".txt")) {
            writeFile.write(text);
            writeFile.close();
            return true;
        }
    }

    public void appendText(String text) throws Exception {
        try(FileWriter writeFile = new FileWriter(dbLocation + fileName+ ".txt", true)) {
            writeFile.append(text+ "\n");
            writeFile.close();
        }
    }

    public void updateText(String oldText, String newText) throws Exception {
        File myFile = new File(this.dbLocation+ this.fileName +".txt");
        File myTempFile = new File(this.dbLocation+ "temp." + this.fileName +".txt");
        try(
                Scanner fileInput = new Scanner(myFile);
                PrintWriter fileOutput = new PrintWriter(myTempFile);
        ){
            while(fileInput.hasNext()) {
                String original = fileInput.nextLine();
                String trimmedLine = original.trim();
                if(trimmedLine.equals(oldText)) {
                    fileOutput.println(newText);
                } else {
                    fileOutput.println(original);
                }
            }
        }
        myFile.delete();
        myTempFile.renameTo(myFile);
    }

    public void removeText(String oldText) throws Exception {
        File myFile = new File(this.dbLocation+ this.fileName +".txt");
        File myTempFile = new File(this.dbLocation+ "temp." + this.fileName +".txt");
        try(
                Scanner fileInput = new Scanner(myFile);
                PrintWriter fileOutput = new PrintWriter(myTempFile);
        ){
            while(fileInput.hasNext()) {
                String original = fileInput.nextLine();
                String trimmedLine = original.trim();
                if(trimmedLine.equals(oldText)) {
                    continue;
                }
                fileOutput.println(original);
            }
        }
        myFile.delete();
        myTempFile.renameTo(myFile);
    }



    public ArrayList<String> readText() throws IOException {
        File myFile = new File(this.dbLocation+ this.fileName +".txt");
        ArrayList<String> myData = new ArrayList<String>    ();
        try(Scanner readFile = new Scanner(myFile)) {
            while (readFile.hasNextLine()) {
                String data = readFile.nextLine();
                myData.add(data);
            }
        }
        return myData;
    }

    public static String encrypt(ArrayList<String> data) {
        String res = "";
        for (int i = 0; i < data.size(); i++) {
            String item = data.get(i);
            res += item.length() + "$" + item;
        }
        return res;
    }

    public static ArrayList<String> decrypt(String data) {
        ArrayList<String> myData = new ArrayList<String>();
        int i = 0;
        while (i < data.length()) {
            int j = i;
            while (j < data.length() && data.charAt(j) != '$') {
                j++;
            }
            int length = Integer.parseInt(data.substring(i, j));
            String str = data.substring(j + 1, j + 1 + length);
            myData.add(str);
            i = j + 1 + length;
        }
        return myData;
    }


}
