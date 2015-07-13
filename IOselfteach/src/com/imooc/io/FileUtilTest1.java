package com.imooc.io;

import java.io.File;
import java.io.IOException;

/**
 * Created by William on 2015/5/21.
 */
public class FileUtilTest1 {

    public static void main(String[] args) throws IOException {
        FileUtils.listDirectory(new File("G://_Algorithm_Sedgewick"));
    }
}
