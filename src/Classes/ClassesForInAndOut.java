package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ClassesForInAndOut {
    private static String getPut(String str){
        String put = "";
        for(int i=2;i<str.length();i++){
            put+=str.charAt(i);
        }
        return put;
    }
    public static int[] getIntArr(List<String> list){
        String[] listNew = list.get(0).split(" ");
        int stroki = listNew.length;
        int[] arr = new int[stroki];
        for(int i=0;i<stroki;i++){
            arr[i]= Integer.parseInt(listNew[i]);
        }
        return arr;
    }
    public static SimpleLinkedList<Integer> getSimpleLIst(List<String> list){
        String[] listNew = list.get(0).split(" ");
        SimpleLinkedList<Integer> listLink = new SimpleLinkedList<>();
        for(int i = listNew.length - 1; i >= 0; i--){
            listLink.addFirst(Integer.parseInt(listNew[i]));
        }
        return listLink;
    }
//    public static String getString(SimpleLinkedList<Integer> list){
//        String str = " ";
//        for (int i = 0; i < list.size(); i++){
//            str += list.get(i);
//            str += " ";
//        }
//        return str.trim();
//    }
    public static SimpleLinkedList<Integer> readFile(String inputPath) throws IOException {
        Path path = Paths.get(getPut(inputPath)).toAbsolutePath();
        System.out.println("Входной файл: " + path);
        List<String> list = Files.readAllLines(path);
        return getSimpleLIst(list);
    }
    public static void writeFile(String outputPath, String answer) throws FileNotFoundException {
        File file = new File(getPut(outputPath));
        PrintWriter pw = new PrintWriter(file);
        pw.println(answer);
        pw.close();
        System.out.println("Выходной файл: " + Paths.get(getPut(outputPath)).toAbsolutePath());
    }
}
