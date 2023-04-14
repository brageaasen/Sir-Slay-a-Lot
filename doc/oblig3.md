REFERAT møte Torsdag. 23/03/2023

Tilstede:

Digitalt 

Denne torsdagen valgte vi å ta gruppetimen hjemmefra ettersom noen var syke og opptatt med andre ting. Vi jobbet derfor med oppgavene våre fra forrige gang hver for oss. Siden forrige gang har Thomas laget en game over screen. I tillegg har Brage laget angrep sprites for Player, men disse er ikke implementert enda. Sist tenkte han også å lage et nytt map, men vi bestemte oss for å beholde det vi hadde. Resten jobber fortsatt med oppgavene sine fra sist møte. 

Vi delte ut oppgaver:

-   Fikse at enemy ikke hopper random -> Ole

-   Implementere gunner/range angrep? -> Vetle

-   Gjøre Start screen mer visuelt estetisk (hjelpeside som viser hvordan kontrollene funker) ->  Brage

-   Lage game loop med flere enemies som spawner over tid -> Alle

-   Legge til ulike Powerups som kan bli plukket opp -> alle? 

-   Legge til player combat (bruke placeholder sprites foreløpig) - Alle

-   Lage et runde system -> alle

-   Se på å kanskje legge til particles for regn -> Brage

-   Implementere Lyd -> Brage

-   Debugge knife attack, slik at fienden blir skadet bare 1 gang når enter er trykket -> Kavya

-   Implementere fall damage (lav prioritet) -> Kavya




REFERAT møte Torsdag. 30/03/2023

Tilstede:

Digitalt

Igjen så hadde vi et digitalt møte, da det var rett før påskeferien og noen skulle reise hjem. Ettersom det gikk fint sist, valgte vi å gjøre det igjen. Siden sist har vi implementert en pistol, men denne gjør ikke skade foreløpig. I tillegg er player combat sprites kommet på plass og spillet ser mye bedre ut allerede. Noen ting vi sliter med er å få flere enemies til å spawne og lage en game loop. Ole jobber også med å finne ut av hvorfor enemy hopper random. Brage driver med å lage game sounds. Thomas jobber med start screen og Kavya jobber med spawning av fiender. Vi må også lage mange flere tester. 

Vi delte ikke ut oppgaver enda ettersom vi tar fri neste uke. Dersom vi vil jobbe med prosjektet ble vi enige om å sjekke ut Trello for oppgaver vi kan gjøre.





REFERAT møte Torsdag. 13/04/2023

Tilstede:

Vetle, Kavya, Ole Kristian, Brage

Vi brukte dette møtet til å jobbe med de ulike arbeidsoppgavene vi hadde tildelt hverandre. Videre brukte vi litt tid på å diskutere hva som måtte gjøres frem mot innleveringen imorgen.

Vi delte ut oppgaver:

* Brage lager ny og fungerende startskjerm, legger til musikk på spillet

* Skrive dokument (oppdatere fra sist, hva vi har gjort og skal gjøre)

* Vetle fikser nytt klassediagram

* Evt andre ting vi jobber med

Bugs vi må finne ut av:

* Når enemy dør vises ikke alltid død-animasjon/sprites. 

*Når bullet treffer enemy treffer den flere enn én gang. Fikse så den bare tar skade én gang per bullet.





Brukerhistorier for videre arbeid: 

-   Som spiller trenger jeg at spillet ikke har bugs for en god spillopplevelse

Akseptansekriterie: Spillet kan ikke inneholde bugs.

Arbeidsoppgave: Finne og fikse bugs.


-   Som spiller trenger jeg at spillet har en spennende startskjerm for å bli interessert i spillet.

Akseptansekriterie: Spillet må ha en innbydende startskjerm.

Arbeidsoppgave: Lage en fin startskjerm.


-   Som spiller trenger jeg at spillet har en forklaring på knappene for at jeg skal skjønne hvordan jeg spiller.

Akseptansekriterie: Spillet må ha en forklaring på knappene.

Arbeidsoppgave: Skrive forklaring på knappene på startskjermen.


-   Som spiller trenger jeg en mulighet for å få påfyll av skudd når jeg er tom.

Akseptansekriterie: Spillet må ha powerups for skudd.

Arbeidsoppgave: Implementere powerups.


-   Som spiller trenger jeg en mulighet for å få påfyll av health når jeg har lite.

Akseptansekriterie: Spillet må ha powerups for health.

Arbeidsoppgave: Implementere powerups.


-   Som spiller trenger jeg game sounds for å leve meg inn i spillet.

Akseptansekriterie: Spillet må ha diverse lyder ved diverse bevegelser.

Arbeidsoppgave: Implementere lyder.



Dette har vi fikset siden sist oblig:

-   Vi har introdusert et nytt våpen, pistol. 

-   Lagt inn inventory.

