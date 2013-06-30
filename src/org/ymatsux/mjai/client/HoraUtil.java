package org.ymatsux.mjai.client;

import java.util.Arrays;
import java.util.List;

public class HoraUtil {

    private static final int NUM_HAI = 34;
    private static final int NUM_MENTSU = 55;

    public static boolean isHora(List<Hai> hais) {
        int[] countVector = new int[NUM_HAI];
        for (Hai hai : hais) {
            countVector[hai.getIndex()]++;
        }
        if (isChitoitsu(countVector)) {
            return true;
        }
        // TODO: Handle Kokushimuso.
        return isHoraInternal(countVector, new int[NUM_HAI], 4, 0);
    }

    private static boolean isHoraInternal(
            int[] currentVector, int[] targetVector, int leftMentsu,
            int minMentsuIndex) {
        if (leftMentsu == 0) {
            // Add janto
            for (int haiIndex = 0; haiIndex < NUM_HAI; haiIndex++) {
                targetVector[haiIndex] += 2;
                if (Arrays.equals(currentVector, targetVector)) {
                    return true;
                }
                targetVector[haiIndex] -= 2;
            }
            return false;
        }

        for (int mentsuIndex = minMentsuIndex; mentsuIndex < NUM_MENTSU; mentsuIndex++) {
            MentsuUtil.addMentsu(targetVector, mentsuIndex);
            boolean isValid = true;
            for (int haiIndex = 0; haiIndex < NUM_HAI; haiIndex++) {
                if (currentVector[haiIndex] < targetVector[haiIndex]) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                boolean isHora = isHoraInternal(
                        currentVector, targetVector, leftMentsu - 1, mentsuIndex);
                if (isHora) {
                    return true;
                }
            }
            MentsuUtil.removeMentsu(targetVector, mentsuIndex);
        }
        return false;
    }

    private static boolean isChitoitsu(int[] countVector) {
        for (int haiIndex = 0; haiIndex < NUM_HAI; haiIndex++) {
            if (countVector[haiIndex] != 0 && countVector[haiIndex] != 2) {
                return false;
            }
        }
        return true;
    }

    private HoraUtil() {
    }
}