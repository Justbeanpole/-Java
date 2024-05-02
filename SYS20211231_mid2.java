import java.util.Scanner;

public class SYS20211231_mid2 {
    //게임 타이틀, 선택지 출력
    public static void printHead() {
        System.out.print("""
                ------------------------------
                           묵찌빠 게임
                ------------------------------
                """);
        System.out.println("0.가위 1.바위 2.보 중 하나 선택해주세요");
    }
    //첫 선공 가위바위보 게임
    public static int rspGame() {
        SYS20211231_mid sys = new SYS20211231_mid(); //가위바위보 객체생성(메서드 활용을 위해)
        int result; // 1 : 승리, 0 : 무승부 -1 : 패배, 확인 변수
        while (true) {
            result = sys.winnerDetermine(sys.getInput(0, 2)); //함수를 통해 승리 변수 대입
            //승리 및 패배 시 반복문 탈출 그러나 무승부 시 가위바위보 재개
            if (result != 0) {
                break;
            }
            else {
                System.out.println("무승부");
                System.out.println("다시 입력해주세요 - 0.가위 1.바위 2.보");
            }
        }
        return result;
    }

    static void mjpGame() {
        SYS20211231_mid sys = new SYS20211231_mid();
        int detatt = rspGame(); //승리변수 대입
        while (true) {
            //승리변수를 통해 공격 및 수비 여부 출력
            if (detatt == 1) {
                System.out.println("공격입니다.");
            }
            else {
                System.out.println("수비입니다.");
            }
            System.out.println("무엇을 내시겠습니까?");
            int result = sys.winnerDetermine(sys.getInput(0, 2)); //공격수비 묵찌빠 변수 입력
            //공격 시 사용자와 컴퓨터가 낸 값이 같다면 승리
            if (detatt == 1) {
                if (result == 0) {
                    System.out.println("승리");
                    break;
                }
                detatt = result; //만약 승리하지 못했다면 공격수비 변수 업데이트
            }
            //수비 시 사용자와 컴퓨터가 낸 값이 같다면 패배
            else {
                if (result == 0) {
                    System.out.println("패배");
                    break;
                }
                detatt = result; //만약 수비를 잘 했다면 공격수비 변수 업데이트
            }
        }
    }

    public static void gameplay() {
        printHead();
        mjpGame();
    }

    public static void main(String[] args) {
        boolean cont = true; //cont값 초기화
        while(cont) {
            gameplay();
            cont = SYS20211231_mid.oneMore(); //한번 더?
        }
    }
}
