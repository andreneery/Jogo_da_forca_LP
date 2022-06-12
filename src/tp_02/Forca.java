package tp_02;

import static java.lang.Boolean.TRUE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author kauad
 */
public class Forca {

    public static void main(String[] args) {
    	      
       //Seleciona aleatoriamente uma das palavras        
        String selecionada = selecionaPalavra("");
        
        //Chama a subrotina com recurs?o que vai pedir a letra, fazer a testagem e dar o resultado
        //int erro = 
        verificador("", selecionada, selecionada, 6, 0);
        
        System.out.println("FIM DE JOGO");
        
       
    }
    
    private static String selecionaPalavra(String selecionada) {
    	//Vetor com as palavras a serem sorteadas
    	String palavra[] = new String[]{"laranja", "abacate", "banana", "morango", 
        		"manga", "ameixa", "goiaba", "carambola", "uva", "abacaxi"};
    	
    	selecionada = palavra[new Random().nextInt(palavra.length)];
    	
    	return selecionada;
    }

    private static void verificador(String palpite, String selecionada, String palavra, int erro, int encerrar) {
        Scanner sc = new Scanner(System.in);
        
        //Verifica se o usuário ainda não ganhou nem perdeu
        //se o jogo continua, pede uma nova letra
        if (selecionada != "" && erro > 0 && encerrar == 0) {
            System.out.print("Digite uma letra: ");
            palpite = sc.next();
           
            //Verifica se a letra informada está na palavra
            if (selecionada.contains(palpite) == TRUE) {
                System.out.println("Letra correta!");
                System.out.println("");
                
                //envia a letra digitada e o tipo TRUE
                boolean acerto = selecionada.contains(palpite);
                gravaArquivo(palpite, acerto);
                
                // elimina a letra selecionada da palavra
                selecionada = selecionada.replace(palpite, "");
               
                // acerto++;

               // se n?o tiver a letra, verifica qual mensagem informar
            } else if (erro > 0){
                System.out.println("Ops! Letra errada ou repetida!");
                System.out.println("");
                
              //envia a letra digitada e o tipo FALSE
                boolean acerto = selecionada.contains(palpite);
                gravaArquivo(palpite, acerto);
                
                erro--;

                switch (erro) {
                    case 5:
                        System.out.println("Você perdeu o braço esquerdo!");
                        System.out.println("Tentativas disponiveis: " + erro);
                        System.out.println("");
                        break;
                    case 4:
                        System.out.println("Você perdeu o braço direito!");
                        System.out.println("Tentativas disponiveis: " + erro);
                        System.out.println("");
                        break;
                    case 3:
                        System.out.println("Você perdeu a perna esquerda!");
                        System.out.println("Tentativas disponiveis: " + erro);
                        System.out.println("");
                        break;
                    case 2:
                        System.out.println("Você perdeu a perna direita!");
                        System.out.println("Tentativas disponiveis: " + erro);
                        System.out.println("");
                        break;
                    case 1:
                        System.out.println("Você perdeu o tronco!");
                        System.out.println("Tentativas disponiveis: " + erro);
                        System.out.println("");
                        break;
                }
            }
            
//            System.out.println(selecionada);
            //System.out.println(erro);
            
            //chama a própria função para iniciar a repetição
            verificador(palpite, selecionada, palavra, erro, encerrar);
        } 
        else if (encerrar == 0){
        finalizar(erro, selecionada, palavra, encerrar);   
        }
    }
    
