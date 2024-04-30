import java.util.Scanner;
import java.util.Random;


public class SYS20211231_mid {
    //타이틀 출력
    public static void printHead() {
        System.out.print("""
            ------------------------------
                     가위바위보 게임
            ------------------------------
            """);
        System.out.println("0.가위 1.바위 2.보 중 하나 선택해주세요");
    }
    //입력값 예외처리 - min, max 범위X and 숫자가 아닐 경우 -1 반환
    public static int checkUser(String lineinput, int min, int max) {
        //int 변환 불가 시 예외처리를 통해 에러 출력
        try{
            int userChoice = Integer.parseInt(lineinput); //int로 변환
            //범위 지정
            //범위 이탈 시 에러 출력
            if (userChoice < min || userChoice > max) {
                System.out.printf("[ERROR] %d ~ %d 중 하나를 입력해주세요.\n", min, max);
                return -1;
            }
            return userChoice;
        } //예외처리
        catch(Exception e){
            System.out.printf("[ERROR] %d ~ %d 중 하나를 입력해주세요.\n", min, max);
            return -1;
        }
    }
    //입력받기
    public static int getInput(int min, int max){
        Scanner sc = new Scanner(System.in); //Scanner 객체 생성
        System.out.print("입력 : ");
        int input = checkUser(sc.nextLine(), min, max);
        //예외처리 과정 후 -1 반환 시 다시 입력
        while(input == -1) {
            System.out.print("다시 입력해주세요 : ");
            input = checkUser(sc.nextLine(), min, max);
        }
        return input;
    }
    //승자 결정
    public static int winnerDetermine(int userChoice){
        Random r = new Random(); //Random 객체 생성
        int computer = r.nextInt(3); //computer변수 0,1,2 무작위 선택
        String [] iswhat = {"가위", "바위", "보"};
        //wCondition[0] = 가위, wCondition[1] = 바위, wCondition[2] = 보, 그에 따른 승리 조건 = 1.승리 0.무승부 -1.패배
        int [][] wCondition = {
                {0,-1,1},{1,0,-1},{-1,1,0}
        };
        //누가 무엇을 냈는지
        System.out.printf("사용자 : %s  |  컴퓨터 : %s\n", iswhat[userChoice], iswhat[computer]);
        return wCondition[userChoice][computer];
    }
    //재시작 여부
    public static boolean oneMore()
    {
        System.out.println("계속 하시겠습니까? 1.계속, 2.종료");
        int input = getInput(1,2); //1,2만 입력 가능
        if (input == 1) {
            return true;
        }
        else {
            System.out.println("종료하겠습니다.");
            return false;
        }
    }
    //게임 플레이
    public static void gamePlay()
    {
        printHead(); //시작문구 출력
        int result = winnerDetermine(getInput(0,2)); //승리변수 저장
        if(result == 1) {
            System.out.println("승리");
        }
        else if (result == -1) {
            System.out.println("패배");
        }
        else {
            System.out.println("무승부");
        }
    }
    //main
    public static void main(String[] args) {
        boolean cont = true; //cont값 초기화
        while(cont) {
            gamePlay();
            cont = oneMore();
        }
    }
}
