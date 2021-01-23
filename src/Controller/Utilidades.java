/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Amarildo dos Santos de Lima
 */

public class Utilidades {
 
/**
  Metodo Space Para Inserir Espaços em Branco                                <p>
  1 Argumento                                                                <p>
  1º Tipo inteiro                                                            <p>
  Primeiro,Argumento Quantidade de espaços a inserir                         <p>
  Exemplo de Uso =  Util.Space(1);                                           <p> 
 */
   public String Space(int Qtde){
     String aux="";
     for (byte i = 0 ; i < Qtde ;i++){  
      aux = aux + " ";
     }
      return aux;
   }
   
 /**  
  Metodo Stuff Para Inserir Caracter Em uma Determinda Posiçao no Campo      <p>
  3 Argumentos                                                               <p>
  1º  do tipo String, Argumento Campo                                        <p>
  2º  do Tipo Inteito, quantidade de caracter antes da  Posiçao              <p>
  3º  do Tipo String,Qual o Caracter a Inserir                               <p>
  Exemplo de Uso =  Util.Stuff(Campo,5,"-");                                 <p> 
 */
   public String Stuff(String Campo, int Qtde, String Caracter){
    String aux       = "";
    String ByteCampo = "";
    int    ContCampo =   0;
    for (byte i = 1 ; i <= Campo.length() ;i++){
      ContCampo++;
      ByteCampo    =  Campo.substring(i - 1,ContCampo);
      if (i == Qtde){
        aux = aux + Caracter + ByteCampo;
       }else{
        aux = aux + ByteCampo; 
      }
    }
    return aux;
   }
   
 /**  
  Metodo Padl Para Inserir Caracter a Esquerda do Campo                      <p>
  3 Argumentos                                                               <p>
  1º do tipo String, Argumento Campo                                         <p>
  2º do Tipo Inteito, Quantidade de Caracter a ser Inserido                  <p>
  3º do Tipo String,Qual o Caracter a Inserir                                <p>
  Exemplo de Uso =  Util.Padl(Campo,3,"0");                                  <p> 
 */
   public String Padl(String Campo, int Qtde, String Caracter){
    String aux="";
    for (byte i = 1 ; i <= Qtde-Campo.length() ;i++){
     if (i == Qtde-Campo.length()){
       aux = aux + Caracter + Campo;
      }else{
       aux = aux + Caracter;
     }
    }
    return aux;
   }

 /**  
  Metodo Padr Para Inserir Caracter a Direita do Campo                       <p>
  3 Argumentos                                                               <p>
  1º do tipo String, Argumento Campo                                         <p>
  2º do Tipo Inteito, Quantidade de Caracter a ser Inserido                  <p>
  3º do Tipo String,Qual o Caracter a Inserir                                <p>
  Exemplo de Uso =  Util.Padr(Campo,3,"0");                                  <p> 
 */
   public String Padr(String Campo, int Qtde, String Caracter){
    String aux="";
    for (byte i = 1 ; i <= Qtde-Campo.length() ;i++){
     if (i == Qtde-Campo.length()){
       aux = Campo + aux + Caracter  ;
      }else{
       aux = aux + Caracter;
     }
    }
    return aux;
   }

 /**  
  Metodo RetZeros Para Retirar Zeros a Esquedas Campo                        <p>
  1 Argumentos                                                               <p>
  1º do tipo String, Argumento Campo                                         <p>
  Exemplo de Uso =  Util.RetZeros(Campo);                                        <p> 
 */
   public String RetZeros(String Campo){
    String aux="";
    for (byte i = 1 ; i <= Campo.length() ;i++){
     if (!Campo.substring(i, i + 1).equals("0")){
      aux += Campo.substring(i, Campo.length());
      break; 
     }
    }
    return aux;
   }

/**
  Metodo formataMoeda Para Formatar valor em Moeda                           <p>
  1 Argumento                                                                <p>
  1º Tipo String                                                             <p>
  Exemplo de Uso =  Util.FormatarMoeda(Valor);                               <p> 
 */
   public String FormataMoeda(String Campo){
     String MilharCentena = Campo.substring(0, Campo.length()-2);  
     String Centavos = Campo.substring(MilharCentena.length(),MilharCentena.length() + 2);  
     String aux = MilharCentena+"."+Centavos;
     double Valor = Double.parseDouble(aux);
     NumberFormat Moeda = NumberFormat.getInstance();                           // Sem Locale usa o pais do Sistema Operacional
     return Moeda.format( Valor );
   } 
   
