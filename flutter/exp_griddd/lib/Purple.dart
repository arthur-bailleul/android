import 'package:animate_do/animate_do.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';


class Purple extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Image Web",
      home: Scaffold(
        appBar: AppBar(
          leading: IconButton(
            onPressed: () {
              Navigator.pop(context);
            },
            icon: Icon(Icons.arrow_back)
          ),
          title: Text("Image Web"),
          backgroundColor: Colors.purple,
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () {
            Navigator.pop(
                context,
                // MaterialPageRoute(builder: (context ) => Bleu()))
                MaterialPageRoute(builder: (context) {
                  return Purple();
                })
            );
          },
          child: Icon(
            Icons.keyboard_return
          ),
        ),

        body: Container(
          child: InkWell(
            onTap: () {},
            child: Ink.image(
              image: NetworkImage("https://images.ctfassets.net/ybw2uvetxkr8/610aaSZyovN0rJWvHq1G1g/41633ccc244e3e9120b9049e49c84f1d/chat-peureux.jpg?fm=webp&q=80")
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