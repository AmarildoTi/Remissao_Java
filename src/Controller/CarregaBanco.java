/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.BancoDao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Amarildo dos Santos de Lima
 */
public class CarregaBanco extends Utilidades{
    private String dadosRemissao,dadosCodigo ;
    private String tabela;
    private String campos;
    private String dados;
    private int Verifica = 0;

/** 
Metodo Zap Para Limpar a Tabela Remissao                                     <p>
void                                                                         <p>
Exemplo de Uso =  Estrutura.Zap();                                           <P>
 */
  public void ZapRemissao(){    
  // Começo Conecta Banco de Dados em Run Time , Em tempo de Execução
     final BancoDao conexao = new BancoDao();                                   //Estancia da Classe BancoDao Para Criar o Objeto Conexão
     conexao.connect();                                                         //Conexão com o Banco
     //System.out.println("1 = "+conexao.getDatabaseLocation());                  // Verifica Local de Criação do Banco
     //System.out.println("2 = "+conexao.getDatabaseUrl());                       // Verifica Driver
  // Final Conecta Banco de Dados em Run Time , Em tempo de Execução
   
   // Começo Limpa a Tabela **********************************************
     conexao.executeSQL_BdClear("Delete From App.Remissao");                    // Limpa o Banco de Dados Spool 
   // Final  Limpa a Tabela **********************************************
  }

/** 
Metodo Zap Para Limpar a Tabela Codigo                                       <p>
void                                                                         <p>
Exemplo de Uso =  Estrutura.Zap();                                           <P>
 */
  public void ZapCodigo(){    
  // Começo Conecta Banco de Dados em Run Time , Em tempo de Execução
     final BancoDao conexao = new BancoDao();                                   //Estancia da Classe BancoDao Para Criar o Objeto Conexão
     conexao.connect();                                                         //Conexão com o Banco
     //System.out.println("1 = "+conexao.getDatabaseLocation());                  // Verifica Local de Criação do Banco
     //System.out.println("2 = "+conexao.getDatabaseUrl());                       // Verifica Driver
  // Final Conecta Banco de Dados em Run Time , Em tempo de Execução
   
   // Começo Limpa a Tabela **********************************************
     conexao.executeSQL_BdClear("Delete From App.Codigo");                      // Limpa o Banco de Dados Cifs  
   // Final  Limpa a Tabela **********************************************
  }
  
  
/** 
Metodo Append Para Carregar As Tabelas Com os Dados de Remissao              <p>
1 Argumento                                                                  <p>
1º Tipo String                                                               <p>
Exemplo de Uso =  AppendRemissao(Arquivo_Remissao);                          <p>
     * @param Arquivo_Remissao
     * @return 
 */
 public String AppendRemissao(File[] Arquivo_Remissao){    
   // Come�o Conecta Banco de Dados em Run Time , Em tempo de Execução
      final BancoDao conexao = new BancoDao();                                  //Estancia da Classe BancoDao Para Criar o Objeto Conexão
      conexao.connect();                                                        //Conexão com o Banco
      //System.out.println("1 = "+conexao.getDatabaseLocation());                    // Verifica Local de Criação do Banco
      //System.out.println("2 = "+conexao.getDatabaseUrl());                         // Verifica Driver
   // Final Conecta Banco de Dados em Run Time , Em tempo de Execução

  for (int i = 0; i < Arquivo_Remissao.length; i++) {

    try{

     BufferedReader bufReader = new BufferedReader(new FileReader(Arquivo_Remissao[i]));

      while ((dadosRemissao = bufReader.readLine()) != null) {
        dados     =  "";
        tabela    =  "Remissao"+Space(1);
        campos    =  "Linhas";
        dados     =  "'"+dadosRemissao.replaceAll("'","").trim()+"'";
        
        if (dados.length() > 502){
         String[] Resposta = {"Sim", "Não"};    
          Verifica = JOptionPane.showOptionDialog(null, "Linha Maior que a Tupla do Banco Deseja Continuar ?", "Erro Por favor Verificar", 0 ,JOptionPane.QUESTION_MESSAGE, null, Resposta, Resposta[0]);
          if(Verifica == 1 ){
           System.exit(0);
          }   
        }
 
         //System.out.println(dados);
        
         conexao.executeSQL_BdAppend("Insert Into "                             // Comando Sql
         +"App."                                                                // Squema Da Tabela
         +""+ tabela +""                                                        // Tipo de Tabela
         +"("+ campos +")"                                                      // Nomes dos Campos Da tabela
         +" values (" + dados + ")");                                           // Dados a ser Inseridos Nos Campos Da tabela
       
      }
      bufReader.close();
      dadosRemissao = null;
      Arquivo_Remissao[i] = null;
    }
    catch (IOException erro){
      JOptionPane.showMessageDialog(null, " Erro ao Carregar Banco de Dados Remissao "+erro.getMessage()+Space(01), " Mensagem de Erro Verificar", JOptionPane.ERROR_MESSAGE) ;
    }
  } // end for
  
  
  return dadosRemissao;
 }

 /** 
Metodo Append Para Carregar As Tabelas Com os Dados de Codigos de Busca      <p>
1 Argumento                                                                  <p>
1º Tipo String                                                               <p>
Exemplo de Uso =  Append(Arquivo_Codigos);                                   <p>
 */ 
 public String AppendCodigos(File Arquivo_Codigos){    
  // Começo Conecta Banco de Dados em Run Time , Em tempo de Execução
     final BancoDao conexao = new BancoDao();                                   //Estancia da Classe BancoDao Para Criar o Objeto Conexão
     conexao.connect();                                                         //Conexão com o Banco
     //System.out.println("1 = "+conexao.getDatabaseLocation());                    // Verifica Local de Criação do Banco
     //System.out.println("2 = "+conexao.getDatabaseUrl());                         // Verifica Driver
  // Final Conecta Banco de Dados em Run Time , Em tempo de Execução
   
  try{
    FileReader reader = new FileReader(Arquivo_Codigos);
    BufferedReader bufReader = new BufferedReader(reader);

    while ((dadosCodigo = bufReader.readLine()) != null) {
      dados     =  "";
      tabela    =  "Codigo"+Space(1);
      campos    =  "CodigoId";
      dados     =  "'"+dadosCodigo.trim()+"'";      
      
      if (dados.length() != 36 ){
       String[] Resposta = {"Sim", "Não"};    
        Verifica = JOptionPane.showOptionDialog(null, "Codigos Diverente de 34 deseja Continuar ?", "Erro Por favor Verificar", 0 ,JOptionPane.QUESTION_MESSAGE, null, Resposta, Resposta[0]);
         if(Verifica == 1 ){
          System.exit(0);
         }   
      }
 
        //System.out.println(dados);
        
        
         conexao.executeSQL_BdAppend("Insert Into "                             // Comando Sql
         +"App."                                                                // Squema Da Tabela
         +""+ tabela +""                                                        // Tipo de Tabela
         +"("+ campos +")"                                                      // Nomes dos Campos Da tabela
         +" values (" + dados + ")");                                           // Dados a ser Inseridos Nos Campos Da tabela
     
    }

      bufReader.close();
      reader.close();
   }
   catch (IOException erro){
      JOptionPane.showMessageDialog(null, " Erro ao Carregar Banco de Dados Codigos "+erro.getMessage()+Space(01), " Mensagem de Erro Verificar", JOptionPane.ERROR_MESSAGE) ;
   }
  return dadosCodigo; 
  }


 
}
