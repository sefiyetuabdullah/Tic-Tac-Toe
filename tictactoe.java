import java.util.Scanner ;
class Main {
  public static void main(String[] args) {
    //variable initializes number (will later be used to fill the array)
    int number=0;
    //variable sets the computers default character
    String computer =  "O";
    
    //array sets the default tic tac toe board
    String[][] board = {{" "," "," "},{" "," "," "},{" "," "," "}};
    
    //calls method to print the header
    header();

    //ask player for their name and character
    System.out.print("Please enter your name:  ");
    Scanner keyboard = new Scanner(System.in);
    String name = keyboard.next();
    System.out.println();
    System.out.println("Hello " + name + "!");
    System.out.print("Choose your character - X or O: ");
    //players character is set as "object"
    String object = keyboard.next();

    //if player chooses a character that is not X or O, have player reselect until they choose an X or O.
    while(!object.equals("O") && !object.equals("X"))
    {
      System.out.println();
      System.out.println("Oops! Please choose an X or O!");
      System.out.println("\033[3m-case sensitive-\033[0m");
      System.out.println();
      String reselect = keyboard.next();
      object = object.replaceAll(object, reselect);
    }
    //if player chooses "O" (the default character for the computer) replace the default character with "X"
    if (object.equals("O"))
    {
      computer = computer.replaceAll( "O" , "X" );
    }
    //call method runGame to start the game
    runGame(computer,name,object,board,number);
  }

