package ua.in.mordas.codejam;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BleatrixTrotter {
    public static void main(String[] args) throws Exception {

        String fileIn = "/tmp/A-large.in";
        String fileOut = "/tmp/A-large.out";
        List<Integer> in = new ArrayList<>();

        try {
            File file = new File(fileIn);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                in.add(Integer.parseInt(line));
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        in.remove(0);
        File fout = new File(fileOut);
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        int counter = 1;
        for (Integer reminded : in) {
            List<Integer> pattern = new ArrayList<>();
            for (int i = 0; i < 10 ; i++) {
                pattern.add(i);
            }
            Collections.sort(pattern);

            int count = 1;
            int lastDigit = reminded;

            int extednalCounter = 0;
            boolean flag = false;
            do {
                int digit = reminded * count;
                pattern.removeAll(doString(digit));

                lastDigit = digit;
                count++;
                extednalCounter++;
                if(extednalCounter == 1000000){
                    flag = true;
                    break;
                }
            } while (!pattern.isEmpty());

            if(!flag){
                bw.append(String.format("Case #%d: %s\n", counter, String.valueOf(lastDigit)));
            } else {
                bw.append(String.format("Case #%d: %s\n", counter, "INSOMNIA"));
            }

            counter++;
        }

        bw.close();

    }

    public static List<Integer> doString(int digit){
        List<Integer> listResult = new ArrayList<>();

        do {
            int dig = digit % 10;
            digit = digit / 10;
            listResult.add(dig);
        } while (digit > 0);

        return listResult;
    }
}
