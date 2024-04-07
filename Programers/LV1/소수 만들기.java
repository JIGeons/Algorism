/*
**문제 설명**
주어진 숫자 중 3개의 수를 더했을 때 소수가 되는 경우의 개수를 구하려고 합니다. 숫자들이 들어있는 배열 nums가 매개변수로 주어질 때, nums에 있는 숫자들 중 서로 다른 3개를 골라 더했을 때 소수가 되는 경우의 개수를 return 하도록 solution 함수를 완성해주세요.
**제한사항**
- nums에 들어있는 숫자의 개수는 3개 이상 50개 이하입니다.
- nums의 각 원소는 1 이상 1,000 이하의 자연수이며, 중복된 숫자가 들어있지 않습니다.
*/

class Solution {
    public int solution(int[] nums) {
        int answer = 0;

        for (int i = 0; i < nums.length-2; i++) {
            for (int j = i + 1; j < nums.length-1; j++) {
                for (int h = j + 1; h < nums.length; h++) {
                    if (isPrime(nums[i] + nums[j] + nums[h]))
                        answer++;
                }
            }
        }

        return answer;
    }
    
    public boolean isPrime(int n) {
        if ( n < 2 ) return false;  // 1은 소수가 아님
        else {
            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) return false;   // 나머지연산을 했을 때 0이 나오면 소수가 아니므로 false;
            }
            return true;
        }
    }
}
