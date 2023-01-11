package kalcul;

import kalcul.exp.DifferentOperandTypes;
import kalcul.exp.IncorrectEnter;
import kalcul.exp.IncorrectOperation;
import kalcul.exp.NegativeRomanResult;

import java.util.Scanner;

public class Katacalculatrice {
    public static void main(String[] arguments) throws Exception, DifferentOperandTypes, IncorrectEnter, NegativeRomanResult, IncorrectOperation {
        System.out.println("La calculatrice est prete a l'emploi. Entrez deux nombres  (de 1 a 10) romains ou arabes " +
                "(mais dans le meme format) par +,-,/,*");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            System.out.println(calc(input));
        }
    }


    public static String calc(String input) throws Exception, DifferentOperandTypes, IncorrectEnter, NegativeRomanResult, IncorrectOperation {
        String[] strings = input.split("\\s*\\W+\\s*");
        String[] operator = input.split("\\s*\\w+\\s*");

        String strA = strings[0];
        String strB = strings[1];

        if(diffOperandTypes(strA, strB)){
            throw new DifferentOperandTypes(); //DifferentOperandTypes
        }

        int a = parseOperand(strA);
        int b = parseOperand(strB);

        if(isValueInvalid(a) | isValueInvalid(b)){
            throw new IncorrectEnter();
        }

        String op = operator[operator.length - 1];
        int result = operation(a, b, op);

        if(RomanConverter.isRoman(strA)){
            if(result < 1){
                throw new NegativeRomanResult();
            }
            return RomanConverter.convertToRoman(result);
        }else{
            return String.valueOf(result);
        }
    }

    public static boolean isValueInvalid(int num){
        return num <= 0 | num > 10;
    }

    public static boolean diffOperandTypes(String a, String b){
        return RomanConverter.isRoman(a) != RomanConverter.isRoman(b);

    }


    private static int parseOperand(String operand) throws IncorrectEnter {
        try {
            return Integer.parseInt(operand);
        } catch (Exception e) {
            int arabian = RomanConverter.convertToArabian(operand);
            if(arabian == -1){
                throw new IncorrectEnter();
            }
            return arabian;
        }
    }


    private static int operation(int a, int b, String op) throws IncorrectOperation {
        if ("*".equals(op)) {
            return a * b;
        } else if ("+".equals(op)) {
            return a + b;
        } else if ("-".equals(op)) {
            return a - b;
        } else if ("/".equals(op)) {
            return a / b;
        } else {
            throw new IncorrectOperation();
        }
    }
}


