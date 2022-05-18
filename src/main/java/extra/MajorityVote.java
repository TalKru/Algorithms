package extra;

import java.util.HashMap;

public class MajorityVote {

	public static void main(String[] args) {

		char[] chars = {'s','s','d','t','t','t','t','t','t','d','t','d','f','s','t','f'};

		System.out.println("Most frequent character: " + majorityVote(chars));
		System.out.println("Most frequent character: " + mostFreqChar(chars));
	}

	// will work only when majority (more than 50%) element is present
	public static char majorityVote(char[] chars) {

		int vote = 0;
		char cand = ' ';

		for(char c: chars) {

			System.out.println(cand);

			if(vote > 0) {
				if(cand == c) {
					++vote;
				}
				else { // cand != c
					--vote;
				}
			}
			else {
				++vote;
				cand = c;
			}
		}
		return cand;
	}

	public static char mostFreqChar(char[] chArr) {

		HashMap<Character, Integer> map = new HashMap<>();
		int freq = 0;
		char mstFrqChar = ' ';

		for(char c: chArr) {
			map.put(c, map.getOrDefault(c, 0)+1 );

			if(map.get(c) > freq) {
				freq = map.get(c);
				mstFrqChar = c;
			}
		}
		return mstFrqChar;
	}

}
