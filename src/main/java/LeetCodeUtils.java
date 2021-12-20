import java.util.ArrayList;
import java.util.List;

public final class LeetCodeUtils {

    /**
     * string 转int 数组 如：[[1,3,1],[1,5,1],[4,2,1]]
     * @param input
     * @return
     */
    public static int[][] convertStrToArr(String input){
        int len = input.length();
        String processInput = input.substring(2, len - 2);
        String[] strings = processInput.split("],\\[");
        List<List<Integer>> resList = new ArrayList<>();
        List<Integer> tmpList = new ArrayList<>();
        for (String oneArr : strings) {
            String[] oneArrays = oneArr.split(",");
            tmpList.clear();
            for (String element : oneArrays) {
                tmpList.add(Integer.valueOf(element.trim()));
            }
            resList.add(new ArrayList<>(tmpList));
        }

        List<int[]> intsList = new ArrayList<>();
        for (List<Integer> tmp : resList) {
            intsList.add(tmp.stream().mapToInt(Integer::intValue).toArray());
        }

        return intsList.toArray(new int[0][]);
    }

    public static char[][] convertStrToCharArr(String input){
        int len = input.length();
        String processInput = input.substring(2, len - 2);
        String[] strings = processInput.split("],\\[");
        List<List<Character>> resList = new ArrayList<>();
        List<Character> tmpList = new ArrayList<>();
        for (String oneArr : strings) {
            String[] oneArrays = oneArr.split(",");
            tmpList.clear();
            for (String element : oneArrays) {
                tmpList.add(Character.valueOf(element.trim().charAt(1)));
            }
            resList.add(new ArrayList<>(tmpList));
        }

        char[][] res = new char[resList.size()][resList.get(0).size()];
        int i = 0;
        for (List<Character> tmpCharacters : resList) {
            int j = 0;
            for (Character c : tmpCharacters) {
                res[i][j] = c;
                j++;
            }
            i++;
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] p = convertStrToArr("[[1,3,1],[1,5,1],[4,2,1]]");
        System.out.println(p);
    }
}