 /**
  Metodo Acrescenta Zeros as Esqueda do campo                                <p>
  2 Argumentos                                                               <p>
  1º do tipo String                                                          <p>
  2º do tipo Inteiro                                                         <p>
  Exemplo de Uso = Util.Strzero(Campo,3);                                    <p>
 */
   public String Strzero(String Campo, int Qtde){
     int aux = Integer.parseInt(Campo);
     String Aux_f = String.format("%0"+Qtde+"d", aux);        
     return Aux_f;
   }
   
 /**
  Metodo para, Retira Caracteres do campo e Substitui Por Outro              <p>
  3 Argumentos                                                               <p>
  1º do tipo String                                                          <p>
  2º do tipo String                                                          <p>
  3º do tipo String                                                          <p>
  Exemplo de Uso = Util.StrTran(Campo,Caracteres,SubCaracter);               <p>
  StrTran("Am#ar%i?ldo#?@","@?%#",Space(00))                                 <p>
 */
  public String StrTran(String Campo,String Caracter,String SubCaracter){
   String   aux           =  "";
   String   ByteCampo     =  "";
   String   ByteCaracter  =  "";
   int      ContCampo     =   0;
   int      ContCaracter  =   0;
   int      Acrescenta    =   0;
   boolean  Verifica      = false;
    for (byte i = 1 ; i <= Campo.length() ;i++){
     ContCampo++; 
     ContCaracter = 0;
     Acrescenta   = 1;         
     ByteCampo    =  Campo.substring(i - 1,ContCampo);
      for (byte j = 1 ; j <= Caracter.length() ;j++){
       ContCaracter++;
       ByteCaracter = Caracter.substring(j - 1,ContCaracter);
       Verifica     = ByteCampo.equals(ByteCaracter);
        if (Verifica){ 
         Acrescenta = 0;
        }
      }//for
       if ((!Verifica && Acrescenta == 1)){
        Acrescenta++;
        aux = aux + ByteCampo;
       }else{
        aux = aux + SubCaracter;
       }
      // System.out.println("Acrescentou"+Space(1)+aux);
    }//for
   return aux;
  }
   
 /**
  Metodo CriarPastas                                                         <p>
  1 Argumentos                                                               <p>
  1º do tipo String                                                          <p>
  Exemplo de Uso = CriarPastas("c:\java\pasta1\pasta2");                     <p> 
 */
   public void CriarPastas(String path){
    String[] pastas = path.split("\\\\"); 
    String raiz = pastas[0].toString() + "\\";
     for (int i = 0; i<pastas.length; i++){
      if (i>0) {
       File dir = new File(raiz + pastas[i].toString());
        if (!dir.exists()){
         dir.mkdir();
        }
         raiz = raiz + pastas[i].toString() + "\\";
      }
     }
   }

 /**
  Metodo para, Criar Arquivo Txt                                             <p>
  1 Argumentos                                                               <p>
  1º do tipo String                                                          <p>
  Exemplo de Uso = Util.CriarArquivo(Arquivo);                               <p>
 */
    public void CriarArquivo(String Arquivo){                                         
     try {
      File file = new File(Arquivo);
        boolean success = file.createNewFile();
         if (success) {
          // File did not exist and was created
          //label2.setText("Criado arquivo "+nomearq);
          }else{
           // File already exists
           // label2.setText("Arquivo "+nomearq+" já existe");
        }
     }catch(IOException ErroCriar){
      System.out.println("Erro ao Criar no arquivo!!"+Space(01)+ErroCriar);
     }
   }    

 /**  
  Metodo para, Gravar em arquivo Texto                                       <p>
  2 Argumentos do Tipo String                                                <p>
  Primeiro Argumento, Nome do Arquivo                                        <p>
  Segundo  Argumento, Conteudo a Ser Gravado                                 <p>
  Exemplo de Uso =  Util.Write(NomeArquivo,Conteudo);                        <p> 
 */
   public void Write(String arquivo, String conteudo){

     try {

      PrintWriter Fwrite = new PrintWriter(new BufferedWriter(new FileWriter(
      arquivo,true)),true);

      Fwrite.println(conteudo);                                                 
      Fwrite.flush();
      Fwrite.close();
      Fwrite.checkError();
         
     //fecha os arquivos
     }catch(Exception erroTxt){
      JOptionPane.showMessageDialog(null, " Erro ao Escrever no arquivo!! "+erroTxt.getMessage()+Space(01), " Mensagem de Erro Verificar ", JOptionPane.ERROR_MESSAGE) ;
     }
   } 

   
 /**
  Metodo para, Deletar Arquivos                                              <p>
  1 Argumentos                                                               <p>
  1º do tipo String                                                          <p>
  Exemplo de Uso = Util.DeleteFile(Arquivo);                                 <p>
 */
   public void DeleteFile(String file){
    File Arqs = new File(file); 
     boolean success = Arqs.delete();
     if (!success){ 
       System.out.println(" Deletion failed. "+Arqs.toString()+Space(1)+"Arquivo(s) Não Encontrado"); 
     } else { 
       System.out.println( "File deleted."+Arqs.toString()); 
     } 
   }

