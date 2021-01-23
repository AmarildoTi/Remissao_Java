/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.BancoDao;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Amarildo dos Santos de Lima
 */
public class CriaSpool extends Utilidades {
   private ResultSet Eof;
   private int ContDJDE, QtdeArqs, Quebra = 500, sequencia, Vezes, SeqExt = 1;
   private String Spool,Verifica = "Vazia", DJDE ;
   private boolean ImprimeDJDE = false;
 

/**
  Metodo para, Criar Spool de Impressão para Posicionamento de Variaveis     <p>
  1 Argumentos                                                               <p>
  1º do tipo String                                                          <p>
  Exemplo de Uso = Imprime.ImprimeSpool(Spool);                              <p>
  Retorno void                                                               <p>
     * @param Arquivo
     * @throws java.sql.SQLException
 */
  public void ImprimeSpool(String Arquivo) throws SQLException{  

   // Começo Conecta Banco de Dados em Run Time , Em tempo de Execução
    final BancoDao conexao = new BancoDao();                                    //Estância da Classe BancoDao Para Criar o Objeto Conexãoo
    conexao.connect();                                                          //Conexão com o Banco
    //System.out.println("1 = "+conexao.getDatabaseLocation());                   // Verifica Local de Criação do Banco
    //System.out.println("2 = "+conexao.getDatabaseUrl());                        // Verifica Driver
  // Final Conecta Banco de Dados em Run Time , Em tempo de Execução

    
    
//*************************************************************************************
//Começo Conexão com o Banco de Codigos de Busca para poder Criar a Quebra de Arquivos*    
//                          E cabeçalho do arquivo                                    *
//*************************************************************************************

    
    // Começo Seleciona Banco de Dados Codigos de Busca ************************
     conexao.executeSQL_BdNavigator("select * from app.codigo");
    // Final Seleciona Banco de Dados Codigos de Busca  ************************

   // Começo Criação de Variavel Eof ,com Resultado de um ResultSet ******
     Eof = conexao.resultset;                                                    // Eof ou End Of File  Enquando não for final de Arquivo
   // Final Criação de Variavel Eof ,com Resultado de um ResultSet ******

    
    Eof.last();                                                                 //-----\  Percorre Banco Até o Fim
    int count = Eof.getRow();                                                   //----- > count agora tem o numero de linhas do ResultSet
    Eof.beforeFirst();                                                          //-----/  volta o cursor la pra cima pra vc realizar suas tarefas. Como Um "Goto Top" No Banco
    
     QtdeArqs = count/Quebra + 1;                                               //----- > Retorna a Quantidade Total De Arquivos Que Serão Gerados

    // Começo Deletar Aquivos Para Gerar Novos ********************************
    for (int X = 1; X <= QtdeArqs; X++) {                                       //-----\  Faz Até Atingir a Quantidade de Arquivos 
        DeleteFile(Arquivo+"."+Padl(Integer.toString(X),03,"0"));               //----- > Deleta Os Arquivo para Gerar Novos Arquivos
    }                                                                           //-----/  Apenas se Os Arquivos já Existirem na Pasta
    //DeleteFile(Arquivo+".OS");                                                //----- > Deleta Arquivo .Os Para Gera Novo Arquivo
   // Final Deletar Aquivos Para Gerar Novos  ********************************

   
 while (Eof.next()) {                                                           // Eof ou End Of File  Enquando não for final de Arquivo
     sequencia++;
     Spool   =  Arquivo+"."+Padl(Integer.toString(SeqExt),03,"0");
     
      if (sequencia%Quebra == 1) {
       Write(Spool,"%!");
       Write(Spool,"XGF");
       Write(Spool,"3000 SETBUFSIZE");
       Write(Spool,"(PRINCIPAL.JDT) STARTLM");
      }

     if (sequencia%Quebra == 0 || sequencia == count){
       SeqExt++ ;
      }  
 } // Fim do While

    
//*************************************************************************************
//Final Conexão com o Banco de Codigos de Busca para poder Criar a Quebra de Arquivos *    
//                          E cabeçalho do arquivo                                    *
//*************************************************************************************
   
    
//******************************************************************************
//Começo Conexão com o Banco Remissão Com os Arquivos que serão pesquisado     *    
//                             Gerar o Spool                                   *
//******************************************************************************

    
  // Começo Seleciona Banco de Dados *******************************************   
     conexao.executeSQL_BdNavigator("Select R.Linhas, C.CodigoID From APP.Remissao R, "
                                   +"APP.Codigo C Where Substr(linhas,1, 7) = '+$DJDE$' "
                                   +"Or  Substr(linhas,4, 34) = C.CodigoID ");
  // Final Seleciona Banco de Dados *** ****************************************
  
 
  // Começo Criação de Variavel Eof ,com Resultado de um ResultSet ******
    Eof = conexao.resultset;                                                    // Eof ou End Of File  Enquando não for final de Arquivo
  // Final Criação de Variavel Eof ,com Resultado de um ResultSet  ******
    
    while (Eof.next()) {                                                        // Eof ou End Of File  Enquando não for final de Arquivo
      Vezes++; 
 
           if(Eof.getString("Linhas").substring(0, 7).equals("+$DJDE$")){ 
             ContDJDE++;
             ImprimeDJDE = false;
             DJDE = Eof.getString("Linhas");
           }else if(!Verifica.equals(Eof.getString("Linhas").substring(3, 37).trim())){
             ContDJDE = 0;
             Verifica = Eof.getString("Linhas").substring(3, 37).trim();
             ImprimeDJDE = true;
           }else {
             ImprimeDJDE = false;   
           }
           
//***************   Fazer o Spool Aqui começo  *********************************

     if(ImprimeDJDE) {
         Write(Spool,DJDE);
         Write(Spool,Eof.getString("Linhas"));
     }else if (ContDJDE < 2 && Vezes > 1){
         Write(Spool,Eof.getString("Linhas"));
     }    
     
//***************    Fazer o Spool Aqui Fim    *********************************    
   } // Fim do While
     Write(Spool,"%%EOF");
     Eof.close();
     conexao.disconnect();
     System.exit(0);
  
  
//******************************************************************************
//Final Conexão com o Banco Remissão Com os Arquivos que serão pesquisado      *    
//                             Gerar o Spool                                   *
//******************************************************************************
  
  
  }
}
    

