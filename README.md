<h4>Copyright 2022 Diaconu Tudor-Gabriel</h4>
<h1>Etapa 1 - Proiect</h1>

<h3>Citirea</h3>
Pentru a realiza citirea, m-am folosit de **formatul Jackson**, utilizand un
object mapper care realiza citirea intr-un database, creat cu ajutorul design
pattern-ului **Singleton**, utilizat pentru a stocatoate informatiile din
json-uri.

<h3>Flow-ul simularii - runda zero</h3>
Simularea este formata din 2 etape: etapa rundei 0 si etapa urmatorilor ani.
Pentru runda zero, am populat listele de copii si de cadouri, extragandu-le
din datele initiale si am realizat simularea impartirii cadourilor.

<h4>Impartirea cadourilor</h4>
Pentru a imparti cadourile, le-am sortat dupa pret, deoarece se dorea sa fie
oferit cel mai ieftin cadou. Apoi, am aflat categoria de varsta a fiecarui
copil, aceasta fiind utila pentru a afla average score-ul fiecarui copil din
database. Pentru a afla acest scor, am implementat design pattern-ul
**Strategy**, astfel construind cate o strategie de calcul doar pentru tipurile
Baby, Kid sau Teen, intrucat Young Adults nu mai primeau cadouri. De asemenea,
am implementat si un **Factory** pentru a crea strategia in functie de
categoria de varsta.
Apoi, dupa calculul scorurilor, am calculat buget-ul pentru fiecare copil si
am asignat fiecarui copil cadourile in functie de buget si categorii. Pentru a
putea primi un cadou, verificam in cazul unui copil daca nu a primit deja un
cadou din acea categorie, verificat daca are bani pentru respectivul cadou si
daca nu are deja in lista respectivul cadou.

<h3>Flow-ul simularii - urmatorii ani</h3>
Pentru urmatorii ani, initial reinitializam listele de cadouri si de categorii,
pentru a nu interfera cu metodele de primire a cadourilor. De asemenea, 
incrementam varsta si stergeam copii care ajungeau peste 18 ani. Apoi,
implementam fiecare schimbare anuala. Trebuie mentionat faptul ca, pentru a
implementa update-ul preferintei pentru cadouri, m-am complicat putin,
deoarece, am inversat ambele liste de preferinte(cea veche si cea cu cele noi),
le-am adaugat pe cele noi si apoi am inversat iar lista finala, pentru a se
pastra prioritatea dorita. Dupa implementarea acestor schimbari, am repetat
procesul de impartire a cadourilor ce a fost explicat si la runda 0.

<h3>Scrierea</h3>
Pentru a realiza scrierea, mi-am construit cateva clase. In primul rand, am
implementat un database creat special pentru scriere, care contine o lista
de liste de ChildWriter. ChildWriter este o clasa ce reprezinta un copil, insa
care are doar field-urile ce trebuie afisate in json-uri, iar acest ChildWriter
este creat cate unul pentru fiecare copil in parte. La final, se realizeaza
scrierea in json-uri a intregului database de scriere.

<h2>Feedback tema </h2>
O tema chiar ok, care m-a ajutat sa inteleg mai bine unele design pattern-uri.
