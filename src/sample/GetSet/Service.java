package sample.GetSet;

public class Service {

    private int idService;
    private String NameService;
    private String PriceService;


    public Service(int idService, String NameService, String PriceService) {
        this.idService = idService;
        this.NameService = NameService;
        this.PriceService = PriceService;
    }

    public Service() {
    }

    public int getIDService1(){
        return idService;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getNameService() {
        return NameService;
    }

    public void setNameService(String NameService) {
        this.NameService = NameService;
    }

    public String getPriceService() {
        return PriceService;
    }

    public void setPriceService(String PriceService) {
        this.PriceService = PriceService;
    }


}