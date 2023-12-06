package day5;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import utils.utils;

public class day5 {
    public static void main(String[] args) throws FileNotFoundException {
        //solve1();
        solve2();
    }

    private static void solve1() throws FileNotFoundException {
        List<String> lines = utils.readFile("src/day5/test.txt");
        String[] stringSeeds = lines.get(0).split(": ")[1].split(" ");
        long[] seeds = new long[stringSeeds.length];
        for (int i = 0; i < stringSeeds.length; i++) {
            seeds[i] = Long.parseLong(stringSeeds[i]);
        }
        lines.remove(0);

        convertSeed(seeds, lines);
        System.out.println("Nearest location is: " + utils.getMinimum(seeds));

    }

    private static void convertSeed(long[] seeds, List<String> lines) {
        boolean seedConverted = false;

        for (int j = 0; j < seeds.length; j++) {
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                //seed has already been converted this map
                if (seedConverted) {
                    //skip to next map
                    if (Objects.equals(line, "")) {
                        seedConverted = false;
                        i++;
                        continue;
                    }
                    continue;
                }
                //seed wasnt converted in this map and stays the same, skip to next map
                if (Objects.equals(line, "")) {
                    i++;
                    continue;
                }

                long[] nums = utils.getLongNumValues(line);
                if (seeds[j] >= nums[1] && seeds[j] <= nums[1] + nums[2]) {
                    seeds[j] += nums[0] - nums[1];
                    seedConverted = true;
                }
            }
        }
    }

    private static void solve2() throws FileNotFoundException {
        List<String> lines = utils.readFile("src/day5/test.txt");
        String[] stringSeedRanges = lines.get(0).split(": ")[1].split(" ");
        List<Long> seedRanges = new ArrayList<>();

        for (String stringSeedRange : stringSeedRanges) {
            seedRanges.add(Long.parseLong(stringSeedRange));
        }

        lines.remove(0);

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            long[] nums = utils.getLongNumValues(line);
            long startRuleRange = nums[1];
            long endRulesRange = nums[1] + nums[2];

            if (Objects.equals(line, "")) {
                i++;
            }

        }
    }
}
