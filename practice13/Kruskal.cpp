
#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;


int main() {
    int n, m;
    cin >> n >> m;
    vector <vector <int> > edges(m, vector<int>(3));
    for (int i = 0; i < m; ++i)
        cin >> edges[i][1] >> edges[i][2] >> edges[i][0];
    sort(edges.begin(), edges.end());
    vector <int> comp(n);
    for (int i = 0; i < n; ++i)
        comp[i] = i;
    int ans = 0;

    for (auto& edge : edges)
    {
        int weight = edge[0];
        int start = edge[1];
        int end = edge[2];

        if (comp[start] != comp[end])
        {
            ans += weight;
            int a = comp[start];
            int b = comp[end];

            cout << a + 1 << " " << b + 1 << " " << weight << endl;

            for (int i = 0; i < n; ++i)
                if (comp[i] == b)
                    comp[i] = a;
        }
    }

    cin.get();
    cin.get();
    cin.get();

    // для первого варинта: 
     /*
     *   5 8
         0 1 1
         0 2 2
         0 4 10
         1 3 3
         1 4 6
         2 4 7
         2 3 4
         3 4 11
     */


}