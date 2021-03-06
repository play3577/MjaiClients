package org.ymatsux.mjai.client;

import static org.ymatsux.mjai.client.CommonConsts.NUM_MENTSU_ID;

public class MentsuUtil {

    private static final int MANZU_START = 0;
    private static final int PINZU_START = 9;
    private static final int SOZU_START = 18;

    private static final int MANZU_SHUNTSU_START = 0;
    private static final int PINZU_SHUNTSU_START = 7;
    private static final int SOZU_SHUNTSU_START = 14;
    private static final int KOTSU_START = 21;
    private static final int[][] MENTSUS;

    static {
        MENTSUS = new int[NUM_MENTSU_ID][3];
        for (int i = MANZU_SHUNTSU_START; i < PINZU_SHUNTSU_START; i++) {
            MENTSUS[i][0] = i - MANZU_SHUNTSU_START + MANZU_START;
            MENTSUS[i][1] = i - MANZU_SHUNTSU_START + MANZU_START + 1;
            MENTSUS[i][2] = i - MANZU_SHUNTSU_START + MANZU_START + 2;
        }
        for (int i = PINZU_SHUNTSU_START; i < SOZU_SHUNTSU_START; i++) {
            MENTSUS[i][0] = i - PINZU_SHUNTSU_START + PINZU_START;
            MENTSUS[i][1] = i - PINZU_SHUNTSU_START + PINZU_START + 1;
            MENTSUS[i][2] = i - PINZU_SHUNTSU_START + PINZU_START + 2;
        }
        for (int i = SOZU_SHUNTSU_START; i < KOTSU_START; i++) {
            MENTSUS[i][0] = i - SOZU_SHUNTSU_START + SOZU_START;
            MENTSUS[i][1] = i - SOZU_SHUNTSU_START + SOZU_START + 1;
            MENTSUS[i][2] = i - SOZU_SHUNTSU_START + SOZU_START + 2;
        }
        for (int i = KOTSU_START; i < NUM_MENTSU_ID; i++) {
            MENTSUS[i][0] = MENTSUS[i][1] = MENTSUS[i][2] = i - KOTSU_START;
        }
    }

    public static void addMentsu(int[] countVector, int mentsuIndex) {
        countVector[MENTSUS[mentsuIndex][0]]++;
        countVector[MENTSUS[mentsuIndex][1]]++;
        countVector[MENTSUS[mentsuIndex][2]]++;
    }

    public static void removeMentsu(int[] countVector, int mentsuIndex) {
        countVector[MENTSUS[mentsuIndex][0]]--;
        countVector[MENTSUS[mentsuIndex][1]]--;
        countVector[MENTSUS[mentsuIndex][2]]--;
    }

    public static int[] getMentsu(int mentsuIndex) {
        return MENTSUS[mentsuIndex];
    }

    public static boolean isYaochuhai(int mentsuId) {
        return mentsuId == 21 || mentsuId == 29 ||
                mentsuId == 30 || mentsuId == 38 ||
                mentsuId == 39 || mentsuId == 47 ||
                (48 <= mentsuId && mentsuId < 55);
    }

    public static boolean isSangenpai(int mentsuId) {
        return 52 <= mentsuId && mentsuId < 55;
    }

    public static boolean hasYaochuhai(int mentsuId) {
        return mentsuId == 0 || mentsuId == 6 ||
                mentsuId == 7 || mentsuId == 13 ||
                mentsuId == 14 || mentsuId == 20 ||
                isYaochuhai(mentsuId);
    }

    private MentsuUtil() {
    }
}
