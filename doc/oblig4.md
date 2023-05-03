# Oblig 4

* Manuelle tester: (Antall: )
    * Bevege høyre og venstre med A og D. Hoppe med Space.
    * Hoppe på en blokk for å sjekke collision.
    * Se at sprites endrer seg etter bevegelse og retning.
    * Se at spiller kan angripe med enter-knappen.
    * Se at pistol skyter.
    * Se at spiller kan bytte mellom kniv og pistol.
    * Se at spiller får falldamage av å falle for hardt.
    * Se at angrepssprites vises for spiller og enemy.
    * Se at fiende beveger seg av seg selv.
    * Se at fiende dør dersom han blir slått mange ganger.
    * Se at flere fiender spawner.
    * Se at fiender har en død-animasjon

* Klassediagram
*INSERT KLASSEDIAGRAM BILDE HER*

* Spotbugs: 
* Vi har testet ut SpotBugs og den fant ingen store feil. Men, den fant flere BugInstances som ligner på denne: "BugInstance message is inf112.skeleton.app.GameScreen.getEnemies() may expose internal representation by returning GameScreen.enemies". Dette betyr at det ikke er enkapsulert nok. Derfor er dette nå endret fra å returnere listen med enemies, til en copy av listen med enemies.
* Rollene i teamet fungerer fint. Vi jobber litt hver for oss med forskjellige oppgaver også pleier vi å merge litt når vi møtes. Tarjei tar ansvar for dette ettersom han har mest erfaring med merging. Vi har ikke valgt noe teamlead ettersom vi klarer oss fint uten. Kundekontakten funker fint, selv om han ikke har hatt noe særlig kontakt med kunder.. 
* Vi hadde ikke skrevet om dette som en rolle sist. Men Brage har fått ansvar for å lage sprites/texture og lyd. Så han har en slags designer-rolle.
* Vi synes at valget å bruke Discord fungerer fint. Trello-board for ideer funker også bra. Foreløpig har vi vært fornøyd med valgene vi har tatt. Vi har pleid å dele ut oppgaver til hver enkelt og det har egentlig fungert fint.  
Gruppedynamikken er fin. Det har ikke oppstått noen konflikter enda. Kommunikasjonen funker som nevnt tidligere fint og det skjer over Discord.
* Det vi har klart til nå er å bruke git som et verktøy. Vi har lært mye om merging og branches. Vi er fornøyde med hvordan prosjektet vårt er blitt bygd opp, nemlig steg for steg. Da har det vært lett å dele ut oppgaver og henge med. Ting vi kan bli bedre på er å si hvor langt vi er kommet på forskjellige oppgaver. I tillegg kan vi bli flinkere til å skrive tester underveis. Selv om vi kanskje ikke har trengt det foreløpig, kan vi bli bedre til å spørre hverandre om hjelp til koding utenom de tidene vi møtes.
* Forrige oblig skulle vi følge opp disse punktene:


Skrive tester underveis


Si hvor langt vi er kommet på oppgaver


Vi er fortsatt ikke blitt så flinke til å skrive tester underveis, mye på grunn av at vi finner det vanskelig å teste med libgdx. Vi er blitt flinkere til å si hvor langt vi er kommer på oppgaver, ofte gjennom å oppdatere trello.
Vi har prioritert oppgavene fremover ut fra hva som er viktigst først. Vi må fikse diverse bugs vi har funnet. I tillegg skal vi få på plass powerups, som f.eks. flere skudd og liv. I tillegg skal vi få på plass lyd for de fleste bevegelser. Vi må også bytte navn på prosjektet. Dette er vi ikke sikre på om er en enkel prosess, men det finner vi ut av. Vi må også finne et navn på spillet. 
En bug vi har funnet er at spillet kjører mye raskere på Vetle sin mac enn de andre sin pc. Derfor eksperimenterer vi litt med jumpforce, speed og gravity for å finne det som funker best. 
Ettersom vi har funnet ut at det er vanskelig å teste med JUnit og LibGdx har vi laget noen manuelle tester.


## Dette har vi fikset siden sist oblig:

## Møtereferater

