#Pseudocode

- Take input from the user using Prompt
- Pass input as a parameter to function that returns the number of combos
	- This function uses the following algorithm where each loop is nested:
		- Loop through the number of quarters as long as is less than the cents/25
		- Loop through the number of dimes as long as is less than the remaining cents
		- Loop through the number of nickels as long as is less than remaning cents
	- Send the number of each coin to a printing function
- The printing function calculates the remaning cents and prints that as pennies
- Prints the number of combinations in main
		
- The overall effect is that the function starts with pennies and then moves upwards using nickels,
then dimes, then quarters. After it is done with two coin combos, it moves to three coin combos, and
then moves up the list using combos of four coins.

####This is the pseudocode you will need for the Coins.java assignment. If you have any questions,
feel free to contact me.
