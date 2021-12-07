import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ListProcessor{

    private int[] numberArray;
    public List<Integer> numberArrayList = new ArrayList <Integer>();
    Random random = new Random();

    public static void main(String[] args){
        ListProcessor listProcessor = new ListProcessor();

        List<Integer> Mylist= listProcessor.listSequence(5,11);

        System.out.println(listProcessor.sumIterative(Mylist));
        System.out.println(listProcessor.sumRecursive(Mylist));
    }

    public int[] arraySequence(int from, int to){
        if(to < from)
        {
            throw new IllegalArgumentException("Lower bound can't be higher than upper bound");
        }

        numberArray = new int[to - from];
        for(int i = 0; i < to - from; i++)
        {
            numberArray[i] = from + i;
        }

        return numberArray;
    }

    public List<Integer> listSequence(int from, int to)
    {
        if(to < from)
        {
            throw new IllegalArgumentException("Lower bound can't be higher than upper bound");
        }

        for(int i = 0; i < to - from; i ++)
        {
            numberArrayList.add(from + i);
        }

        return numberArrayList;
    }

    public void printArrayList(){
        for(Integer anint: numberArrayList)
        {
            System.out.println(anint.toString());
        }
    }


    public int[] shuffled(int[] numbers)
    {
        ArrayList <Integer> availableIndices = new ArrayList<Integer>();

        int[] numbersCopy = numbers.clone();
        for(int i = 0; i < numbersCopy.length; i++){
            availableIndices.add(i);
        }

        for(int i = 0; i < numbersCopy.length; i++)
        {
            int randomIndex = random.nextInt(availableIndices.size());
            int randomInt = availableIndices.get(randomIndex);

            numbersCopy[randomInt] = numbers[i];
            availableIndices.remove(randomIndex);
        }

        return numbersCopy;
    }


    public List<Integer> shuffled(List<Integer> numbers)
    {
        List<Integer> numbersCopy = new ArrayList<Integer>();
        ArrayList <Integer> availableIndices = new ArrayList<Integer>();

        for(Integer number: numbers){
            numbersCopy.add(number);
        }

        for(int i = 0; i < numbers.size(); i++)
        {
            availableIndices.add(i);
        }

        for(int i = 0; i < numbersCopy.size(); i++)
        {
            int randomIndex = random.nextInt(availableIndices.size());
            int randomInt = availableIndices.get(randomIndex);
            Integer shuffledNumber = numbers.get(i);

            numbersCopy.set(randomInt, shuffledNumber);
            availableIndices.remove(randomIndex);
        }

        return numbersCopy;
    }


    public int sumIterative(int[] numbers)
    {
        int sum = 0;

        if(numbers.length == 0)
        {
            return sum;
        }

        for(int i = 0; i < numbers.length; i++)
        {
            sum = sum + numbers[i];
        }

        return sum;

    }

    public int sumIterative(List<Integer> numbers)
    {
        int sum = 0;

        if(numbers.size() == 0)
        {
            return sum;
        }

        for(int i =0; i < numbers.size(); i++)
        {
            sum = sum + numbers.get(i);
        }


        return sum;
    }

    public int sumRecursive(int[] numbers)
    {

        if(numbers.length == 0)
        {
            return 0;
        }
        int n = numbers.length - 1;

        return recursiveHelperArray(numbers, n);
    }




    private int recursiveHelperArray(int[] somenumbers, int n)
    {
        if(n == 0)
        {
            return somenumbers[0];
        }

        return somenumbers[n] + recursiveHelperArray(somenumbers, n-1);
    }






    public int sumRecursive(List<Integer> numbers)
    {
        if(numbers.size() == 0)
        {
            return 0;
        }

        int n = numbers.size() -1;

        return recursiveHelperArrayList(numbers, n);
    }


    private int recursiveHelperArrayList(List<Integer> somenumbers, int n)
    {
        if(n == 0)
        {
            return somenumbers.get(0);
        }
        //System.out.println(somenumbers.get(n));
        return somenumbers.get(n) + recursiveHelperArrayList(somenumbers, n-1);
    }




}