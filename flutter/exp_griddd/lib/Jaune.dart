import 'package:animate_do/animate_do.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'PlayerVideo.dart';

class Jaune extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Spin",
      home: Scaffold(
        appBar: AppBar(
          leading: IconButton(
            onPressed: () {
              Navigator.pop(context);
            },
            icon: Icon(Icons.arrow_back),
          ),
          title: Text("Spin"),
          backgroundColor: Colors.yellow,
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () {
            Navigator.pop(
              context,
              // MaterialPageRoute(builder: (context ) => Bleu()))
              MaterialPageRoute(
                builder: (context) {
                  return Jaune();
                },
              ),
            );
          },
          child: Icon(Icons.keyboard_return),
        ),

        body: PlayerVideo(),
      ),
      // width: 800,
      // height: 600,
      // child: Center(
      //   child: ,
      // ),
    );
  }
}