 /**
  Metodo para, Parar a Aplicação até que a Tecla Enter seja Acionada         <p>
  2 Argumentos                                                               <p>
  1º do tipo String                                                          <p>
  2º do tipo Boolean                                                         <p>
  Exemplo de Uso = Util.Inkey("mensagem",true );                             <p>
 */
   public void Inkey(String mensagem, boolean parar){
    int tecla=0;
     boolean continuar=false;
      System.out.print(mensagem + " ");
       if (parar)  
        System.out.println("parar = "+parar);
       else //Espera o pressionamento da tecla ENTER
        while (!continuar) {
            // System.out.println("continuar = "+continuar);
          try {
           tecla = System.in.read();
            if (tecla < 0 || (char)tecla == '\n')
              continuar = true;
            } catch(java.io.IOException e) {
              continuar = true;
            }
        }

   }

 /**
  Metodo para, Retornar Data Do Systema,Semana,Cabeçalho e Hora              <p>
  Exemplo de Uso = Util.DataSystema();                                       <p>
 */
   public String DataSystema(){

   Calendar hoje = Calendar.getInstance();

    SimpleDateFormat formata_Cabecalho  =  new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
    SimpleDateFormat formata_Data       =  new SimpleDateFormat("dd/MM/yyyy");             
    SimpleDateFormat formata_Hora       =  new SimpleDateFormat("hh:mm:ss");
    SimpleDateFormat formata_Semana     =  new SimpleDateFormat("EEEE");

    String Cabecalho  =  formata_Cabecalho.format(hoje.getTime());
    String Data       =  formata_Data.format( hoje.getTime() );
    String Hora       =  formata_Hora.format( hoje.getTime() );
    String Semana     =  formata_Semana.format( hoje.getTime() );                 
    
    /*
    System.out.println("Data Systema");
    System.out.println(Cabecalho);
    System.out.println(Data);
    System.out.println(Hora); 
    System.out.println(StrTran(Semana,"-"," "));
   */

return Data+Space(1)+"#"+Space(1)+Semana+Space(1)+"#"+Space(1)+Cabecalho+Space(1)+"#"+Space(1)+Hora;
   
}

