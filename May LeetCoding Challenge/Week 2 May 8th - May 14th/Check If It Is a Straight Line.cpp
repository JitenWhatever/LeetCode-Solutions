/*
You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point. Check if these points make a straight line in the XY plane.

Example 1:
Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
Output: true

Example 2:
Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
Output: false

Constraints:
    2 <= coordinates.length <= 1000
    coordinates[i].length == 2
    -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
    coordinates contains no duplicate point.
*/

class Solution {
public:
    bool checkStraightLine(vector<vector<int>>& coordinates) { // solpe count
        int N = coordinates.size();
        
        if(N < 3) return true;
        
        int maxPoint = INT_MIN;
        map<pair<int, int>, int> mp;
        
        for(int i=1; i<N; i++){
            int dy = coordinates[i][1] - coordinates[i-1][1];
            int dx = coordinates[i][0] - coordinates[i-1][0];
            
            int g  = __gcd(dy, dx);
            
            dy /= g;
            dx /= g;
            
            mp[make_pair(dy, dx)]++;

            maxPoint = max(maxPoint, mp[make_pair(dy, dx)] + 1);
        }
        
        cout<<maxPoint<<endl;
        return N == maxPoint;
    }
    //Using Y = slope*X + C
    bool checkStraightLine(vector<vector<int>>& coordinates) {
        int N = coordinates.size();
        
        if(N < 3) return true;
        
        // Y = slope*X + C, point(X, Y)
        double dy = coordinates[1][1] - coordinates[0][1];
        double dx = coordinates[1][0] - coordinates[0][0];
        
        double slope = dy/dx;
        
        double C = coordinates[0][1] - (slope*coordinates[0][0]);
        
        for(int point = 2; point < N; point++){
            if((coordinates[point][1] - (slope*coordinates[point][0])) != C){
                return false;
            }
        }
       
        return true;
    }
};

