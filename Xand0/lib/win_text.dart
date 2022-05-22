import "package:flutter/material.dart";

class WinText extends StatefulWidget{
  final Stream<String> stream;

  const WinText({Key? key,
    required this.stream}) : super(key: key);

  @override
  State<WinText> createState()=>_WinTextState();
}

class _WinTextState extends State<WinText>{
  String win_text="No winner yet!";

  void _update_win_text(String value){
    setState((){
      win_text=value;
    });
  }

  @override
  void initState(){
    super.initState();
    widget.stream.listen((value){
      _update_win_text(value);
    });
  }

  @override
  Widget build(BuildContext context){
    return Text(
      win_text,
      style: const TextStyle(
        fontSize: 50,
      ),
    );
  }
}