import 'package:flutter/material.dart';
import 'package:animate_do/animate_do.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  int _counter = 0;

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Super Appli",
      home: Scaffold(
        appBar: AppBar(
          title: Text("Ma first app flutter"),
          backgroundColor: Colors.redAccent,
        ),
        body: Center(
          child: Spin(
            infinite: false,
            // spins: 3.5, // Fait exactement 3 tours et demi
            spins: 1, // Fait exactement 3 tours et demi
            delay: const Duration(milliseconds: 700), // Attend une demi-seconde avant de bouger
            duration: const Duration(seconds: 2),
            curve: Curves.easeOut, // Ralentit doucement vers la fin
            onFinish: (direction) {
              print("L'animation est terminée !");
            },
            child: MonImage(
              // monContenu: Image.network(
              //   'https://franklinpetfood.com/cdn/shop/files/600X600_BENGAL_HEADER.jpg?v=1702485452&width=642',
              //   // color: Colors.amber, // marche avec le png
              // )
              monContenu: FloatingActionButton(onPressed: () {

                _incrementCounter();
              },
                child: Text('click'),
              // monContenu: Icon(
              //   Icons.favorite,
              //   color: Colors.red,
              //   size: 100,
              ),
              // monContenu: Image.asset(
              //   'asset/chat2.jpeg',
              //   width: 500,
              //   height: 200,
              //   // color: Colors.amber,
              // )
            )
          ),
        ),
      ),
    );
  }

  void _incrementCounter() {
    _counter++;
    print(_counter);
  }
}

class MonImage extends StatelessWidget {
  final Widget? monContenu;
  const MonImage({super.key, this.monContenu});

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 600,
      height: 600,
      child: Center(
          // '??' signifie : si monContenu est null, utilise ce qu'il y a à droite
          child: monContenu ?? const SizedBox(), // SizedBox() affiche un espace vide
      ),
      decoration: BoxDecoration(color: Colors.grey)
    );
  }

}

