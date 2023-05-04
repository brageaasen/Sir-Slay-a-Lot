# Oblig 4

## Teambeskrivelse

# Kompetanse:
* Vetle - Bare programmering gjennom skole.
* Thomas - Bare programmering gjennom skole.
* Ole - Hovedsakelig programmering gjennom skole. Har lært litt andre språk i egen fritid i tillegg.
* Kavya - Har programmert i ulike språk (C, Java, SQL, Haskell, JS, HTML), men har ikke særlig drevet mye med prosjekter. Begynte å programmere etter videregående.
* Tarjei - Har programmert i noen år før universitet. Også på fritiden.
* Brage - Programmert gjennom skole, drevet litt med Unity.

# Roller:
* Developer/Grafisk ansvarlig - Brage Aasen
* Mergeansvarlig/Developer - Tarjei Landøy
* Developer - Thomas Claussen Stakset 
* Developer - Kavya Malhotra 
* Developer/Kundekontakt - Vetle Mathiesen Knutsen 
* Developer - Ole Kristian Breivik


## Minimal Viable Product (MVP).
* Konkrete punkt for Minimal Viable Product: 
    * Vise et brett/verden.
    * Vise en spiller/karakter.
    * Flytte spilleren høyre/venstre/hoppe med A/D/Space.
    * Spilleren krasjer i vegg/platform og kan stå på platformene.
    * Spilleren har liv og kan plukke opp nye hjerter eller regenerere liv (hvis spilleren er ikke slått).
    * Vise en/flere fiende(r) som kan bevege seg.
    * Fiender genereres automatisk.
    * Spilleren kan miste liv / ta skade.
    * Spilleren kan angripe tilbake / beskytte seg selv mot fiender.
    * Game Over når fiende er død eller tom for liv.
    * Vise Game Over skjerm UI, med en score til spiller.
    * Mulighet for å kunne starte spillet på nytt uten å restarte applikasjonen.


## Brukerhistorier:
* Som spiller trenger jeg at spillet ikke har bugs for en god spillopplevelse
    * Akseptansekriterie: Spillet kan ikke inneholde bugs.
    * Arbeidsoppgave: Finne og fikse bugs.

* Som spiller trenger jeg at spillet har en spennende startskjerm for å bli interessert i spillet.
    * Akseptansekriterie: Spillet må ha en innbydende startskjerm.
    * Arbeidsoppgave: Lage en fin startskjerm.

* Som spiller trenger jeg at spillet har en forklaring på knappene for at jeg skal skjønne hvordan jeg spiller.
    * Akseptansekriterie: Spillet må ha en forklaring på knappene.
    * Arbeidsoppgave: Skrive forklaring på knappene på startskjermen.

* Som spiller trenger jeg en mulighet for å få påfyll av skudd når jeg er tom.
    * Akseptansekriterie: Spillet må ha powerups for skudd.
    * Arbeidsoppgave: Implementere powerups.

* Som spiller trenger jeg en mulighet for å få påfyll av health når jeg har lite.
    * Akseptansekriterie: Spillet må ha powerups for health.
    * Arbeidsoppgave: Implementere powerups.

* Som spiller trenger jeg game sounds for å leve meg inn i spillet.
    * Akseptansekriterie: Spillet må ha diverse lyder ved diverse bevegelser.
    * Arbeidsoppgave: Implementere lyder.

* Som spiller trenger jeg en mulighet for å starte spillet på nytt når jeg taper.
    * Akseptansekriterie: Spillet må ha en knapp ved slutten av spillet som starter spillet på nytt.
    * Arbeidsoppgave: Legg til en "Restart" knapp i game over screen.


