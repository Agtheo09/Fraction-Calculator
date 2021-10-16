import java.util.Scanner;

public class Main {
    public static int lcm(int num1, int num2) {
        int lowerNum, higherNum;
        
        if (num1 < num2) {
            lowerNum = num1;
            higherNum = num2;
        } else {
            lowerNum = num2;
            higherNum = num1;
        }

        if (higherNum % lowerNum == 0) {
            return higherNum;
        }

        int i = 2;
        int inHighNum = higherNum;
        boolean found = false;
        while (!found) {
            if (higherNum % lowerNum == 0) {
                found = true;
            } else {
                higherNum = inHighNum * i;
            }
            i++;
        }
        return higherNum;
    }
    static int gcd(int number1, int number2) {
        if (number2 == 0) {
            return number1;
        } else {
        int reminder = number1 % number2;
        return gcd(number2, reminder);
    }
}
    
    static int[] simplifyFraction(int frNum, int frDen) {
        int[] res = new int[2];
        int lcmOfFractionParts = gcd(frNum, frDen);
        int newFrNum = frNum / lcmOfFractionParts;
        int newFrDen = frDen / lcmOfFractionParts;
        res[0] = newFrNum;
        res[1] = newFrDen;
        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(gcd(8, 16));
        int frNum1 = 0, frDen1 = 0, frNum2 = 0, frDen2 = 0, frNum1Simple = 0, frDen1Simple = 0, frNum2Simple = 0, frDen2Simple = 0, extraUnits = 0;
        int resultNum = 0, resultDen = 0;
        char op = '+';
        char userSelection = 'n';
        boolean isBad = false;
        boolean again = true;
        boolean playAgainFirstTime = true;
        boolean isEndQuestionAgain = true;
        Scanner scan = new Scanner(System.in);
        
        
        while(again) {
            playAgainFirstTime = true;
            isEndQuestionAgain = true;
            System.out.print("\033[H\033[2J");  
            System.out.flush();  
            System.out.println("Enter first's num Numerator: ");
            frNum1 = scan.nextInt();
            System.out.println("Enter first's num Denominator: ");
            frDen1 = scan.nextInt();
            if(frDen1 == 0) {
                System.out.println("Error! Denominator can not be 0!");
                break;
            }
            System.out.println("Enter the symbol of mathematical equation: ");
            op = scan.next().charAt(0);
            System.out.println("Enter second's num Numerator: ");
            frNum2 = scan.nextInt();
            System.out.println("Enter second's num Denominator: ");
            frDen2 = scan.nextInt();
            if(frDen2 == 0) {
                System.out.println("Error! Denominator can not be 0!");
            }
            
            frNum1Simple = simplifyFraction(frNum1, frDen1)[0];
            frDen1Simple = simplifyFraction(frNum1, frDen1)[1];
            frNum2Simple = simplifyFraction(frNum2, frDen2)[0];
            frDen2Simple = simplifyFraction(frNum2, frDen2)[1];
            
            if(frNum1Simple < frNum2Simple && op == '-') {
                System.out.println("Error! Second's fraction Numeraror cannot be greater than first's one!");
                break;
            }
            
            switch(op) {
                case '+':
                    if(frDen1Simple == frDen2Simple) {
                        resultDen = frDen1Simple;
                        resultNum = frNum1Simple + frNum2Simple;
                    } else {
                        int lcmOfDen = lcm(frDen1Simple, frDen2Simple);
                        int aKapelaki = lcmOfDen / frDen1Simple;
                        int bKapelaki = lcmOfDen / frDen2Simple;
                        
                        frNum1Simple = aKapelaki * frNum1Simple;
                        frDen1Simple = aKapelaki * frDen1Simple;
                        frNum2Simple = bKapelaki * frNum2Simple;
                        frDen2Simple = bKapelaki * frDen2Simple;
                        
                        resultDen = frDen1Simple;
                        resultNum = frNum1Simple + frNum2Simple;
                    }
                    break;
                case '-':
                    if(frDen1Simple == frDen2Simple) {
                        resultDen = frDen1Simple;
                        resultNum = frNum1Simple - frNum2Simple;
                    } else {
                        int lcmOfDen = lcm(frDen1Simple, frDen2Simple);
                        int aKapelaki = lcmOfDen / frDen1Simple;
                        int bKapelaki = lcmOfDen / frDen2Simple;
                        
                        frNum1Simple = aKapelaki * frNum1Simple;
                        frDen1Simple = aKapelaki * frDen1Simple;
                        frNum2Simple = bKapelaki * frNum2Simple;
                        frDen2Simple = bKapelaki * frDen2Simple;
                        
                        resultDen = frDen1Simple;
                        resultNum = frNum1Simple - frNum2Simple;
                    }
                    break;
                case '*':
                    resultDen = frDen1Simple * frDen2Simple;
                    resultNum = frNum1Simple * frNum2Simple;
                    break;
                case '/':
                    resultDen = frDen1Simple * frNum2Simple;
                    resultNum = frNum1Simple * frDen2Simple;
                    break;
                default: 
                    System.out.println("No matching operator!!!");
                    isBad = true;
            }
            
            if(!isBad) {
                int[] newResFracNum = simplifyFraction(resultNum, resultDen);
                System.out.println("The solution is: ");
                System.out.print(frNum1 + "/" + frDen1 + " " + op + " " + frNum2 + "/" + frDen2);
                if(frDen1 != frDen1Simple || frDen2 != frDen2Simple) {
                    System.out.print(" = " + frNum1Simple + "/" + frDen1Simple + " " + op + " " + frNum2Simple + "/" + frDen2Simple);
                }
                
                System.out.print(" = " + resultNum + "/" + resultDen);
                
                if(resultNum != newResFracNum[0] || resultDen != newResFracNum[1]) {
                    System.out.print(" = "  + newResFracNum[0] + "/" + newResFracNum[1] + "");
                }
                
                if(newResFracNum[0] == newResFracNum[1]) {
                    System.out.print(" = 1");
                }
                
                if(newResFracNum[0] > newResFracNum[1]) {
                    int wholes = newResFracNum[0] / newResFracNum[1];
                    int wholesNumerator = newResFracNum[0] % newResFracNum[1];
                    if(wholesNumerator != 0) {
                        System.out.print(" = " + wholes + " " + wholesNumerator + "/" + newResFracNum[1]);
                    } else {
                        System.out.print(" = " + wholes);
                    }
                }
            }
            
            while(isEndQuestionAgain) {
                if(!playAgainFirstTime) {
                    System.out.println("Wrong Selection!");
                }
                System.out.println(" ");
                System.out.println("Do you want to make an other operation? Type y or n: ");
                userSelection = scan.next().charAt(0);
                if(userSelection == 'y' || userSelection == 'n') {
                    isEndQuestionAgain = false;
                    break;
                }
                playAgainFirstTime = false;
            }
            
            if(userSelection == 'n') {
                again = false;
            }
        }
        
        scan.close();
    }
}


