public class ArrayStackTest
{
    public static void main(String[] args)
    {
        String postFix = "a b * c a - / d e * +";
        System.out.println(evaluatePostFix(postFix));
    }
    public static int evaluatePostFix(String postfix)
    {
        ResizeableArrayStack<Integer> valueStack = new ResizeableArrayStack<>();
        postfix = removeSpaces(postfix);
        int index = 0;
        char nextCharacter;
        int operandOne;
        int operandTwo;
        int result = 0;
        while(index < postfix.length())
        {
            nextCharacter = postfix.charAt(index);
            index++;
            switch (nextCharacter)
            {
                case 'a': case 'b': case 'c': case 'd': case 'e': case 'f':
                case 'g': case 'h': case 'i': case 'j': case 'k': case 'l':
                case 'm': case 'n': case 'o': case 'p': case 'q': case 'r':
                case 's': case 't': case 'u': case 'v': case 'w': case 'x':
                case 'y': case 'z':
                    valueStack.push(getValue(nextCharacter));
                    break;
                case '+': case '-': case '*': case '/': case '^':
                    operandOne = valueStack.pop();
                    operandTwo = valueStack.pop();
                    if (nextCharacter == '+')
                    {
                        result = operandTwo + operandOne;
                        valueStack.push(result);
                    }
                    else if (nextCharacter == '-')
                    {
                        result = operandTwo - operandOne;
                        valueStack.push(result);
                    }
                    else if (nextCharacter == '*')
                    {
                        result = operandTwo * operandOne;
                        valueStack.push(result);
                    }
                    else if (nextCharacter == '/')
                    {
                        result = operandTwo /operandOne;
                        valueStack.push(result);
                    }
                    else if (nextCharacter == '^')
                    {
                        result = (int) Math.pow(operandTwo,operandOne);
                        valueStack.push(result);
                    }
                    break;
                default:
                    break;
            }


        }
        return valueStack.peek();
    }

    //removes spaces of the postfix expression
    public static String removeSpaces(String postfix)
    {
        String newPostfix = "";
        for(int index = 0; index < postfix.length(); index++)
        {
            if(!(postfix.charAt(index) == ' '))
                newPostfix += postfix.charAt(index);
        }
        return newPostfix;
    }

    //gets the value that is stored in the variable and returns it
    public static int getValue(Character c)
    {
        int value;
        switch(c) {
            case 'a':
                value = 2;
                break;
            case 'b':
                value = 3;
                break;
            case 'c':
                value = 4;
                break;
            case 'd':
                value = 5;
                break;
            case 'e':
                value = 6;
                break;
            default:
                value = 0;
                break;
        }
        return value;
    }

}
