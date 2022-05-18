

/*
 Procedure Huffman(C):     // C is the set of n characters and related information
n = C.size
Q = priority_queue()
for i = 1 to n
    n = node(C[i])
    Q.push(n)
end for
while Q.size() is not equal to 1
    Z = new node()
    Z.left = x = Q.pop
    Z.right = y = Q.pop
    Z.frequency = x.frequency + y.frequency
    Q.push(Z)
end while
Return Q.pop // only the root left there



Procedure HuffmanDecompression(root, S):   // root represents the root of Huffman Tree
n = S.length                              // S refers to bit-stream to be decompressed
for i = 1 to n
    current = root
    while (current.left != NULL)  &&  (current.right != NULL)
        if S[i] is equal to '0'
            current = current.left
        else
            current = current.right
        endif
        i = i+1
    endwhile
    print current.symbol
endfor



psedo-code :
public void huffmanCode()
	for i=0; to n-1
		child = i
		parent = mat[child][3]
		while(parent != -1)
			if (mat[parent][1]==child) code[i]="0" + _code[i]
			else code[i]="1" + code[i]
			child = parent
			parent = mat[child][3]
		end-while
 	end-for
end-huffmanCode






 */
package graphs;

import java.util.Arrays;
import java.util.Collections;

public class HuffmanArray {

	public static void huffmanCode(int[] frequency) {

		int n = frequency.length;

		int[] freqHelper = new int[2*n-1];

		for (int i = 0; i < n; i++) {
			freqHelper[i] = frequency[i];
		}
		for (int i = n; i < 2*n-1; i++) {
			freqHelper[i] = Integer.MAX_VALUE;
		}
		// [20][30][50]-[0][0]
		// [   n      ] [n-1 ]

		int[][] mat = new int[2*n-1][3];

		int point = n;

		int minA;
		int minB;
		int son_0 = 0;
		int son_1 = 0;

		for (int i = 0; i < n-1; i++) { // O(n)


			//minA = Collections.min(Arrays.asList(_freq));
			minA = Arrays.stream(freqHelper).min().getAsInt(); // O(n) - find first min

			for (int j = 0; j < freqHelper.length; j++) { // O(n)
				if(freqHelper[j] == minA) {
					freqHelper[j] = Integer.MAX_VALUE;
					son_0 = j;
					break;
				}
			}
			minB = Arrays.stream(freqHelper).min().getAsInt(); // O(n) - find second min

			for (int j = 0; j < freqHelper.length; j++) { // O(n)
				if(freqHelper[j] == minB) {
					freqHelper[j] = Integer.MAX_VALUE;
					son_1 = j;
					break;
				}
			}

			freqHelper[point] = minA + minB;

			mat[point][1] = son_0;
			mat[point][2] = son_1;

			mat[son_0][0] = point;
			mat[son_1][0] = point;

			++point;
		}



		/////////////////////////////////////////////////////////////////////
		for (int i = 0; i < mat.length; i++) {
			System.out.println(Arrays.toString(mat[i]));
		}
		System.out.println("------------------------");
		System.out.println(Arrays.toString(freqHelper));
		System.out.println("------------------------");
		/////////////////////////////////////////////////////////////////////

		
		String code[] = new String[n]; // array of codewords
		
		for (int i = 0; i < n; i++) { // for each leaf - return code word
			code[i] = new String();
			int child = i;
			int parent = mat[child][0]; 
			
			while(parent != 0) {
				
				if (mat[parent][1] == child) { // left child
					code[i] = code[i] + "0";
				}
				else {
					code[i] = code[i] + "1"; 
				}
				
				child = parent;
				parent = mat[child][0];
			}
		}
		
		for (int j = 0; j < code.length; j++) {
			System.out.println(code[j]);
		}

	}
	// ==================================================================================





	public static void main(String[] args) {

		int[] freq = {12, 40, 15, 8, 25};

		huffmanCode(freq);



	} // main
}
