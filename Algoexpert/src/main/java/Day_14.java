import java.util.*;

public class Day_14 {

    private static int part = 1;
    private static long timerStart;
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList array = new ArrayList<String>();
        while (true){
            var v = scan.nextLine();
            if(v.equals("-1"))
                break;
            array.add(v);
        }
        solve(array);

    }

    public static void solve(List<String> inputStrs)
    {
        //PART 1
        char[] bitMask = new char[36];
        Map<String, Long> memory = new HashMap<>();
        for(String s : inputStrs)
        {
            String[] parts = s.split("=");
            if(parts[0].trim().equals("mask"))
            {
                bitMask = parts[1].trim().toCharArray();
            }
            else
            {
                int index = Integer.parseInt(parts[0].substring(4, parts[0].length() - 2));
                long result = 0;
                String number = String.format("%36s", Integer.toBinaryString(Integer.parseInt(parts[1].trim()))).replace(" ", "0");
                for(int i = 0; i < bitMask.length; i++)
                {
                    if(bitMask[i] != 'X')
                        result = (result << 1) | Integer.parseInt(String.valueOf(bitMask[i]));
                    else
                        result = (result << 1) | Integer.parseInt(String.valueOf(number.charAt(i)));
                }

                memory.put(String.valueOf(index), result);
            }
        }

        long sum = 0;
        for(Long value : memory.values())
        {
            sum += value;
        }
        lap(sum);


        //PART 2
        memory = new HashMap<>();
        for(String s : inputStrs)
        {
            String[] parts = s.split("=");
            if(parts[0].trim().equals("mask"))
            {
                bitMask = parts[1].trim().toCharArray();
            }
            else
            {
                List<String> address = new ArrayList<>();
                int index = Integer.parseInt(parts[0].substring(4, parts[0].length() - 2));
                address.add(String.format("%36s", Integer.toBinaryString(index)).replace(" ", "0"));

                long number = Integer.parseInt(parts[1].trim());
                for(int i = 0; i < bitMask.length; i++)
                {
                    for(int j = address.size() - 1; j >= 0; j--)
                    {
                        String addressEdit = address.remove(j);
                        if(bitMask[i] == 'X')
                        {
                            address.add(addressEdit.substring(0, i) + '0' + addressEdit.substring(i + 1));
                            address.add(addressEdit.substring(0, i) + '1' + addressEdit.substring(i + 1));
                        }
                        else if(bitMask[i] == '1')
                        {
                            address.add(addressEdit.substring(0, i) + '1' + addressEdit.substring(i + 1));
                        }
                        else
                        {
                            address.add(addressEdit);
                        }
                    }
                }

                for(String addr : address)
                    memory.put(addr, number);
            }
        }

        sum = 0;
        for(Long value : memory.values())
        {
            sum += value;
        }
        lap(sum);
    }

    public static void lap(long answer)
    {
        long timeSpent = (System.nanoTime() - timerStart) / 1000;
        if(timeSpent < 1000)
            System.out.println("Part " + part + ": " + answer + ", Duration: " + timeSpent + "µs");
        else
            System.out.println("Part " + part + ": " + answer + ", Duration: " + (timeSpent / 1000.0) + "ms");
        timerStart = System.nanoTime();
        part++;
    }

}
