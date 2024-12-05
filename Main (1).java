/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.Random;
public class Main
{
	public static void main(String[] args) 
	{
	    int length = 100000;
	    int min = 0;
	    int max = 500;
		int[] ar = RandomizedArray(length, min, max);
		int[] secondArray = RandomizedArray(length, min, max);
		int[] gapValues = {64836, 32468, 16284, 8192, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
		long start = System.currentTimeMillis();
		shellSort(ar, gapValues);
		long end = System.currentTimeMillis();
		long timeElapsed = end - start;
		System.out.println("Shell sort took " + timeElapsed + " milliseconds.");
		start = System.currentTimeMillis();
		quicksort(secondArray, 0, (length - 1));
		end = System.currentTimeMillis();
		timeElapsed = end - start;
		System.out.println("Quick sort took " + timeElapsed + " milliseconds.");
	}
	
	public static int[] RandomizedArray(int size, int start, int end)
	{
	    int[] theArray = new int[size];
	    Random r = new Random();
	    for(int i = 0; i < theArray.length; i++)
	    {
	        theArray[i] = Math.abs(r.nextInt() % end);
	        if(theArray[i] < start)
	        {
	            i--;
	        }
	    }
	    return theArray;
	}
    
    public static void shellSort(int[] numbers, int[] gapValues) 
    {
        for (int g = 0; g < gapValues.length; g++) 
        {
            for (int i = 0; i < gapValues[g]; i++) 
            {
                insertionSortInterleaved(numbers, i, gapValues[g]);
            }
        }
    }
    
    public static void insertionSortInterleaved(int[] numbers, int startIndex, int gap) 
    {
        for (int i = startIndex + gap; i < numbers.length; i += gap) 
        {
            int j = i;
            while (j - gap >= startIndex && numbers[j] < numbers[j - gap]) 
            {
                int temp = numbers[j];
                numbers[j] = numbers[j - gap];
                numbers[j - gap] = temp;
                j -= gap;
            }
        }
    }
    
    public static void quicksort(int[] numbers, int startIndex, int endIndex) 
    {
        if (endIndex <= startIndex) 
        {
            return;
        }
        int high = partition(numbers, startIndex, endIndex);
        quicksort(numbers, startIndex, high);
        quicksort(numbers, high + 1, endIndex);
    }
    
    public static int partition(int[] numbers, int startIndex, int endIndex) 
    {
        int midpoint = startIndex + (endIndex - startIndex) / 2;
        int pivot = numbers[midpoint];
        int low = startIndex;
        int high = endIndex;
   
        boolean done = false;
        while (!done) 
        {
            while (numbers[low] < pivot) 
            {
                low = low + 1;
            }
            while (pivot < numbers[high]) 
            {
                high = high - 1;
            }

            if (low >= high) 
            {
                done = true;
            }
            else 
            {
                int temp = numbers[low];
                numbers[low] = numbers[high];
                numbers[high] = temp;
                low++;
                high--;
            }
        }

        return high;
    }
}