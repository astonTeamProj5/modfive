import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import java.util.Optional;

public class BusFileService {

    public List<Bus> readBusesFromFile(String fileName) throws IOException {

        try (Stream<String> lines = Files.lines(Path.of(fileName))) {

            return lines
                    .map(this::parseBus)
                    .flatMap(Optional::stream)
                    .toList();
        }
    }

    private Optional<Bus> parseBus(String line) {

        String[] parts = line.split(";");

        if (parts.length != 3) {
            return Optional.empty();
        }

        try {
            int number = Integer.parseInt(parts[0]);
            String model = parts[1];
            int mileage = Integer.parseInt(parts[2]);

            if (number < 0 || mileage < 0)
            {
                return Optional.empty();
            }

            return Optional.of(new Bus(number, model, mileage));

        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public void writeBusesToFile(List<Bus> buses, String fileName) throws IOException {

        List<String> lines = buses.stream()
                .map(bus -> bus.getNumber() + ";" + bus.getModel() + ";" + bus.getMileage())
                .toList();

        Files.write(Path.of(fileName), lines);
    }

}
