# INF112 Prosjekt - *Sir Slay-a-lot*

* Team: *Bit by bit* (Gruppe 5): Brage Aasen, Ole Kristian Breivik, Vetle Knutsen, Tarjei Landøy, Kavya Malhotra, Thomas Claussen Stakset*

* Gitlab lenke: https://git.app.uib.no/bit-by-bit
* Trello: https://trello.com/b/sPdGmGpN/bit-by-bit

## Om spillet
*«Du er flyttet til en annen verden med fiender. Du har ingenting utenom sverd. Klarer du å overleve hauer av fiender?»*

## Kjøring
* Spillet krever Java 17 eller Java 19:
* For å starte spillet må du først laste ned prosjektet. Når du åpner prosjektet i en IDE så vil skjermen din se noe slik ut:
* <img src="/src/main/resources/assets/Gitlab/screen.png" alt="Opening Screen" title="Opening Screen">
* For å åpne spillet må du trykke på denne start knappen oppe i høyre hjørnet av skjermen.
* <img src="/src/main/resources/assets/Gitlab/runJava.png" alt="Opening Screen" title="Opening Screen">

## Kjente feil
* Et par bugs, f.eks når fiendene prøver å slå

## Credits
* LibGDX for rammeverk

* Sprites: Alle sprites/animasjoner (med unntak av kniv/enemies) er laget av Brage, ved hjelp av pixel-art programmet Aseprite.

* Sprites (enemies): Skeleton enemy er funnet med gratis download på spill-utviklings nettsiden itch.io.
* Sprites (gun/knife): Gun & Knife er laget selv. 
* Sprites (ammocrate) : https://www.pngwing.com/en/free-png-bjmep

* Lyd (sfx): All lyd (sfx) er laget av Brage, ved hjelp av programmet Bfxr.
* Lyd (musikk): Spill musikken er laget av Nicky Flowers, og sangen heter "Pretty Girls Make Graves".
    https://bandcamp.com/download?id=2952704477&ts=1681300815.144843886&tsig=1805ede82803919920cb635bf023338b&type=track

# Hvordan fungerer spillet?
* Figuren kan bevege seg høyre eller venstre i 2D verden. Figuren kan også hoppe på høyere flater
* Fiender også kan bevege seg og er skadelig
* Det vises kill-count ved å drepe fiendene
* Spilleren kan bli skadet/drept av fiendene
* Når spilleren får 10 kills kan han bruke nytt våpen
* Power-ups kan gi midlertidige/umiddelbare fordeler (f.eks ekstra liv)
