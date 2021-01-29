public class LinkedStackTest
{
    public static void main(String[] args)
    {
        String infix = "a * b / (c-a) + d * e";
        infix = removeSpaces(infix);
        System.out.println(convertToPostFix(infix));
    }

    /** converts a infix expression to a postfix expression
        @param infix is a infix expression we are trying to convert to a postfix
        @return a postfix expression converted from infix expression
     */
    public static String convertToPostFix(String infix)
    {
        LinkedStack<Character> operatorStack = new LinkedStack<>();
        String postfix = "";
        char nextCharacter;
        int index = 0;
        char topOperator;
        while (index < infix.length())
        {
            nextCharacter = infix.charAt(index);
            //converting nextCharacter to a lower case
            nextCharacter = Character.toLowerCase(nextCharacter);
            index++;
            switch (nextCharacter)
            {
                case 'a': case 'b': case 'c': case 'd': case 'e': case 'f':
                case 'g': case 'h': case 'i': case 'j': case 'k': case 'l':
                case 'm': case 'n': case 'o': case 'p': case 'q': case 'r':
                case 's': case 't': case 'u': case 'v': case 'w': case 'x':
                case 'y': case 'z':
                    postfix += nextCharacter;
                    break;
                case '^':
                    operatorStack.push(nextCharacter);
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    while (!operatorStack.isEmpty() && checkOperations(nextCharacter,operatorStack.peek()))
                    {
                        postfix += operatorStack.peek();
                        operatorStack.pop();
                    }
                    operatorStack.push(nextCharacter);
                    break;
                case '(':
                    operatorStack.push(nextCharacter);
                    break;
                case ')': //stack is not empty if infix expression is valid
                    topOperator = operatorStack.pop();
                    while (topOperator != '(')
                    {
                        postfix += topOperator;
                        topOperator = operatorStack.pop();
                    }
                    break;
                default: //ignore unexpected characters
                    break;
            }
        }
        while (!operatorStack.isEmpty())
        {
            topOperator = operatorStack.pop();
            postfix += topOperator;
        }

        return postfix;
    }

    //removes spaces of the infix expression and returns a string without white spaces
    public static String removeSpaces(String infix)
    {
        String newInfix = "";
        for(int index = 0; index < infix.length(); index++)
        {
            if(!(infix.charAt(index) == ' '))
                newInfix += infix.charAt(index);
        }
        return newInfix;
    }

    //checks to see the precedence of the operations for the stack
    //if operator in nextCharacter is less than or equal to the top of the operatorStack,
    //it will satisfy the condition of the while loop
    public static boolean checkOperations(char nextCharacter, char operatorStack)
    {
        if (nextCharacter == '+' && operatorStack == '*')
            return true;
        else if (nextCharacter == '+' && operatorStack == '/')
            return true;
        else if (nextCharacter == '-' && operatorStack == '*')
            return true;
        else if (nextCharacter == '-' && operatorStack == '/')
            return true;
        else if (nextCharacter == '*' && operatorStack == '*')
            return true;
        else if (nextCharacter == '/' && operatorStack == '/')
            return true;
        else if (nextCharacter == '*' && operatorStack == '/')
            return true;
        else if (nextCharacter == '/' && operatorStack == '*')
            return true;
        else
            return false;
    }
 }
