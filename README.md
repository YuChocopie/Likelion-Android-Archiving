# 코틀린

- 코틀린은 JetBrains가 2011에 발표한 프로그래밍 언어이다.
- 2017년 구글이 개발 언어로 채택했다.
- 코틀린은 일반 목적으로도 사용할 수 있고 오픈 소스로도 사용할 수 있다.
- jvm과 안드로이드를 위한 언어이며, 객체 지향과 함수 기능을 합쳤다.

## 코틀린을 사용하는 이유

- 함수가 줄어든다
- 배우기 쉽다
- 자바기반이다
- null exception을 피하기쉽다. (Int에 ?를 붙이면 nullable 설정 가능)
- 코드가 쉽고 짧다. 강력한 에러 감지 기능을 가졌다.
- 코틀린은 컴파일 전 잘못된 것을 알아내어 에러를 사전에 방지할 수 있다.

## 코틀린 문법

- 세미콜론이 없다



### a. 연산

코틀린은 숫자에 대한 표준 수치 연산을 지원한다.
이 연산자는 각 타입의 멤버로 정의되어 있다(컴파일러가 해당 명령어로 최적화한다).

비트 연산의 경우 이를 위한 특수 문자를 사용하지 않고 중위 형식으로 호출할 수 있는 (이름을 가진) 함수가 존재한다. 

~~~
val x = (1 shl 2) and 0x000FF000
~~~

전체 비트 연산 목록( Int 와 Long 만 가능).

~~~
shl(bits)		//부호가진 좌측 쉬프트 (자바의 << )
shr(bits)	 	//부호가진 우측 쉬프트 (자바의 >> )
ushr(bits)	 	//부호없는 오른쪽 쉬프트 (자바의 >>> )
and(bits) 		//and 비트연산
or(bits) 		// or 비트연산
xor(bits) 		// xor 비트연산
inv() 			// 역 비트연산
~~~





### b . 변수설정

#### **val**

- 변경 불가능(immutable) 

-  초기화 후에는 재대입이 불가능. 자바로 말하면 final 변수에 해당

- 코틀린에서는 가능한 val 키워드를 사용해 불변 변수 사용을 권장. 나중에 꼭 필요한 경우에만 var로 변경.

- val 변수의 주의할 사항은 참조 자체가 불변이라는 점이다. 참조가 가르키는 객체 내부의 값은 변경 가능하다. 아래 예 참고.

  - ~~~
    val lang = arrayListOf("Java") 
    /* 불변 참조를 선언 */
    lang.add("Kotlin") 
    /* 참조가 아닌 객체 내부를 변경하므로 error 아님 */
    lang = arrayListOf("C")
    /* 에러!! 참조를 변경 */
    ~~~

- 

#### var

- var(=variable) - 변경 가능(mutable) 
- 값은 바뀔 수 있다. 자바의 일반 변수에 해당

- val 변수는 정확히 한 번만 초기화 되어야 한다. 조건에 따라 다른 여러 값으로 초기화 가능하다. 

  ~~~
  val message: String
  if (isSuccess()) {
  	message = "Success"
  }
  else {
  	message = "Failed"
  }
  ~~~

- var 변수는 값 변경은 가능하나, 초기화 시점의 변수 타입은 고정이다. 따라서 아래 예제는 오류를 발생한다.

  ~~~
  var answer = 42
  answer = "no answer" 
  /* 컴파일 에러!! Type mismatch */
  ~~~



### c. 배열

- 코틀린은 Array 클래스를 이용해서 배열을 표현함.
  *이 클래스는 get 과 set 함수와 size 프로퍼티를 가지며(get, set 함수는 연산자 오버로딩 규칙에 따라 [] 로 바뀐다), 몇 개의 다른 유용한 멤버 함수가 있다.

  ~~~
  class Array private constructor() {
  	val size: Int
  	fun get(index: Int): T
  	fun set(index: Int, value: T): Unit
  	fun iterator(): Iterator
  	// ...
  }
  ~~~
  - arrayOf() 라이브러리 함수를 이용해서 배열을 생성. 예를 들어, arrayOf(1, 2, 3) 은 [1, 2, 3] 배열을 생성함.
  - arrayOfNulls() 라이브러리 함수를 사용하면 null을 값으로 갖는 지정한 크기의 배열을 생성함.