 /**
  Metodo para, Retornar Data Apartir de Uma data de entrada                  <p> 
  Data,Semana,Cabeçalho e Hora                                               <p>
  1 Argumentos                                                               <p>
  1º do tipo String                                                          <p>
  Exemplo de Uso = Util.Datas(Vencimento);                                   <p>
 */
   public String Datas(String datapost){

   //Calendar hoje = Calendar.getInstance();

    SimpleDateFormat formata_Cabecalho  =  new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
    SimpleDateFormat formata_Data       =  new SimpleDateFormat("dd/MM/yyyy");             
    SimpleDateFormat formata_Hora       =  new SimpleDateFormat("hh:mm:ss");
    SimpleDateFormat formata_Semana     =  new SimpleDateFormat("EEEE");

    Date date = null;
        try {
            date = formata_Data.parse(datapost);
        } catch (ParseException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    String Cabecalho  =  formata_Cabecalho.format(date.getTime());
    String Data       =  formata_Data.format( date.getTime() );
    String Hora       =  formata_Hora.format( date.getTime() );
    String Semana     =  formata_Semana.format( date.getTime() );                 

    /*
    System.out.println("Data Adicionada");
    System.out.println(Cabecalho);
    System.out.println(Data);
    System.out.println(Hora); 
    System.out.println(StrTran(Semana,"-"," "));
    */
    
return Data+Space(1)+"#"+Space(1)+Semana+Space(1)+"#"+Space(1)+Cabecalho+Space(1)+"#"+Space(1)+Hora;
}
   
   
 /**
  Metodo para, Gerar Digito de Codigo Postal PostNet Apartir do Campo Cep    <p>
  1 Argumentos                                                               <p>
  1º do tipo String                                                          <p>
  Exemplo de Uso = Util.Postnet(Cep);                                        <p>
 */
   public String Postnet(String cep){
    
    int soma=0;
    int tclint=0;
    int tcrint=0;
    int cepnetint=0;

     String numero=cep; 
   
     numero = this.StrTran(numero," -*.",Space(0)); 
     
     String ra;
     String cepnet;
     String dig;
     String cepbarra; 
     String tcl,tcr;
     String dc1 = numero.substring(0,1);
     String dc2 = numero.substring(1,2);
     String dc3 = numero.substring(2,3);
     String dc4 = numero.substring(3,4);
     String dc5 = numero.substring(4,5);
     String dc6 = numero.substring(5,6);
     String dc7 = numero.substring(6,7);
     String dc8 = numero.substring(7,8);

     soma = Integer.parseInt(dc1)+Integer.parseInt(dc2)+Integer.parseInt(dc3)+
             Integer.parseInt(dc4)+Integer.parseInt(dc5)+Integer.parseInt(dc6)+
             Integer.parseInt(dc7)+Integer.parseInt(dc8);

     ra=String.valueOf(soma);
      if (ra.length()==1){
        ra="0"+ra;
      }

      tcl = ra.substring(0,1);
      tcr = ra.substring(1,2);

       if (! tcr.equals("0")){
         tclint = Integer.parseInt(tcl) + 1;
        }else{
          tclint= Integer.parseInt(tcl);
       }

     tcl = String.valueOf(tclint);
     cepnet = tcl+"0";
     cepnetint = Integer.parseInt(cepnet)-soma;
     dig = String.valueOf(cepnetint);
     cepbarra = numero+dig;
  
        if(cepbarra.length() != 9){
         cepbarra="*********";
         }else{
          cepbarra="*"+cepbarra+"*";    
        }
    
     return cepbarra;

   }

 /**
  Metodo para, Conveter Caracter para Codigo de Barras                       <p>
  1 Argumentos                                                               <p>
  1º do tipo String                                                          <p>
  Exemplo de Uso = Util.CodigoDeBarras(Barras);                              <p>
 */
   public String CodigoDeBarras(String sCodigoNumerico){
    //limpa o campo 1º 
   
     sCodigoNumerico = this.StrTran(sCodigoNumerico," */.%#",Space(0));
     //transforma o código de barras em caracteres interpretáveis Por Fontes de Converção
     int i, l, wktam, tamanho, Indice, x, y, numx, numy;
     String codbarra, compxerox, compxerox1, compxerox2, binxer;
     String[] codxerox = new String[10];
            
      codxerox[0] = "10001";
      codxerox[1] = "01001";
      codxerox[2] = "11000";
      codxerox[3] = "00101";
      codxerox[4] = "10100";
      codxerox[5] = "01100";
      codxerox[6] = "00011";
      codxerox[7] = "10010";
      codxerox[8] = "01010";
      codxerox[9] = "00110";

       sCodigoNumerico = sCodigoNumerico.trim();
       tamanho = sCodigoNumerico.length();
       wktam = tamanho % 2;
            
         if (wktam != 0){
           return "Erro Codigo de Barras Menor que 44 Caracter Ou Quantidade Impar";
         }

         codbarra = "<";
         l = 0;
         for (i = 1; i <= tamanho/2; i++){
          l++;
          numx = Integer.parseInt(sCodigoNumerico.substring(l-1, l));

          if (numx == 0){
           numx = 9;
          }
                
          l++;
          numy = Integer.parseInt(sCodigoNumerico.trim().substring(l-1, l));

          if (numy == 0){
           numy = 9;
          }

           compxerox1  =  codxerox[numx];
           compxerox2  =  codxerox[numy];
                
           compxerox   =  compxerox1.substring(0, 1);
           compxerox  +=  compxerox2.substring(0, 1);
           compxerox  +=  compxerox1.substring(1, 2);
           compxerox  +=  compxerox2.substring(1, 2);
           compxerox  +=  compxerox1.substring(2, 3);
           compxerox  +=  compxerox2.substring(2, 3);
           compxerox  +=  compxerox1.substring(3, 4);
           compxerox  +=  compxerox2.substring(3, 4);
           compxerox  +=  compxerox1.substring(4, 5);
           compxerox  +=  compxerox2.substring(4, 5);
                
           y = 0;
           Indice = 0;
             
            for (x = 1; x <= 5; x++){
             Indice += 2;
             binxer = compxerox.substring(y,Indice);

             if (binxer.equals("01")){
                  codbarra+= "N";
             }else if(binxer.equals("11")){
                  codbarra += "W";
             }else if (binxer.equals("00")){
                  codbarra += "n";
             }else if (binxer.equals("10")){
                  codbarra += "w";
             }

             y += 2;

            }//for
         }//for
   codbarra += ">";
   return codbarra;
}


}
