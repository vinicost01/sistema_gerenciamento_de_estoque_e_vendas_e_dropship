package v.sistema_1;

public class finanx {
    private Double receita_extra;
    private Double gasto_extra;
    private Integer idfinanx;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdfinanx() {
        return idfinanx;
    }

    public void setIdfinanx(Integer idfinanx) {
        this.idfinanx = idfinanx;
    }

    public Double getReceita_extra() {
        return receita_extra;
    }

    public void setReceita_extra(Double receita_extra) {
        this.receita_extra = receita_extra;
    }

    public Double getGasto_extra() {
        return gasto_extra;
    }

    public void setGasto_extra(Double gasto_extra) {
        this.gasto_extra = gasto_extra;
    }

    public finanx(Double receita_extra, Double gasto_extra, Integer idfinanx, String nome) {
        this.receita_extra = receita_extra;
        this.gasto_extra = gasto_extra;
        this.idfinanx = idfinanx;
        this.nome=nome;
    }
}
