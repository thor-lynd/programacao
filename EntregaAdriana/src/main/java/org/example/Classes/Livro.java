package org.example.Classes;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private int paginas;

    public Livro(int id, String titulo, String autor, int paginas) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getPaginas() {
        return paginas;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    // Métodos
    public void abrir() {
        System.out.println("Você abriu o livro '" + titulo + "' do autor " + autor + ".");
    }

    public void fechar() {
        System.out.println("Você fechou o livro '" + titulo + "'.");
    }

    public void lerPagina(int pagina) {
        if(pagina > 0 && pagina <= paginas) {
            System.out.println("Lendo a página " + pagina + " do livro '" + titulo + "'.");
        } else {
            System.out.println("Número de página inválido.");
        }
    }
}
