# INF112 Prosjekt - *Sir Slay-a-lot*


* Team: *Bit by bit* (Gruppe 5): Brage Aasen (grafisk ansvarlig/developer), Ole Kristian Breivik (developer), Vetle Knutsen (kundekontakt/developer), Tarjei Landøy (mergeansvarlig/developer), Kavya Malhotra (developer), Thomas Claussen Stakset (developer)

* Lenker:
  * [Gitlab repository](https://git.app.uib.no/bit-by-bit)
  * [Trello](https://trello.com/b/sPdGmGpN/bit-by-bit)

## Om spillet

«I Sir Slay-a-Lot  må spilleren ta på seg rollen som den tapre ridderen Sir Slay-a-Lot, og kjempe mot en horde av zombier som har invadert kongeriket. Spilleren må bruke sverdet sitt til å drepe zombiene og beskytte byen fra den ondskapsfulle hæren.»


## Kjøring

* Spillet krever Java 17 eller Java 19.
* Kompileres med `mvn package`.
* Kjøres med `java -jar target/gdx-app-1.0-SNAPSHOT-fat.jar


* Dersom du åpner spillet i IDE:
Når du åpner prosjektet i en IDE så vil skjermen din se noe slik ut:

<img src="/doc/Gitlab/screen.png" alt="Opening Screen" title="Opening Screen">

For å åpne spillet må du trykke på denne start knappen oppe i høyre hjørnet av skjermen.

<img src="/doc/Gitlab/runJava.png" alt="Opening Screen" title="Opening Screen">



## Hvordan fungerer spillet?

* Figuren kan bevege seg høyre eller venstre i 2D verden. Figuren kan også hoppe på høyere flater
* Fiender også kan bevege seg og er skadelig
* Det vises kill-count ved å drepe fiendene
* Spilleren kan bli skadet/drept av fiendene
* Når spilleren får 10 kills kan han bruke nytt våpen
* Spilleren må også samle oppgraderinger, som kan gi spilleren midlertidige fordeler som ekstra helse eller ekstra skudd.
* Målet med spillet er å overleve zombiene, og samtidig samle så mange poeng som mulig. 

---

## Credits

* LibGDX for rammeverk

* Sprites: Alle sprites/animasjoner (med unntak av kniv/enemies) er laget av Brage, ved hjelp av pixel-art programmet Aseprite.
* Unntak:
  * (enemies): Skeleton enemy er funnet med gratis download på spill-utviklings nettsiden itch.io [(lenke til sprites).](https://sanctumpixel.itch.io/sword-skeleton-pixel-art-character).
  * (knife): Knife er laget av Tarjei.
  * (gun): Gun er laget selv. <!-- av...? -->
  * (ammocrate): https://www.pngwing.com/en/free-png-bjmep

* Lyd (sfx): All lyd (sfx) er laget av Brage, ved hjelp av programmet Bfxr.
* Lyd (musikk): Spill musikken er laget av Nicky Flowers, og sangen heter "Pretty Girls Make Graves" [(lenke)](https://bandcamp.com/download?id=2952704477&ts=1681300815.144843886&tsig=1805ede82803919920cb635bf023338b&type=track)