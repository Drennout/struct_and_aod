package practice12;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class Handler {

    public static void remove(ArrayList<PhonePerson> persons) {
        try {
            FileWriter file = new FileWriter("List.txt");

            int i = 1;
            for (PhonePerson iter : persons) {
                file.write(iter.getName() + " " + iter.getAdress() + " " + iter.getPhone() + "\n");
            }

            file.close();
        }catch (IOException e){
            System.out.println("File isn't find");
        }
    }

    public static void writer(ArrayList<PhonePerson> persons){
        try {
            FileWriter file = new FileWriter("List.txt", true);

            int i = 1;
            for (PhonePerson iter : persons) {
                file.write(iter.getName() + " " + iter.getAdress() + " " + iter.getPhone() + "\n");
            }

            file.close();
        }catch (IOException e){
            System.out.println("File isn't find");
        }

        System.out.println("enter code for add new persons in CodePerson.txt");

        handlerWriteAtCode(new Scanner(System.in).next());
        handlerRemoveAtFirstNum(new Scanner(System.in).next());
    }


    private static void handlerWriteAtCode(String numCode){
        try(FileReader reader = new FileReader("List.txt")) {
            Scanner scan = new Scanner(reader);

            while(scan.hasNextLine()){
                String data = scan.nextLine();

//              parsing code number
                try(FileWriter atCode = new FileWriter("CodePerson.txt", true)){

                    if (data.substring(data.indexOf("+")).substring(3, 6).equals(numCode)){
                        atCode.write(data + "\n");
                    }

                    atCode.close();
                }catch (IOException e){
                    System.out.println("Error in Handler.handlerWriteAtCode, parsing code number part, file not founded");
                }

//                remove data about person at first number of phone
            }
            reader.close();
        }
        catch(IOException ex){
            System.out.println("Error in Handler.handlerWriteAtCode - file-reader not founded");
        }
    }

    private static void handlerRemoveAtFirstNum(String num){
        ArrayList<String> data = new ArrayList<>();

        try(FileReader reader = new FileReader("List.txt")) {
            Scanner scan = new Scanner(reader);

            while(scan.hasNextLine()){
                String _data = scan.nextLine();

                if (!_data.substring(_data.indexOf("+")).substring(1, 2).equals(num)){
                    data.add(_data);
                }
            }

            reader.close();
        }catch (IOException e){
            System.out.println("Error in Handler.handlerRemoveFirstNum - file-reader not founded");
        }

        try{
            File file = new File("List.txt");
            if(file.exists()){
                file.delete();
                file.createNewFile();
            }
            FileWriter writer = new FileWriter("List.txt", true);

            for (String iter : data){
                writer.write(iter + "\n");
            }

            writer.close();
        }catch (IOException e){
            System.out.println("Error in Handler.handlerRemoveFirstNum - file-writer not founded");
        }
    }
}
