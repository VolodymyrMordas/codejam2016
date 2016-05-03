package ua.in.mordas.codejam.qualification;

import java.util.ArrayList;
import java.util.List;

public class JamCoin {

    public static void main(String[] args) {
        int bLength = 6; //100001
        int dNumber = 3; //jamcoin

        List<Long> list = new ArrayList<>();

        for (long i = (int)Math.pow(2,bLength - 1) ; i < ((int)(Math.pow(2,bLength))) ; i++){
            if(i!=2){
                if((i%2) == 0){
                    continue;
                }
            }

            boolean isPrime = false;

            for (int j = 2; j <= 10 ; j++) {
                Digit digit = new Digit(
                        Long.valueOf(
                                Long.toBinaryString(i)),j);
                isPrime = digit.isPrime();
                if(isPrime){
                    break;
                }
            }
            if(!isPrime){
                list.add(i);
            }
        }


        for (Long jamcoin : list.subList(0,dNumber)){
            StringBuffer buffer = new StringBuffer();

            buffer.append(Long.toBinaryString(jamcoin))
                    .append("|"+jamcoin);

            List<Long> legitimate = new ArrayList<>();

            for (int i = 2; i <= 10 ; i++) {
                Digit digit = new Digit(Long.valueOf(Long.toBinaryString(jamcoin)),i);
                List<Long> dividers = digit.getDividers();
                legitimate.add(dividers.get(dividers.size() - 1));
            }
            System.out.println(Long.toBinaryString(jamcoin) + "|"
                    + jamcoin + "|"
                    + legitimate.toString());
        }

    }

    public static class Digit {

        private final long digit;
        private final long base;

        private long decimal;

        public Digit(long digit, long base) {
            this.digit = digit;
            this.base = base;

            this.decimal = toDecimal();
        }

        private long toDecimal(){
            int count = 0;
            long result = 0;
            long dig = digit;
            while(dig > 0) {
                long reminder = dig % 10;
                dig = dig / 10;

                result = result + reminder * ((long) Math.pow((double)base,(double)count));
                count++;
            };

            return result;
        }

        public boolean isPrime() {
            long n = toDecimal();
            for(int i = 2; i < n;i++) {
                if(n % i == 0)
                    return false;
            }
            return true;
        }

        @Override
        public String toString() {
            StringBuffer buffer = new StringBuffer();
            buffer.append("[DIGIT : "+this.digit+",")
                    .append("BASE : " + this.base+",")
                    .append("DECIMAL : " +this.toDecimal()+",")
                    .append((isPrime() ? "PRIME]" : "NOT_PRIME]")).trimToSize();
            return buffer.toString();
        }

        public List<Long> getDividers(){
            List<Long> dividers = new ArrayList<>();
            for (long i = 2; i < this.decimal; i++) {
                if((this.decimal % i) == 0){
                    dividers.add(i);
                }
            }
            return dividers;
        }
    }
}