public class SYS20211231_mid2 extends SYS20211231_mid {
    //게임 타이틀, 선택지 출력
    @Override
    void printHead() {
        System.out.print("""
                ------------------------------
                           묵찌빠 게임
                ------------------------------
                """);
        System.out.println("0.가위 1.바위 2.보 중 하나 선택해주세요");
    }

    //첫 선공 가위바위보 게임
    int rspGame() {
        int result = winnerDetermine(getInput(0, 2));// 1 : 승리, 0 : 무승부 -1 : 패배, 확인 변수
        //무승부 일 경우 반복
        while (result == 0) {
            System.out.println("무승부");
            System.out.println("다시 입력해주세요 - 0.가위 1.바위 2.보");
            result = winnerDetermine(getInput(0, 2));
        }
        return result;
    }

    @Override
    void gamePlay() {
        printHead();
        int detatt = rspGame(); //승리변수 대입
        while (true) {
            //승리변수를 통해 공격 및 수비 여부 출력
            if (detatt == 1) {
                System.out.println("공격입니다.");
            } else {
                System.out.println("수비입니다.");
            }
            System.out.println("무엇을 내시겠습니까?");
            int result = winnerDetermine(getInput(0, 2)); //공격수비 묵찌빠 변수 입력
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

    public static void main(String[] args) {
        SYS20211231_mid2 mjp = new SYS20211231_mid2();
        boolean cont = true; //cont값 초기화
        while (cont) {
            mjp.gamePlay();
            cont = mjp.oneMore(); //한번 더?
        }
    }
}
