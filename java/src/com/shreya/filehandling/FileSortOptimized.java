import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.UUID;

import javax.management.RuntimeErrorException;

// using external sort
public class FileSortOptimized {
    public void sort(File input, File output) throws IOException {
        long fileSize = input.length();
        long freeRam = Runtime.getRuntime().freeMemory() / 2;

        int blockSize = (int)fileSize;

        System.out.println("File size: " + fileSize);
        System.out.println("Half of RAM: " + freeRam);

        // freeRam = 100;

        if (freeRam < fileSize) {
            // external sort
            // merge sort + quick sort
            int fileCount = (int)(fileSize / freeRam) + 1;

            if (fileCount > 1024) throw new RuntimeErrorException(null, "Please increase RAM!");

            blockSize = (int)freeRam;

            List<File> tempFiles = createSortedTempFiles(input, blockSize);
        
            mergeFiles(tempFiles, output);

            // for(File tempFile: tempFiles) {
            //     tempFile.delete();
            // }
        }
    }

    private void mergeFiles(List<File> tempFiles, File output) throws IOException {
        PriorityQueue<CustomBufferReader> pq = new PriorityQueue<>();
        for (File tempFile: tempFiles) {
            pq.add(new CustomBufferReader(tempFile));
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(output))) { 
            while (!pq.isEmpty()) {
                CustomBufferReader cbr = pq.poll();
                bw.write(cbr.pop());
                bw.newLine();

                if (cbr.peek() != null) {
                    pq.add(cbr);
                }
            }
        }
    }

    private List<File> createSortedTempFiles(File input, int maxBlockAllowed) throws IOException {
        List<File> tempFiles = new ArrayList<>();
        List<String> data = new ArrayList<>();

        int currentBlockSize = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String s = br.readLine();

            while (s != null) {
                data.add(s);
                currentBlockSize += s.length();

                if (currentBlockSize > maxBlockAllowed) {
                    Collections.sort(data);

                    File f = new File(UUID.randomUUID() + ".txt");
                    
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) { 
                        for (String d: data) {
                            bw.write(d);
                            bw.newLine();
                        }
                    }

                    data.clear();
                    currentBlockSize = 0;
                    tempFiles.add(f);
                }

                s = br.readLine();
            }

            if (!data.isEmpty()) {
                Collections.sort(data);

                File f = new File(UUID.randomUUID() + ".txt");
                
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) { 
                    for (String d: data) {
                        bw.write(d);
                        bw.newLine();
                    }
                }

                data.clear();
                currentBlockSize = 0;
                tempFiles.add(f);
            }
        }

        return tempFiles;
    }
}
