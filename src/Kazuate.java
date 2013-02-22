import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.Random;
import java.util.StringTokenizer;

public class Kazuate{
	static int n, lim, sol;
	
	public static void main(String[] args) throws IOException{
		while(true){
			System.out.print("(1)数あてゲーム (2)過去に生成された答え\n");
			
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String buf = br.readLine();
			n = Integer.parseInt(buf);
			
			if(n == 1 || n == 2){
				break;
			}else{
				java.awt.Toolkit.getDefaultToolkit().beep();
				System.out.println("1か2の整数を入力してください！");
			}
		}
		
		switch(n){
			case 1:
				Create_num();
				Input_sol();
				break;
			case 2:
				Output_sol();
				break;
		}
	}

	public static void Create_num() throws IOException{
		
		while(n <= 0 || lim <= 0 || n > 6){
			System.out.print("\n何桁の数字を何回以内に答えますか？（桁数は6桁以内） \n>>");
			
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String buf = br.readLine();
			StringTokenizer st = new StringTokenizer(buf, " ");
			String tmp = st.nextToken();
			n = Integer.parseInt(tmp);
			tmp = st.nextToken();
			lim = Integer.parseInt(tmp);
			
			if(n <= 0 || lim <= 0){
				java.awt.Toolkit.getDefaultToolkit().beep();
				System.out.println("1以上の整数を入力してください！\n");
			}
			if(n > 6){
				java.awt.Toolkit.getDefaultToolkit().beep();
				System.out.println("桁数は6桁以上指定できません！\n");
			}
		}
		Random rnd = new Random();
		
		switch(n){
			case 1:
				sol = rnd.nextInt(10) + 1;
				break;
			case 2:
				sol = rnd.nextInt(100) + 10;
				break;
			case 3:
				sol = rnd.nextInt(1000) + 100;
				break;
			case 4:
				sol = rnd.nextInt(10000) + 1000;
				break;
			case 5:
				sol = rnd.nextInt(100000) + 10000;
				break;
			case 6:
				sol = rnd.nextInt(1000000) + 100000;
				break;
		}
		System.out.println(n + "桁の数字が生成されました！");
		System.out.println("Debug: " + sol + "が生成されました。");
		
		try{
			FileWriter fp = new FileWriter("data.txt", true);
			fp.write(sol + " ");
			fp.close();
		}catch(IOException e){
			java.awt.Toolkit.getDefaultToolkit().beep();
			System.out.println("Error: ファイルをオープンできません！\n正常に書き込むことができませんでした。>> " + e);
		}
	}

	public static void Input_sol() throws IOException{
		int ans =-1, t = lim, c;
		double start = System.currentTimeMillis();
		while(true){
			System.out.print(n + "桁の数字を入力してください。（あと" + lim + "回）\n>>");
			
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String buf = br.readLine();
			ans = Integer.parseInt(buf);
			
			if(ans > sol){
				if(ans - sol <= 30){
					System.out.println("おしい！");
					System.out.println("ヒント: その数字より少し小さいです。\n");
				}else{
					System.out.println("不正解です！");
					System.out.println("ヒント: その数字より小さいです。\n");
				}
				lim--;
			}else if(ans < sol){
				if(sol - ans <= 30){
					System.out.println("おしい！");
					System.out.println("ヒント: その数字より少し大きいです。\n");
				}else{
					System.out.println("不正解です！");
					System.out.println("ヒント: その数字より大きいです。\n");
				}
				lim--;
			}else{
				double stop = System.currentTimeMillis();
				System.out.println("正解です！");
				System.out.println("正解するまでに" + ((t - lim) + 1) +"回回答し、" + ((stop - start) / 1000) + "秒かかりました。");
				break;
			}
			if(lim == 0){
				System.out.println("ゲームオーバー！");
				System.out.println("正解は" + sol + "でした！残念でした。\n");
				break;
			}
		}
		do{
			System.out.println("もう一度しますか？");
			System.out.print("(1)はい (2)いいえ\n>>");
			
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String buf = br.readLine();
			c = Integer.parseInt(buf);
			
			if(c != 1 && c != 2){
				java.awt.Toolkit.getDefaultToolkit().beep();
				System.out.println("1か2の整数を入力してください！\n");
			}
		}while(c != 1 && c != 2);
		
		if(c == 1){
			System.out.println("コンティニューします。\n");
			n = 0;
			Create_num();
			Input_sol();
		}else{
			System.out.println("終了します。お疲れ様でした。");
		}
	}

	public static void Output_sol(){
		try{
			File fp = new File("data.txt");
			FileReader fr = new FileReader(fp);
			int ch = fr.read();
			
			System.out.println("過去に生成された数字です。古い順に並んでいます。");
			
			while(ch != -1){
				System.out.print((char)ch);
				ch = fr.read();
			}
			fr.close();
		}catch(FileNotFoundException e){
			java.awt.Toolkit.getDefaultToolkit().beep();
			System.out.println("Error: ファイルをオープンできません！>> " + e);
		}catch(IOException e){
			java.awt.Toolkit.getDefaultToolkit().beep();
			System.out.println("Error: ファイルをオープンできません！>> " + e);
		}
	}
}