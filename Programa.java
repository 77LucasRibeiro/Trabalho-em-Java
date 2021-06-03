import java.util.ArrayList;
import java.util.List;

interface IVisitor{
    void visit(Pasta pastinha);
    void visit(Arquivo arquivinho);
    void visitando(Arquivo arquivo);
    void visitando(Pasta pastanvai);
}

interface INome{
    public void insereNome(String nome);
    public void insereInformacoes(String extensao, String tipo, int tamanho);
    public void add(INome adiciona);
    public void accept(IVisitor visitante);
    public void arqptipo(IVisitor visita);
}

abstract class Inform{
    String name;
    String extension;
    String type;
    int size;
}

class Pasta extends Inform implements INome{
    public List<INome> filhos = new ArrayList<INome>();
    public void insereNome(String nome){
        name = nome;
    }
    public void insereInformacoes(String extensao, String tipo, int tamanho){

    }
    public void add(INome adiciona) {
        filhos.add(adiciona);
        }
    public void accept(IVisitor visitante){
        for(INome a : filhos){
            a.accept(visitante);
        }
        visitante.visit(this);
    }
    public void arqptipo(IVisitor visita){
        for(INome arq : filhos){
            arq.arqptipo(visita);
        }
        visita.visitando(this);
    }
}

class Arquivo extends Inform implements INome{
    
    public void insereNome(String nome){
        name = nome;
    }
    public void insereInformacoes(String extensao, String tipo, int tamanho){
        extension = extensao;
        type = tipo;
        size = tamanho;
    }
    public void add(INome adiciona) {
    }
    public void accept(IVisitor visitante){
        visitante.visit(this);
    }
    public void visit(Pasta pastinha){

    }
    public void arqptipo(IVisitor visita){
        visita.visitando(this);
    }
}

abstract class Criador {
    static INome criaEles(String qual){
        if(qual == "pasta"){
            return new Pasta();
        }else if(qual == "arquivo"){
            return new Arquivo();
        }
        return null;
    }
}

class ArquivoPrint implements IVisitor{
    public void visit(Pasta pastinha){
        System.out.println("Pasta: "+ pastinha.name);
    }
    public void visit(Arquivo arquivinho){
        System.out.println("Arquivo: "+ arquivinho.name);
    }
    public void visitando(Pasta pastanvai){
        return;
    }
    public void visitando(Arquivo arquivo){
        if(arquivo.type == "texto")
            System.out.println("Arquivo por Tipo = Texto: "+ arquivo.name);
        else
            return;
    }
}

public class Programa {
    public static void main (String[] args){
        INome pasta1 = Criador.criaEles("pasta");
        INome pasta2 = Criador.criaEles("pasta");
        INome pasta3 = Criador.criaEles("pasta");
        INome pasta4 = Criador.criaEles("pasta");

        INome arquivo1 = Criador.criaEles("arquivo");
        INome arquivo2 = Criador.criaEles("arquivo");
        INome arquivo3 = Criador.criaEles("arquivo");
        INome arquivo4 = Criador.criaEles("arquivo");
        INome arquivo5 = Criador.criaEles("arquivo");

        String nome1 = "Aulas";
        String nome2 = "Trabalhos";
        String nome3 = "Terminados";
        String nome4 = "Fotos";
        String nome5 = "Feito";
        String nome6 = "Feito versao final";
        String nome7 = "Perdido";
        String nome8 = "Rascunho";
        String nome9 = "Fechamento";
        String tipo1 = "texto";
        String tipo2 = "pdf";
        int tam1 = 5;
        int tam2 = 10;
        String ext1 = "extendido";
        String ext2 = "rar";

        pasta1.insereNome(nome1);
        pasta2.insereNome(nome2);
        pasta3.insereNome(nome3);
        pasta4.insereNome(nome4);
        arquivo1.insereNome(nome5);
        arquivo2.insereNome(nome6);
        arquivo3.insereNome(nome7);
        arquivo4.insereNome(nome8);
        arquivo5.insereNome(nome9);
        arquivo1.insereInformacoes(ext1, tipo1, tam1);
        arquivo2.insereInformacoes(ext2, tipo2, tam2);
        arquivo3.insereInformacoes(ext1, tipo1, tam2);
        arquivo4.insereInformacoes(ext2, tipo1, tam1);
        arquivo5.insereInformacoes(ext2, tipo1, tam1);

        pasta1.add(pasta2);
        pasta2.add(pasta3);
        pasta1.add(pasta4);
        pasta1.add(arquivo4);
        pasta1.add(arquivo5);
        pasta1.accept(new ArquivoPrint());
        pasta2.add(arquivo1);
        pasta2.add(arquivo2);
        pasta3.add(arquivo3);

        pasta2.arqptipo(new ArquivoPrint());
    }
}
