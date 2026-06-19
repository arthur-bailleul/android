import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:animator/animator.dart';

// import 'package:animator/.dart';

class Green extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Animation Zoom",
      home: Scaffold(
        appBar: AppBar(
          leading: IconButton(
            onPressed: () {
              Navigator.pop(context);
            },
            icon: Icon(Icons.arrow_back),
          ),
          title: Text("Animation Zoom"),
          backgroundColor: Colors.green,
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () {
            Navigator.pop(
              context,
              // MaterialPageRoute(builder: (context ) => Bleu()))
              MaterialPageRoute(
                builder: (context) {
                  return Green();
                },
              ),
            );
          },
          child: Icon(Icons.keyboard_return),
        ),

        body: Animation(),
      ),
      // width: 800,
      // height: 600,
      // child: Center(
      //   child: ,
      // ),
    );
  }
}

class Animation extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Animator<double>(
      tween: Tween(begin: 0, end: 1920),
      repeats: 1,
      duration: Duration(milliseconds: 6000),
      builder: (context, animatorState, child) => Center(
        child: Container(
          margin: EdgeInsets.symmetric(vertical: 10),
          height: animatorState.value,
          width: animatorState.value,
          child: Card(
            semanticContainer: true,
            clipBehavior: Clip.antiAliasWithSaveLayer,
            child: Image.network(
              "assets/chat5.jpg",
              fit: BoxFit.cover,
            ),
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(10.0),
            ),
            elevation: 5,
            margin: EdgeInsets.all(10),
          ),
        ),
      ),
    );
  }
}
