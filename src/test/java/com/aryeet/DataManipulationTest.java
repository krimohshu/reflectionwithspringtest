package com.aryeet;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * The purpose of this exercise is to assess your problem solving ability (not your programming ability).
 * Whilst a working solution is preferred, for candidates not skilled in JAVA, we accept any other form of scripting or pseudo-code
 * to demonstrate the thought process behind the problems.
 * <p>
 * Time guide for completion:  30 minutes
 */
@RunWith(SpringRunner.class)
//@DirtiesContext
@SpringBootTest
@ContextConfiguration(classes = FunctionNameWithAttribute.class)
public class DataManipulationTest {

    @Autowired
    FunctionNameWithAttribute functionNameWithAttribute;

    private static final Logger log = LoggerFactory.getLogger(DataManipulationTest.class);
    private static final List<Integer> ORIGINAL = Arrays.asList(3, 6, 7, 8, 9, 12, 15, 17, 359);

    /**
     * Implement a solution to square each integer in the collection named ORIGINAL
     * and output to the console a comma separated list of the processed values
     *
     * @return console output 9,36,49,64,81,144,225,289,128881
     * @implNote Expected output : 9,36,49,64,81,144,225,289,128881
     * @author Krishan Shukla
     * @since 1.0
     */

    @Tag("commaseperatedlist")
    @DisplayName("java stream with reflection- square each integer in list with comma seperation")
    @Test
    public void exerciseOneFirstApproach() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        functionNameWithAttribute.clean();
        functionNameWithAttribute.setFunctionName("squareOfInteger");
        getCommaSeperatedTransformationUsingStream(functionNameWithAttribute);

    }

    @Tag("commaseperatedlist")
    @DisplayName("java without stream but with reflection- square of each integer in list with comma seperation")
    @Test
    public void exerciseOneSecondApproach() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        functionNameWithAttribute.clean();
        functionNameWithAttribute.setFunctionName("squareOfInteger");
        getCommaSeperatedTransformation(functionNameWithAttribute);

    }



    /**
     * Implement a solution to obtain only the numbers in the collection named ORIGINAL that are divisible by 3
     * and output to the console a comma separated list of the values
     *
     * @implNote Expected output : 3,6,9,12,15
     */

    @Test
    public void exerciseTwoFirstApproach() {
        functionNameWithAttribute.clean();
        functionNameWithAttribute.setFunctionName("numDivByThree");
        getCommaSeperatedTransformationUsingStream(functionNameWithAttribute);
    }

    @Test
    public void exerciseTwoSecondApproach() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        functionNameWithAttribute.clean();
        functionNameWithAttribute.setFunctionName("numDivByThree");
        getCommaSeperatedTransformation(functionNameWithAttribute);
    }


    /**
     * Implement a solution to sum the adjacent integers in the collection named ORIGINAL
     * and output to the console a comma separated list of the values
     *
     * @implNote Expected output : 9,16,21,24,29,36,44,391
     * 3, 6, 7, 8, 9, 12, 15, 17, 359
     */
    /*@Test
    public void exerciseThreeFirstApproach() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        functionNameWithAttribute.setFunctionName("addingAdjacentNumber");
        functionNameWithAttribute.setFunctionAttribute("futureValueInList");
        getCommaSeperatedTransformationUsingStream(functionNameWithAttribute);

    }*/
    @Test
    public void exerciseThreeSecondApproach() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        functionNameWithAttribute.clean();
        functionNameWithAttribute.setFunctionName("addingAdjacentNumber");
        functionNameWithAttribute.setFunctionAttribute("futureValueInList");
        getCommaSeperatedTransformation(functionNameWithAttribute);

    }

    private void getCommaSeperatedTransformationUsingStream(FunctionNameWithAttribute functionName) {
        Instant start = Instant.now();
        AtomicInteger counter = new AtomicInteger(0);
        DataTransformerReflex dataTransformerReflex = new DataTransformerReflex();
        StringBuilder sbCommaSeperatedValues = new StringBuilder();

        ORIGINAL.stream()
                .map(doSquare -> {
                    try {
                        return DataTransformerReflex.class.getDeclaredMethod(functionName.getFunctionName(), Integer.class).invoke(dataTransformerReflex, doSquare);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return false;
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                        return false;
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                        return false;
                    }
                })
                .filter(notNullElement -> notNullElement != null)
                .forEach(soutput -> {
                    sbCommaSeperatedValues.append(soutput);
                    if (counter.incrementAndGet() >= ORIGINAL.size()) return;
                    sbCommaSeperatedValues.append(",");
                });

        sbCommaSeperatedValues.replace(sbCommaSeperatedValues.length() - 1, sbCommaSeperatedValues.length(), "");
        System.out.println(sbCommaSeperatedValues);

        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Time taken in exerciseOneFirstApproach:  " + timeElapsed.toMillis() + " milliseconds" + "for method " + functionName);
    }

    private void getCommaSeperatedTransformation(FunctionNameWithAttribute functionName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Instant start = Instant.now();
        int count = 0;
        DataTransformerReflex dataTransformerReflex = new DataTransformerReflex();
        StringBuilder sbCommaSeperatedValues = new StringBuilder();
        int sizeOfInputNumbers = ORIGINAL.size();
        Object numberFromReflectionMethod = null;
        String finalString = null;

        for (Integer currentVal : ORIGINAL) {
            //Using reflection for reusability
            if ( "futureValueInList".equalsIgnoreCase(functionName.getFunctionAttribute())) {
                if(count >= sizeOfInputNumbers - 1) break;
                numberFromReflectionMethod = DataTransformerReflex.class.getDeclaredMethod(functionName.getFunctionName(), Integer.class, List.class).invoke(dataTransformerReflex, currentVal, ORIGINAL);

            }
            else
                numberFromReflectionMethod = DataTransformerReflex.class.getDeclaredMethod(functionName.getFunctionName(), Integer.class).invoke(dataTransformerReflex, currentVal);
            if (currentVal != null && numberFromReflectionMethod != null) {
                count++;
                if (count <= sizeOfInputNumbers - 1)
                    sbCommaSeperatedValues
                            .append(numberFromReflectionMethod).append(",");
                else
                    sbCommaSeperatedValues
                            .append(numberFromReflectionMethod);
            }
        }
        sbCommaSeperatedValues.replace(sbCommaSeperatedValues.length() - 1, sbCommaSeperatedValues.length(), "");
        //or it can be done
        // finalString =sbCommaSeperatedValues.toString();
        //  System.out.println(finalString.substring(0,finalString.length() -1 ));
        System.out.println(sbCommaSeperatedValues);

        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Time taken in exerciseOneSecondApproach: " + timeElapsed.toMillis() + " milliseconds" + "for method " + functionName);
    }


    @AfterAll
    static void tearDown() {
        log.info("Test Execution finished - Thanks for running it! - Krishan");
    }

}
