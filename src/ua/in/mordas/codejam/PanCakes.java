package ua.in.mordas.codejam;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PanCakes {
    public static void main(String[] args) throws Exception {
        String fileIn = "/tmp/B-large.in";
        List<String> inData = new ArrayList<>();

        try {
            File file = new File(fileIn);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                inData.add(line.trim());
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        inData.remove(0);

        List<boolean[]> inDataBoolean = new ArrayList<>();
        for (String data : inData) {
            boolean[] dat = new boolean[data.length()];
            for (int i = 0; i < data.length(); i++) {
                if (data.charAt(i) == '-') {
                    dat[i] = false;
                } else if (data.charAt(i) == '+') {
                    dat[i] = true;
                } else {
                    throw new Exception("wrong data");
                }
            }
            inDataBoolean.add(dat);
        }

        String fileOut = "/tmp/B-large.out";
        File fout = new File(fileOut);
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter outData = new BufferedWriter(new OutputStreamWriter(fos));
//        List<boolean[]> tmpDataBoolean = new ArrayList<>();
//        tmpDataBoolean.add(new boolean[]{false,false,true,false});//inDataBoolean.get(8));

        int index = 1;
        for (boolean[] workLine : inDataBoolean) {
//            System.out.print(Arrays.toString(tmpDataBoolean.get(i)) + "<-");
            int rotateCounter = 0;
            int counter = 0;
//            System.out.println(Arrays.toString(workLine));
            do {
                if (needRotate(workLine, counter)) {
                    workLine = doRotate(workLine, counter);
                    rotateCounter++;
                    counter = 0;
                } else counter++;
            } while (counter < workLine.length);

            if(!workLine[0]){
                rotateCounter++;
            }

//            System.out.println(Arrays.toString(workLine));
//            System.out.printf("Case #%d: %s\n", index, rotateCounter);
            outData.append(String.format("Case #%d: %s\n", index, String.valueOf(rotateCounter)));

            index++;
        }

        outData.close();
    }

    public static boolean hasNext(boolean[] array, int number) {
        if (array.length > number + 1) {
            return true;
        } else return false;
    }

    public static boolean needRotate(boolean[] array, int currenIndex) {
        if (hasNext(array, currenIndex)) {
            if (array[currenIndex] != array[currenIndex + 1]) {
                return true;
            }
        }

        return false;
    }

    public static boolean[] doRotate(boolean[] array, int toPosition) {
        boolean[] result = new boolean[array.length];

        int counter = 0;
        for (int i = toPosition; i >= 0; i--) {
            result[counter] = !array[i];
            counter++;
        }

        for (int i = toPosition + 1; i < array.length; i++) {
            result[i] = array[i];
        }

//        System.out.println("rotate->" + Arrays.toString(result));

        return result;
    }
}
