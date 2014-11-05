package com.thoughtworks.iamcoach.pos.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    public static List readFile(String fileName) {

        String filePath = FileProcessor.class.getClassLoader().getResource(fileName).getPath();
        List<String> data = new ArrayList<String>();
        try {

            data = Files.readAllLines(Paths.get(filePath),
                    Charset.defaultCharset());
        } catch (IOException e) {

            System.out.print("读取文件失败。。。。。。");
        }
        return data;
    }

}