## Dette har vi fikset siden sist oblig:
* Oppdatert startskjerm med “logo”
* Oppdatert sluttskjerm med score og game over UI.
* Lagt til kontroller på startskjerm
* Fikset bug hvor fiende blir angrepet flere ganger
* Fikset bug hvor død-animasjon ikke alltid ble vist for fiende
* Lagt inn flere lyder
* Laget flere tester
* Lagt inn jar-fil
* Lagt til cooldown på angrep med kniven
* Fikset så sangen i spillet looper. 
* Skrevet en litt bedre forklaring på hvordan man kjører spillet.
* Byttet “Gun Unlocked” text til bilde så resolution ikke blir ødelagt 
* Legge til kildereferanser til lyd / sprites om er blitt brukt i spillet. 
* Lagt til powerups for hvert 10. og 15. kill
* Fikset at klassediagrammet ikke ser rotete ut lenger
* Fikset mangel / feil på brukerhistorier 
* Fikset bug at enemy beveger seg når de blir skadet 
* Oppdatery readme. 
* Ryddet Trello boardet og sortert hva som har blitt gjort / ikke blitt gjort.
* Skrevet hele teambeskrivelse og rollefordeling i oblig4
       

## Manuelle tester: (Antall: 21)

* Her testet vi funksjonaliteter som ikke lot seg teste i unit test. 

* Player Movement / Keyhandlig
    * Hva er forventet skal skje?
        * Spiller skal bevege til høyre og venstre med tasteklikkene A og D, og hoppe med Space.
    * Hvordan vurdere om det har skjedd en feil?
        * Spiller beveger seg ikke eller beveger seg i feil retning

* Player Collision
    * Hva er forventet skal skje?
        * Spiller skal hoppe på en blokk, og ikke bevege seg/falle gjennom blokken.
    * Hvordan vurdere om det har skjedd en feil?
        * Spiller hopper på en blokk, men beveger seg gjennom/faller gjennom blokken.

* Player Attack
    * Hva er forventet skal skje?
        * Spiller skal kunne angripe fiender med bruk av Enter tastetrykk.
    * Hvordan vurdere om det har skjedd en feil?
        * Spiller gjør ikke skade på fiender, eller våpen dukker ikke opp med tastetrykket.

* Player Gun
    * Hva er forventet skal skje?
        * Spiller skal skyte med pistolen, og det skal dukke opp kuler som beveger seg horisontalt på kartet.
    * Hvordan vurdere om det har skjedd en feil?
        * Spiller bruker angrepsknappen med pistol, men det dukker ikke opp kuler.

* Player Weapon Changing
    * Hva er forventet skal skje?
        * Spiller bytter mellom våpnene kniv/pistol ved hjelp av tallene på tastaturet sitt (1, 2).
    * Hvordan vurdere om det har skjedd en feil?
        * Spiller klarer ikke å bytte mellom våpnene ved tastetrykk, selv etter at pistol har blitt låst opp.

* Player Fall Damage
    * Hva er forventet skal skje?
        * Spiller skal ta skade når spilleren faller fra en viss høyde.
    * Hvordan vurdere om det har skjedd en feil?
        * Spiller faller fra en stor høyde, men tar ikke skade av fallet.

* Show Player / Enemy Sprite
    * Hva er forventet skal skje?
        * Angrepssprites for spiller og enemy skal lastes og vises på skjermen.
    * Hvordan vurdere om det har skjedd en feil?
        * Angrepssprites for spiller og enemy dukker ikke opp på skjermen etter at spillet har startet.

* Player / Enemy Sprite Movement
    * Hva er forventet skal skje?
        * Sprites for spiller / enemy skal endre seg etter relativ bevegelse og retning for spill-objekt.
    * Hvordan vurdere om det har skjedd en feil?
        * Sprites for spiller / enemy står låst i en sprite, eller viser feil sprite til feil handling.

* Enemy Movement
    * Hva er forventet skal skje?
        * Enemy skal kunne bevege seg av seg selv, i retning av spilleren.
    * Hvordan vurdere om det har skjedd en feil?
        * Enemy beveger seg ikke, eller beveger seg i feil retning.

* Enemy Death
    * Hva er forventet skal skje?
        * Enemy skal dø, og fjernes fra spillet dersom den blir angrepet tilstrekkelig mange ganger.
    * Hvordan vurdere om det har skjedd en feil?
        * Enemy dør, og fjernes fra spillet når livet dens går tomt.

