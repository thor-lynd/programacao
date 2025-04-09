public class Main {
    public static void main(String[] args) {

        Cachorro puppy = new Cachorro("thor", "Golden", 2);
        Mangas mangaum = new Mangas("Bakemonogatari", 3, 10);
        {


            puppy.latir();
            puppy.falarIdade();
            puppy.falarRaca();

            mangaum.falarNome();
            mangaum.falarCapitulo();
            mangaum.falarNota();
        }
    }
}