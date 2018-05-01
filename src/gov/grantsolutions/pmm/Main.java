package gov.grantsolutions.pmm;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {

    private int[] stories;
    private int[] storyPoints;
    private int[] fibonacci = {1, 2, 3, 5, 8, 13, 21, 34, 55};
    private int lowEnd = 2;
    private int range = 5;
    private PrintStream log;

    private static Random random = new Random(System.currentTimeMillis());
    private IntStream randomInts;

    private Main() {
        log = System.out;
    }

    public static void main(String[] args) {
        Main app = new Main();
        if (args.length>0) {
            app.init(args[0]);
            app.generate();
            app.consoleLog();
        } else {
            app.log("usage: parameter is comma separated list of user story numbers");
        }
    }

    private void generate() {
        PrimitiveIterator.OfInt iter = randomInts.iterator();
        for (int i=0;i<storyPoints.length;i++)
            storyPoints[i] = fibonacci[iter.nextInt()];
    }

    private void consoleLog() {
        log("\tUser Story\tPoints");
        log("\t==========\t======");
        String line;
        for (int i=0;i<stories.length;i++){
            line = String.format("\tPMM-%05d\t% 4d",stories[i],storyPoints[i]);
            log(line);
        }
    }

    private void log(final String line) {
        log.println(line);
    }

    private void init(String arg) {
        String[] storiesStr = arg.split(",");
        stories = new int[storiesStr.length];
        storyPoints = new int[stories.length];
        for (int i=0; i<storiesStr.length; i++) {
            stories[i] = Integer.parseInt(storiesStr[i]);
        }
        int streamSize = stories.length; // the number of values to generate
        int randomNumberOrigin= 2; // the origin (inclusive) of each random value
        int randomNumberBound = 7; //the bound (exclusive) of each random value
        randomInts = random.ints(streamSize, randomNumberOrigin, randomNumberBound);
    }
}
