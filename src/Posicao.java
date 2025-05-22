public class Posicao {
    private int linha;   // Linha da posição no tabuleiro
    private int coluna;  // Coluna da posição no tabuleiro

    // Construtor para inicializar a posição
    public Posicao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    // Getters para acessar a linha e a coluna
    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    // Setters para alterar a linha e a coluna
    public void setLinha(int linha) {
        this.linha = linha;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    // Método para verificar se duas posições são iguais
    public boolean equals(Posicao outraPosicao) {
        return this.linha == outraPosicao.linha && this.coluna == outraPosicao.coluna;
    }

    // Método para representar a posição como uma string (útil para debug)
    @Override
    public String toString() {
        return "Posição [Linha=" + linha + ", Coluna=" + coluna + "]";
    }
}
