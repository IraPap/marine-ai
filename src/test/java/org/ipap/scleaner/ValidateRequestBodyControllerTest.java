package org.ipap.scleaner;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.stream.Stream;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = Controller.class)
class ValidateRequestBodyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final MockHttpServletRequestBuilder REQUEST_BUILDER =
            request(HttpMethod.POST, "/instructions")
                    .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                    .accept(APPLICATION_JSON);

    @ParameterizedTest
    @MethodSource("provideRequests")
    void whenInputIsInvalid_thenReturnsStatus400(String request) throws Exception {


        MvcResult result = mockMvc.perform(REQUEST_BUILDER.content(request))
                .andExpect(status().isBadRequest())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    private static Stream<Arguments> provideRequests() {
        return Stream.of(
                Arguments.of("{\"areaSize\": [], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": null, \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [0, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5, 5, 6], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": [], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": null, \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": [1], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": [1, -1], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": [1, 2, 4], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": [5, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": [1, 6], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0, 4], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, -3], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [5, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 6]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 2]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": null, \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \" NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNEseESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWBHG\"}"),
                Arguments.of("{\"areaSize\": [05, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"\"}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": null}"),
                Arguments.of("{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNNNNNNNNNN\"}")
        );
    }
}
