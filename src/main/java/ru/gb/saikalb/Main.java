package ru.gb.saikalb;
import java.io.IOException;
import java.util.Arrays;

import static ru.gb.saikalb.Task5.*;

public class Main {
    public static void main(String[] args) throws IOException {
        backupFiles(".");

        int[] ar = {1, 2, 3, 0, 1, 2, 0, 3, 0};
        saveGameState(ar);

        int[] ar2 = restoreGameState("gameState.bin");
        System.out.println(Arrays.toString(ar2));
    }
}
