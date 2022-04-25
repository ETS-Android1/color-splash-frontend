# ColorSplash Frontend
ColorSplash is a multiplayer game created for Android devices as the main project for the course TDT4240 Software Architecture at NTNU.

The game is based on the ["Hippo Splash"-minigame](https://www.youtube.com/watch?v=fXto5A-gSJA&t=91s) in Buzz! Junior: Jungle Party. The players are playing on separate devices using a Game Pin to connect. Each round a color combination will be splashed on the screen and your job is to remeber this sequence. Get as many correct colors in the right order as fast as possible to get the highest score and become the winner.

<div>
    <img src="https://imgur.com/VLesQlT.png" alt="Buzz! Junior: Jungle Party" width="120"/>
    <img src="https://imgur.com/7A5KONj.png" alt="Hippo Splash-minigame" width="300"/>
</div>

 **ColorSplash game views:**
 
<div>
    <img src="https://imgur.com/lqlnZNJ.png" alt="MainMenuView" width="105"/>
    <img src="https://imgur.com/pP4FkTe.png" alt="HowToPLayView" width="105"/>
    <img src="https://imgur.com/j3u7vSp.png" alt="JoinGameView" width="105"/>
    <img src="https://imgur.com/GEwWEMj.png" alt="CreateGameView" width="105"/>
    <img src="https://imgur.com/JoL78Hc.png" alt="GameLobbyView" width="105"/>
    <img src="https://imgur.com/hNrqEBx.png" alt="SplashView" width="105"/>
    <img src="https://imgur.com/CUh0u6o.png" alt="AnswerView" width="105"/>
    <img src="https://imgur.com/0os9ble.png" alt="AnswerView with results" width="105"/>
    <img src="https://imgur.com/D2jMNVA.png" alt="ScoreBoardView" width="105"/>
</div>

## Structure
```
.
├── android                   # Platform specific configurations
├── assets                    # png- and mp3-files
├── core/src/com/mygdx/game   # Core logic built on MVC pattern
├── .gitignore               
└──  README.md
```
Our project is divided into two distinct repositories as they are not dependent of each other. To get to the backend repository please visit [color-splash](https://github.com/Carlvebbesen/color-splash).

## How to compile and run project

You only need the frontend part of the project to run. As different operating systems and processors need different setup and configurations for Android Studio to work, we have not included the build.gradle files in the repository. These are local for each developer and .gitignore prevents it from being added. Thus you need to to build your own Libgdx-project to generate the right configurations for your computer to run. 

Here is how to do it:
1. Create a libGDX project using the libGDX Project Setup Tool. Click [here](https://libgdx.com/wiki/start/project-generation) for guide/download. 
2. Open the libGDX project in Android Studio.
3. Inside the project in Android Studio, clone this repository. 
4. Find your build.gradle file and replace the project(":core") with this:

```gradle:
project(":core") {
    apply plugin: "java-library"

    dependencies {
        api "com.badlogicgames.gdx:gdx:$gdxVersion"
        api "com.badlogicgames.gdx:gdx-box2d:$gdxVersion"
        implementation ('io.socket:socket.io-client:2.0.1') {
            exclude group: 'org.json', module: 'json'
        }
        implementation 'org.json:json:20160212'
        implementation group: 'com.google.code.gson', name: 'gson', version: '2.8.9'
    }

}
```
5. Add an Android Configuration to run the game in an emulator or on a connected Android device: Click the Add Configurartion-button in the upper right corner of Android Studio, hit the Android App -> "+"-button. Name it "android" and choose "color-splash-frontend.android" as module. Click OK. 
6. You should now be ready to run the game. Play and have fun!


## Design Files
We have published our design files which is used as assets on the Figma Community. You can easily access the Figma file [here](https://www.figma.com/community/file/1100115383891154505/ColorSplash). Duplicate it, play around and export your new png-files to /assets to further develop the theme or change the design.

All art work is created and done by us. 

## References
### Sounds:
**splash.mp3:** Muddy Water Splash Sound Effect, [fesliyanstudios.com](https://www.fesliyanstudios.com/play-mp3/2443). <br>
**click.mp3:** Menu Selection Change Sound Effect, [fesliyanstudios.com](https://www.fesliyanstudios.com/play-mp3/2903).

### Music:
**music.mp3:** "Smilin And Vibin" by David Renda, [fesliyanstudios.com](https://www.fesliyanstudios.com/royalty-free-music/download/smilin-and-vibin/1197).

### Font
**Bebas Kai** by Ryoichi Tsunekaw. Click [here](https://www.dafont.com/bebas-neue.font) to download. 

## Contributors
**The group consists of:**<br>
[Fabian Foss Budal](https://github.com/FabianFoss)<br>
[Carl Valdemar Ebbesen](https://github.com/Carlvebbesen)<br>
[Ingrid Hagen](https://github.com/hageningrid)<br>
[Karen Hompland](https://github.com/karenhompland)<br>
[Marius Nåvli Sjølie](https://github.com/mariussjolie)<br>
[Håkon Telje](https://github.com/vaarantnu)<br>
[Vår Åsheim](https://github.com/haakonte)<br>
