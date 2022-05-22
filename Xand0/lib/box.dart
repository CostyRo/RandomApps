import "package:flutter/material.dart";

import "functions.dart";

class Box extends StatefulWidget{
  final Stream<String> stream;
  final int row,column;

  const Box({Key? key,
    required this.stream,
    required this.row,
    required this.column
  }) : super(key: key);

  @override
  State<Box> createState()=>_BoxState();
}

class _BoxState extends State<Box>{
  String box_value="";

  void _update_box_text(String value){
    setState((){
      box_value=value;
    });
  }

  @override
  void initState(){
    super.initState();
    widget.stream.listen((value){
      _update_box_text(value);
    });
  }

  @override
  Widget build(BuildContext context){
    return TextButton(
      onPressed: (){
        if(box_value.isEmpty){
          Functions.cell_logic(widget.row,widget.column);
        }
      },
      style: ButtonStyle(
        backgroundColor: MaterialStateProperty.all<Color>
          (Colors.blueGrey.shade300),
        foregroundColor: MaterialStateProperty.all<Color>
          (Colors.white),
      ),
      child: Text(
        box_value,
        style: const TextStyle(
          fontSize: 20,
        ),
      ),
    );
  }
}