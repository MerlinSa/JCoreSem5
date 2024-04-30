package ru.gb.saikalb;

import java.nio.file.*;
import java.io.*;


public class Task5 {
    // Написать функцию, создающую резервную копию всех файлов в директории(без поддиректорий) во вновь созданную папку
    // ./backup
    public static void backupFiles(String dir) throws IOException {
        Path path = Path.of(dir + "/backup");
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        Files.list(Path.of(dir))
                .forEach(entry -> {
                    try {
                        if (Files.isRegularFile(entry)) {
                            Path p = Path.of("./backup/" + entry);
                            if (Files.exists(p)) {
                                Files.delete(p);
                            }
                            Files.copy(entry, p);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    // Предположить, что числа в исходном массиве из 9 элементов имеют диапазон[0, 3], и представляют собой,
    // например, состояния ячеек поля для игры в крестикинолики, где 0 – это пустое поле, 1 – это поле с крестиком,
    // 2 – это поле с ноликом, 3 – резервное значение. Такое предположение позволит хранить в одном числе типа int всё поле 3х3.
    // Записать в файл 9 значений так, чтобы они заняли три байта.

    public static void saveGameState(int[] field) throws IOException {
        FileOutputStream fout = new FileOutputStream("gameState.bin");

        for (int i = 0; i < field.length; i += 3) {
            byte b = 0;
            for (int j = i; j < i + 3; j++) {
                b |= (byte) field[j] & 0x3;
                b <<= 2;
            }
            fout.write(b);
        }

        fout.close();
    }

    public static int[] restoreGameState(String filename) throws IOException {
        FileInputStream fin = new FileInputStream(filename);
        int[] ar = new int[9];
        int n = 0;
        int b;

        while ((b = fin.read()) != -1) {
            int mask = 3 << 6;
            for (int j = 0; j < 3; j++) {

                ar[n++] = (b & mask) >> (6 - j * 2);
                mask >>>= 2;
            }
            // System.out.println(Arrays.toString(ar));
        }

        fin.close();
        return ar;
    }
}
