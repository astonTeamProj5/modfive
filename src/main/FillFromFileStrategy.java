package main;

import java.io.*;
import java.util.*;

public class FillFromFileStrategy implements TextStrategy {
    @Override
    public List<Record> fill(int length, Scanner scanner) {
        System.out.print("Enter file path: ");
        if(!scanner.hasNext()){
            System.out.println("No input provided");
            return new CustomList<>();
        }

        String path = scanner.nextLine();
        path = path.replace("\"","");//Для кнопки "копировать как путь" винды
        System.out.println();//переносим строку для красивого вывода

        return RecordFileService.readRecordFromFile(path);
    }
}
