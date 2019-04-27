package edu.mx.ipn.lineezefaqs;

class Faq {

    private int id;
    private String pregunta;
    private String respuesta;

    public Faq(){

    }

    public Faq(int id, String pregunta, String respuesta) {
        this.id = id;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "Faq{" +
                "id=" + id +
                ", pregunta='" + pregunta + '\'' +
                ", respuesta='" + respuesta + '\'' +
                '}';
    }
}
