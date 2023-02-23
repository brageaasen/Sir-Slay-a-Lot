A0)
Gruppenavn: Bit by Bit

Kompetanse:
Vetle - Bare programmering gjennom skole.
Thomas - Bare programmering gjennom skole.
Ole - Hovedsakelig programmering gjennom skole. Har lært litt andre språk i egen fritid i tillegg.
Kavya - Har programmert i ulike språk (C, Java, SQL, Haskell, JS, HTML), men har ikke særlig drevet mye med prosjekter. Begynte å programmere etter videregående.
Tarjei - Har programmert i noen år før universitet. Også på fritiden.
Brage - Programmert gjennom skole, drevet litt med Unity.


A1)
Se Readme-fil
Roller:
Kundekontakt/Developer - Brage Aasen, erfaring fra jobbsammenheng 
Mergeansvarlig/Developer - Tarjei Landøy, mest erfaring med Git
Developer - Thomas Claussen Stakset 
Developer - Kavya Malhotra 
Developer - Vetle Mathiesen Knutsen 
Developer - Ole Kristian Breivik

A2)
Konsept: 

? = kanskje

*Karakter som styres – går til høyre/venstre og kan hoppe
* 2D verden:
   * Plattform – Plattformer som spilleren kan hoppe til
   * Vegger – vertikale flater som spilleren ikke kan gå gjennom
   * Spilleren kan både hoppe og falle
* Fiender som beveger seg og angriper deg
* Spilleren kan få mer liv ved å plukke opp hjerter
* Utfordringen i spillet:
	Game loop, overleve lengst mulig mot fiender med uendelig loop.
	Runder
		Fiendene blir sterkere fra runde-til-runde / mer helse/skade
* Verden er bygget opp av tiles med fast størrelse 
* Verden tar i bruk plattformer / stiger for å skape et interaktivt miljø som man kan hoppe opp gjennom 
* Verden er på størrelse med skjermen
?* Kanskje plattformer som beveger seg 
* Spilleren kan drepe fiendene ved å angripe dem, og senke hitpoints til 0
* «Power-ups» som gir spilleren spesielle krefter
	* F.eks insta-kill, speed boost, super jump, osv
?* Akrobatikk
?* Har penger som kan brukes å kjøpe dører og våpen
?* Ha flere etasjer

A3)

Vi tenker å dele ut oppgaver til hverandre som vi kan jobbe på hjemme frem mot neste møte. Møtene organiserer vi ved å snakke sammen på discord og avtale en tid, vanligvis torsdag kl 10. Sted kan variere. Jobber i forskjellige branches og snakker sammen på møtene om vi trenger hjelp med en oppgave eller om det gikk fint. Vi har en merge-ansvarlig som har ansvar for merging. Vi har et trello board der vi kan legge til ideer underveis og i tillegg et google docs dokument hvor vi kan skrive sammen. 

Vi planlegger å bruke et “TODO:” panel på trello, der ulike gruppemedlemmer kan plukke ting å implementere/gjøre underveis i prosjektet. Noen oppgaver er større enn andre, og vi vil da samarbeide på disse.Vi bør også sørge for at vi ikke gjør for mye om gangen (f.eks maks x å gjøre i to do), akkurat som i Kanban.


A3) 
Målet for applikasjonen er en game loop der det er en spillbar karakter som kan angripe en fiende. Spilleren og fienden kan begge miste liv og angripe hverandre. Dersom fienden dør, skal det komme en vanskeligere fiende eller flere fiender. 

Minimal Viable Product: 
Vise et brett/verden
Vise en spiller/karakter
Flytte spilleren høyre/venstre/hoppe med piltaster/wasd
Spilleren krasjer i vegg/platform og kan stå på platformene
Spilleren har hjerter/liv og kan plukke opp nye hjerter eller regenerere liv (hvis spilleren er ikke slått)
Vise en NPC/fiende som kan bevege seg 
Spilleren kan miste hjerter / ta skade
Game Over når fiende er død eller tom for liv
Vise Game Over skjerm UI
	
Brukerhistorier:
Som spiller trenger jeg et spillbrett/verden for å kunne spille.
Akseptansekriterie: En boks/verden der spillet kan foregå.
Arbeidsoppgave: Finne ut hvordan man lager en verden.

Som spiller trenger jeg en karakter for å spille.
Akseptansekriterie: En figur på skjermen.
Arbeidsoppgave: Finne ut hvordan man lager en figur.

Som programmerer trenger jeg å kunne skille mellom hva som er bakgrunn og hva som er objekter i spillet.
Akseptansekriterie: Må være mulig å skille bakgrunn og objekter.
Arbeidsoppgave: Sørge for en tydelig forskjell mellom bakgrunn og objekter.

Som spiller trenger man å kunne navigere i UI, startskjermen/Game Over-skjermen for å kunne starte nytt spill eller avslutte pågående.
Akseptansekriterie: Et fungerende user interface med start og game over.
Arbeidsoppgave: Finn ut hvordan UI kan se ut og hvordan det funker.

Som spiller trenger jeg å kunne flytte karakteren for å ikke bli angrepet.
Akseptansekriterie: Karakteren må kunne flyttes.
Arbeidsoppgave: Finn ut hvordan spilleren kan flytte seg.

Som spiller trenger jeg å kunne angripe for å vinne.
Akseptansekriterie: Karakteren må ha mulighet for å angripe.
Arbeidsoppgave: Finn ut hvordan spilleren kan angripe.

Som fiende trenger man å kunne bevege seg uten å bli styrt av en spiller.
Akseptansekriterie: Fienden må kunne bevege seg av seg selv.
Arbeidsoppgave: Finn ut hvordan fienden NPC’s fungerer i spill.

Som fiende trenger man å kunne angripe for å kunne vinne over spiller.
Akseptansekriterie: Fienden må kunne angripe spilleren.
Arbeidsoppgave: Finn ut hvordan en NPC kan angripe.

Som spiller trenger jeg å få beskjed når spillet er over for å avslutte runden.
Akseptansekriterie: Game Over skjerm når spiller er død.
Arbeidsoppgave: Finn ut hvordan Game Over kan poppe opp når spiller er død.

En prioritert liste over hvilke brukerhistorier vi vil ha med i første iterasjon:
Som spiller trenger jeg et spillbrett/verden for å kunne spille.
Som spiller trenger jeg en karakter for å spille.
Som fiende trenger man å kunne bevege seg uten å bli styrt av en spiller.




A5)
Vi synes det gikk relativt greit å gjøre oblig1. Alle har vært innstilt på å komme frem til en spill-idé som alle kan være fornøyd med. Vi har hatt problemer med sykdom, men vi klarte å håndtere det. Det funker bra å kommunisere på discord, så det tenker vi å fortsette med. Vi må fortsatt tilpasse prosessen og vi må snakke litt mer med hverandre, men vi er på riktig spor. Teamet ønsker å utforske libGDX for å bli bedre kjent med rammeverket vi skal bruke fremover.
Vi tenker at vi traff helt greit på oppgaven. Vi følte det ble litt trangt med tid grunnet en del sykdom og henholdsvis fordelt oppmøte på gruppen. På grunn av dette ønsker vi å begynne tidligere med neste oppgave.