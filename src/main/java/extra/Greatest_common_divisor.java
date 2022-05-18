package sorts;

public class Greatest_common_divisor {

	public static void main(String[] args) {

		int x = 611; // input 1 - 525
		int y = 806; // input 2 - 495

		int gcd = 0;

		if( x==0 || y==0){
			System.out.println("That's easy! GCD = " + gcd);
			System.exit(0);
		} // End of if

		
		
		else if ( x == y) {
			System.out.println( "That's easy! GCD = " + x );
			System.exit(0);
		}

		else if ( x > y){
//////////////////////////////////////////////////////////////////////////////////////
			for(int i = y ; i > 0 ; i--){
				
				if( x%i == 0 && y%i==0){
					System.out.println("That's not that easy, but GCD = " + i);
					System.exit(0);
				}
				
			}
//////////////////////////////////////////////////////////////////////////////////////
		}

		else if ( y > x){
//////////////////////////////////////////////////////////////////////////////////////
			for (int i = x ; i > 0 ; i--){
				
				if( x%i == 0 && y%i == 0){
					System.out.println("That's not that easy, but GCD = " + i);
					System.exit(0);
				}
				
				
			}
//////////////////////////////////////////////////////////////////////////////////////
		}





	} // End of Main.




} // End of CODE.
