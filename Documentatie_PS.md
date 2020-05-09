![utcn_logo.jpg](https://www.dropbox.com/s/jp4ec7zvbyr4rsz/utcn_logo.jpg?dl=0&raw=1)

# Documentatie PS

## Aplicatie pentru prezenta la cursuri 
  - Student :Popa Rares-Dennis 
  - Grupa 30239

# 1.Descrierea Problemei

Scopul acestei aplicatii este de a usura procedeul de verificare a prezentelor la curs.
Problema de rezolvat este reprezentata de facilitarea unui sistem pe o platforma de mobile, in acest caz este vorba de Anroid, unde profesorii sa creeze cursuri, iar studentii sa isi marcheze singuri prezenta la acel curs folosind un cod alfanumeric sau un cod QR.
Pentru evitarea situatiilor de marcare a unui student ca fiind prezent desi acesta nu este prezent, trebuie sa existe un sistem de verificare.

## 2.Solutia 

Pentru implementarea partii de backend am folosit mai multe API-uri, de exemplu : API-ul pus la dispozitie Spring, mai exact Spring Boot, Hibernate si JPA (ultimele 2 sunt folosite pentru conexiunea la baza de date).Pentru elaborarea solutiei am folosit IDE-ul IntelliJ, iar pentru baza de date am folosit suita pusa la dispozitie de MySQL.  

### 2.1.API 

#### 2.1.1 Spring Boot 

Spring este un framework folosit utilizat in cadrul platformei JAVA. Spring Boot are urmatoarele avantaje : 
> Tomcat inglobat in aplicatie 
> Fara configurari XML 
> Usor de setat 

#### 2.1.2 Hibernate si JPA
Hibernate este un framework ce usureaza munca dezvoltatorului deoarece mapeaza obiecte Java la o baza de date relationala, si tipurile de date Java la tipuri de date SQL. 
JPA este utilizat pentru managementul bazelor de date.

### 2.2.JSON  
Formatul JSON este folosit pentru schimbarea de mesaje intre servere. Spring are un  "ObjectMapper" folosit pentru a converti un JSON la un obiect Java. Un fisier JSON contine perechi de chei-valoare pe care le mapeaza la un obiect(atribut-valoare). 

## 3.Implementare 

### 3.1.Clasele utilizate 
#### 3.1.1.Clasele 
Clasele entitati reprezinta modele cu care aplicatia interactioneza. De exemplu, clasa User are toate informatiile de baza pe care un User le detine (email,nume,nr de telefon etc.). Clasele Student,Professor si Admin sunt clase care extind clasa User, acestea avand atribute diferite si permisiuni diferite in cadrul aplicatiei.  

Interfetele DAO sunt utilizate pentru managementul bazei de date.Clasele entitati coincid cu tabelele din baza de date.  

Clasele controller sunt utilizate pentru comunicare cu Serverul.Managementul fiecarei entitati se realizeaza in clasa controller asignata entitatii. Folosind adnotatia @RestController aplicatia mapeaza o comanda la controller-ul respectiv.  

Clasa UserFactory este utilizata la creearea unui nou obiect de tipul User.
> UML updatat 

![UML PS2020 (2).png](https://www.dropbox.com/s/l6b8jji9uxqdxt5/UML%20PS2020%20%282%29.png?dl=0&raw=1)

Clasa **User** retine informatiile de baza pe care le are orice user al aplicatiei, de exemplu:
>- prenumele 
>- numele 
>- emailul 

Clasele **Student**,**Professor** si **Admin** extind clasa **User**, acestea proceseaza date specifice fiecarui tip de utilizator. 

Clasa **Course** retine toate datele referiotare la un curs, de exemplu: 
>- numele 
>- data 
>- o lista de prezenta 

Clasa **Subject** proceseaza informatiile legate de o materie, acesata fiind formata din urmatorele atribute: 
>- o lista de studenti care urmeaza materia respectiva 
>- numele materiei 
>- numarul de credite 
>- o lista de cursuri 
>- un profesor 

Clasa **AttendanceCode** este utilizata pentru generarea unui cod unic pentru fiecare curs, codul fiind folosit mai apoi de studenti pentru a marca prezenta la curs. 
Clasa **PasswordAuthentication** este utilizata pentru a genera un *hash* fiecarei parole. 
Clasa **Account** retine datele de autentificare pentru useri. 
Clasa **UserFactory** si enumeratia **UserTypes** sunt utilizate impreuna, acestea fiind utilizate in implementarea design-patternului *Factory*. 

Toate *Interfetele* **DAO** extind interfata **JPARepository** si executa operatii pe baza de date. 
Clasele **Controller** executa diverse operatii pe modelul de date, cat si pe baza de date.

![sequence diagram 1.png](https://www.dropbox.com/s/xfomimrbdgjqc13/sequence%20diagram%201.png?dl=0&raw=1)



#### 3.1.2 Baza de date 
 
 Tabele din baza de de date coincid cu clasele entitate. 
 
 ![database.png](https://www.dropbox.com/s/g4etfxe5fytang2/database.png?dl=0&raw=1) 
 
 > Schema bazei de date 
 
 ![Untitled Document.png](https://www.dropbox.com/s/kk4xisjtkn9edmj/Untitled%20Document.png?dl=0&raw=1)


