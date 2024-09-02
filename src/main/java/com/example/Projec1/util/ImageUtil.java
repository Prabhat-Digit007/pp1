package com.example.Projec1.util;

//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//
//public class ImageUtil {
//
//    public static byte[] convertImageToByteArray(String imagePath) throws IOException {
//        File imageFile = new File(imagePath);
//        return Files.readAllBytes(imageFile.toPath());
//
//    }
//
//    public static void main(String[] args) {
//        try {
//            // Path to your image file
//            String imagePath = "src/main/java/com/example/Projec1/util/Screenshot (1).png";
//            byte[] imageBytes = convertImageToByteArray(imagePath);
//            System.out.println("Product saved successfully with image!");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ImageUtil {

    public static byte[] convertImageToByteArray(String imagePath) throws IOException {
        File imageFile = new File(imagePath);
        return Files.readAllBytes(imageFile.toPath());
    }

    public static void main(String[] args) {
        try {
            // Path to your image file
            String imagePath = "src/main/java/com/example/Projec1/util/Screenshot (1).png";
            byte[] imageBytes = convertImageToByteArray(imagePath);
            System.out.println("Image converted to byte array successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
