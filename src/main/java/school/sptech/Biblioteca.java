package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.LivroNaoEncontradoException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Biblioteca {
    private String nome;
    private List<Livro> livros;

    public Biblioteca() {
        this.livros = new ArrayList<>();
    }

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        if (livro == null || livro.getAutor() == null || livro.getDataPublicacao() == null || livro.getTitulo() == null
        || livro.getAutor().isBlank() || livro.getTitulo().isBlank()) {
            throw new ArgumentoInvalidoException();
        }

        livros.add(livro);
    }

    public void removerLivroPorTitulo(String titulo) {

        livros.remove(buscarLivroPorTitulo(titulo));
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new ArgumentoInvalidoException();
        }

        Livro busca = livros.stream().
                filter(livroAtual -> livroAtual.getTitulo().equalsIgnoreCase(titulo)).
                findFirst().
                orElseThrow(
                        LivroNaoEncontradoException::new
                );
        return busca;
    }

    public Integer contarLivros() {
        return livros.size();
    }

    public List<Livro> obterLivrosAteAno(Integer ano) {
        List<Livro> busca = livros.stream().
                filter(livro -> livro.getDataPublicacao().getYear() <= ano).
                toList();

        return busca;
    }

    public List<Livro> retornarTopCincoLivros() {
        List<Livro> busca = livros.stream().
                sorted(Comparator.comparingDouble(Livro::calcularMediaAvaliacoes).reversed()).
                limit(5).
                toList();

        return busca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
