import "package:fluttertoast/fluttertoast.dart";

import "controllers.dart";
import "variables.dart";

class Functions{
  /// function that handle the logic of the cell at row [row] and column [column]
  static void cell_logic(int row,int column){
    Controllers.table_controllers[row][column].add(Variables.turn);
    Controllers.win_text_controller.add("No winner yet!");
    // update the UI

    Variables.table[row][column]=Variables.turn;
    Functions.change_turn();
    // update the table and the turn

    if(is_finished()) reset(message: "Tie!");
    // start a new round if the game ends up with a tie

    if(Functions.winner().isNotEmpty){
      // if a winner exists

      Variables.score[Functions.winner()[0]]++;
      // update winner's score

      Controllers.score_text_controller.add(
          "Score "
              "${Variables.score["X"]}"
              "-"
              "${Variables.score["0"]}"
      );
      // update the UI

      reset(message: Functions.winner());
      // start a new round
    }
  }

  /// function that changes the turn
  /// if turn is "X", it will become "0"
  /// otherwise turn will become "X"
  static void change_turn(){
    Variables.turn=Variables.turn=="X" ? "0" : "X";
  }

  /// function that checks if a empty cell exists
  static bool is_finished(){
    return !Variables.table.expand((i)=>i).contains("");
  }

  /// function that sets a new round with [message]
  /// or resets the whole game if [new_game]
  static void reset({String message="New Game!",
    bool new_game=false}){
    Variables.table=[
      ["","",""],
      ["","",""],
      ["","",""]
    ];
    Variables.turn="X";
    // reset variables

    Controllers.table_controllers.forEach(
            (row)=>row.forEach(
                (cell)=>cell.add("")
        )
    );
    Controllers.win_text_controller.add(message);
    // update the UI

    Fluttertoast.showToast(
      msg: message,
      toastLength: Toast.LENGTH_LONG,
    );
    // show a toast

    if(new_game){
      Variables.score={"X":0,"0":0};
      Controllers.score_text_controller.add("Score 0-0");
    }
    // if is a new game restart the score and update it
  }

  /// function that returns the winner
  static String winner(){
    for(int i=0;i<=2;i++){
      if(Variables.table[i].join("")=="XXX") return "X won!";
    }
    for(int i=0;i<=2;i++){
      if(Variables.table[i].join("")=="000") return "0 won!";
    }
    // check the rows

    for(int i=0;i<=2;i++){
      if(
      Variables.table[0][i]==Variables.table[1][i] &&
          Variables.table[1][i]==Variables.table[2][i]
      ) return "${Variables.table[0][i]} won!";
    }
    // check the columns

    if(
    Variables.table[0][0]==Variables.table[1][1] &&
        Variables.table[1][1]==Variables.table[2][2]
    ) return "${Variables.table[0][0]} won!";
    // check the principal diagonal

    if(
    Variables.table[0][2]==Variables.table[1][1] &&
        Variables.table[1][1]==Variables.table[2][0]
    ) return "${Variables.table[0][2]} won!";
    // check the secondary diagonal

    return "";
    // return a empty string if a nobody won yet
  }
}