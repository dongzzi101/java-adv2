package io.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class OldFileMain {

    public static void main(String[] args) throws IOException {
        File file = new File("temp/example.txt");
        File directory = new File("temp/exampleDir");

        System.out.println("File exists: " + file.exists());
        boolean created = file.createNewFile();
        System.out.println("created : " + created);

        boolean dirCreated = directory.mkdir();
        System.out.println("dirCreated : " + dirCreated);

//        boolean delete = file.delete();
//        System.out.println("file delete : " + delete);

        System.out.println("is file: " + file.isFile());
        System.out.println("is directory: " + directory.isDirectory());

        System.out.println("file name: " + file.getName());
        System.out.println("file size: " + file.length());;

        File newFile = new File("temp/newExample.txt");
        boolean rename = file.renameTo(newFile);
        System.out.println("rename : " + rename);

        long lastModified = newFile.lastModified();
        System.out.println("lastModified = " + new Date(lastModified));
    }
}
