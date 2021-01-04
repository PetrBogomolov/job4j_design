package optional.aviabilets;

public class SearchDublicateFlight {
    public boolean searchDublicate(String flight1, String flight2) {
        boolean result = false;
        if (flight1.contains(" ") || flight1.contains("0")) {
            flight1 = flight1.replace(" ", "").replaceAll("0", "");
        }
        if (flight2.contains(" ") || flight2.contains("0")) {
            flight2 = flight2.replace(" ", "").replaceAll("0", "");
        }
        if (flight1.equals(flight2)) {
            result = true;
        }
        return result;
    }
}
