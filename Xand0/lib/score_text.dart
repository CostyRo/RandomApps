import "package:flutter/material.dart";

class ScoreText extends StatefulWidget{
  final Stream<String> stream;

  const ScoreText({Key? key,
    required this.stream}) : super(key: key);

  @override
  State<ScoreText> createState()=>_ScoreTextState();
}

class _ScoreTextState extends State<ScoreText>{
  String score_text="Score 0-0";

  void _update_score_text(String value){
    setState((){
      score_text=value;
    });
  }

  @override
  void initState(){
    super.initState();
    widget.stream.listen((value){
      _update_score_text(value);
    });
  }

  @override
  Widget build(BuildContext context){
    return Text(
      score_text,
      style: const TextStyle(
        fontSize: 35,
      ),
    );
  }
}