import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class Test {
    public static void main(String[] args) throws IOException {
        Random r = new Random();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./input.txt"))) {
            for (int i = 0; i < 100; i++) {
                String d = UUID.randomUUID().toString().substring(0, r.nextInt(10) + 1);
                bw.write(d);
                bw.newLine();
            }
        }
        
        FileSortOptimized fs = new FileSortOptimized();

        fs.sort(new File("./input.txt"), new File("./output.txt"));
    
        // check(input, output);
    }   

    private void check(File input, File output) {

    }
}
