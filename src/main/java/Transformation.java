import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Transformation {

    public static void main (String[] args){
        ArrayList<String> expressionsList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int expressionLength = 400;
        int expressionNumber = 100;
        while (true) {
            try {
                String s = reader.readLine();
                expressionsList.add(s);
                if (s.equals("") || s.length() > expressionLength || expressionsList.size() > expressionNumber){
                    reader.close();
                    break;
                }
            } catch (IOException e) {
                System.out.println("Exception!");
            }
        }
        for (String anExpressionsList : expressionsList) {
            List<String> expression = ExpressionParser.parse(anExpressionsList);
            boolean flag = ExpressionParser.flag;
            if (flag) {
                for (String x : expression) System.out.print(x);
                System.out.println();
            }
        }
    }
}
