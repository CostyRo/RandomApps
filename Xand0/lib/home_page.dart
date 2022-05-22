import "package:flutter/material.dart";

import "box_row.dart";
import "controllers.dart";
import "functions.dart";
import "score_text.dart";
import "win_text.dart";

class HomePage extends StatelessWidget{
  const HomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context){
    return Scaffold(
      appBar: AppBar(
        title: const Center(
          child: Text(
            "X & 0",
          ),
        ),
      ),
      body: Column(
        children: <Widget>[
          const Spacer(
            flex: 1,
          ),
          Expanded(
            flex: 3,
            child: WinText(
              stream: Controllers.win_text_controller.stream,
            ),
          ),
          const Spacer(
            flex: 1,
          ),
          Expanded(
            flex: 3,
            child: ScoreText(
              stream: Controllers.score_text_controller.stream,
            ),
          ),
          const Spacer(
            flex: 7,
          ),
          Expanded(
            flex: 3,
            child: BoxRow(
              index: 0,
              streams_list:
              Controllers.table_controllers[0].map((x)=>x.stream).toList(),
            ),
          ),
          const Spacer(
            flex: 1,
          ),
          Expanded(
            flex: 3,
            child: BoxRow(
              index: 1,
              streams_list:
              Controllers.table_controllers[1].map((x)=>x.stream).toList(),
            ),
          ),
          const Spacer(
            flex: 1,
          ),
          Expanded(
            flex: 3,
            child: BoxRow(
              index: 2,
              streams_list:
              Controllers.table_controllers[2].map((x)=>x.stream).toList(),
            ),
          ),
          const Spacer(
            flex: 10,
          ),
          ElevatedButton(
            onPressed: (){
              Functions.reset(new_game: true);
            },
            style: ElevatedButton.styleFrom(
              minimumSize: const Size.fromHeight(60),
              shape: const RoundedRectangleBorder(
                borderRadius: BorderRadius.only(
                  topLeft: Radius.circular(50),
                  topRight: Radius.circular(50),
                ),
              ),
            ),
            child: const Text(
              "Reset",
              style: TextStyle(
                fontSize: 18,
              ),
            ),
          ),
        ],
      ),
    );
  }
}