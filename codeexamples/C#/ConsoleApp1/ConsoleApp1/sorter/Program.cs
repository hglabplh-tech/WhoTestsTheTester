// See https://aka.ms/new-console-template for more information

// ReSharper disable once UnusedMember.Global
namespace ConsoleApp1.sorter;

public class Program
{
    public static void Main(string[] args)
    {
        int[] arr = { 10, 7, 8, 9, 1, 5 };
        var n = arr.Length;

        QSorter.quickSort(arr, 0, n - 1);
        foreach (var val in arr)
        {
            Console.Write(val + " ");


        }
    }
}
