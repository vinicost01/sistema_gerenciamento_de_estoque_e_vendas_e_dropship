package v.sistema_1;

import java.io.*;

public class dbsg implements java.io.Serializable{
    private String URL;
    private String USER;
    private String PASSWD;
    private transient int SSN;
    private String MSG;

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public String getPASSWD() {
        return PASSWD;
    }

    public void setPASSWD(String PASSWD) {
        this.PASSWD = PASSWD;
    }


    public void armzdbconfig(dbsg dbsg) {
        dbsg.URL=dbsg.getURL();
        dbsg.USER=dbsg.getUSER();
        dbsg.PASSWD=dbsg.getPASSWD();
        try {
            FileOutputStream fileOut = new FileOutputStream("C:/Windows/Temp/dbconfig.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(dbsg);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in C:/Windows/Temp/dbconfig.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public void dbconfig(){
        dbsg dbsg=new dbsg();
        dbsg dbsg1 = null;
        try {
            FileInputStream fileIn = new FileInputStream("C:/Windows/Temp/dbconfig.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            dbsg1 = (dbsg) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("dbconfig class not found");
            c.printStackTrace();
            return;
        }
        dbsg.setURL(dbsg1.URL);
        dbsg.setUSER(dbsg1.USER);
        dbsg.setPASSWD(dbsg1.PASSWD);
        connector.startconec(dbsg);
    }
}
