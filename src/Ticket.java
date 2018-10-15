import javafx.print.PageOrientation;

import java.util.*;

public class Ticket {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int sizeOfWorld = Integer.parseInt(scan.nextLine());
        int numberOfEvents = Integer.parseInt(scan.nextLine());
        HashMap<int[],List<int[]>> eventList = new HashMap<>();
        while(numberOfEvents > 0){
            String eventLine = scan.nextLine();
            String[] eventinfo = eventLine.split(" ");
            int no = Integer.parseInt(eventinfo[0]);
            int []key = new int[2];
            key[0] = Integer.parseInt(eventinfo[1]);
            key[1] = Integer.parseInt(eventinfo[2]);
            List<int[]> priceNoList = new ArrayList<>();
            for(int i = 3; i < eventinfo.length; i++){
                priceNoList.add(new int[]{Integer.parseInt(eventinfo[i]),no});
            }
            if(!priceNoList.isEmpty())
                eventList.put(key, priceNoList);
            numberOfEvents--;
        }
        int numberOfBuyers = Integer.parseInt(scan.nextLine());
        List<int []> buyerList = new ArrayList<>();
        while(numberOfBuyers > 0){
            String buyerLine = scan.nextLine();
            String [] temp = buyerLine.split(" ");
            int [] buyer = new int[temp.length];
            for(int i = 0; i < temp.length; i++){
                buyer[i] = Integer.parseInt(temp[i]);
            }
            buyerList.add(buyer);
            numberOfBuyers--;
        }

        for(int [] buyer : buyerList){
            int min = Integer.MAX_VALUE;
            for(int key[] : eventList.keySet()){
                int currDistance = calculateManhattanDistance(key[0],key[1],buyer[0],buyer[1]);
                if(currDistance < min){
                    min = currDistance;
                }
            }
            PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (a[0] == b[0])?a[1]-b[1]:a[0]-b[0]);
            for(int key[] : eventList.keySet()){
                int curr = calculateManhattanDistance(key[0],key[1],buyer[0],buyer[1]);
                if(curr == min){
                    List<int[]> priceNoList = eventList.get(key);
                    for(int temp[] : priceNoList){
                        pq.offer(temp);
                    }

                }
            }
            int res[] = new int[2];
            res[0] = 0;
            res[1] = -1;
            if(!pq.isEmpty()){
                res = pq.poll();
            }
            int []deleteKey = new int[2];
            for(int []key : eventList.keySet()){
                List<int[]> list = eventList.get(key);
                for(int [] temp :list){
                    if(temp[0] == res[0] && temp[1] == res[1]){
                            list.remove(temp);
                            if(list.size() == 0){
                                deleteKey = key;
                            }
                            break;
                    }
                }

            }
            eventList.remove(deleteKey);
            System.out.println(res[1]+" "+res[0]);
        }
    }
    public static int calculateManhattanDistance(int x1, int y1, int x2, int y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
