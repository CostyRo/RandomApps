import "dart:async";

class Controllers{
  static StreamController<String> win_text_controller=
  StreamController<String>();

  static StreamController<String> score_text_controller=
  StreamController<String>();

  static List<List<StreamController<String>>> table_controllers=[
    [
      StreamController<String>(),
      StreamController<String>(),
      StreamController<String>()
    ],
    [
      StreamController<String>(),
      StreamController<String>(),
      StreamController<String>()
    ],
    [
      StreamController<String>(),
      StreamController<String>(),
      StreamController<String>()
    ]
  ];
}