- 팩토리 함수를 사용할 수도 있음. 팩토리 함수는 배열 크기와 배열의 각 인덱스에 대해 초기 값을 생성하는 함수를 인자로 받음.

  ~~~
  val asc = Array(5, i -> (i * i).toString() )
  //["0", "1", "4", "9", "16"]을 값으로 갖는 Array 생성
  ~~~
  - 코틀린은 기본 타입 배열을 위해 박싱 오버헤드가 없는 ByteArray , ShortArray , IntArray 등의 클래스를 제공함. 이 클래스는 Array 클래스와 상속 관계를 갖지 않지만, 동일한 메서드와 프로퍼티를 갖음. 또한 각 클래스를 위한 팩토리 함수가 존재함.

  - ~~~
    val x: IntArray = intArrayOf(1, 2, 3)
    x[0] = x[1] + x[2]
    ~~~



### d. 문자열 형식 지정: 문자열 템플릿

코틀린은 문자열 템플릿(String Template)을 지원한다.

 println 안의 **name이 문자열 템플릿이다. 가능한 문자열 템플릿은 중괄호로 묶어 **{name}처럼 사용하는 것이 좋음.

~~~
fun main(args: Array) {
	val name = if(args.size > 0) args[0] else "Kotlin"
	println("Hello, $name")
	println("Hello, ${name}") /* 중괄호 사용 */
}
~~~

- 템플릿은 raw 문자열과 이스케이프드 문자열에서 모두 지원. 

  (역슬래시를 이용한 특수문자 표기를 지원하지 않는)raw문자열에서 문자를 표현하고 싶다면 다음 구문을 사용.

  ~~~
  val = price = “”“
  ${‘$’}9.99
  ~~~



### e . 함수선언

<자바 함수의 기본 형태>

~~~
void 함수명(변수){}
or
리턴타입 함수명(변수){ return 값; }
~~~

<코틀린 함수의 기본형태>

~~~
fun 함수명(변수): Unit{ }
or
fun 함수명(변수): 리턴타입{ return 값 }
~~~

함수가 값을 반환하지 않을 경우 RETURN_TYPE 생략해도 된다.

```
fun THENAME (a : TYPE, b : TYPE) : RETURN_TYPE {
whatever
}
fun sum(a : Int, b : Int): **Int** {return a+b}
```

함수가 간단한 계산기의 기능만을 할 경우 이런 식으로도 선언 가능하다.

```
fun THENAME (a : TYPE, b : TYPE) = a + b
```

-코틀린에서는 return하지 않는 함수(void 함수)에 선언한 리턴타입 Unit은 생략이 가능.

그렇다면 두 수의 합을 리턴하는 간단한 예제를 하나 만들어 보자.

JAVA

~~~
int sum(int a, int b){return a+b;}
~~~

Kotlin

~~~
fun sum(a: Int, b: Int) : Int { return a+b }
=>  fun sum(a: Int, b: Int): Int = a+b;		//a+b의 타입이 Int인 것을 유추가능
~~~





### f. 반복문

- i를 사용하여 cnt를 올려주지 않아도 in을 사용하여 for문사용가능
- items.size 를 사용하여 배열 내에 몇개의 원소가 있는지 알 수있음.

~~~
for(item in items) {println(items)}

// 함수가 간단한 계산일 경우
fun bigger(a : Int, b : Int) = if (a>b) a else b
~~~

~~~
for ( i in 1..10) {
print(x)
}
//12345678910
~~~

~~~
//i가 인덱스로 사용될 수 있다.

for ( x in 1..10 step 2) = for (int x = 1; x <= 10; x+=2)

or

for (x in 10 downTo 0 step 2) = for (int x = 10; x >= 0; x -= 2)
~~~

~~~
val list = list1{"hi ", "i'm ", "a developer" }
for ( i in list)
	print(i);

//hi i'm a developer
~~~



### g. 클래스

class Person constructor(name : Strung){}
코틀린은 보조생성자를 1개이상 가질 수 있다.

### h. printf

- c에서는 변수를 %형, 이름
- 코틀린에서는 $이름

### i. When

switch와 비슷하다

~~~
when(res) {
1 -> print("one")
2 -> print("two")
...
}
~~~

