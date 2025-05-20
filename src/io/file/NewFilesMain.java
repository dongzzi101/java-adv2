package io.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class NewFilesMain {

    public static void main(String[] args) throws IOException {
        Path file = Path.of("temp/example.txt");
        Path directory = Path.of("temp/exampleDir");

        System.out.println("File exist : " + Files.exists(file));

        try {
            Files.createFile(file);
            System.out.println("created file");
        } catch (FileAlreadyExistsException e) {
            System.out.println(file + "file already exists");
        }

        try {
            Files.createDirectories(directory);
            System.out.println("directories created");
        } catch (FileAlreadyExistsException e) {
            System.out.println("Directory already exists");
        }

//        Files.delete(file);
//        System.out.println("File deleted");

        System.out.println("is regular file: " + Files.isRegularFile(file));
        System.out.println("is directory: " + Files.isDirectory(directory));
        System.out.println("File name: " + file.getFileName());
        System.out.println("File size: " + Files.size(file));

        Path newFile = Path.of("temp/newExample.txt");
        Files.move(file, newFile, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File moved");

        System.out.println("Last modified: " + Files.getLastModifiedTime(newFile));

        BasicFileAttributes attrs = Files.readAttributes(newFile, BasicFileAttributes.class);
        System.out.println("== attributes ==");
        System.out.println("creation time: " + attrs.creationTime());
        System.out.println("is directory: " + attrs.isDirectory());
        System.out.println("is regular file: " + attrs.isRegularFile());
        System.out.println("is a directory: " + attrs.isDirectory());

    }
}
