import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CustomBufferReader implements Comparable<CustomBufferReader> {
    private String data;
    private BufferedReader br;
    private boolean hasData;
    
    public CustomBufferReader(File f) throws IOException {
        br = new BufferedReader(new FileReader(f));
        reload();
    }

    private void reload() throws IOException {
        data = br.readLine();

        if (data == null) {
            br.close();
            hasData = false;
        }
        else {
            hasData = true;
        }
    }

    public String peek() {
        return data;
    }

    public String pop() throws IOException {
        if (!hasData) return null;
        
        String t = data;
        reload();

        return t;
    }

    @Override
    public int compareTo(CustomBufferReader o) {
        return this.data.compareTo(o.data);
    }
}
