package servicios;

public class Seguridad {
    public boolean Autorizacion(String user,String password){
        if(user.equals("fbolano") && password.equals("pds")){
            System.out.println("Usuario " + user + " autorizado");
            return true;
        }else{
            System.out.println("Usuario " + user + " no autorizado");
            return false;
        }
    }
}
