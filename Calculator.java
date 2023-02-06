import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String userInput;
        InfixToPostfixGenerator converter = new InfixToPostfixGenerator();


        System.out.println("Input valid postfix expressions for evaluation. Hit ENTER only to quit.");
        userInput=input.nextLine();
        try {
            String postfix =converter.convert(userInput);

        }catch(Exception e){
            System.out.println(e.getMessage());        }
    }
}
