package main;

import java.util.List;
import java.util.Scanner;

public interface TextStrategy {
    List<Record> fill(int length, Scanner scanner);
}
