import 'package:flutter/material.dart';
import 'package:chewie/chewie.dart';
import 'package:video_player/video_player.dart';

class PlayerVideo extends StatefulWidget {
  @override
  _PlayerVideoState createState() => _PlayerVideoState();
}

class _PlayerVideoState extends State<PlayerVideo> {
  late VideoPlayerController _videoPlayerController;
  ChewieController? _chewieController;

  @override
  void initState() {
    super.initState();

    // On prépare le contrôleur vidéo
    // _videoPlayerController = VideoPlayerController.networkUrl(
    //   Uri.parse('https://flutter.github.io/assets-for-api-docs/assets/videos/butterfly.mp4'),
    // );
    _videoPlayerController = VideoPlayerController.networkUrl(Uri.file("assets/butterfly.mp4"));

    // On attend que la vidéo s'initialise avant de créer le ChewieController
    _videoPlayerController.initialize().then((_) {
      setState(() {
        _chewieController = ChewieController(
          videoPlayerController: _videoPlayerController,
          autoPlay: true, // Lance la vidéo direct
          looping: true,  // Tourne en boucle
        );
      });
    });
  }

  @override
  void dispose() {
    // On libère la mémoire quand on quitte la page
    _videoPlayerController.dispose();
    _chewieController?.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      // 4. On affiche la vidéo seulement si elle est prête, sinon un cercle de chargement
      child: _chewieController != null && _chewieController!.videoPlayerController.value.isInitialized
          ? Chewie(controller: _chewieController!)
          : Center(child: CircularProgressIndicator()),
    );
  }
}