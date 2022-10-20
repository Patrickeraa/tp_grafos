import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map;

public class Grafo {
    int n;
    int prev[];
    Map<Integer, List<Aresta>> grafo;
    public Grafo(){
        this.grafo = new HashMap<>();
    }
    public void criaGrafo(){

        String property = System.getProperty("user.dir");
        File f = new File(property+"/resources/grafo.txt");
        try (Scanner s = new Scanner(f)) {
            n = s.nextInt();
            int u,v;
            float p;
            while(s.hasNext()){
                String[] split = s.nextLine().split(" ");
//                u = Integer.parseInt(split[0]);
//                v = Integer.parseInt(split[1]);
//                p = Float.parseFloat(split[2]);
//                System.out.println(String.format("%d %d %.f",u,v,p));
                System.out.println(split[1]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