    private static int finalizar(int erro, String selecionada, String palavra, int encerrar){
         if (erro == 0){
            System.out.println("Você perdeu a cabeça!");
            System.out.println("GAME OVER!!");
            System.out.println("A PALAVRA ERA: " + palavra);          
        }
        else {
            System.out.println("PARABÉNS! VOCÊ VENCEU!");
            System.out.println("A PALAVRA É: " + palavra);
        }
        
        System.out.println("DESEJA JOGAR NOVAMENTE?");
        System.out.println("Digite sim ou nao: ");
        Scanner sc = new Scanner(System.in);
        String rejogar = sc.next();
        
        if (rejogar.equals("sim")){
            selecionada = selecionaPalavra("");
            verificador("", selecionada, selecionada, 6, 0);
        }
        else {
            System.out.println("OBRIGADO POR JOGAR!");
            encerrar++;
        }
        return encerrar;
    }
            
    
    private static void gravaArquivo(String palpite, Boolean acerto) {
    	
    	//Arquivo
    	File objArquivo = new File("forca.txt");
    	
    	 if(objArquivo.exists()){
    	        //System.out.println(objArquivo + " Exists");
    	        
    	        try{
    	            // instancia objeto (objLeitorArquivo) capaz de ler conteudos do arquivo (representado pelo objeto objArquivo)
    	            FileReader objLeitorArquivo = new FileReader(objArquivo);
    	            
    	            // instancia objeto (objBufferDeLeitura) para armazenar (em buffer) conteudo lido do arquivo
    	            BufferedReader objBufferDeLeitura = new BufferedReader(objLeitorArquivo);
    	            
    	            // comando para ler conte?do do arquivo
    	            String conteudoLido = "";
    	            // l?gica para leitura do conte?do: enquanto o buffer de leitura estiver "ready", 
    	            // significa que ha conteudo a ser lido; com isso, continua no laço
    	            while(objBufferDeLeitura.ready()){
    	                // le conteudo e o armazena em uma variavel (para ser exibido de uma unica vez)
    	                conteudoLido += objBufferDeLeitura.readLine() + "\n";
    	            }
    	            
    	            //System.out.println("Conte?do lido: ");
    	            // exibe o conte?do recuperado do arquivo
    	            //System.out.println(conteudoLido);
    	            
    	            
    	         // instancia objeto (objEscritorArquivo) capaz de escrever em arquivos (representado pelo objeto objArquivo)
    	            FileWriter objEscritorArquivo = new FileWriter(objArquivo);
    	            
    	         // instancia objeto (objBufferDeEscrita) para armazenar (em buffer) conteudo a ser escrito no arquivo
    	            BufferedWriter objBufferDeEscrita = new BufferedWriter(objEscritorArquivo);
    	            
    	            // instancia objeto (objEscritor) que envia caracteres ao buffer, para depois envia-los ao arquivo
    	           PrintWriter objEscritor = new PrintWriter(objBufferDeEscrita, true);
    	            
    	            // imprime conteudos no buffer
    	            objEscritor.print(conteudoLido + palpite + " , ");
                    if(acerto == true){
                        objEscritor.println("parabéns! Você acertou a letra.");
                    } else{
                        objEscritor.println("que pena! Você errou a letra");
                    }
    	            
    	        } catch(IOException ex){
    	            System.out.println("Erro ao manipular arquivo para ler valores.\n" + ex.toString());
    	        }
    	 }
    	 
    	 else{
    	        //System.out.println(objArquivo + " Does not exists");
    	        try{
    		           
    	            // tenta criar o arquivo
    	            objArquivo.createNewFile();
    	            //System.out.println("CRIOU 1? VEZ!!");
    	            
    	            // instancia objeto (objEscritorArquivo) capaz de escrever em arquivos (representado pelo objeto objArquivo)
    	            FileWriter objEscritorArquivo = new FileWriter(objArquivo);
    	            
    	            // instancia objeto (objBufferDeEscrita) para armazenar (em buffer) conteudo a ser escrito no arquivo
    	            BufferedWriter objBufferDeEscrita = new BufferedWriter(objEscritorArquivo);
    	            
    	            // instancia objeto (objEscritor) que envia caracteres ao buffer, para depois envia-los ao arquivo
    	           PrintWriter objEscritor = new PrintWriter(objBufferDeEscrita, true);
    	            
    	            // imprime conteudos no buffer
                   if(acerto == true){
                       objEscritor.println(palpite + " , parabéns! Você acertou");
                   } else{
                       objEscritor.println(palpite + " , que pena! você errou");
                   } 
                       
    	           
    	            
    	            //System.out.println("Conte?do escrito com sucesso.");
    	        }
    	        catch(IOException ex){
    	            System.out.println("Erro ao manipular arquivo para gravar valores.\n" + ex.toString());
    	        }
    	    }
    	
    }

}