import "dart:async";

import "package:flutter/material.dart";

import "box.dart";

class BoxRow extends StatelessWidget{
  final int index;
  final List<Stream<String>> streams_list;

  const BoxRow({Key? key,
    required this.index,
    required this.streams_list
  }) : super(key: key);

  @override
  Widget build(BuildContext context){
    return Padding(
        padding: const EdgeInsets.only(
          left: 30,
          right: 30,
        ),
        child:
        Row(
          children: <Widget>[
            Expanded(
              flex: 4,
              child: Box(
                stream: streams_list[0],
                row: index,
                column: 0,
              ),
            ),
            const Spacer(
              flex: 1,
            ),
            Expanded(
              flex: 4,
              child: Box(
                stream: streams_list[1],
                row: index,
                column: 1,
              ),
            ),
            const Spacer(
              flex: 1,
            ),
            Expanded(
              flex: 4,
              child: Box(
                stream: streams_list[2],
                row: index,
                column: 2,
              ),
            ),
          ],
        )
    );
  }
}