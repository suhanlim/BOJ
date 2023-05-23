import java.io.*;
import java.util.*;
public class Main {
    int[] parents;
    public static int sti(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
    public static long stl(StringTokenizer st) {
        return Long.parseLong(st.nextToken());
    }
    private int find(int a) {
        if (parents[a] < 0)
            return a;
        return parents[a] = find(parents[a]);
    }

    private void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;

        int hi = parents[a]<parents[b]?a:b;
        int lo = parents[a]<parents[b]?b:a;
        parents[hi] = parents[lo]; // -1 값 유지
        parents[lo] = hi; // 가르키는 노드 표시
    }
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = sti(st); int m = sti(st); int k = sti(st); int ans = 0;
        parents = new int[n+1]; int a = 0, b = 0;
        Arrays.fill(parents, -1);

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = sti(st); int node2 = sti(st);
            if(i==k) {
                a = node1;
                b = node2;
                continue;
            }
            union(node1,node2);
        }
        // 특별한 간선의 union 체크
        a = find(a);
        b = find(b);
        // 동일한 그룹
        if (a == b) {
            System.out.println(0);
            return;
        }
        // a 그룹에 포함된 정점의 수 * b 그룹에 포함된 정점의 수
        int aCnt = 0, bCnt = 0;
        for (int i = 1; i <= n; i++) {
            int cur = find(i);
            if (cur == a)
                aCnt++;
            else if (cur == b)
                bCnt++;
        }

        System.out.println((long) aCnt * bCnt);

        br.close();
        bw.flush();
        bw.close();
    }
    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}