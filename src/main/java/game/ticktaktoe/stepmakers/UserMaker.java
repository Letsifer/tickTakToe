package game.ticktaktoe.stepmakers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author Евгений
 */
public class UserMaker implements StepMaker {

    private static UserMaker maker;
    public static UserMaker getInstance() {
        if (maker == null) {
            maker = new UserMaker();
        }
        return maker;
    }
    private UserMaker(){}    

    @Override
    public int getX() throws Exception{
        return nextInt();
    }

    @Override
    public int getY() throws Exception{
        return nextInt();
    }    
    
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st = new StringTokenizer("");
    private static final String pattern = "[0-9]+";

    private Integer nextInt() throws Exception {
        while (!st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine());
        }
        String token = st.nextToken();
        if (token.matches(pattern)) {
            return Integer.parseInt(token);
        }
        throw new IllegalArgumentException(token + " is not int");
    }

}
