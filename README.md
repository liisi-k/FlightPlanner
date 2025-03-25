# FlightPlanner

## Paigaldamine ja käivitamine
1. Kloonige repository:
    ```bash
    git clone https://github.com/liisi-k/FlightPlanner.git
    ```

2. Installige:
   ```bash
    ./mvnw install  # Installib backend'i sõltuvused
    npm install     # Installib frontend'i sõltuvused
    npm install axios  # Installib axios, et frontend saaks teha päringud backendile
    ```
4. Liikuge kausta:
    ```bash
   cd backend
    ```
5. Käivitage backend:
    ```bash
   ./mvnw spring-boot:run
    ```
6. Avage uus terminal ja liikuge kasuta:
    ```bash
   cd frontend
    ```
7. Käivitage frontend
   ```bash
   npm run dev
   ```
8. Avage brauser ja külastage rakendust:
   * Backend on saadaval aadressil:
   ```bash
   http://localhost:8080
   ```
   * Frontend on saadaval aadressil:
   ```bash
   http://localhost:5173
   ```

## Kood ja Struktuur
Rakenduse põhiosad:
- Frontend: Rakendus on kirjutatud Vue.js raamistikus. Kasutatakse ka Pinia staatuse haldamiseks ja Axios API päringute jaoks.
- Backend: Kasutatakse Spring Boot'i.

Rakenduse struktuur:
- **Frontend** ja **Backend** on eraldi kaustades ning suhtlevad omavahel **REST API** kaudu.


Rakenduse põhifunktsioonid:
* Lennu valimine: Kasutaja saab valida lennu sihtkoha, kuupäeva, lennuaja ja hinna järgi. Filtreerimise võimalused aitavad kasutajal kiiremini leida sobiva lennu.
* Istekohtade soovitamine: Kui kasutaja valib lennu, siis pakub rakendus soovitusi istekohtade valimiseks, võttes arvesse kasutaja eelistusi nagu:

    * Istekoht akna all
    
    *  Rohkem jalaruumi
    
    * Lähedal väljapääsule

* Juhuslikult hõivatud kohad: Kui kõik kohad on broneeritud, siis rakendus genereerib juhuslikult hõivatud kohad, et simuleerida reaalses elus toimuvat.
* Istekohtade kuvamine: Rakendus kuvab lennuki istekohtade plaani koos juba broneeritud ja vabad kohad.


Autor
Liisi Kask
Lahenduse arendamiseks kulus umbes 20 tundi.

Aitäh
Tänu järgmistele ressurssidele:
- **Stack Overflow**: Kasutatud erinevaid lahendusi ja ideid, et lahendada koodiga seotud probleeme.
- **ChatGPT**: Koodinäidete ja arendusvõtete leidmiseks.
- **ClaudeAI**: Täiendavate arendustööde ja mõtete genereerimiseks.
