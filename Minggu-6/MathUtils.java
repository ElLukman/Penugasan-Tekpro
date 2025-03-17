public class MathUtils 
{
    public static int factorial(int n) throws IllegalArgumentException 
    {
        if (n < 0) 
        {
            throw new IllegalArgumentException("Factorial is not defined for negative integers.");
        } 
        else if (n > 16) 
        {
            throw new IllegalArgumentException("Factorial of numbers greater than 16 cannot be represented accurately.");
        }
        int fac = 1;
        for (int i = n; i > 0; i--) 
        {
            fac *= i;
        }
        return fac;
    }
}