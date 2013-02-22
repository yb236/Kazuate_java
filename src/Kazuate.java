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
			System.out.print("(1)�����ăQ�[�� (2)�ߋ��ɐ������ꂽ����\n");
			
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String buf = br.readLine();
			n = Integer.parseInt(buf);
			
			if(n == 1 || n == 2){
				break;
			}else{
				java.awt.Toolkit.getDefaultToolkit().beep();
				System.out.println("1��2�̐�������͂��Ă��������I");
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
			System.out.print("\n�����̐���������ȓ��ɓ����܂����H�i������6���ȓ��j \n>>");
			
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
				System.out.println("1�ȏ�̐�������͂��Ă��������I\n");
			}
			if(n > 6){
				java.awt.Toolkit.getDefaultToolkit().beep();
				System.out.println("������6���ȏ�w��ł��܂���I\n");
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
		System.out.println(n + "���̐�������������܂����I");
		System.out.println("Debug: " + sol + "����������܂����B");
		
		try{
			FileWriter fp = new FileWriter("data.txt", true);
			fp.write(sol + " ");
			fp.close();
		}catch(IOException e){
			java.awt.Toolkit.getDefaultToolkit().beep();
			System.out.println("Error: �t�@�C�����I�[�v���ł��܂���I\n����ɏ������ނ��Ƃ��ł��܂���ł����B>> " + e);
		}
	}

	public static void Input_sol() throws IOException{
		int ans =-1, t = lim, c;
		double start = System.currentTimeMillis();
		while(true){
			System.out.print(n + "���̐�������͂��Ă��������B�i����" + lim + "��j\n>>");
			
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String buf = br.readLine();
			ans = Integer.parseInt(buf);
			
			if(ans > sol){
				if(ans - sol <= 30){
					System.out.println("�������I");
					System.out.println("�q���g: ���̐�����菭���������ł��B\n");
				}else{
					System.out.println("�s�����ł��I");
					System.out.println("�q���g: ���̐�����菬�����ł��B\n");
				}
				lim--;
			}else if(ans < sol){
				if(sol - ans <= 30){
					System.out.println("�������I");
					System.out.println("�q���g: ���̐�����菭���傫���ł��B\n");
				}else{
					System.out.println("�s�����ł��I");
					System.out.println("�q���g: ���̐������傫���ł��B\n");
				}
				lim--;
			}else{
				double stop = System.currentTimeMillis();
				System.out.println("�����ł��I");
				System.out.println("��������܂ł�" + ((t - lim) + 1) +"��񓚂��A" + ((stop - start) / 1000) + "�b������܂����B");
				break;
			}
			if(lim == 0){
				System.out.println("�Q�[���I�[�o�[�I");
				System.out.println("������" + sol + "�ł����I�c�O�ł����B\n");
				break;
			}
		}
		do{
			System.out.println("������x���܂����H");
			System.out.print("(1)�͂� (2)������\n>>");
			
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String buf = br.readLine();
			c = Integer.parseInt(buf);
			
			if(c != 1 && c != 2){
				java.awt.Toolkit.getDefaultToolkit().beep();
				System.out.println("1��2�̐�������͂��Ă��������I\n");
			}
		}while(c != 1 && c != 2);
		
		if(c == 1){
			System.out.println("�R���e�B�j���[���܂��B\n");
			n = 0;
			Create_num();
			Input_sol();
		}else{
			System.out.println("�I�����܂��B�����l�ł����B");
		}
	}

	public static void Output_sol(){
		try{
			File fp = new File("data.txt");
			FileReader fr = new FileReader(fp);
			int ch = fr.read();
			
			System.out.println("�ߋ��ɐ������ꂽ�����ł��B�Â����ɕ���ł��܂��B");
			
			while(ch != -1){
				System.out.print((char)ch);
				ch = fr.read();
			}
			fr.close();
		}catch(FileNotFoundException e){
			java.awt.Toolkit.getDefaultToolkit().beep();
			System.out.println("Error: �t�@�C�����I�[�v���ł��܂���I>> " + e);
		}catch(IOException e){
			java.awt.Toolkit.getDefaultToolkit().beep();
			System.out.println("Error: �t�@�C�����I�[�v���ł��܂���I>> " + e);
		}
	}
}