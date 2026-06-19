import 'package:exp_griddd/Green.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:animator/animator.dart';

import 'Green-bis.dart';
// import 'package:animator/.dart';

class Red extends StatelessWidget {
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
                  return Red();
                },
              ),
            );
          },
          child: Icon(Icons.keyboard_return),
        ),

        body: Container(
          child: Center(
            child: GridView.count(
              scrollDirection: Axis.vertical,
              crossAxisCount: 10,
              children: [
                Card(
                  elevation: 5,
                  semanticContainer: true,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(10.0)
                  ),
                  margin: EdgeInsets.all(10),
                  clipBehavior: Clip.antiAliasWithSaveLayer,
                  child: InkWell(
                    onTap: () {
                      Navigator.push(context, MaterialPageRoute(builder: (context) => GreenBis("images/chat1.jpg")));
                    },
                    child: Ink.image(
                      fit: BoxFit.cover,
                      // image: AssetImage("images/chat1.jpg")
                      image: NetworkImage("images/chat1.jpg")
                    ),
                  ),
                ),
                ImageCard(imageUrl: "images/chat2.jpg"),
                ImageCard(imageUrl: "images/chat3.png"),
                ImageCard(imageUrl: "images/chat4.png"),
                ImageCard(imageUrl: "images/chat5.jpg"),
                ImageCard(imageUrl: "images/chat6.jfif"),
                ImageCard(imageUrl: "images/chat7.jpg"),
                ImageCard(imageUrl: "images/chat8.jpg"),
                ImageCard(imageUrl: "images/chat9.jpg"),
                ImageCard(imageUrl: "images/chat10.jpg"),
              ],
            )
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

class ImageCard extends StatelessWidget {
  final String imageUrl;
  final bool isNetworkImage;

  const ImageCard({
    super.key,
    required this.imageUrl,
    this.isNetworkImage = true, // Par défaut en NetworkImage comme ton exemple
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      elevation: 5,
      semanticContainer: true,
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(10.0),
      ),
      margin: const EdgeInsets.all(10),
      clipBehavior: Clip.antiAliasWithSaveLayer,
      child: InkWell(
        onTap: () {
          Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => GreenBis(this.imageUrl),
          ));
        },
        child: Ink.image(
          fit: BoxFit.cover,
          image: isNetworkImage
              ? NetworkImage(imageUrl)
              : AssetImage(imageUrl) as ImageProvider,
        ),
      ),
    );
  }
}