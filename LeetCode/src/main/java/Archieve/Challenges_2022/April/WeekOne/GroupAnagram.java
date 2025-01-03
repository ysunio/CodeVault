package Archieve.Challenges_2022.April.WeekOne;

import java.util.*;

public class GroupAnagram {

    public static void main(String[] args) {
        String[] arr = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> anagramList = getAnagramList(arr);
        for (List<String> a : anagramList)
            System.out.println(a);
    }

    private static List<List<String>> getAnagramList(String[] str) {
        List<List<String>> anagramGroup = new ArrayList<>();
        HashMap<String, ArrayList<String>> hashStore = new HashMap<String, ArrayList<String>>();
        for (String current : str) {
            char[] characters = current.toCharArray();
            Arrays.sort(characters);
            String sortedString = new String(characters);
            if (!hashStore.containsKey(sortedString)) {
                ArrayList<String> a = new ArrayList<String>() {
                    /**
                     *
                     */
                    private static final long serialVersionUID = 1L;

                    {
                        add(current);
                    }
                };
                hashStore.put(sortedString, a);
            } else {
                ArrayList<String> a = hashStore.get(sortedString);
                a.add(current);
                hashStore.put(sortedString, a);
            }
        }

        for (Map.Entry<String, ArrayList<String>> val : hashStore.entrySet()) {
            anagramGroup.add(val.getValue());
        }

        return anagramGroup;
    }

}
