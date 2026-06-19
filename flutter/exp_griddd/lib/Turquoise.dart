import 'package:animate_do/animate_do.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';


import 'Red.dart';

class Turquoise extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Spin",
      theme: ThemeData(
        primarySwatch: Colors.teal,
        visualDensity: VisualDensity.adaptivePlatformDensity
      ),
      home: MyHomePage(title: 'utilisation de Menu')
    );
  }

}

class MyHomePage extends StatelessWidget {
  final String title;

  const MyHomePage({Key? key, required this.title});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(this.title),
      ),
      body: Center(
        child: Text(
          'utilisation de menu',
          style: TextStyle(
            color: Colors.teal,
            fontSize: 40.0
          ),
        ),

      ),
      drawer: Drawer(
        semanticLabel: 'mon super menu',
        child: ListView(
          children: [
            DrawerHeader(
              decoration: BoxDecoration(
                color: Colors.teal
              ),
              child: Text(
                'Menu',
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 24
                ),
              ),
            ),
            ListTile(
              leading: Icon(Icons.home_outlined),
              title: Text("Home", textDirection: TextDirection.rtl),
              subtitle: Text("Retour Main"),
              onTap: () => Navigator.pop(context, MaterialPageRoute(builder: (context) => Red())),
            )
          ],
        ),
      ),
    );
  }

}