import 'package:exp_griddd/Turquoise.dart';
import 'package:flutter/material.dart';
import 'package:expanded_grid/expanded_grid.dart';
import 'Bleu.dart';
import 'Jaune.dart';
import 'Purple.dart';
import 'Green.dart';
import 'Red.dart';
import 'Turquoise.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(title: 'Flutter Demo', home: ExpandedGridSample());
  }
}

class ExpandedGridSample extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      floatingActionButton: FloatingActionButton(
        onPressed: () {},
        child: Text("press"),
      ),
      appBar: AppBar(
        backgroundColor: Colors.pink,
        title: Container(
          color: Colors.lightGreenAccent,
          child: Text("TD Colors"),
        ),
      ),
      body: Align(
        alignment: Alignment.centerLeft,
        child: AspectRatio(
          aspectRatio: 16 / 9,
          child: ExpandedGrid(
            column: 4,
            row: 5,
            children: [
              ExpandedGridContent(
                rowIndex: 0,
                columnIndex: 0,
                rowSpan: 2,
                columnSpan: 3,
                child: Container(
                  color: Colors.lightBlue,
                  child: IconButton(
                    onPressed: () {
                      print("blue button");
                      Navigator.push(
                        context,
                        // MaterialPageRoute(builder: (context ) => Bleu()))
                        MaterialPageRoute(
                          builder: (context) {
                            return Bleu();
                          },
                        ),
                      );
                    },
                    icon: Icon(
                      Icons.screen_rotation_outlined,
                      color: Colors.black,
                      size: 100,
                    ),
                  ),
                ),
              ),
              ExpandedGridContent(
                rowIndex: 2,
                columnIndex: 0,
                rowSpan: 2,
                columnSpan: 2,
                child: Container(
                  color: Colors.red,

                  child: IconButton(
                    icon: Icon(
                      Icons.smart_screen,
                      color: Colors.black,
                      size: 100,
                    ),
                    onPressed: () {
                      print("Red button");
                      Navigator.push(
                        context,
                        // MaterialPageRoute(builder: (context ) => Bleu()))
                        MaterialPageRoute(
                          builder: (context) {
                            return Red();
                          },
                        ),
                      );
                    },
                  ),
                ),
              ),
              ExpandedGridContent(
                rowIndex: 2,
                columnIndex: 2,
                rowSpan: 1,
                columnSpan: 2,
                child: Container(
                  color: Colors.yellow,
                  child: IconButton(
                    onPressed: () {
                      print("yellow button");
                      Navigator.push(
                        context,
                        // MaterialPageRoute(builder: (context ) => Bleu()))
                        MaterialPageRoute(
                          builder: (context) {
                            return Jaune();
                          },
                        ),
                      );
                    },
                    icon: Icon(
                      Icons.rocket_launch,
                      color: Colors.black,
                      size: 100,
                    ),
                  ),
                ),
              ),
              ExpandedGridContent(
                rowIndex: 4,
                columnIndex: 0,
                rowSpan: 1,
                columnSpan: 2,
                child: Container(
                  color: Colors.teal,
                  child: IconButton(
                    onPressed: () {
                      print("turquoise button");
                      Navigator.push(
                        context,
                        // MaterialPageRoute(builder: (context ) => Bleu()))
                        MaterialPageRoute(
                          builder: (context) {
                            return Turquoise();
                          },
                        ),
                      );
                    },
                    icon: Icon(
                      Icons.airplanemode_off_outlined,
                      color: Colors.black,
                      size: 100,
                    ),
                  ),
                ),
              ),
              ExpandedGridContent(
                rowIndex: 3,
                columnIndex: 2,
                rowSpan: 2,
                columnSpan: 1,
                child: Container(
                  color: Colors.purple,
                  child: IconButton(
                    onPressed: () {
                      print("purple button");
                      Navigator.push(
                        context,
                        // MaterialPageRoute(builder: (context ) => Bleu()))
                        MaterialPageRoute(
                          builder: (context) {
                            return Purple();
                          },
                        ),
                      );
                    },
                    icon: Icon(
                      Icons.add_a_photo_outlined,
                      color: Colors.black,
                      size: 100,
                    ),
                  ),
                ),
              ),
              ExpandedGridContent(
                rowIndex: 3,
                columnIndex: 3,
                rowSpan: 2,
                columnSpan: 1,
                child: Container(
                  color: Colors.orange,
                  child: Icon(
                    Icons.add_to_drive_outlined,
                    color: Colors.black,
                    size: 100,
                  ),
                ),
              ),
              ExpandedGridContent(
                rowIndex: 0,
                columnIndex: 3,
                rowSpan: 2,
                columnSpan: 1,
                child: Container(
                  color: Colors.green,
                  child: IconButton(
                    onPressed: () {
                      print("green button");
                      Navigator.push(
                        context,
                        // MaterialPageRoute(builder: (context ) => Bleu()))
                        MaterialPageRoute(
                          builder: (context) {
                            return Green();
                          },
                        ),
                      );
                    },
                    icon: Icon(Icons.window, color: Colors.black, size: 100),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
