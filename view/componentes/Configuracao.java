/***********************************************************************
* Autor: Cassio Meira Silva
* Matricula: 201610373
* Inicio: 12/03/18
* Ultima alteracao: 14/03/18
* Nome: Configuracao
* Funcao: Criar os ComboBox para o usuario escolher alguma configuracao
***********************************************************************/

package view.componentes;

import model.MeioDeComunicacao;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Slider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class Configuracao extends VBox {

  public Componente enquadramento;//Configuracoes de ENQUADRAMENTO
  public Componente controleErro;//Configuracoes de CONTROLE DE ERRO
  //public Componente controleFluxo;//Configuracoes de CONTROLE DE FLUXO

  /*********************************************
  * Metodo: Configuracao - Construtor
  * Funcao: Constroi objetos da classe Configuracao
  * Parametros: void
  *********************************************/
  public Configuracao(int posX, int posY) {
    this.setLayoutX(posX);//Definindo a posicao X
    this.setLayoutY(posY);//Definindo a posicao Y
    this.setSpacing(10);//Adicionando espacamento entre os Componentes

    this.enquadramento = new Componente("Enquadramento");
    this.enquadramento.setItens("CONTAGEM DE CARACTERES","INSERCAO DE BYTES","INSERCAO DE BITS","VIOLACAO DA CAMADA FISICA");

    HBox boxControleErro = new HBox();
    boxControleErro.setSpacing(5);

    this.controleErro = new Componente("Controle de Erro");
    this.controleErro.setItens("BIT PARIDADE PAR","BIT PARIDADE IMPAR","CRC","CODIGO DE HAMMING");

    VBox boxErro = new VBox();
    Label erroLabel = new Label("Prob de ERRO 0%");
    Slider sliderProbabilidadeErro = new Slider();
    sliderProbabilidadeErro.setBlockIncrement(1);
    sliderProbabilidadeErro.setShowTickLabels(true);
    sliderProbabilidadeErro.setShowTickMarks(true);
    boxErro.getChildren().addAll(erroLabel,sliderProbabilidadeErro);
    boxControleErro.getChildren().addAll(controleErro, boxErro);

    //Funcao do Slider para alterar velocidade da Variavel
    sliderProbabilidadeErro.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
          int valor = newValue.intValue();
          MeioDeComunicacao.PERCENTUAL_ERRO = valor;
          erroLabel.setText("Prob de ERRO " + valor + "%");
        }
    });


    this.getChildren().addAll(enquadramento,boxControleErro);
  }

  /*********************************************
  * Metodo: setDisabilitar
  * Funcao: Habilita ou Desabilita o componente
  * Parametros: disable : Boolean
  * Retorno : void
  *********************************************/
  public void setDisabilitar(Boolean disable) {
    this.enquadramento.setDisable(disable);
    this.controleErro.setDisable(disable);
  }

  /***********************************************************************
  * Nome: Componente
  * Inicio: 12/03/18
  * Ultima alteracao: 26/03/18
  * Funcao: Criar os ComboBox para o usuario escolher alguma configuracao
  ***********************************************************************/
  public class Componente extends VBox {
    private Label titulo;//Titulo da Configuracao
    private ComboBox<String> combo;//ComboBox para escolher um opcao

    /*********************************************
    * Metodo: Configuracao - Construtor
    * Funcao: Constroi objetos da classe Configuracao
    * Parametros: void
    *********************************************/
    public Componente(String descricao) {
      this.titulo = new Label(descricao);
      this.combo = new ComboBox<>();
      this.getChildren().add(titulo);//Adicionando titulo na interface
      this.getChildren().add(combo);//Adicionando combobox na interface
    }

    /*********************************************
    * Metodo: setItens
    * Funcao: Adiciona Itens no ComboBox
    * Parametros: item : String
    * Retorno : void
    *********************************************/
    public void setItens(String... item) {
      this.combo.getItems().addAll(item);
      this.combo.getSelectionModel().select(0);
    }

    /*********************************************
    * Metodo: getIndiceSelecionado
    * Funcao: Retorna o indice do Item selecionado
    * Parametros: void
    * Retorno : index : int
    *********************************************/
    public int getIndiceSelecionado() {
      return combo.getSelectionModel().getSelectedIndex();
    }

  }//Fim class Componente

}//Fim class