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

public class FileSort {
    public void sort(File input, File output) throws IOException {
        List<String> data = new ArrayList<>();

        // Buffer is faster than hard disk
        // BufferedReader br;
        
        // // GOOD practice
        // try {
        //     br = new BufferedReader(new FileReader(input));

        //     String s = br.readLine();

        //     while (s != null) {
        //         data.add(s);
        //         s = br.readLine();
        //     }
        // }
        // catch(IOException e) {
        //     System.out.println(e.getMessage());
        //     throw e;
        // }
        // finally {
        //     br.close();
        // }

        // -------------- OR -------------
        // try-resource block
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String s = br.readLine();

            while (s != null) {
                data.add(s);
                s = br.readLine();
            }
        }

        // System.out.println(br.ready()); // false
        // br.close();

        Collections.sort(data);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(output))) { 
            for (String d: data) {
                bw.write(d);
                bw.newLine();
            }
        }

        // bw.flush();
        // bw.close(); // automatically flushes
    }
}
