/*
Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. 
For numbers which are multiples of both three and five output “FizzBuzz”.

Example:

n = 15,

Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
*/

class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        
         for(int index = 1; index <= n; index++) {
            if(index%3 == 0 && index%5 == 0) {
                result.add("FizzBuzz");
            }
            else if(index%3 == 0) {
                result.add("Fizz");
            }
            else if(index%5 == 0) {
                result.add("Buzz");
            }
            else {
                result.add(Integer.toString(index));
            }
        }
        
        return result;
    }
}