import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Pos {
        int row, col;
        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static Queue<Pos> queue = new LinkedList<>();
    static int[][] fourWay = {{1,0},{-1,0},{0,1},{0,-1}};
    public int bfs(int w,int h,char[][]map,int[][] visit){
        int ans = -1;

        // 제일 외곽 부분에 위치한 상태라면 바로 return
        while(!queue.isEmpty()){
            Pos unit = queue.remove();
            if(map[unit.row][unit.col]!='*'&&(unit.col==0||unit.col==w-1||unit.row ==0||unit.row ==h-1)) {
                ans = visit[unit.row][unit.col] + 1;
                break;
            }
            for(int[] way : fourWay) {

                int x = unit.col + way[1];
                int y = unit.row + way[0];

                if (x >= 0 && y >= 0 && x < w && y < h && visit[y][x] == -1 && map[y][x] != '#') {
                    if (map[y][x] == '*')
                        continue;
                    if (map[unit.row][unit.col] == '*')
                        map[y][x] = '*';
                    else
                        map[y][x] = '@';

                    queue.add(new Pos(y, x));
                    visit[y][x] = visit[unit.row][unit.col] + 1;
                }
            }
        }

        return ans;
    }
    public void Solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int ans = 0; int personInitX = 0; int personInitY = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int[][] visit = new int[h][w];
            char[][] map = new char[h][w];
            for (int row = 0; row < h; row++) {
                String s = br.readLine();
                for (int col = 0; col < w; col++) {
                    map[row][col] = s.charAt(col);
                    visit[row][col] = -1;
                    if (map[row][col] == '*') {
                        visit[row][col] = 0;
                        queue.add(new Pos(row,col));
                    }
                    if (map[row][col] == '@') {
                        visit[row][col] = 0;
                        personInitY = row;
                        personInitX = col;
                    }
                }
            }

            queue.add(new Pos(personInitY,personInitX));
            ans = bfs(w,h,map,visit);
            System.out.println(ans==-1?"IMPOSSIBLE":String.valueOf(ans));
            queue.clear();
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().Solution();
    }
}
