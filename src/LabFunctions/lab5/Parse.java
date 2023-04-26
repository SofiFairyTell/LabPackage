package LabFunctions.lab5;

import java.util.ArrayList;
public class Parse
{
    protected ArrayList<City> cityList = new ArrayList<City>();
    protected ParseDOM parseDOM;

    public Parse(ArrayList<City> cities) {
        this.cityList = cities;
    }

    public Parse(ArrayList<City> cityListXML, ParseDOM parseDOM) {
        this.cityList = cityListXML;
        this.parseDOM = parseDOM;
    }

    public void parseXMLtoDB() {
        var sqlOBJ = new ParseSQL();
        for (City city : this.cityList) {
            try {
                sqlOBJ.addNewRecord(city);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void parseDBtoXML() {
        var sqlOBJ = new ParseSQL();
        try {
            var result = sqlOBJ.getAll();
            while (result.next()) {
                this.cityList.add(new City(
                        result.getInt("id"),
                        result.getString("country"),
                        result.getString("name"),
                        result.getString("mayor"),
                        result.getInt("population"),
                        result.getInt("area"),
                        result.getInt("qualityOfLife")
                ));
            }
            this.parseDOM.setDomNodes(this.cityList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
