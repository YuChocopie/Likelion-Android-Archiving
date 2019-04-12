/*

0.
what is kotlin?

kotlin is a programming language that is announced by JetBrains, in 2011.
and google has declared that its the next development language since 2017.

it is very easy, and short when coded. and has a strong error detection.

kotlin is a gerneral purpose, open source, and statically typed
so called pragmatic programming language for the jvm
and adroid, that combines object-oriendted and functional programming features.

since im just a beginner i prolly have to start with printing hello world

how it is spoken is : 
*/

fun main (args: Array<String>) {
	printfln ("Hello World!")
}

/*
we can see theres no need of semicolon.


1. 
gettin started with basic 


(1)
a package has to be said on the very first line of a source code.
for kotlin to get imported all functions and classes it needs the name of the package.


(2)
function definition is like : 
fun THENAME (a : TYPE, b : TYPE) : RETURN_TYPE {
	whatever
}

you can skip the return_type if the function don return a value.

if a function is just some kinda calculator,
you can say :

fun THENAME (a : TYPE, b : TYPE) = a + b



(3)
val ~ : read-only value, its like const value in c.
var ~ : value that can be modified
 

(4)
in c it was %~ , name
its $~ in kotlin


(5)
if and else, it looks the same w c but
like i said in (2), if a function is like a simple calculator
you can say :

fun bigger(a : Int, b : Int) = if (a>b) a else b

a>b?a:b is more simple isn it?


(6)
for ( i in 1..10) {
	print(x)
}

result : 12345678910

for ( x in 1..10 step 2)
= for (int x = 1; x <= 10; x+=2)

or 

for (x in 10 downTo 0 step 2)
= for (int x = 10; x >= 0; x -= 2)


i can be used as an index

val list =  list1{"hi ", "i'm ", "a developer" }
for ( i in list)
	print(i);

hi i'm a developer

if we put ! in front of in
can prevent an error of being out of range

it is very intuitive


(7)
while seems to be same w that in c 


(8)
when can be used like switch

while(res) {
	1 -> print("one")
	2 -> print("two")
	.
	.
	.
} 


(9)
uses collection 
think its like struct in c


(10)
when (s) {
	is Name ->
	is Height ->

}

- checking that an object is an instance of the class



3. 
when code you need to know...


if a file has one class, the name of the file should be the same w its name.
~~.kt

package names should be small letters.
not underlines, dots.


4.
need an expression about lazy property.





thanks.


*/