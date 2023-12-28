package com.ll;

public class Calc { //Calc 클래스
  public static int run(String exp) { // 10 + (10 + 5) // exp라는 스트링 값을 받는 스테틱 매소드
    exp = exp.trim(); //띄어쓰기 방지
    exp = stripOuterBracket(exp); //바깥의 괄호를 제거하는 메소드

    // 연산기호가 없으면 바로 리턴
    if (!exp.contains(" ")) return Integer.parseInt(exp);

    boolean needToMultiply = exp.contains(" * "); //논리 확인 만약 곱하기가 exp에 있으면 True가됨
    boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");//논리 확인 만약 마이너스가가 exp에 있으면 True가됨

    boolean needToCompound = needToMultiply && needToPlus; //논리 확인 만약 곱하기와 플러스가 exp에 있으면 True가됨
    boolean needToSplit = exp.contains("(") || exp.contains(")"); //논리 확인 아직도 괄호에 exp에 있으면 True가됨

    if (needToSplit) {  // 800 + (10 + 5) // 괄호가 더 존재하는 경우

      int splitPointIndex = findSplitPointIndex(exp); //매서드 포인트 인덱스 값을 답는 정수를 선언

      String firstExp = exp.substring(0, splitPointIndex); //첫번째 괄호를 제거 메소드
      String secondExp = exp.substring(splitPointIndex + 1);//두번쨰 괄호를 제거 메소드

      char operator = exp.charAt(splitPointIndex); // 문자열 오퍼레이터는 exp 문자에서 찾은 문자열

      exp = Calc.run(firstExp) + " " + operator + " " + Calc.run(secondExp); //매서드로 괄호의 값을제거

      return Calc.run(exp); //매서드 실행

    } else if (needToCompound) {
      String[] bits = exp.split(" \\+ "); //+ 값으로 나눈다

      return Integer.parseInt(bits[0]) + Calc.run(bits[1]); // TODO
    }
    if (needToPlus) {
      exp = exp.replaceAll("\\- ", "\\+ \\-");

      String[] bits = exp.split(" \\+ ");

      int sum = 0;

      for (int i = 0; i < bits.length; i++) {
        sum += Integer.parseInt(bits[i]);
      }

      return sum;
    } else if (needToMultiply) {
      String[] bits = exp.split(" \\* ");

      int rs = 1;

      for (int i = 0; i < bits.length; i++) {
        rs *= Integer.parseInt(bits[i]);
      }
      return rs;
    }

    throw new RuntimeException("처리할 수 있는 계산식이 아닙니다"); // 아무것도 해당 안될시 출력
  }

  private static int findSplitPointIndexBy(String exp, char findChar) { //스트링 exp와 문자열 find char값을 받는 정수형 매서드
    int bracketCount = 0; //초기의 괄호의 값은 0

    for (int i = 0; i < exp.length(); i++) { //반복분으로 스트링 exp의 길이만큼 정수를 뽑아냄
      char c = exp.charAt(i); //문자 exp의 문자열만큼 문자 생성

      if (c == '(') {  //문자열의 값이 안쪽괄호와 같으면 값 증가
        bracketCount++;
      } else if (c == ')') { //문자열의 값이 바깥괄호와 같으면 값 감소
        bracketCount--;
      } else if (c == findChar) { //입력한 인자값과 같으면
        if (bracketCount == 0) return i; //괄호를 모두 찾으면 찾은 값으로 리턴
      }
    }
    return -1;
  }

  private static int findSplitPointIndex(String exp) { //exp를 매개변수로 하는 정수형 매서드문
    int index = findSplitPointIndexBy(exp, '+'); //인덱스 바이 매서드를 실행 +를 찾는다

    if (index >= 0) return index; //찾는 값이 존재하면 인덱스로 값을 넣는다

    return findSplitPointIndexBy(exp, '*'); //찾는 값이 존재하지 않으면 *를 찾는다
  }

  private static String stripOuterBracket(String exp) { //겉의 괄호를 제거하는 매서드
    int outerBracketCount = 0; //초기의 바깥의 괄호의 값은 0이다

    //반복문
    while (exp.charAt(outerBracketCount) == '(' && exp.charAt(exp.length() - 1 - outerBracketCount) == ')') {
      outerBracketCount++; //아웃브레이커는 초기의 (과 끝의 값에서의 )와 같다
    }

    if (outerBracketCount == 0) return exp; //초기의 값이 0이면 exp로 리턴함


    return exp.substring(outerBracketCount, exp.length() - outerBracketCount); //exp는 바깥괄호와 끝의 바깥괄호를 제거
  }
}