* Enemy spawning
    * Hva er forventet skal skje?
        * Flere enemies skal bli generert etterhvert som spiller går.
    * Hvordan vurdere om det har skjedd en feil?
        * Enemies blir ikke generert over tid, og spiller står alene på kartet.

* Enemy Death Sprite
    * Hva er forventet skal skje?
        * Enemy skal spille av en død-animasjon når livet dens går tomt.
    * Hvordan vurdere om det har skjedd en feil?
        * Enemy forsvinner bare fra spillet, og spiller ikke av død-animasjonen dens.

* SFX Avspilling
    * Hva er forventet skal skje?
        * Importert lyd skal spille av én gang når lyden blir tilkalt.
    * Hvordan vurdere om det har skjedd en feil?
        * Lyden spilles av flere ganger, eller ikke i det hele tatt for lydene: Hit, Hurt, Jump, Pickup, Select og Shoot.
* Music Avspilling
    * Hva er forventet skal skje?
        * Musikken skal spilles av ved start.
    * Hvordan vurdere om det har skjedd en feil?
        * Musikken spilles ikke av ved start, eller begynner avspilling for sent.

* Music Looping
    * Hva er forventet skal skje?
        * Musikken skal automatisk spilles av på nytt igjen etter endt kjøring.
    * Hvordan vurdere om det har skjedd en feil?
        * Musikken stopper å spille etter én kjøring, og kjører ikke igjen.

* Start Meny UI
    * Hva er forventet skal skje?
        * Start meny skal vises som den skal, og knappen skal funke.
    * Hvordan vurdere om det har skjedd en feil?
        * Start menyen vises ikke, eller knappene funker ikke.

* Game Over Screen UI
    * Hva er forventet skal skje?
        * Game over screen vises ved tap, og knappene skal funke.
    * Hvordan vurdere om det har skjedd en feil?
        * Game over screen vises ikke ved tap, eller knappene funker ikke.

* Player Score
    * Hva er forventet skal skje?
        * Player score skal vises ved game over screen, og vise korrekt score i forhold til player sin killcount.
    * Hvordan vurdere om det har skjedd en feil?
        * Player score vises ikke i game over screen, eller viser feil score i forhold til player sin killcount.

* Parallax Background Horisontal Movement
    * Hva er forventet skal skje?
        * Parallax bakgrunnen skal fungere som den skal horisontalt, og utvide seg automatisk.
    * Hvordan vurdere om det har skjedd en feil?
        * Parallax bakgrunn utvider seg ikke ved player bevegelse i horisontal retning.

* Parallax Background Vertical Movement
    * Hva er forventet skal skje?
        * Parallax bakrunnen skal fungere som den skal vertikalt, og gå over til "evig himmel" etter spiller har nådd en viss høyde på kartet.
    * Hvordan vurdere om det har skjedd en feil?
        * Den vertikale parallax bakgrunnen går ikke over til evig himmel, men viser heller feil bilde/svart skjerm etter spiller går høyt opp på kartet.

* TileMap
    * Hva er forventet skal skje?
        * Tiles vi plasserer ved hjelp av redskapet Tiled, skal være i samme poisisjon i spillet når det er lastet.
    * Hvordan vurdere om det har skjedd en feil?
        * Posisjionen på ulike plasserte tiles er i forskjellige plasser enn forventet fra Tiled plasseringen.

## Klassediagram
<img src="/doc/Gitlab/diagramOblig4.png" alt="Opening Screen" title="Opening Screen">

## Beskrivelse av arkitektur
Vi har delt opp klassene våre i 6 forskjellige mapper: Animations, Back_end, Controller, Entity, Screen og Weapons. 
Animations tar for seg animasjonen i spillet, Back_end inneholder klasser som gjør viktige jobber for grunnmuren, Controller inneholder KeyHandleren, Entity inneholder alt som har med spillkarakteren å gjøre, Screen inneholder det som vises på skjermen og Weapons inneholder alle våpen. 

