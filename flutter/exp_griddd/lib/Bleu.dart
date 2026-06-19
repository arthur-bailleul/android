import 'package:animate_do/animate_do.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';


class Bleu extends StatelessWidget {
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
            icon: Icon(Icons.arrow_back)
          ),
          title: Text("Spin"),
          backgroundColor: Colors.lightBlue,
        ),
        floatingActionButton: FloatingActionButton(
            onPressed: () {
              Navigator.pop(
                  context,
                  // MaterialPageRoute(builder: (context ) => Bleu()))
                  MaterialPageRoute(builder: (context) {
                    return Bleu();
                  })
              );
            },
            child: Icon(
            Icons.keyboard_return
        ),
        ),

        body: Center(
          child: Spin(
            spins: 2,
            duration: const Duration(seconds: 2),
            curve: Curves.bounceIn,
            child: Icon(
              Icons.android_outlined
            ),
          ),
        ),
      ),
      // width: 800,
      // height: 600,
      // child: Center(
      //   child: ,
      // ),
    );
  }

}