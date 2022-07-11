import sample.Algorithms.ApproximationTSP;
import sample.Algorithms.BitonicTSP;
import sample.Algorithms.BruteForceTSP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.exit;




public class Main {
    private static int[][]  graph;


    public static void main(String[] args) {

        // If the user does not give a filepath for the graph
        if (args.length != 1){
            System.out.println(".txt file not specified");
            exit(1);
        }

        // Reads the adj matrix of the graph from the file given in main
        try {
            readGraphFromFile(args[0]);
        }
        catch (Exception e ){
            System.out.println("Error reading from " + args[0] + " file");
            System.out.println(e.getMessage());
            exit(1);
        }


//        int[][] graph;
        ApproximationTSP.printResult(graph);
        BitonicTSP.printResult(graph);
        BruteForceTSP.printResult(graph);

    }


    // Reading the graph's adj matrix from the given .txt file
    private static void readGraphFromFile(String fileName) throws FileNotFoundException {
        // Used to get the size of the array
        Scanner scanner = new Scanner(new File(fileName));
        String firstLine = scanner.nextLine();
        String[] data = firstLine.split(" "); // *First number on first line of the file must not have a "space"*
        int n = data.length;
        scanner.close();

        // Copy the graph to the graph array
        scanner = new Scanner(new File(fileName));
        graph = new int[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++)
                graph[r][c] = scanner.nextInt();
        }

    }
}
