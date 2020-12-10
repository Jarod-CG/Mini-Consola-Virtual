package src.Modelo;

public enum JSON {
    PIXELES ("pixeles")
    ,PIXEL("pixel")
    ,POSICION("posicion")
    ,COLOR("color")
    ,BOTONES("botones")
    ,BOTON("boton")
    ,ARRIBA("arriba")
    ,ABAJO("abajo")
    ,DERECHA("derecha")
    ,IZQUIERDA("izquierda")
    ,ACCION("accion")
    ;
    
    String str;

    private JSON(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }


}
