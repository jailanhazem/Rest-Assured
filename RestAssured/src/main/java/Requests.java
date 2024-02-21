import java.util.Random;

public class Requests {

    public static int getRandomNumber()
    {
        return new Random().nextInt(10)+1;
    }

}