I Screen finner vi GameScreen, som inneholder hovedlogikken i spillet. Den utvider ScreenAdapter, som er en del av spillrammeverket libGDX, og dermed fungerer den som skjermen som spillet vises på. Klassen inneholder variabler og objekter som representerer spillverdenen, som kamera, fysikk, sprite-rendering, fiender og mye mer. Klassen inneholder også metoder for å oppdatere spillobjekter og brukerinput, håndtere kollisjoner og sjekke om spillet skal avsluttes. Det er i denne klassen de fleste render-metodene blir kalt. 

I Back_end finner vi TileMapHelper, denne klassen tar for seg tilesene i spillet. Vi har laget mappet i Tiled og der kan vi gi forskjellige navn og typer til tilesene. Alle statiske blokker er for eksempel et Polygon og når den da får inn et Polygon vil den lage statiske blokker. Dersom den får inn et polygon med navn "moving", vil den lage en bevegende platform. Men, dersom den får inn et Rektangel-objekt, vil den lage enten en spiller eller en fiende basert på rektanglets navn. TileMapHelper er derfor et viktig fundament for spillet vårt ettersom den lager alle blokkene. 

To viktige klasser er også Player og Enemy i Entity mappen. Disse klassene utvider GameEntity-klassen vår som gir oss enkle egenskaper som bredde, høyde og body. Body representerer den fysiske kroppen til spilleren i en Box2D verden. Klassene inneholder forskjellige sett av animasjoner for de forskjellige tilstandene til karakterene. Disse animasjonene blir håndtert av AnimationHandler-klassen i Animations mappen. Enemy-klassen er spesielt viktig fordi den lar spillet simulere oppførselen til en fiende basert på spillerens handlinger. 

Som vi ser på klassediagrammet er GameScreen og Player klassene med flest forhold til andre klasser. Dette er naturlig ettersom mye av oppførselen i spillet blant annet baserer seg på hva spilleren velger å gjøre. I tillegg er det naturlig at GameScreen må ha mange forhold for å kunne vite det som trengs for å ha en velfungerende spillskjerm. Vi kan samtidig se at AudioManager også har flere forhold. Ettersom denne styrer de forskjellige lydene til forskjellige hendelser i spillet, ender den opp med flere forhold til andre klasser. Det er da spesielt Player, Enemy, Gun, TitleScreen og EndScreen som bruker AudioManager ofte. 


## Spotbugs: 
* Vi har testet ut SpotBugs og den fant ingen errors, men noen bugs. 
Den returnerte blant annet disse bugs'ene: 

* [ERROR] Medium: BugInstance message is inf112.skeleton.app.GameScreen.getEnemies() may expose internal representation by returning GameScreen.enemies
Dette betyr at det ikke er enkapsulert nok. Derfor er dette nå endret fra å returnere listen med enemies, til en copy av listen med enemies. 

* [ERROR] Medium: Unwritten field: inf112.skeleton.app.Entity.Enemy.maxHealth [inf112.skeleton.app.Entity.Enemy] At Enemy.java:[line 320] UWF_UNWRITTEN_FIELD
Denne erroren betyr at vi prøver å få tilgang til en udefinert variabel. I dette tilfellet fant vi ut at denne metoden/variablen ikke blir brukt, derfor fjernet vi den og løste det på den måten. 

* [ERROR] Medium: inf112.skeleton.app.TileMapHelper.gameScreen should be package protected [inf112.skeleton.app.TileMapHelper] In TileMapHelper.java MS_PKGPROTECT
Denne erroren betyr at gameScreen variabelen burde være package protected og ikke public. Så vi fjernet public og gjorde den package protected. 



## Retrospektiv

Vi syntes prosjektet har gått fint og rollene i teamet fungerte bra. Vi har jobbet litt hver for oss med forskjellige oppgaver også å merget litt når vi møtes. Tarjei tok ansvar for dette ettersom han har mest erfaring med merging. Vi synes at valget om å bruke Discord har fungert fint. Trello-board for ideer funket også veldig bra. Vi har delt ut oppgaver til hver enkelt og det har egentlig fungert fint. Vi har lært å bruke git som et verktøy og mye om merging og branches. Alt i alt er vi fornøyd med hvordan spillet har blitt og vi er ganske stolte over grafikken. 

