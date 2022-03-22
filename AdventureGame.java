import java.util.Random;
import java.util.Scanner;

public class AdventureGame {
	private static String playerName;
	private static boolean hasStone = false;
	private static boolean onHilly = false;
	private static boolean onSmooth = false;
	private static boolean onRocky = false;
	private static String gear="";
	private static int punishmentCount=1;
	private static int beastCount=1;
	private static int playerHealth=3;
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.println("\nWelcome to Mystery Mountain! Your goal:");
		System.out.println("Find the chest of gold.");
		System.out.println("What is your name?");
		playerName = console.nextLine();
		System.out.println("Hello, " + playerName + "!");
		firstChoice(console);
	}

	public static void firstChoice(Scanner console) {
		//holds name of player and the three paths they can choose
		System.out.println("\nAs you make way on your quest, you come across 3 paths:");
		System.out.println("One rocky, the other hilly, the last smooth.");
		System.out.println("You may also enter a potential code in to unlock cheats.");
		System.out.println("Which path do you want to take? (r=rocky, h=hilly, s=smooth)");
		String firstChoice = console.nextLine();
		if (firstChoice.equals("r")) {	//rocky path
			onRocky=true;
			System.out.println("You chose the rocky path!");
			System.out.println("As you stumble across the rocky terrain, you finally find a cavern to safely walk into.");
			System.out.println("However, upon entering, you hear a low grumble, then heavy footsteps that slowly get closer.");
			fleeChoice(console);
			rockyPath(console);
			endingOne(console);

		} else if (firstChoice.equals("h")) {	//hilly path
			onHilly=true;
			System.out.println("You chose the hilly path!");
			System.out.println("After awhile of walking, you find a room of some sort.");
			hillyPath(console);
		} else if (firstChoice.equals("s")) {	//smooth path
			onSmooth=true;
			System.out.println("You chose the smooth path!");
			smoothPath(console);
			endingTwo(console);
		} else if (firstChoice.equals("112") || firstChoice.equals("212") || firstChoice.equals("122") || firstChoice.equals("222")) {
			//little easter egg, figure it out through the correct dialogue choices in smooth path
			secret();
			firstChoice(console);
			System.out.println();
		} else {
			firstChoice(console);
		}
	}
	public static void fleeChoice(Scanner console){
		//if player flees and chooses right way, they can choose another path. Otherwise, they lose one heart and have to fight beast
		//if player continues, normal 3 life beast fight
		String flee = "";
		String what = "";
		System.out.println("You can attempt to flee, or continue (f=flee, any key continues).");
		String scary = console.nextLine();
		if (scary.equals("f")) {
			System.out.println("As you flee, the footsteps get louder and closer. You try to remember the path you took,");
			System.out.println("but you come across a cross roads. Do you go left or right? (l=left, r=right)");
			flee = console.nextLine();
			if (flee.equals("r")) {
				System.out.println("You run right, and see a tunnel of light. You chose the right way!");
				System.out.println("Do you want to go back, go the hilly path, or smooth path? (b=back, h=hilly, s=smooth)");
				what = console.nextLine();
				if (what.equals("h")) {
					beastCount=1;
					hillyPath(console);
				} else if (what.equals("s")) {
					beastCount=1;
					smoothPath(console);
				}
			}
		}
		if (scary.equals("c") || flee.equals("l") || what.equals("b"))
			if (flee.equals("l")) {
				playerHealth = 2;
			}
	}
	public static void rockyPath(Scanner console) {
		//includes fighting mini game
		//similar to James Bond, attack, block, reload. attacking uses ammo, waiting reloads 1. Blocking blocks attacks
		beastCount=0;
		String hearts;
		System.out.println("A giant beast erupts through the wall in front of you and roars. The only way out will be defeating this thing.");
		System.out.println("You can attack, block, or wait (to rest). (a=attack, b=block, w=wait)");
		System.out.println("You each start at 1 stamina. Waiting restores 1 stamina. You have " + playerHealth + " lives.");
		System.out.println("The Beast has 3 lives.");
		System.out.println("Attacking uses 1 stamina. Blocking uses none. Good luck!");
		int computerHealth = 3;
		int playerStamina = 1;
		int computerStamina = 1;
		String computerChoice = "";
		Random rand = new Random();
		String fightChoice = console.nextLine();
		while ((playerHealth > 0 && computerHealth > 0) && !fightChoice.equals("cheat")) {	//main loop for this sequence
			int computerNumber = rand.nextInt(10);	//random choice for what beast does
			if (computerStamina != 0) {		//if beast doesn't have stamina, it has to rest just like the player
				if (computerNumber == 0 || computerNumber == 1 || computerNumber == 2 || computerNumber == 3) {	//0-3 attacks,4-7 blocks,8-9 waits
					computerChoice = "attacked";
				} else if (computerNumber == 4 || computerNumber == 5 || computerNumber == 6 || computerNumber == 7) {
					computerChoice = "blocked";
				} else {
					computerChoice = "waited";
				}
			} else {
				computerNumber=8;
				computerChoice = "waited";
			}
			if (fightChoice.equals("a")) {
				System.out.println("You attacked!");
				System.out.println("The Beast " + computerChoice + "!");
				playerStamina--;
				System.out.println("Your stamina is " + playerStamina);
			} else if (fightChoice.equals("b")) {
				System.out.println("You blocked!");
				System.out.println("The Beast " + computerChoice + "!");
				System.out.println("Your stamina is " + playerStamina);
			} else if (fightChoice.equals("w")) {
				System.out.println("You rested!");
				System.out.println("The Beast " + computerChoice + "!");
				playerStamina++;
				System.out.println("Your stamina is " + playerStamina);
			}
			if (playerStamina < 0) {
				System.out.println("You must rest!");
				fightChoice = "w";
				playerStamina++;
			}
			if (fightChoice.equals("a") && (computerNumber == 0 || computerNumber == 1 || computerNumber == 2 || computerNumber == 3)) {
				System.out.println("You both attacked and parried each other!");
				computerStamina--;
				System.out.println("The Beast's stamina is " + computerStamina);
			} else if (fightChoice.equals("b") && (computerNumber == 0 || computerNumber == 1 || computerNumber == 2 || computerNumber == 3)) {
				System.out.println("You successfully blocked!");
				computerStamina--;
				System.out.println("The Beast's stamina is " + computerStamina);
			} else if (fightChoice.equals("a") && (computerNumber == 4 || computerNumber == 5 || computerNumber == 6 || computerNumber == 7)) {
				System.out.println("The Beast successfully blocked!");
			} else if (fightChoice.equals("b") && (computerNumber == 4 || computerNumber == 5 || computerNumber == 6 || computerNumber == 7)) {
				System.out.println("You both blocked!");
			} else if (fightChoice.equals("w") && (computerNumber == 0 || computerNumber == 1 || computerNumber == 2 || computerNumber == 3)) {
				System.out.println("The Beast attacked as you were resting!");
				computerStamina--;
				playerHealth--;
				System.out.println("The Beast's stamina is " + computerStamina);
				if (playerHealth == 0) {
					loseScreen();
				}
			} else if (fightChoice.equals("w") && (computerNumber == 4 || computerNumber == 5 || computerNumber == 6 || computerNumber == 7)) {
				System.out.println("The Beast blocked while you were resting!");
			} else if (fightChoice.equals("a") && (computerNumber == 8 || computerNumber == 9)) {
				System.out.println("You did damage!");
				computerHealth--;
				computerStamina++;
				System.out.println("The Beast's stamina is " + computerStamina);
				if (computerHealth == 0) {
					System.out.println("You successfully defeated the Beast! Press enter to continue...");
				}
			} else if (fightChoice.equals("b") && (computerNumber == 8 || computerNumber == 9)) {
				System.out.println("While you blocked the Beast waited!");
				computerStamina++;
				System.out.println("The Beast's stamina is " + computerStamina);
			} else if (fightChoice.equals("w") && (computerNumber == 8 || computerNumber == 9)) {
				System.out.println("You both waited!");
				computerStamina++;
				System.out.println("The Beast's stamina is " + computerStamina);
			}
			if (playerHealth == 3) {
				hearts = "<3 <3 <3";
			} else if (playerHealth == 2) {
				hearts = "<3 <3";
			} else if (playerHealth == 1) {
				hearts = "<3";
			} else {
				hearts = "";
			}
			System.out.println(hearts);
			fightChoice = console.nextLine();
		}
	}

	public static void hillyPath(Scanner console) {
		//includes choosing equipment, sword, armor, or bow and arrow. Then certain scenario where player needed one of these
		//if they don't pick right thing, still chance at redemption, but more unlikely
		//if picked right thing, makes it much easier, hint in smooth path on what to take (bow and arrow)
		System.out.println("You make your way into the room. There is a choice of gear, but a sign reads:");
		System.out.println("\"Traveler, you may take only one.\" The choices are a sword, some armor, or a bow and arrow.");
		System.out.println("Do you choose to take only one item (1) or everything (2)?");
		String gearChoice = console.nextLine();
		if (gearChoice.equals("1")){
			System.out.println("Which item? (Sword=s, Armor=a, Bow and arrow=b");
			String secondGearChoice = console.nextLine();
			if (secondGearChoice.equals("s")){
				gear="Sword";
			} else if (secondGearChoice.equals("a")){
				gear="Armor";
			} else if (secondGearChoice.equals("b")){
				gear="Bow and arrow";
			} else {
				hillyPath(console);
			}
			System.out.println("You take the " + gear + " and continue on. Eventually you arrive at some sort of contraption,");
		} else if (gearChoice.equals("2")){
			gear="Bow and arrow";
			System.out.println("You take everything and continue on. Eventually you arrive at some sort of contraption,");
		}
		System.out.println("where a door is closed, but set to open on a piece of rope that lifts if an arrow is shot");
		System.out.println("through a target about 100 feet away.\n");
		if (gear.equals("Bow and arrow")){
			bowAndArrow(console);
		} else if (gear.equals("Sword")) {
			System.out.println("Unfortunately, you did not pick the bow and arrow.");
			System.out.println("Your options are: \nWack the wall with your sword to call for help.(1)");
			System.out.println("Try to throw your sword into the target.(2)");
			String hillyChoice = console.nextLine();
			if (hillyChoice.equals("1")){
				System.out.println("You knock the wall as hard as you can. It echoes throughout the room. Then you hear a low grumble.");
				tooLoud(console);
			} else if (hillyChoice.equals("2")){
				clumsy(console);
			}
		} else if (gear.equals("Armor")){
			System.out.println("Unfortunately, you did not pick the bow and arrow.");
			System.out.println("Your options are:\nBull rush the door to try to budge it open.(1)");
			System.out.println("Try to throw your helmet into the target.(2)");
			String armorChoice=console.nextLine();
			if (armorChoice.equals("1")){
				System.out.println("You get in a running stance and explode towards to door. You get closer and closer.");
				System.out.println("You ram into it and get knocked on the ground hard. The metal on metal clang echoes throughout.");
				System.out.println("The door doesn't budge.\n");
				tooLoud(console);
			} else if (armorChoice.equals("2")){
				clumsy(console);
			}
		}
		if (gearChoice.equals("2")){
			System.out.println("\nYou confidently stroll onwards with you armor, sword, and bow.");
			System.out.println("However, as you get close to the door, it slams shut. You hear some shuffling behind you.\n");
			System.out.println("\"Did you not see the sign?\" A voice behind you said. You spin around. A stranger is standing there,");
			System.out.println("with a disappointed look on their face. \"Unfortunately, you will have to pay for that.\" They then");
			System.out.println("pull a lever and the bridge gives way. You fall into a pool of water, and see another sign:\n");
			if (punishmentCount>0) {
				punishment(console);
			} else {
				loseScreen();
			}
		} else {
			endingOne(console);
		}
	}
	public static void bowAndArrow(Scanner console){
		Random rand = new Random();
		int windFactor=rand.nextInt(6)+1;
		String windDirection="";
		if (windFactor%2==0){
			windDirection="left";
		} else {
			windDirection="right";
		}
		System.out.println("Good thing you picked the bow and arrow!\nTo aim, fire against the wind factor.");
		System.out.println("The wind factor is: " + windFactor);
		System.out.println("The wind direction is: " + windDirection + "\n");
		System.out.println("For every 10 times wind factor amount of feet, the arrow will move over to the " + windDirection + " by 1 foot.");
		System.out.println("Where will you shoot the arrow? (0 is the target)\n10\t5\t3\t2\t1\t0\t1\t2\t3\t5\t10\n(If between numbers, round down)");
		int shot=console.nextInt();
		if ((100/(10*windFactor)==shot)||(shot==112)){		//basically 10 divided by wind factor, simple math problem
			System.out.println("Your arrow perfectly hits right into the bullseye. A weight falls and bring the rope down, opening the door.");
		} else {
			System.out.println("Your arrow missed the target, and struck the wall! A loud echo is heard.");
			if (beastCount==1) {
				tooLoud(console);
			} else {
				System.out.println("\"That was my only arrow. I'm sorry,\" the Stranger says. They then pull a lever that drops the bridge");
				System.out.println("that you are standing on.");
				if (punishmentCount>0) {
					punishment(console);
				} else {
					loseScreen();
				}
			}
		}
	}
	public static void tooLoud(Scanner console) {
		if (beastCount == 1) {	//if beast is still alive, player must fight it
		System.out.println("You then hear a low grumble. Large and slow footsteps accompany the grumbles, and get closer and closer. You can only wait.");
		System.out.println("Finally a large beast breaks through the wall behind you. It looks very angry and hungry for a fight.");
		rockyPath(console);
		endingThree(console);
		} else {	//if already defeated beast, makes it easier
			System.out.println("A minute passes with no sound. Then the Stranger emerges from the entrance. ");
			System.out.println("\"You should have taken the bow and arrow!\" They say. \"Lucky for you, I took it with me.\"");
			System.out.println("They take the bow and arrow out and hand it to you.");
			bowAndArrow(console);
		}
	}
	public static void clumsy(Scanner console){	//similar to right above, just scripted differently based on situation
		System.out.println("As you get prepared to throw your " + gear + ", you realize how difficult this will be.");
		System.out.println("\"Stop!\" You hear. The Stranger emerges from the entrance. \"You should have taken the bow and arrow!");
		System.out.println("Lucky for you, I took it with me.\" They take the bow and arrow out and hand it to you.");
		bowAndArrow(console);
	}
	public static void smoothPath(Scanner console) {
		//dialogue tree, if pick right choices, person will tell you correct container of gold, otherwise they lie
		System.out.println("You encounter another person on the path. You can:");
		System.out.println("Talk (t), Attack (a), or Sneak (s) by them.");
		String encounterChoice = console.nextLine();
		if (encounterChoice.equals("t")){	//talk tree
			System.out.println("You can say:");
			System.out.println("Excuse me, who are you? (1)");
			System.out.println("Get out of my way! (2)");
			String talkChoice = console.nextLine();
			if (talkChoice.equals("1")){
				System.out.println("\"I am but a simple traveler, similar to you, " + playerName + "\".");
				System.out.println("You can say: \nHow do you know my name? (1) \nWhat is your name? (2)");
				String secondChoice = console.nextLine();
				if (secondChoice.equals("1")){
					howName(console);
				} else if (secondChoice.equals("2")){
					whatStrangerName(console);
				}
			} else if (talkChoice.equals("2")){
				System.out.println("\"Very well, " + playerName + ", however then you will not find my gold,\" they say.");
				System.out.println("You can say: \nHow do you know my name?(1) \nWhat gold?(2)");
				String nextChoice = console.nextLine();
				if (nextChoice.equals("1")){
					howName(console);
				} else if (nextChoice.equals("2")){
					whatGold(console);
				}
			}
		} else if (encounterChoice.equals("a")){	//attack tree, makes player fight beast or second chance arena or they lose
			System.out.println("You try to attack, but they react with lightning quick speed and block!");
			System.out.println("\"That was unwise,\" the Stranger says. They pound the wall behind them with their sword, and you hear a low rumble.");
			if (beastCount==1) {
				System.out.println("Slow and loud footsteps get closer. The Stranger smiles softly. \"I could have helped you.\"\n");
				System.out.println("Suddenly you hear a roar behind you. You whip around and see a terrifying beast standing above you.");
				System.out.println("You look behind you and the Stranger is gone.");
				rockyPath(console);
				endingThree(console);
			} else {
				System.out.println("The Stranger then slips behind a wall behind them. The ground below you rumbles louder, then gives out.");
				System.out.println("You all down deep into a dark tunnel.");
				if (punishmentCount>0) {
					punishment(console);
				} else {
					loseScreen();
				}
			}

		} else {	//sneak choice, can still talk with them, or continue on to the hilly path
			System.out.println("As you are sneaking by, the stranger says:");
			System.out.println("\"You can leave, but then you won't know where the gold is.\"");
			System.out.println("Do you Respond (r), or Continue (c)?");
			String sneakChoice = console.nextLine();
			if (sneakChoice.equals("r")){
				System.out.println("Do you want to ask: What gold? (1) or Who are you? (2)");
				String nextChoice = console.nextLine();
				if (nextChoice.equals("1")){
					whatGold(console);
				} else if (nextChoice.equals("2")){
					whatStrangerName(console);
				}
			} else if (sneakChoice.equals("c")){
				System.out.println("You ignore them and continue on.");
				System.out.println("After awhile of walking, you come across some sort of room.");
				hillyPath(console);
			}
		}
		endingTwo(console);//if pick right answers, gives you special stone. if player gets stone, the crate stranger says will be it.
	}//otherwise, if no stone, stranger will say the false crate
	public static void howName(Scanner console){		//dialogue possibility, asks how they know your name
		System.out.println("\"Oh, I've known you for quite some time, my friend,\" they say.");
		System.out.println("The Stranger has a smile in their eyes, and you want to believe them,");
		System.out.println("but something about them seems off. \"I can give you what you are looking for.\"");
		System.out.println("You can say: \nWhere is the gold? (demanding)(1) \nAnd what do I want? (subtle)(2)");
		String thirdChoice = console.nextLine();
		System.out.println("The Stranger smiles.");
		if (thirdChoice.equals("1")){
			badChoice();
		} else if (thirdChoice.equals("2")){
			goodChoice();
		}
	}
	public static void whatGold(Scanner console){	//dialogue possibility, asks what gold
		System.out.println("\"Oh, you know what gold. You have been searching for treasure for months,\" they say.");
		System.out.println("You don't know who this is or how they know this, but you get the sense they won't tell you either.");
		System.out.println("You can say: \nWell then show it to me, or move.(1) \nHow do I get your gold then, stranger?(2)");
		String nextChoice = console.nextLine();
		System.out.println("The Stranger smiles.");
		if (nextChoice.equals("1")){
			badChoice();
		} else if (nextChoice.equals("2")){
			goodChoice();
		}
	}
	public static void whatStrangerName(Scanner console){	//dialogue possibility, asks what is the strangers name
		System.out.println("\"I go by many names, but none are important,\" they say, dodging the question.");
		System.out.println("\"I am here to help you.\" Do you say: \nI trust you.(1) \nI can't trust you.(2)");
		String thirdChoice = console.nextLine();
		System.out.println("The Stranger smiles.");
		if (thirdChoice.equals("1")){
			badChoice();
		} else if (thirdChoice.equals("2")){
			goodChoice();
		}
	}
	public static void goodChoice(){	//if player selects the correct options, they get the truth
		System.out.println("\"The crate with the gold is crate 1,\" they say.");
		System.out.println("\"Take this stone,\" they say. \"It means I trust you, and you can trust me.\"");
		if (!onHilly) {
			System.out.println("\"Also, if the moment arises, take the bow and arrow.\"");
		}
		System.out.println("You take the stone, examine it, and continue. \nBefore long, you reach the crates.");
		hasStone=true;
	}
	public static void badChoice(){		//if player selects the wrong options, they get the lie (still crate 1, hasStone dictates if it is right or not)
		System.out.println("\"The crate with the gold is crate 1,\" they say.");
		if (!onHilly) {
			System.out.println("\"Also, if the moment arises, don't take the bow and arrow.\"");
		}
		System.out.println("The Stranger dissipates into the darkness.");
	}
	public static void winScreen(){	//if player wins, game ends and they see this
		System.out.println("Congratulations! YOU WIN!");
		System.out.println("Credits: Me");
		System.exit(1);
	}
	public static void loseScreen(){	//if player loses, game ends and they see this
		System.out.println("YOU LOSE");
		System.exit(1);
	}
	public static void secret(){	//if player at first choice does the correct code, they see this for cheat codes
		System.out.println("Congrats! You cracked the code!");
		System.out.println("Cheat codes: In some scenarios, type \"cheat\" to pass it.");
		System.out.println("Some scenarios using numbers, type \"112\" to pass it (crates, bow and arrow shot).");
	}
	public static void punishment(Scanner console){	//the second chance arena, consists of array of possible memory questions
		punishmentCount=0;	//player has been here, they can't go here again. Second chance only, no third chances
		Random rand = new Random();
		String[] questions1 = new String[3];//all possible questions player could get, in groups of 3
		questions1[0]="Did you go on the hilly path?(y/n)";
		questions1[1]="Did you go on the smooth path?(y/n)";
		questions1[2]="Did you go on the rocky path?(y/n)";
		String[] questions2 = new String[3];
		questions2[0]="How many paths are there?";
		questions2[1]="What is your name?";
		questions2[2]="What is the Stranger's name?";
		String[] questions3 = new String[2];
		questions3[0]="What is the name of the Adventure you are on?";
		questions3[1]="What were all the path names?(list in order they were offered with commas and no spaces in between like apple,orange,banana)";
		System.out.println("Unfortunately, you made the wrong choice(s) to lead you down to the second chance arena.");
		System.out.println("You have one chance to redeem yourself, go back in time, and make better decisions.");
		int number1=rand.nextInt(3);
		System.out.println(questions1[number1]);
		String correct=console.next();	//all possible correct answers for each question for group 1:
		if (correct.equals("cheat")||(correct.equals("y") && onHilly&&number1==0)||(correct.equals("n")&&!onHilly&&number1==0)||((correct.equals("y")&&onSmooth&&number1==1)||(correct.equals("n")&&!onSmooth&&number1==1))||((correct.equals("y")&&onRocky&&number1==2)||(correct.equals("n")&&!onRocky&&number1==2))) {
			int number2=rand.nextInt(3);
			System.out.println("Correct.\n" + questions2[number2]);//to trip up those who made up a name
			String nextCorrect = console.next();	//all possible correct answers for each question for group 2:
			if (nextCorrect.equals("cheat")||(nextCorrect.equals("3")&&number2==0)||(nextCorrect.equals(playerName)&&number2==1)||(nextCorrect.equals("Stranger")&&number2==2)){
				int number3=rand.nextInt(2);
				System.out.println("Correct.\n" + questions3[number3]);
				String lastCorrect = console.next();	//all possible correct answers for each question for group 3:
				if (lastCorrect.equals("cheat")||(lastCorrect.equals("Mountain Mystery")&&number3==0)||(lastCorrect.equals("rocky,hilly,smooth")&&number3==1)){
					System.out.println("Fortunately for you, your soul has been redeemed. Good luck.");//if correct, sends back to beginning
					firstChoice(console);
				} else {	//if player loses here, they lose the game
					loseScreen();
				}
			} else {
				loseScreen();
			}
		} else {
			System.out.println("c");
			loseScreen();
		}
	}

	public static void endingOne(Scanner console){//first potential ending, random crates, one holds gold
		Random rand = new Random();
		System.out.println("Congrats, " + playerName + "! You continue on...\n");
		System.out.println("You find 3 crates, but only one has gold. If you choose the wrong");
		System.out.println("one, a trap door will open and you will fall into a dungeon. If you choose the right");
		System.out.println("one, you win! Do you want to test your chances, or continue your adventure and seek guidance? (t=test luck, l=leave)");
		String choice = console.next();
		if (choice.equals("l")){	//if player leaves, they go to smooth path for dialogue to get clarity on what crate
			System.out.println("You leave the crates of gold for another time in hopes to find some answers.");
			System.out.println("You continue on the path and walk for awhile.\n");
			smoothPath(console);
		} else if (choice.equals("t"))	//otherwise player has 1 in 3 chance to win, else they go to second chance/lose
		System.out.println("(1=crate 1, 2=crate 2, 3=crate 3)");
		int correctCrate = rand.nextInt(3) + 1;
		int crateChoice = console.nextInt();
		if (crateChoice == correctCrate || crateChoice==112) {
			System.out.println("Crate contents: Piles of gold");
			winScreen();
		} else {
			System.out.println("Crate contents: Dust, cobwebs, a dirty shoe.\n\n\n");
			if (punishmentCount>0) {
				punishment(console);
			} else {
				loseScreen();
			}
		}
	}
	public static void endingTwo(Scanner console){//very similar to ending 1, just after they talk with stranger
		System.out.println("As the Stranger described, you come across 3 different crates.");
		System.out.println("Do you trust the Stranger and open crate 1, or do you try a different crate?\n(1=crate 1, 2=crate 2, 3=crate 3)");
		int choice = console.nextInt();
		if ((choice==1&& hasStone)||(choice==112)){	//if player has stone, it is crate 1. If no stone, random chance b/w crate 2 and 3
			winScreen();
		} else if (choice==1&& !hasStone){
			if (punishmentCount>0) {
				punishment(console);
			} else {
				loseScreen();
			}
		} else if ((choice==2||choice==3)&&!hasStone){
			Random rand = new Random();
			int correctCrate=rand.nextInt(3)+1;
			if (choice==correctCrate){
				winScreen();
			} else {
				if (punishmentCount>0) {
					punishment(console);
				} else {
					loseScreen();
				}
			}
		} else {
			if (punishmentCount>0) {
				punishment(console);
			} else {
				loseScreen();
			}
		}
	}
	public static void endingThree(Scanner console){//the bad ending, ultimately player doesn't win or lose, unsatisfactory ending
		System.out.println("As the Beast is slain, it stumbles and falls into the door in front of you, breaking some wall supports.");
		System.out.println("The ceiling comes crashing down, and you escape through the back wall just in time. A chain reaction");
		System.out.println("of the mountain breaking and falling within itself begins.");
		System.out.println("\"Hurry!\" You hear the Stranger say. You did not know they were there, but you decide not to question it right now.");
		System.out.println("As you are running out, you pass by a large crate open and full of gold.");
		System.out.println("Do you leave now and have a higher chance of escaping, or do you try to grab the gold first? (l=leave, g=grab gold)");
		String greedy = console.nextLine();
		if (greedy.equals("l")){
			System.out.println("You start running out the way you came in. Stones are tumbling down behind you. Dust and sand are getting");
			System.out.println("thicker. It becomes harder and harder to breathe. Finally you emerge outside.\n");
			System.out.println("You look behind you and see everything collapse. The Stranger is nowhere to be found.");
			System.out.println("Do you want to go back in (1), or leave the Stranger and save yourself? (2)");
			String save = console.nextLine();
			if (save.equals("1")){
				System.out.println("You find another way in around the side. You go back in and call out to the Stranger.");
				System.out.println("No one responds. You head further in but can barely see anything through the dust in the air.\n");
				System.out.println("Finally you see them though some rubble trapped in another room. You call out to them.");
				System.out.println("\"You should have left when you had the chance,\" they say. \"There is no getting out.\"\n");
				System.out.println("You look behind you and see the way you came in is blocked. The Stranger smiles sadly.\n");
				end();
			} else if (save.equals("2")) {
				System.out.println("You continue on and leave empty handed, though luckily still alive.");
				System.out.println("As you walk away, you find it hard to live with your decision in leaving someone else behind.\n");
				end();
			}
		} else if (greedy.equals("g")){
			System.out.println("You grab the crate of gold. It weighs you down heavily. As you try to stumble to the exit,");
			System.out.println("a giant boulder falls on you, trapping you underneath. You look behind you, but the Stranger is gone.");
			System.out.println("The gold is spilled over the ground. Better luck next time.\n");
			loseScreen();
		} else {
			endingThree(console);
		}
	}
	public static void end(){	//ending screen for ending 3, where player doesn't really win nor lose
		System.out.println("THE END");
		System.exit(1);
	}
}