-   Spiller kan nå bytte mellom kniv og pistol. (Etterhvert låses pistol opp ettersom enemies dør). 

-   Flere fiender spawner. 

-   Spiller og fiender blir ikke lenger blokkert av hverandre. 

-   Lagt inn Audiomanager, foreløpig bare hoppelyd. 

-   Fikset så enemy ikke hopper random. 

-   Fikset bug hvor den bevegende platformen ikke er i veien for health og inventory.

-   Laget ordentlig startskjerm

-   Laget ordentlig end-skjerm

-   Oppdatert Readme litt

-   Lagt inn flere tester



Spotbugs: 

Vi har testet ut SpotBugs og den fant ingen store feil. Men, den fant flere BugInstances som ligner på denne: "BugInstance message is inf112.skeleton.app.GameScreen.getEnemies() may expose internal representation by returning GameScreen.enemies". Dette betyr at det ikke er enkapsulert nok. Derfor er dette nå endret fra å returnere listen med enemies, til en copy av listen med enemies.

Rollene i teamet fungerer fint. Vi jobber litt hver for oss med forskjellige oppgaver også pleier vi å merge litt når vi møtes. Tarjei tar ansvar for dette ettersom han har mest erfaring med merging. Vi har ikke valgt noe teamlead ettersom vi klarer oss fint uten. Kundekontakten funker fint, selv om han ikke har hatt noe særlig kontakt med kunder.. 

Vi hadde ikke skrevet om dette som en rolle sist. Men Brage har fått ansvar for å lage sprites/texture og lyd. Så han har en slags designer-rolle. 

Vi synes at valget å bruke Discord fungerer fint. Trello-board for ideer funker også bra. Foreløpig har vi vært fornøyd med valgene vi har tatt. Vi har pleid å dele ut oppgaver til hver enkelt og det har egentlig fungert fint.  

Gruppedynamikken er fin. Det har ikke oppstått noen konflikter enda. Kommunikasjonen funker som nevnt tidligere fint og det skjer over Discord. 

Det vi har klart til nå er å bruke git som et verktøy. Vi har lært mye om merging og branches. Vi er fornøyde med hvordan prosjektet vårt er blitt bygd opp, nemlig steg for steg. Da har det vært lett å dele ut oppgaver og henge med. Ting vi kan bli bedre på er å si hvor langt vi er kommet på forskjellige oppgaver. I tillegg kan vi bli flinkere til å skrive tester underveis. Selv om vi kanskje ikke har trengt det foreløpig, kan vi bli bedre til å spørre hverandre om hjelp til koding utenom de tidene vi møtes. 


Forrige oblig skulle vi følge opp disse punktene:

-   Skrive tester underveis

-   Si hvor langt vi er kommet på oppgaver

Vi er fortsatt ikke blitt så flinke til å skrive tester underveis, mye på grunn av at vi finner det vanskelig å teste med libgdx. Vi er blitt flinkere til å si hvor langt vi er kommer på oppgaver, ofte gjennom å oppdatere trello.

Vi har prioritert oppgavene fremover ut fra hva som er viktigst først. Vi må fikse diverse bugs vi har funnet. I tillegg skal vi få på plass powerups, som f.eks. flere skudd og liv. I tillegg skal vi få på plass lyd for de fleste bevegelser. Vi må også bytte navn på prosjektet. Dette er vi ikke sikre på om er en enkel prosess, men det finner vi ut av. Vi må også finne et navn på spillet. 

En bug vi har funnet er at spillet kjører mye raskere på Vetle sin mac enn de andre sin pc. Derfor eksperimenterer vi litt med jumpforce, speed og gravity for å finne det som funker best. 

Ettersom vi har funnet ut at det er vanskelig å teste med JUnit og LibGdx har vi laget noen manuelle tester.

Manuelle tester: 

-   Bevege høyre og venstre med A og D. Hoppe med Space. 

-   Hoppe på en blokk for å sjekke collision. 

-   Se at sprites endrer seg etter bevegelse

-   Se at fiende beveger seg av seg selv

-   Se at spiller kan angripe med enter-knappen

-   Se at fiende dør dersom han blir slått mange ganger

-   Se at spiller får falldamage av å falle for hardt

-   Se at spiller kan bytte mellom kniv og pistol

-   Se at pistol skyter

-   Se at angrepssprites vises for spiller og enemy

-   Se at flere fiender spawner

-   Se at fiender har en død-animasjon

![](https://lh3.googleusercontent.com/jXVHS1qj651m34J7s7laXF5KFtDPqG4aufCvI0p0ifEN4o3pesouXidSK6Tjhgocc7c4WAUo4B2YRqsGax75_gc3MhDcqbaZKVbeAQFP9D74rlLgGRsoVe7pnEh426qSk0l8r8D-MXcONRLlPgc33uA)