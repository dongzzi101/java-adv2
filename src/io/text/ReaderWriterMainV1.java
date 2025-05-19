package io.text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV1 {

    public static void main(String[] args) throws IOException {
        String writeString = "ABC";

        // 문자 -> byte
        byte[] writeByte = writeString.getBytes(UTF_8);
        System.out.println("write string = " + writeString);
        System.out.println("write byte = " + Arrays.toString(writeByte));

        // 파일에 쓰기
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        fos.write(writeByte);
        fos.close();
        
        // 파일에서 읽기
        FileInputStream fis = new FileInputStream(FILE_NAME);
        byte[] readBytes = fis.readAllBytes();
        fis.close();

        // byte -> String utf-8 decoding
        String readString = new String(readBytes, UTF_8);
        System.out.println("read byte : " + Arrays.toString(readBytes));
        System.out.println("read String : " + readString);


    }

}
