/*
Note: This is a companion problem to the System Design problem: Design TinyURL.
TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl 
and it returns a short URL such as http://tinyurl.com/4e9iAk. Design a class to encode a URL and decode a tiny URL.

There is no restriction on how your encode/decode algorithm should work. 
You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.

Implement the Solution class:

Solution() Initializes the object of the system.
String encode(String longUrl) Returns a tiny URL for the given longUrl.
String decode(String shortUrl) Returns the original long URL for the given shortUrl. 
It is guaranteed that the given shortUrl was encoded by the same object.
 

Example 1:

Input: url = "https://leetcode.com/problems/design-tinyurl"
Output: "https://leetcode.com/problems/design-tinyurl"

Explanation:
Solution obj = new Solution();
string tiny = obj.encode(url); // returns the encoded tiny url.
string ans = obj.decode(tiny); // returns the original url after deconding it.
 

Constraints:

1 <= url.length <= 10^4
url is guranteed to be a valid URL.
*/

class EncodeAndDecodeTinyURL {
    public class Codec {
        Map<Integer, String> map = new HashMap<>();
        int i = 0;
    
        public String encode(String longUrl) {
            map.put(i, longUrl);
            return "http://tinyurl.com/" + i++;
        }
    
        public String decode(String shortUrl) {
            return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
        }
    }

    public class Codec {
        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        HashMap<String, String> map = new HashMap<>();
        int count = 1;
    
        public String getString() {
            int c = count;
            StringBuilder sb = new StringBuilder();
            while (c > 0) {
                c--;
                sb.append(chars.charAt(c % 62));
                c /= 62;
            }
            return sb.toString();
        }
    
        public String encode(String longUrl) {
            String key = getString();
            map.put(key, longUrl);
            count++;
            return "http://tinyurl.com/" + key;
        }
    
        public String decode(String shortUrl) {
            return map.get(shortUrl.replace("http://tinyurl.com/", ""));
        }
    }

    public class Codec {
        Map<Integer, String> map = new HashMap<>();
    
        public String encode(String longUrl) {
            map.put(longUrl.hashCode(), longUrl);
            return "http://tinyurl.com/" + longUrl.hashCode();
        }
    
        public String decode(String shortUrl) {
            return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
        }
    }

    public class Codec {
        Map<Integer, String> map = new HashMap<>();
        Random r = new Random();
        int key = r.nextInt(Integer.MAX_VALUE);
    
        public String encode(String longUrl) {
            while (map.containsKey(key)) {
                key = r.nextInt(Integer.MAX_VALUE);
            }
            map.put(key, longUrl);
            return "http://tinyurl.com/" + key;
        }
    
        public String decode(String shortUrl) {
            return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
        }
    }

    public class Codec {
        String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        HashMap<String, String> map = new HashMap<>();
        Random rand = new Random();
        String key = getRand();
    
        public String getRand() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                sb.append(alphabet.charAt(rand.nextInt(62)));
            }
            return sb.toString();
        }
    
        public String encode(String longUrl) {
            while (map.containsKey(key)) {
                key = getRand();
            }
            map.put(key, longUrl);
            return "http://tinyurl.com/" + key;
        }
    
        public String decode(String shortUrl) {
            return map.get(shortUrl.replace("http://tinyurl.com/", ""));
        }
    }
}
