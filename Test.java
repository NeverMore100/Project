import java.util.*;

public class Test extends Question{
    private String[] options = new String[4];
    private int numOfoptions;
    ArrayList<Integer> labels=new ArrayList<>(4);
    public void Test(){}
    public void setOptions(String[] options) {
        ArrayList<String> options2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            options2.add(options[i]);
        }
        Collections.shuffle(options2);
        for (int i = 0; i < 4; i++) {
           this.options[i]=options2.get(i);
        }
    }

    public String getOptionAt(int index) {
        return options[index];
    }

    @Override
    public String toString() {
       String table =getDescription() + "\n";
       for(int i =0;i<4;i++){
         table += (char)('A' + i) + ") " + getOptionAt(i)+"\n";
     }
     return table;
    }
}