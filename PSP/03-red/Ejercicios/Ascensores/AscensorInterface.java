public interface AscensorInterface {

    abstract void subir(); //synchronized
    abstract void bajar();//synchronized
    abstract String getPlanta();//synchronized
    abstract String toProtocolo();//synchronized
    static AscensorInterface fromProtocolo(String info){
        return null;
    };
    abstract void run();
    abstract void setConfig(int id, String ip, int puerto);
}