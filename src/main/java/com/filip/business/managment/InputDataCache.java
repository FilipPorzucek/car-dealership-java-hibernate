package com.filip.business.managment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class InputDataCache {

        private static final String FILE_PATH="./src/main/resources/car-dealership-traffic-simulation.md";

        private static final Map<String, List<String>> inputData;

        static {
            try {
            inputData=redFileContent();
            }catch (Throwable ex){
                throw new ExceptionInInitializerError(ex);
            }
        }

    private static Map<String, List<String>> redFileContent() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILE_PATH)).stream()
                .filter(line -> !line.startsWith("[//]: #"))
                .filter(line -> !line.isBlank())
                .toList();

      return   lines.stream()
                .collect(Collectors.groupingBy(
                        line->line.split("->")[0].trim(),
                        Collectors.mapping(
                                line->line.substring(line.indexOf("->")+2).trim(),
                                Collectors.toList()
                        )
                ));
    }

   public static <T> List<T> getInputData(
           final Keys.InputDataGroup inputDataGroup,
           final Keys.Entity entity,
           final Function<String,T>mapper
           ){
            return Optional.ofNullable(inputData.get(inputDataGroup.toString()))
                    .orElse(List.of())
                    .stream()
                    .filter(line->line.startsWith(entity.toString()))
                    .map(mapper)
                    .toList();
   }

    public static <T> List<T> getInputData(
            final Keys.InputDataGroup inputDataGroup,
            final Function<String,T>mapper
    ){
        return Optional.ofNullable(inputData.get(inputDataGroup.toString()))
                .orElse(List.of())
                .stream()
                .map(mapper)
                .toList();
    }
}