  //This method runs the game from start to finish (after asking your character and name)
  public static void runGame(String computer, String name, String object,String [] [] board, int number)
  {
    //this section shows the instructions on how to play
    Scanner keyboard = new Scanner(System.in);
    System.out.println();
    System.out.println("_____________________________________");
    System.out.println();
    System.out.println("\033[0;1mHow to Play:\033[0m");
    System.out.println("\033[3mThe computer will play as \033[0m" + computer);
    System.out.println("\033[3mTo quit, press 999.\033[0m");
    System.out.println();
    System.out.print("Choose a row and column to place your " + object);
    System.out.println();
    
    //calls the method to print the default board so that players can see where they can move their first character
    printBoard(board);

    //System asks you to enter the first number to place your character
    System.out.print("Column: ");
    int column = keyboard.nextInt();
    System.out.print("Row: ");
    int row = keyboard.nextInt();
    
    //this method converts the row and column input into numbers, then players characters and fills the board
    inputNumber(board,object,computer,column,row,number);

    //if number is not between 1 and 3, ask player to reselect. If they reselect, run the game. If they select 999 end the game.
    if(column != 999)
    {
    while(column>3 || column<1)
            {
            System.out.println();
            System.out.print("Oops! Didn't catch that, please choose a column number between 1 and 3: ");
            int colAgain = keyboard.nextInt();
            column = colAgain;
            System.out.println();
            }
    
    while(row>3 || row<1)
            {
            System.out.println();
            System.out.print("Oops! Didn't catch that, please choose a row number between 1 and 3: ");
            int rowAgain = keyboard.nextInt();
            row = rowAgain;
            System.out.println();
            }

    //loop that will continue to ask you for column & row until the computer/player/nobody wins or until 10 moves have been made
    gameloop:
    for(int i = 0; i < 10; i++)
    {
      //Asks for player to input column number
		  System.out.print("Column: ");
      column = keyboard.nextInt();
        //if number is not between 1 and 3, ask the player to re-enter
      while(column>3 || column<1)
            {
          //if the number entered is 999 the game will end.
            if(column == 999)
            {
            System.out.println("Thanks for playing!");
            break gameloop;
            }
            else
            {
            System.out.println();
            System.out.print("Oops! Didn't catch that, please choose a column  number between 1 and 3: ");
            int colAgain = keyboard.nextInt();
            column = colAgain;
            System.out.println();
            }
            }
          
    //Asks for player to input row number
    System.out.print("Row: ");
    row = keyboard.nextInt();
    //if number is not between 1 and 3, ask the player to re-enter
    while(row>3 || row<1)
            {
            if(row == 999)
            {
            System.out.println("Thanks for playing!");
            break gameloop;
            }
            else
            {
            System.out.println();
            System.out.print("Oops! Didn't catch that, please choose a row number between 1 and 3: ");
            int rowAgain = keyboard.nextInt();
            row = rowAgain;
            System.out.println();
            }
            }
      
      //method to convert row and column number into characters on the array and determine computers next move
      inputNumber(board,object,computer,
      column,row,number);  
        
      //methods to determine the winner
      computerWinner(board,name, computer,object, number);
      playerWinner(board,name,object,computer,number);
      noWinner(board,name,object,computer,number);
      keyboard.nextLine();  
    }
    }
    //if row is 999, quit the game.
    else
    {
      System.out.println("Thanks for playing!");
		}
  }
     
//This method will convert the numbers into character moves and also controls the computers moves
public static void fillBoard(String [] [] board, int number, String object, String computer)
{   
  //if player enters number 1 and if it is not the last move for the player, move the computers character.
    if (number == 1 && !lastMove(board,object) && !board[0][0].equals(computer))
    {
      //the players character will be placed in spot 1 
      board [0][0] = object;
      //calls method to determine computers next move
      computerDecision(board,number,computer, object);
    }
    //if it is the players last move, do not move the computers character
    else if (number == 1 && lastMove(board,object))
    {
      board[0][0] = object;
    }
    
  //if player enters number 2 and if it is not the last move for the player, move the computers character.
    if (number == 2 && !lastMove(board,object) && !board[0][1].equals(computer))
    {
      //the players character will be placed in spot 2 
      board [0][1] = object;
      //calls method to determine computers next move
      computerDecision(board,number,computer,object);
    }
    //if it is the players last move, do not move the computers character
    else if (number == 2 && lastMove(board,object))
    {
      board[0][1] = object;
    }


  //if player enters number 3 and if it is not the last move for the player, move the computers character.
    if (number == 3 && !lastMove(board,object) && !board[0][2].equals(computer))
    {
      //the players character will be placed in spot 3
      board [0][2] = object;
      //calls method to determine computers next move
      computerDecision(board,number,computer,object);
    }
    //if it is the players last move, do not move the computers character
    else if (number == 3 && lastMove(board,object))
    {
      board[0][2] = object;
    }
  

  //if player enters number 4 and if it is not the last move for the player, move the computers character.
    if (number == 4 && !lastMove(board,object))
    {
      //the players character will be placed in spot 4
      board [1][0] = object;
      //calls method to determine computers next move
      computerDecision(board,number,computer,object);
    }
    //if it is the players last move, do not move the computers character
    else if (number == 4 && lastMove(board,object))
    {
      board[1][0] = object;
    }

  //if player enters number 5, and if it is not the last move for the player, move the computers character.
    if (number == 5 && !lastMove(board,object) && !board[1][1].equals(computer))
    {
     //the players character will be placed in spot 5 
      board [1][1] = object;
      //calls method to determine computers next move
      computerDecision(board,number,computer,object);
    }

    //if it is the players last move, do not move the computers character
    else if (number == 5 && lastMove(board,object))
    {
      board[1][1] = object;
    }
    

  //if player enters number 6, and if it is not the last move for the player, move the computers character.
    if (number == 6 && !lastMove(board,object) && !board[1][2].equals(computer))
    {
      //the players character will be placed in spot 6 
      board [1][2] = object;
      //calls method to determine computers next move
      computerDecision(board,number,computer,object);
    }

    //if it is the players last move, do not move the computers character
    else if (number == 6 && lastMove(board,object))
    {
      board[1][2] = object;
    }

  //if player enters number 7, and if it is not the last move for the player, move the computers character.
    if (number == 7 && !lastMove(board,object) && !board[2][0].equals(computer))
    {
      //the players character will be placed in spot 7
      board [2][0] = object;
      //calls method to determine computers next move
      computerDecision(board,number,computer,object);
    }
    //if it is the players last move, do not move the computers character
    else if (number == 7 && lastMove(board,object))
    {
      board[2][0] = object;
    }
  
  //if player enters number 8, and if it is not the last move for the player, move the computers character.
    if (number == 8 && !lastMove(board,object) && !board[2][1].equals(computer))
    {
      //the players character will be placed in spot 8.
      board [2][1] = object;
      //calls method to determine computers next move
      computerDecision(board,number,computer,object);
    }
    //if it is the players last move, do not move the computers character
    else if (number == 8 && lastMove(board,object))
    {
      board[2][1] = object;
    }

  //if player enters number 9, and if it is not the last move for the player, move the computers character.
    if (number == 9 && !lastMove(board,object) && !board[2][2].equals(computer) && !board[2][2].equals(computer))
    {
      //the players character will be placed in spot 9
      board [2][2] = object;
      //calls method to determine computers next move
      computerDecision(board,number,computer,object);
    }

    //if it is the players last move, do not move the computers character
    else if (number == 9 && lastMove(board,object))
    {
      board[2][2] = object;
    }  
  
  //if number entered is already occupied by computer character, run error message and have player try again. 
  else
    {
      tryAgain(board,number,computer);
    }

  //calls the method to print the board with the new characters.
      printBoard(board);
}

//This method makes a player reselect a number if the computer has already chosen it
public static void tryAgain(String [] [] board, int number, String computer)
{
 if(number == 1 && board[0][0].equals(computer))
  {
  System.out.println();
  System.out.println("\033[3mOops! Choose a new number!\033[0m");
  }

  if(number == 2 && board[0][1].equals(computer))
  {
  System.out.println();
  System.out.println("\033[3mOops! Choose a new number!\033[0m");
  }

  if(number == 3 && board[0][2].equals(computer))
  {
  System.out.println();
  System.out.println("\033[3mOops! Choose a new number!\033[0m");
  }

  if(number == 4 && board[1][0].equals(computer))
  {
  System.out.println();
  System.out.println("\033[3mOops! Choose a new number!\033[0m");
  }

  if(number == 5 && board[1][1].equals(computer))
  {
  System.out.println();
  System.out.println("\033[3mOops! Choose a new number!\033[0m");
  }

  if(number == 6 && board[1][2].equals(computer))
  {
  System.out.println();
  System.out.println("\033[3mOops! Choose a new number!\033[0m");
  }

  if(number == 7 && board[2][0].equals(computer))
  {
  System.out.println();
  System.out.println("\033[3mOops! Choose a new number!\033[0m");
  }

  if(number == 8 && board[2][1].equals(computer))
  {
  System.out.println();
  System.out.println("\033[3mOops! Choose a new number!\033[0m");
  }

  if(number == 9 && board[2][2].equals(computer))
  {
  System.out.println();
  System.out.println("\033[3mOops! Choose a new number!\033[0m");
  }
}

//this method decides the next position for computer. It will choose a winning move or a random position.
public static void computerDecision(String [][]board, int number, String computer, String object)
{  
  //winning plays
  //horizontal wins 
    //if player is at 1 and 2, and computer is not at 3, place computer at 3 to win
    if(board[0][0].equals(object) && board[0][1].equals(object) && !board[0][2].equals(computer))
      {
      board[0][2] = computer;
      }
    
    //if player is at 4 and 5, and computer is not at 6, place computer at 6 to win
    else if(board[1][0].equals(object) && board[1][1].equals(object) && !board[1][2].equals(computer))
      {
      board[1][2]= computer;
      }
    
    //if player is at 7 and 8, and computer is not at 9, place computer at 9 to win
    else if(board[2][0].equals(object) && board[2][1].equals(object) && !board[2][2].equals(computer))
      {
      board[2][2]= computer;
      }
    
    //if player is at 2 and 3, and computer is not at 1, place computer at 1 to win
    else if(board[0][1].equals(object) && board[0][2].equals(object) && !board[0][0].equals(computer))
      {
      board[0][0]= computer;
      }

    //if player is at 5 and 6, and computer is not at 4, place computer at 4 to win
    else if(board[1][1].equals(object) && board[1][2].equals(object) && !board[1][0].equals(computer))
      {
      board[0][1]= computer;
      }

    //if player is at 8 and 9, and computer is not at 7, place computer at 7 to win
    else if(board[2][1].equals(object) && board[2][2].equals(object) && !board[2][0].equals(computer))
      {
      board[2][0]= computer;
      }

    //if player is at 1 and 3, and computer is not at 2, place computer at 2 to win
    else if(board[0][0].equals(object) && board[0][2].equals(object) && !board[0][1].equals(computer))
      {
      board[0][1]= computer;
      }

    //if player is at 4 and 6, and computer is not at 5, place computer at 5 to win
    else if(board[1][0].equals(object) && board[1][2].equals(object) && !board[1][1].equals(computer))
      {
      board[1][1]= computer;
      }

    //if player is at 7 and 9, and computer is not at 8, place computer at 8 to win
    else if(board[2][0].equals(object) && board[2][2].equals(object) && !board[2][1].equals(computer))
      {
      board[2][1]= computer;
      }

  //vertical wins

    //if player is at 1 and 4, and computer is not at 7, place computer at 7 to win
    else if(board[0][0].equals(object) && board[1][0].equals(object) && !board[2][0].equals(computer))
      {
      board[2][0] = computer;
      }
    
    //if player is at 1 and 7, and computer is not at 4, place computer at 4 to win
    else if(board[0][0].equals(object) && board[2][1].equals(object) && !board[1][0].equals(computer))
      {
      board[1][0]= computer;
      }
    
    //if player is at 7 and 4, and computer is not at 1, place computer at 1 to win
    else if(board[2][0].equals(object) && board[1][0].equals(object) && !board[0][0].equals(computer))
      {
      board[0][0]= computer;
      }
    
    //if player is at 2 and 5, and computer is not at 8, place computer at 8 to win
    else if(board[0][1].equals(object) && board[1][1].equals(object) && !board[2][1].equals(computer))
      {
      board[2][1]= computer;
      }

    //if player is at 2 and 8, and computer is not at 5, place computer at 5 to win
    else if(board[0][1].equals(object) && board[2][1].equals(object) && !board[1][1].equals(computer))
      {
      board[1][1]= computer;
      }

    //if player is at 5 and 8, and computer is not at 2, place computer at 2 to win
    else if(board[1][1].equals(object) && board[2][1].equals(object) && !board[0][1].equals(computer))
      {
      board[0][1]= computer;
      }

    //if player is at 3 and 6, and computer is not at 9, place computer at 9 to win
    else if(board[0][2].equals(object) && board[1][2].equals(object) && !board[2][2].equals(computer))
      {
      board[2][2]= computer;
      }

    //if player is at 3 and 9, and computer is not at 6, place computer at 6 to win
    else if(board[0][2].equals(object) && board[2][2].equals(object) && !board[1][2].equals(computer))
      {
      board[1][2]= computer;
      }

    //if player is at 6 and 9, and computer is not at 3, place computer at 3 to win
    else if(board[1][2].equals(object) && board[2][2].equals(object) && !board[0][2].equals(computer))
      {
      board[0][2]= computer;
      }


  //Cross wins
    
    //if player is at 1 and 9, and computer is not at 5, place computer at 5 to win
    else if(board[0][0].equals(object) && board[2][2].equals(object) && !board[1][1].equals(computer))
      {
      board[1][1]= computer;
      }

    //if player is at 3 and 7, and computer is not at 5, place computer at 5 to win
    else if(board[0][2].equals(object) && board[2][0].equals(object) && !board[1][1].equals(computer))
      {
      board[1][1]= computer;
      }

    //if player is at 1 and 5, and computer is not at 9, place computer at 9 to win
    else if(board[0][0].equals(object) && board[1][1].equals(object) && !board[2][2].equals(computer))
      {
      board[2][2]= computer;
      }
    
    //if player is at 5 and 9, and computer is not at 1, place computer at 1 to win
    else if(board[1][1].equals(object) && board[2][2].equals(object) && !board[0][0].equals(computer))
      {
      board[0][0]= computer;
      }
    
    //if player is at 3 and 5, and computer is not at 7, place computer at 7 to win
    else if(board[0][2].equals(object) && board[1][1].equals(object) && !board[2][0].equals(computer))
      {
      board[2][0]= computer;
      }

    //if player is at 7 and 5, and computer is not at 3, place computer at 3 to win
    else if(board[2][0].equals(object) && board[1][1].equals(object) && !board[0][2].equals(computer))
      {
      board[0][2]= computer;
      }

  
  //if there are no winning plays, move the computer character to the next open space.
  else
  {
  //labelled for loop, so that when "break label" is written it will break the outer loop.
  label:
  //outer loop starts at 0 and goes to 1
  for(int i = 0; i<2; i++)
  {
  //inner loop starts at 0 and goes to 1
    for (int j = 0; j<2; j++)
    {
    //if the array slot is not "O" or "X", place the computers character in that slot 
    if(!board[i][j].equals("O") && !board[i][j].equals("X"))
    {
    board[i][j] = computer;
    //if it is placed, break the loops (so that it wont place the computers character in more than one spot)
    break label;
    }
    
    //if the array slot (one movement to the left) is not "O" or "X", place the computers character in that slot
    else if (!board[i][j+1].equals("O") && !board[i][j+1].equals("X"))
    {
    board[i][j+1] = computer;
    //if it is placed, break the loops
    break label;
    }

    //if the array slot (one movement to the left and one down) is not "O" or "X", place the computers character in that slot
    else if (!board[i+1][j+1].equals("O") && !board[i+1][j+1].equals("X"))
    {
    board[i+1][j+1] = computer;
    //if it is placed, break the loop
    break label;
    }
    }
    }
  }
}

//this method prints the tic tac toe board
public static void printBoard(String [] [] board)
{
    System.out.println();
    System.out.println("       =     =     ");
    System.out.println("   "+board [0][0]+ "   =  " + board [0] [1] +"  =   "+board [0][2] + "     ");
    System.out.println("       =     =     ");
    System.out.println("=====================");
    System.out.println("       =     =     ");
    System.out.println("   "+board [1][0]+ "   =  " + board [1] [1] +"  =   "+board [1][2] + "     ");
    System.out.println("       =     =     ");
    System.out.println("=====================");
    System.out.println("       =     =     ");
    System.out.println("   "+board [2][0]+ "   =  " + board [2] [1] +"  =   "+board [2][2] + "     ");
    System.out.println("       =     =     ");
    System.out.println();
}

//method decides whether the computer wins by comparing winning plays in the array and determining if theyre the same and if they are the computers character. 
public static void computerWinner(String [] [] board, String name, String computer, String object, int number)
{
  //if the top 3 spots on the board are the computers character, the computer wins
  if (computer.equals(board[0][0]) && computer.equals(board[0][1]) && computer.equals(board[0][2]))
  {
  //calls method that states the computer wins and asks if you want to play again, then restarts the game or says thank you.
  computerWon(board,name,computer,object,number); 
  }

  //if the middle 3 spots on the board are the computers character, the computer wins
  if (computer.equals(board[1][0]) && computer.equals(board[1][1]) && computer.equals(board[1][2]))
  {
  //calls method that states the computer wins and asks if you want to play again, then restarts the game or says thank you.
  computerWon(board, name, computer,object,number); 
  }

  //if the bottom 3 spots on the board are the computers character, the computer wins
  if (computer.equals(board[2][0]) && computer.equals(board[2][1]) && computer.equals(board[2][2]))
  {
  //calls method that states the computer wins and asks if you want to play again, then restarts the game or says thank you.
  computerWon(board, name, computer,object,number); 
  }

  //if the first column on the board are the computers character, the computer wins
  if (computer.equals(board[0][0]) && computer.equals(board[1][0]) && computer.equals(board[2][0]))
  {
  //calls method that states the computer wins and asks if you want to play again, then restarts the game or says thank you.
  computerWon(board,name, computer,object,number); 
  }

  //if the middle column on the board are the computers character, the computer wins
  if (computer.equals(board[0][1]) && computer.equals(board[1][1]) && computer.equals(board[2][1]))
  {
  //calls method that states the computer wins and asks if you want to play again, then restarts the game or says thank you.
  computerWon(board,name, computer,object,number); 
  }
  //if the last column on the board are the computers character, the computer wins
  if (computer.equals(board[0][2]) && computer.equals(board[1][2]) && computer.equals(board[2][2]))
  {
  //calls method that states the computer wins and asks if you want to play again, then restarts the game or says thank you.
  computerWon(board, name, computer,object,number); 
  }
  
  //if the spots 3,5,7 on the board are the computers character, the computer wins
  if (computer.equals(board[0][2]) && computer.equals(board[1][1]) && computer.equals(board[2][0]))
  {
  //calls method that states the computer wins and asks if you want to play again, then restarts the game or says thank you.
  computerWon(board, name, computer,object,number); 
  }
  //if spots 1,5,9 on the board are the computers character, the computer wins
  if (computer.equals(board[0][0]) && computer.equals(board[1][1]) && computer.equals(board[2][2]))
  {
  //calls method that states the computer wins and asks if you want to play again, then restarts the game or says thank you.
  computerWon(board, name,computer,object, number);
  }
}

//method decides whether the player wins by comparing winning plays in the array and determining if theyre the same and if they are the players character. 
public static void playerWinner(String [] [] board,String name, String object, String computer, int number)
{
  //if the top 3 spots on the board are the players character, the player wins
  Scanner keyboard = new Scanner(System.in);
  if (object.equals(board[0][0]) && object.equals(board[0][1]) && object.equals(board[0][2]))
  {
    //calls method that states the player wins and asks if you want to play again, then restarts the game or says thank you.
    playerWon(board,name,computer,object,number);
  }

  //if the middle 3 spots on the board are the players character, the player wins
  if (object.equals(board[1][0]) && object.equals(board[1][1]) && object.equals(board[1][2]))
  {
    //calls method that states the player wins and asks if you want to play again, then restarts the game or says thank you.
    playerWon(board, name, computer,object,number); 
  }

  //if the bottom 3 spots on the board are the players character, the player wins
  if (object.equals(board[2][0]) && object.equals(board[2][1]) && object.equals(board[2][2]))
  {
    //calls method that states the player wins and asks if you want to play again, then restarts the game or says thank you.
    playerWon(board, name, computer,object,number);
  }

  //if the first column on the board are the players character, the player wins
  if (object.equals(board[0][0]) && object.equals(board[1][0]) && object.equals(board[2][0]))
  {
   //calls method that states the player wins and asks if you want to play again, then restarts the game or says thank you.
    playerWon(board, name, computer,object,number);
  }

  //if the middle column on the board are the players character, the player wins
  if (object.equals(board[0][1]) && object.equals(board[1][1]) && object.equals(board[2][1]))
  {
   //calls method that states the player wins and asks if you want to play again, then restarts the game or says thank you.
    playerWon(board, name,computer,object,number);
  }

  //if the last column on the board are the players character, the player wins
  if (object.equals(board[0][2]) && object.equals(board[1][2]) && object.equals(board[2][2]))
  {
   //calls method that states the player wins and asks if you want to play again, then restarts the game or says thank you.
    playerWon(board,name, computer,object,number);
  }

  //if the spots 3,5,7 on the board are the players character, the player wins
  if (object.equals(board[0][2]) && object.equals(board[1][1]) && object.equals(board[2][0]))
  {
   //calls method that states the player wins and asks if you want to play again, then restarts the game or says thank you.
    playerWon(board,name, computer,object,number);
  }

  //if the spots 1,5,9 on the board are the players character, the player wins
  if (object.equals(board[0][0]) && object.equals(board[1][1]) && object.equals(board[2][2]))
  {
   //calls method that states the player wins and asks if you want to play again, then restarts the game or says thank you.
    playerWon(board,name,computer,object,number);
  }
}

//method that states the player won
public static void playerWon(String [] [] board, String name, String computer, String object, int number)
{
  Scanner keyboard = new Scanner(System.in);
  System.out.println(name + " Wins! " );
  System.out.println();
  System.out.println("\033[0;1mCONGRATULATIONS!\033[0m");
  System.out.print("Would you like to play again? Y/N: ");
    String answer = keyboard.next();
    //if player enters "Y" to say they would like to play again,
    if (answer.equals("Y"))
    {
      //call method to reset the array and start the game over
      resetArray(board,name,computer, object, number);
    }
    //if they do not enter "Y" say thank you and end the game 
    else{
      System.out.println("Thank you for playing!");
    }
}

//method that states the computer won
public static void computerWon(String [] [] board, String name, String computer, String object, int number)
{
  Scanner keyboard = new Scanner(System.in);
  System.out.println("Computer Wins! Would you like to play again? Y/N");
    String answer = keyboard.next();
    //if player enters "Y" to say they would like to play again,
    if (answer.equals("Y"))
    {
      //call method to reset the array and start the game over
      resetArray(board,name,computer, object,number);
    }
    else{
      //if they do not enter "Y" say thank you and end the game
      System.out.println("Thank you for playing!");
    }
}

//method that states that nobody won
public static void catScratch(String [][] board, String name, String computer, String object, int number)
{
  Scanner keyboard = new Scanner(System.in);
  System.out.println("Catscratch! Would you like to play again? Y/N");
  String answer = keyboard.next();
  //if player enters "Y" to say they would like to play again,
  if (answer.equals("Y"))
    {
    //call method to reset the array and start the game over
    resetArray(board,name,computer,object,number);
    }
  else
    {
    //if they do not enter "Y" say thank you and end the game
    System.out.println("Thank you for playing!");
    }
}

//method will reset the array back to the default array to erase the player and computer characters and run the game from the beginning
public static void resetArray(String [] []board,String name, String computer, String object, int number)
{
  //reset the array
  board[0][0] = " ";
  board[0][1] = " ";
  board[0][2] = " ";
  board[1][0] = " ";
  board[1][1] = " ";
  board[1][2] = " ";
  board[2][0] = " ";
  board[2][1] = " ";
  board[2][2] = " ";

  //method calls to run the game from the beginning
  runGame(computer,name,object,board,number);
}

//method determines whether there is no winner, by determining if every spot on the board is filled with a player or computer character. 
public static void noWinner(String [][] board, String name, String computer, String object, int number)
{
  if ((board[0][0].equals("X")
     || board[0][0].equals("O"))
     && (board[0][1].equals("X")
     || board[0][1].equals("O"))
     && (board[0][2].equals("X")
     || board[0][2].equals("O"))
     && (board[1][0].equals("X")
     || board[1][0].equals("O"))
     && (board[1][1].equals("X")
     || board[1][1].equals("O"))
     && (board[1][2].equals("X")
     || board[1][2].equals("O"))
     && (board[2][0].equals("X")
     || board[2][0].equals("O"))
     && (board[2][1].equals("X")
     || board[2][1].equals("O"))
     && (board[2][2].equals("X")
     || board[2][2].equals("O")))
    {
    //calls method to say that nobody won and ask to play again
     catScratch(board,name,computer,object,number);
    }
    }

//if it is the last move for the player, this method stops the computer from making a move afterwards
public static boolean lastMove(String [] [] board, String object)
  {
    //if spot 1 is the players character and all of the rest of the spots are filled, do not move the computer character anywhere.
  if(board[0][0].equals(object) &&
    (board[0][1].equals("X")
    || board[0][1].equals("O"))
    && (board[0][2].equals("X")
    || board[0][2].equals("O"))
    && (board[1][0].equals("X")
    || board[1][0].equals("O"))
    && (board[1][1].equals("X")
    || board[1][1].equals("O"))
    && (board[1][2].equals("X")
    || board[1][2].equals("O"))
    && (board[2][0].equals("X")
    || board[2][0].equals("O"))
    && (board[2][1].equals("X")
    || board[2][1].equals("O"))
    && (board[2][2].equals("X")
    || board[2][2].equals("O")))
    {
      return true;
    }
  
    //if spot 2 is the players character and all of the rest of the spots are filled, do not move the computer character anywhere.
   else if(board[0][1].equals(object) &&
    (board[0][0].equals("X")
    || board[0][0].equals("O"))
    && (board[0][2].equals("X")
    || board[0][2].equals("O"))
    && (board[1][0].equals("X")
    || board[1][0].equals("O"))
    && (board[1][1].equals("X")
    || board[1][1].equals("O"))
    && (board[1][2].equals("X")
    || board[1][2].equals("O"))
    && (board[2][0].equals("X")
    || board[2][0].equals("O"))
    && (board[2][1].equals("X")
    || board[2][1].equals("O"))
    && (board[2][2].equals("X")
    || board[2][1].equals("O")))
    {
      return true;
    }
    //if spot 3 is the players character and all of the rest of the spots are filled, do not move the computer character anywhere.
  else if(board[0][2].equals(object) 
    &&(board[0][1].equals("X")
    || board[0][1].equals("O"))
    && (board[0][0].equals("X")
    || board[0][0].equals("O"))
    && (board[1][0].equals("X")
    || board[1][0].equals("O"))
    && (board[1][1].equals("X")
    || board[1][1].equals("O"))
    && (board[1][2].equals("X")
    || board[1][2].equals("O"))
    && (board[2][0].equals("X")
    || board[2][0].equals("O"))
    && (board[2][1].equals("X")
    || board[2][1].equals("O"))
    && (board[2][2].equals("X")
    || board[2][2].equals("O")))
    {
      return true;
    }

    //if spot 4 is the players character and all of the rest of the spots are filled, do not move the computer character anywhere.
  else if(board[1][0].equals(object) &&
    (board[0][1].equals("X")
    || board[0][1].equals("O"))
    && (board[0][2].equals("X")
    || board[0][2].equals("O"))
    && (board[0][0].equals("X")
    || board[0][0].equals("O"))
    && (board[1][1].equals("X")
    || board[1][1].equals("O"))
    && (board[1][2].equals("X")
    || board[1][2].equals("O"))
    && (board[2][0].equals("X")
    || board[2][0].equals("O"))
    && (board[2][1].equals("X")
    || board[2][1].equals("O"))
    && (board[2][2].equals("X")
    || board[2][2].equals("O")))
    {
      return true;
    }

    //if spot 5 is the players character and all of the rest of the spots are filled, do not move the computer character anywhere.
  else if(board[1][1].equals(object) &&
    (board[0][1].equals("X")
    || board[0][1].equals("O"))
    && (board[0][2].equals("X")
    || board[0][2].equals("O"))
    && (board[1][0].equals("X")
    || board[1][0].equals("O"))
    && (board[0][0].equals("X")
    || board[0][0].equals("O"))
    && (board[1][2].equals("X")
    || board[1][2].equals("O"))
    && (board[2][0].equals("X")
    || board[2][0].equals("O"))
    && (board[2][1].equals("X")
    || board[2][1].equals("O"))
    && (board[2][2].equals("X")
    || board[2][1].equals("O")))
    {
      return true;
    }

    //if spot 6 is the players character and all of the rest of the spots are filled, do not move the computer character anywhere.
  else if(board[1][2].equals(object) &&
    (board[0][1].equals("X")
    || board[0][1].equals("O"))
    && (board[0][2].equals("X")
    || board[0][2].equals("O"))
    && (board[1][0].equals("X")
    || board[1][0].equals("O"))
    && (board[1][1].equals("X")
    || board[1][1].equals("O"))
    && (board[0][0].equals("X")
    || board[0][0].equals("O"))
    && (board[2][0].equals("X")
    || board[2][0].equals("O"))
    && (board[2][1].equals("X")
    || board[2][1].equals("O"))
    && (board[2][2].equals("X")
    || board[2][1].equals("O")))
    {
      return true;
    }
  
    //if spot 7 is the players character and all of the rest of the spots are filled, do not move the computer character anywhere.
  else if(board[2][0].equals(object) &&
    (board[0][1].equals("X")
    || board[0][1].equals("O"))
    && (board[0][2].equals("X")
    || board[0][2].equals("O"))
    && (board[1][0].equals("X")
    || board[1][0].equals("O"))
    && (board[1][1].equals("X")
    || board[1][1].equals("O"))
    && (board[1][2].equals("X")
    || board[1][2].equals("O"))
    && (board[0][0].equals("X")
    || board[0][0].equals("O"))
    && (board[2][1].equals("X")
    || board[2][1].equals("O"))
    && (board[2][2].equals("X")
    || board[2][2].equals("O")))
    {
      return true;
    }
  
    //if spot 8 is the players character and all of the rest of the spots are filled, do not move the computer character anywhere.
  else if(board[2][1].equals(object) &&
    (board[0][1].equals("X")
    || board[0][1].equals("O"))
    && (board[0][2].equals("X")
    || board[0][2].equals("O"))
    && (board[1][0].equals("X")
    || board[1][0].equals("O"))
    && (board[1][1].equals("X")
    || board[1][1].equals("O"))
    && (board[1][2].equals("X")
    || board[1][2].equals("O"))
    && (board[2][0].equals("X")
    || board[2][0].equals("O"))
    && (board[0][0].equals("X")
    || board[0][0].equals("O"))
    && (board[2][2].equals("X")
    || board[2][2].equals("O")))
    {
      return true;
    }
    
    //if spot 9 is the players character and all of the rest of the spots are filled, do not move the computer character anywhere.
  else if(board[2][2].equals(object) &&
    (board[0][1].equals("X")
    || board[0][1].equals("O"))
    && (board[0][2].equals("X")
    || board[0][2].equals("O"))
    && (board[1][0].equals("X")
    || board[1][0].equals("O"))
    && (board[1][1].equals("X")
    || board[1][1].equals("O"))
    && (board[1][2].equals("X")
    || board[1][2].equals("O"))
    && (board[2][0].equals("X")
    || board[2][0].equals("O"))
    && (board[2][1].equals("X")
    || board[2][1].equals("O"))
    && (board[0][0].equals("X")
    || board[0][0].equals("O")))
    {
      return true;
    }
  
  else
    {
      return false;
    }
  }



//method shows tictactoe design in header
public static void ticTacToe()
{
    System.out.println("     X     X     ");
    System.out.println("     X     X     ");
    System.out.println("OOOOOXOOOOOXOOOOO");
    System.out.println("     X     X     ");
    System.out.println("     X     X     ");
    System.out.println("OOOOOXOOOOOXOOOOO");
    System.out.println("     X     X     ");
    System.out.println("     X     X     ");
    System.out.println();
}

//this section prints the header of the game and asks the player for their name and to choose a character
public static void header()
{
    System.out.println();
    System.out.println("~~~~~~~~~~~~~~~~~~~");
    System.out.println("Welcome to Tic Tac Toe!");
    System.out.println("~~~~~~~~~~~~~~~~~~~");
    System.out.println();
    ticTacToe();
    System.out.println();
    System.out.println("\033[3mYou will be playing against the computer.\033[0m");
    System.out.println();
    System.out.println("_____________________________________");
    System.out.println();
    System.out.println("\033[0;1mLet's Get Started\033[0m");
}

//This method converts the column and row input into numbers to fill the array.
public static void inputNumber(String [][] board, String object, String computer, int column, int row, int number)
{
  if(column==1 && row ==1)
  {
    number = 1;
    //this method changes the numbers to "X" or "O" and decides the computers next move
    fillBoard(board,number,object,computer);
  }
  if(column== 2 && row ==1)
  {
    number = 2;
    fillBoard(board,number,object,computer);

  }
  if(column== 3 && row ==1)
  {
    number = 3;
    fillBoard(board,number,object,computer);

  }
    if(column==1 && row ==2)
  {
    number = 4;
    fillBoard(board,number,object,computer);

  }
  if(column== 2 && row ==2)
  {
    number = 5;
    fillBoard(board,number,object,computer);

  }
  if(column== 3 && row ==2)
  {
    number = 6;
    fillBoard(board,number,object,computer);

  }
    if(column==1 && row ==3)
  {
    number = 7;
    fillBoard(board,number,object,computer);

  }
  if(column== 2 && row ==3)
  {
    number = 8;
    fillBoard(board,number,object,computer);

  }
  if(column== 3 && row ==3)
  {
    number = 9;
    fillBoard(board,number,object,computer);

  }
}
}
