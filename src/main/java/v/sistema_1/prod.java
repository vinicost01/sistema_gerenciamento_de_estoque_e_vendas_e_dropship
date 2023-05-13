package v.sistema_1;

public class prod {
    private Integer id;
    private String nome;
    private String url;
    private Double val_c;
    private Double val_v;
    private Integer uni_vend;
    private String msg;

    public Integer getUni_vend() {
        return uni_vend;
    }

    public void setUni_vend(Integer uni_vend) {
        this.uni_vend = uni_vend;
    }

    public prod(Integer id, String nome, String url, Double val_c, Double val_v, Integer uni_vend) {
        this.id = id;
        this.nome = nome;
        this.url = url;
        this.val_c = val_c;
        this.val_v = val_v;
        this.uni_vend=uni_vend;
        this.msg = msg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getVal_c() {
        return val_c;
    }

    public void setVal_c(Double val_c) {
        this.val_c = val_c;
    }

    public Double getVal_v() {
        return val_v;
    }

    public void setVal_v(Double val_v) {
        this.val_v = val_v;
    }



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}




