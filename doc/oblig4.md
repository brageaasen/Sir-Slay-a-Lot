# Oblig 4

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


## Dette har vi fikset siden sist oblig:
* Oppdatert startskjerm med “logo”
* Oppdatert sluttskjerm med score og game over UI.
* Lagt til kontroller på startskjerm
* Fikset bug hvor fiende blir angrepet flere ganger
* Fikset bug hvor død-animasjon ikke alltid ble vist for fiende
* Lagt inn flere lyder
* Laget flere tester


## Manuelle tester: (Antall: )
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

## Klassediagram
<img src="/src/main/resources/assets/Gitlab/klassediagram.png" alt="Opening Screen" title="Opening Screen">

## Spotbugs: 
* Vi har testet ut SpotBugs og den fant ingen store feil. Men, den fant flere BugInstances som ligner på denne: "BugInstance message is inf112.skeleton.app.GameScreen.getEnemies() may expose internal representation by returning GameScreen.enemies". Dette betyr at det ikke er enkapsulert nok. Derfor er dette nå endret fra å returnere listen med enemies, til en copy av listen med enemies.
* Rollene i teamet fungerer fint. Vi jobber litt hver for oss med forskjellige oppgaver også pleier vi å merge litt når vi møtes. Tarjei tar ansvar for dette ettersom han har mest erfaring med merging. Vi har ikke valgt noe teamlead ettersom vi klarer oss fint uten. Kundekontakten funker fint, selv om han ikke har hatt noe særlig kontakt med kunder.. 
* Vi hadde ikke skrevet om dette som en rolle sist. Men Brage har fått ansvar for å lage sprites/texture og lyd. Så han har en slags designer-rolle.
* Vi synes at valget å bruke Discord fungerer fint. Trello-board for ideer funker også bra. Foreløpig har vi vært fornøyd med valgene vi har tatt. Vi har pleid å dele ut oppgaver til hver enkelt og det har egentlig fungert fint.  
Gruppedynamikken er fin. Det har ikke oppstått noen konflikter enda. Kommunikasjonen funker som nevnt tidligere fint og det skjer over Discord.
* Det vi har klart til nå er å bruke git som et verktøy. Vi har lært mye om merging og branches. Vi er fornøyde med hvordan prosjektet vårt er blitt bygd opp, nemlig steg for steg. Da har det vært lett å dele ut oppgaver og henge med. Ting vi kan bli bedre på er å si hvor langt vi er kommet på forskjellige oppgaver. I tillegg kan vi bli flinkere til å skrive tester underveis. Selv om vi kanskje ikke har trengt det foreløpig, kan vi bli bedre til å spørre hverandre om hjelp til koding utenom de tidene vi møtes.
* Forrige oblig skulle vi følge opp disse punktene:
    * Skrive tester underveis
    * Si hvor langt vi er kommet på oppgaver
* Vi er fortsatt ikke blitt så flinke til å skrive tester underveis, mye på grunn av at vi finner det vanskelig å teste med libgdx. Vi er blitt flinkere til å si hvor langt vi er kommer på oppgaver, ofte gjennom å oppdatere trello.
* Vi har prioritert oppgavene fremover ut fra hva som er viktigst først. Vi må fikse diverse bugs vi har funnet. I tillegg skal vi få på plass powerups, som f.eks. flere skudd og liv. I tillegg skal vi få på plass lyd for de fleste bevegelser. Vi må også bytte navn på prosjektet. Dette er vi ikke sikre på om er en enkel prosess, men det finner vi ut av. Vi må også finne et navn på spillet. 
* En bug vi har funnet er at spillet kjører mye raskere på Vetle sin mac enn de andre sin pc. Derfor eksperimenterer vi litt med jumpforce, speed og gravity for å finne det som funker best. 
* Ettersom vi har funnet ut at det er vanskelig å teste med JUnit og LibGdx har vi laget noen manuelle tester.


## Dette har vi fikset siden sist oblig:

## Møtereferater

* REFERAT møte Torsdag. 20/04/2023
    * Tilstede: Vetle, Kavya, Tarje, Brage
    * Vi brukte dette møtet på å diskutere hva som manglet å gjøre frem mot endelig innlevering av produktet, og leste tilbakemeldings-utkast fra gruppeleder sammen. Videre delte vi ut oppgaver etter hva som manglet å gjøres
    * Tilbakemelding fra Belmin:
        - Mangler tester for å oppnå 75% test coverage
        - Mangler powerups 
        - Mangel / Feil på brukerhistorier for denne obligen (se Belmin sin tilbakemelding).
        - Klassediagrammet ser veldig rotete ut, ikke ta med veldig mye unødvendig (se Belmin sin tilbakemelding).
        - Husk å skriv hele teambeskrivelse og rollefordeling til neste oblig.
        - Markdown er foreløpig ikke oversiktlig nok (se Belmin sin tilbakemelding).

    * Det vi mener må gjøres:
        * Legge til cooldown på angrep med kniven (eks: 1 sekund / 0.5 sekund) - OLE
        * Fikse bugs med at fiender blir angrepet / dør flere ganger (lyden av at de blir skadet blir også kjørt flere ganger). - OLE
        * Endre game over screen sånn at det står game over som UI i tillegg til det andre. - BRAGE
        * Legge til tittelen: Slay-a-Lot  som både UI, og i git. Altså at spillet vårt ikke heter java-platformer-thing eller hva det gjør nå. - BRAGE
        * Legge til en instruksjon for å kontrollere. - BRAGE
        * Legge til en kill count/score i game over. - BRAGE
        * Gjøre at sangen i spillet looper. - BRAGE
        * Skriv en litt bedre forklaring på hvordan man kjører spillet. - BRAGE
        * Bytte “Gun Unlocked” text til bilde så resolution ikke blir ødelagt (bruke gjentakende font gjennom hele spillet). - BRAGE
        * Legge til kildereferanser til lyd / sprites om er blitt brukt i spillet. - BRAGE
        * Legge til powerup hvis spiller får eks 10 kills - EKS: en timer som gir spiller mer hastighet/mer skade i en gitt tid. - VETLE
        * Fikse at klassediagrammet ikke ser rotete ut lenger, fjerne unødvendig tekst / innhold. - VETLE
        * Fikse mangel / feil på brukerhistorier vi har skrevet (se Belmin sin tilbakemelding). - VETLE
        * Legge til “powerup” UI for ammo refill & health. Spiller må fysisk hente den etter at den er låst opp. - VETLE
        * Fikse bug at enemy beveger seg når de blir skadet (har prøvd å implementere at de skal stå stille i et  sekund hvis de blir skadet). - Tarjei
        * Gjøre Markdown-filen mer oversiktlig og lettere å lese (fått beskjed at den ikke er formatert bra i tilbakemelding fra Belmin): - ALLE
        * Oppdatere readme. - ALLE
        * Prøve å utvide testene: - ALLE
        * Rydde Trello boardet og sortere hva som har blitt gjort / ikke blitt gjort.
        * Skrive hele teambeskrivelse og rollefordeling på git.
        * DELE OPP I MVC 

* REFERAT møte Torsdag. 27/04/2023
    * Tilstede: Vetle, Kavya, Tarje, Brage, Ole Kristian
    * Vi brukte dette møtet på å jobbe videre med oppgavene vi delte ut forrige møte. Videre diskuterte vi også smått hva som manglet å gjøre frem mot endelig innlevering av produktet.


