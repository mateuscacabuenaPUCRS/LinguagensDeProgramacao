% Trabalho 4 - Linguagens de Programação
% Carolina Ferreira, Mateus Caçabuena e Nicholas Spolti
% Site utilizado: https://swish.swi-prolog.org/

% Fatos
homem(ivo).
homem(gil).
homem(rai).
homem(ary).
homem(noe).
% Novos descendentes
homem(jose).
homem(simao).
homem(willy).
% Novos descendentes

mulher(ana).
mulher(bia).
mulher(eva).
mulher(clo).
mulher(lia).
mulher(gal).
% Novas descendentes
mulher(joana).
mulher(matilde).
mulher(camelia).
% Novas descendentes

pai(jose,ana).
pai(jose,gil).
pai(simao,ivo).
pai(willy,bia).
pai(ivo,eva).
pai(gil,rai).
pai(gil,clo).
pai(gil,ary).
pai(rai,noe).
pai(ary,gal).

mae(joana,ana).
mae(joana,gil).
mae(matilde,ivo).
mae(camelia,bia).
mae(ana,eva).
mae(bia,rai).
mae(bia,clo).
mae(bia,ary).
mae(eva,noe).
mae(lia,gal).

%Regras
gerou(X, Y) :- pai(X, Y) ; mae(X, Y).
filho(X, Y) :- homem(X), pai(Y, X).
filho(X, Y) :- homem(X), mae(Y, X).

filha(X, Y) :- mulher(X), pai(Y, X).
filha(X, Y) :- mulher(X), mae(Y, X).

irmao(X, Y) :- pai(Z, X), pai(Z, Y), X\=Y.

tio(X, Y) :- homem(X), irmao(X, Z), (pai(Z, Y); mae(Z, Y)).
tia(X, Y) :- mulher(X), irmao(X, Z), (pai(Z, Y); mae(Z, Y)).

primo(X, Y) :- homem(X), (tio(Z,X), pai(Z,Y)) , gerou(Z,Y).
prima(X, Y) :- mulher(X), (tio(Z,X), pai(Z,Y)) , gerou(Z,Y).

avo(X, Y) :- homem(X), pai(X, Z), (pai(Z, Y); mae(Z, Y)).
avo(X, Y) :- homem(X), mae(X, Z), (pai(Z, Y); mae(Z, Y)).

avo(X, Y) :- mulher(X), pai(X, Z), (pai(Z, Y); mae(Z, Y)).
avo(X, Y) :- mulher(X), mae(X, Z), (pai(Z, Y); mae(Z, Y)).

bisavo(X, Y) :- avo(X, Z), (pai(Z, Y); mae(Z, Y)).