package org.ipap.scleaner.service;

import org.ipap.scleaner.exception.OutOfGridBoundsException;
import org.ipap.scleaner.model.input.Instructions;
import org.ipap.scleaner.model.output.CleaningResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class CleaningServiceTest {

    CleaningService cleaningService = new CleaningService();


    @ParameterizedTest
    @MethodSource("provideTestData")
    void testCleaningAreas(List<Integer> areaSize, List<Integer> startPosition, List<List<Integer>> oilPatches, String navInstructions, int count, List<Integer> fPos) {

        Instructions instructions = Instructions.builder()
                .areaSize(areaSize)
                .startingPosition(startPosition)
                .oilPatches(oilPatches)
                .navigationInstructions(navInstructions)
                .build();

        CleaningResult cleaningResult = cleaningService.cleanArea(instructions);

        Assertions.assertNotNull(cleaningResult);
        Assertions.assertEquals(count, cleaningResult.getOilPatchesCleaned());
        Assertions.assertEquals(fPos, cleaningResult.getFinalPosition());
    }

    @Test
    void testOutOfBounds() {

        Instructions instructions = Instructions.builder()
                .areaSize(Arrays.asList(5, 5))
                .startingPosition(Arrays.asList(0, 0))
                .oilPatches(List.of(Arrays.asList(0, 1)))
                .navigationInstructions("SEWN")
                .build();

        Assertions.assertThrows(OutOfGridBoundsException.class,
                () -> {
                    cleaningService.cleanArea(instructions);
                });

    }

    private static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of(Arrays.asList(5, 5), Arrays.asList(0, 0), Arrays.asList(Arrays.asList(0, 1), Arrays.asList(0, 2), Arrays.asList(1, 2)), "NNES", 3, Arrays.asList(1, 1)),
                Arguments.of(Arrays.asList(4, 8), Arrays.asList(2, 2), Arrays.asList(Arrays.asList(2, 4), Arrays.asList(0, 2), Arrays.asList(3, 6)), "NNESWSS", 1, Arrays.asList(2, 1))

        );
    }
}