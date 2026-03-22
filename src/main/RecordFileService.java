package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import java.util.Optional;

public class RecordFileService {

    public List<Record> readRecordFromFile(String fileName) throws IOException {

        try (Stream<String> lines = Files.lines(Path.of(fileName))) {

            return lines
                    .map(this::parseRecord)
                    .flatMap(Optional::stream)
                    .toList();
        }
    }

    private Optional<Record> parseRecord(String line) {

        String[] parts = line.split(";");

        if (parts.length != 3) {
            return Optional.empty();
        }

        try {
            String field1 = parts[0];
            int field2 = Integer.parseInt(parts[1]);
            double field3 = Double.parseDouble(parts[2]);

            return Optional.of(new Record.Builder().field1(field1).field2(field2).field3(field3).build());

        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public void writeRecordsToFile(List<Record> records, String fileName) throws IOException {

        List<String> lines = records.stream()
                .map(bus -> bus.getField1() + ";" + bus.getField2() + ";" + bus.getField3())
                .toList();

        Files.write(Path.of(fileName), lines);
    }

}
