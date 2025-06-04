import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Driver {

    public static int naivePower(int x, int n) {
        if(x==0){
            return 0;
        }else if(n == 0){
            return 1;
        }
        return x*naivePower(x, n-1);
    }

    public static int unoptimizedDCPower(int x, int n) {
        if(x==0){
            return 0;
        }else if(n == 0){
            return 1;
        }else if(n % 2 == 0){
            return unoptimizedDCPower(x, n/2) * unoptimizedDCPower(x, n/2);
        }
        else{
            return x * unoptimizedDCPower(x, n/2) * unoptimizedDCPower(x, n/2);
        }
    }

    public static int optimizedDCPower(int x, int n) {
        if(x==0){
            return 0;
        }else if(n == 0){
            return 1;
        }
        int temp = optimizedDCPower(x, n/2);
        if(n % 2 == 0){
            return temp * temp;
        }else{
            return x * temp * temp;
        }
    }

    public static void main(String[] args) {
        //generate three different "power tables" (one for each function) and record the time taken to compute each data point.
        long[][] naiveTable = new long[10][10];
        long[][] unoptimizedDCTable = new long[10][10];
        long[][] optimizedDCTable = new long[10][10];

        int temp = 0;
        long startTime = 0;
        long endTime = 0;

        StringBuilder csv = new StringBuilder();//will become a CSV

        System.out.println("Naive Power:");
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                startTime = System.nanoTime();
                temp = naivePower(i, j);
                endTime = System.nanoTime();
                //unit tests to make sure the correct result is calculated
                //also calculate time for performance analysis
                if(temp == Math.pow(i, j)){
                    naiveTable[i][j] = endTime - startTime;
                }else{
                    System.out.println("Error with " + i + "^" + j + " = " + temp + " not " + Math.pow(i, j));
                }
            }
        }

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                System.out.print(naiveTable[i][j] + "     \t     ");
                csv.append(naiveTable[i][j]).append(",");
            }
            System.out.println();
            csv.append("\n");
        }
        csv.append(" , , , , , , , , , \n");
        csv.append(" , , , , , , , , , \n");

        System.out.println("Unoptimized DC Power:");
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                startTime = System.nanoTime();
                temp = unoptimizedDCPower(i, j);
                endTime = System.nanoTime();
                //unit tests to make sure the correct result is calculated
                //also calculate time for performance analysis
                if(temp == Math.pow(i, j)){
                    unoptimizedDCTable[i][j] = endTime - startTime;
                }else{
                    System.out.println("Error with " + i + "^" + j + " = " + temp + " not " + Math.pow(i, j));
                }
            }
        }

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                System.out.print(unoptimizedDCTable[i][j] + "\t\t");
                csv.append(unoptimizedDCTable[i][j]).append(",");
            }
            System.out.println();
            csv.append("\n");
        }
        csv.append(" , , , , , , , , , \n");
        csv.append(" , , , , , , , , , \n");

        System.out.println("Optimized DC Power:");
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                startTime = System.nanoTime();
                temp = optimizedDCPower(i, j);
                endTime = System.nanoTime();
                //unit tests to make sure the correct result is calculated
                //also calculate time for performance analysis
                if(temp == Math.pow(i, j)){
                    optimizedDCTable[i][j] = endTime - startTime;
                }else{
                    System.out.println("Error with " + i + "^" + j + " = " + temp + " not " + Math.pow(i, j));
                }
            }
        }

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                System.out.print(optimizedDCTable[i][j] + "\t\t");
                csv.append(optimizedDCTable[i][j]).append(",");
            }
            System.out.println();
            csv.append("\n");
        }
        csv.append(" , , , , , , , , , \n");
        csv.append(" , , , , , , , , , \n");

        try{
            FileWriter fw = new FileWriter("C:\\dev\\CSC216\\Module2\\CSC216-Lab2-Power\\data.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(csv.toString());
            bw.close();
            System.out.println("Done");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
