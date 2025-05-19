package io.buffered;

import java.io.FileOutputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.*;

public class CreateFileV2 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);

        long startTime = System.currentTimeMillis();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bufferIndex = 0;

        for (int i = 0; i < FILE_SIZE; i++) {
            buffer[bufferIndex] = 1;
            bufferIndex++;

            // 버퍼가 가득 차면 쓰고, 버퍼 비워줌
            if (bufferIndex == BUFFER_SIZE) {
                fos.write(buffer);
                bufferIndex = 0;
            }
        }

        // 끝 부분 버퍼가 가득차지 않고 남아있다면?
        if (bufferIndex > 0) {
            fos.write(buffer, 0, bufferIndex);
        }

        fos.close();

        long endTime = System.currentTimeMillis();

        System.out.println("File created : " + FILE_NAME);
        System.out.println("File size : " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken : " + (endTime - startTime) + "ms");

    }
}
