public class manhattanDistance {
    public int helper(String s1, String s2){
        int res = 0;
        String[] list1 = s1.split(" ");
        String [] list2 = s2.split(" ");
        int []i1 = new int[list1.length];
        int []i2 = new int[list2.length];
        for(int i = 0; i < i1.length; i++){
            i1[i]= Integer.parseInt(list1[i]);
        }
        for(int i = 0; i < i2.length; i++){
            i2[i]= Integer.parseInt(list2[i]);
        }
        for(int i =0; i < i1.length; i++){
            res += Math.abs(i1[i]-i2[i]);
        }
        return res;
    }
    public static void main(String args[]){
        String s1 = "1 2";
        String s2 = "2 2";
        manhattanDistance m = new manhattanDistance();
        System.out.print(m.helper(s1,s2));
    }
}
