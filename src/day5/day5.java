package day5;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import utils.utils;

public class day5 {
    public static void main(String[] args) throws FileNotFoundException {
        solve1();
        solve2();
    }

    private static void solve1() throws FileNotFoundException {
        List<String> lines = utils.readFile("src/day5/test.txt");
        String[] stringSeeds = lines.get(0).split(": ")[1].split(" ");
        long[] seeds = new long[stringSeeds.length];
        for (int i = 0; i < stringSeeds.length; i++) {
            seeds[i] = Long.valueOf(stringSeeds[i]);
        }
        lines.remove(0);

        convertSeed(seeds, lines);
        System.out.println("Nearest location is: "+utils.getMinimum(seeds));
        
    } 
    
    private static void convertSeed(long[] seeds, List<String> lines) {
        boolean seedConverted = false;

        for (int j = 0; j < seeds.length; j++) {
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                //seed has already been converted this map
                if(seedConverted){
                    //skip to next map
                    if (line == "") {
                        seedConverted = false;
                        i++;
                        continue;
                    }
                    continue;
                }
                //seed wasnt converted in this map and stays the same, skip to next map
                if(line == ""){
                    i++;
                    continue;
                }

                long[] nums = utils.getNumValues(line);
                if (seeds[j] >= nums[1] && seeds[j] <= nums[1] + nums[2]) {
                    seeds[j] += nums[0] - nums[1];
                    seedConverted = true;
                }
            }
        }
    }

    private static void solve2() throws FileNotFoundException {
        List<String> lines = utils.readFile("src/day5/input5.txt");
        String[] stringSeedRanges = lines.get(0).split(": ")[1].split(" ");
        long[] seedRanges = new long[stringSeedRanges.length];

        for (int i = 0; i < stringSeedRanges.length; i++) {
            seedRanges[i] = Long.valueOf(stringSeedRanges[i]);
        }


        


        /* 
        List<Long> seedsList = new ArrayList<>();
        for (int i = 0; i < seedRanges.length; i++) {
            Long start = seedRanges[i];
            Long range = seedRanges[++i];
            for (Long j = start; j <= start + range ; j++) {
                seedsList.add(j);
            }
        }

        //so there is no need to rewrite convert seeds
        long[] seeds = new long[seedsList.size()];
        for (int i = 0; i < seedsList.size(); i++) {
            seeds[i] = seedsList.get(i);
        }*/

        lines.remove(0);

        convertSeed(seeds, lines);
        System.out.println("Nearest location from ranges is: "+utils.getMinimum(seeds));
        
    }
}