Det er noen ting vi kunne gjort annerledes. Vi kunne laget tester underveis, i stedet for å lage mot slutten av prosjektet. Det at det har vært så vanskelig å teste libgdx har nok gjort at vi har utsatt det. I tillegg burde vi har startet tidligere med å sette opp et skjelett for spillet, ettersom vi kanskje undervurderte hvor lang tid dette ville ta. Vi kunne nok også lest oss litt mer opp på LibGDX og Box2D i starten. En ting vi også har tenkt på er hvordan vi har organisert branchene våre. I starten var vi litt ukjent med git og derfor tenkte vi å lage en branch hver der vi jobbet, også kalte vi denne branchen for våres egne navn. I ettertid ser vi at vi burde opprettet nye branches når vi startet på nye oppgaver og kalt disse noe relevant. Dette er noe vi lærer av og tar med oss videre. 


## Møtereferater

* REFERAT møte Torsdag. 20/04/2023
    * Tilstede: Vetle, Kavya, Tarjei, Brage
    * Vi brukte dette møtet på å diskutere hva som manglet å gjøre frem mot endelig innlevering av produktet, og leste tilbakemeldings-utkast fra gruppeleder sammen. Videre delte vi ut oppgaver etter hva som manglet å gjøres
    * Tilbakemelding fra Belmin:
        - Mangler tester for å oppnå 75% test coverage
        - Mangler powerups 
        - Mangel / Feil på brukerhistorier for denne obligen (se Belmin sin tilbakemelding).
        - Klassediagrammet ser veldig rotete ut, ikke ta med veldig mye unødvendig (se Belmin sin tilbakemelding).
        - Husk å skriv hele teambeskrivelse og rollefordeling til neste oblig.
        - Markdown er foreløpig ikke oversiktlig nok (se Belmin sin tilbakemelding).

    * Det vi mener må gjøres:
        * Legge til cooldown på angrep med kniven (eks: 1 sekund / 0.5 sekund) 
        * Fikse bugs med at fiender blir angrepet / dør flere ganger (lyden av at de blir skadet blir også kjørt flere ganger). 
        * Endre game over screen sånn at det står game over som UI i tillegg til det andre. 
        * Legge til tittelen: Slay-a-Lot  som både UI, og i git. Altså at spillet vårt ikke heter java-platformer-thing eller hva det gjør nå. 
        * Legge til en instruksjon for å kontrollere.
        * Legge til en kill count/score i game over. 
        * Gjøre at sangen i spillet looper. 
        * Skriv en litt bedre forklaring på hvordan man kjører spillet.
        * Bytte “Gun Unlocked” text til bilde så resolution ikke blir ødelagt (bruke gjentakende font gjennom hele spillet). 
        * Legge til kildereferanser til lyd / sprites om er blitt brukt i spillet. 
        * Legge til powerup hvis spiller får eks 10 kills - EKS: en timer som gir spiller mer hastighet/mer skade i en gitt tid. 
        * Fikse at klassediagrammet ikke ser rotete ut lenger, fjerne unødvendig tekst / innhold. 
        * Fikse mangel / feil på brukerhistorier vi har skrevet (se Belmin sin tilbakemelding). 
        * Legge til “powerup” UI for ammo refill & health. Spiller må fysisk hente den etter at den er låst opp. 
        * Fikse bug at enemy beveger seg når de blir skadet (har prøvd å implementere at de skal stå stille i et  sekund hvis de blir skadet). 
        * Gjøre Markdown-filen mer oversiktlig og lettere å lese (fått beskjed at den ikke er formatert bra i tilbakemelding fra Belmin): 
        * Oppdatere readme. 
        * Prøve å utvide testene: 
        * Rydde Trello boardet og sortere hva som har blitt gjort / ikke blitt gjort.
        * Skrive hele teambeskrivelse og rollefordeling på git.
       

* REFERAT møte Torsdag. 27/04/2023
    * Tilstede: Vetle, Kavya, Tarje, Brage, Ole Kristian
    * Vi brukte dette møtet på å jobbe videre med oppgavene vi delte ut forrige møte. Videre diskuterte vi også smått hva som manglet å gjøre frem mot endelig innlevering av produktet.


